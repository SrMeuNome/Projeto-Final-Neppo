package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.*;
import com.vinicius.neppo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/artigos")
public class ArtigoController
{
    @Autowired
    ArtigoService service;

    @Autowired
    TagService serviceTag;

    @Autowired
    SecaoService serviceSecao;

    @Autowired
    CategoriaService serviceCategoria;

    @Autowired
    UsuarioService serviceUsuario;

    @GetMapping
    public Iterable<Artigo> exibirArtigos(@RequestParam(name = "pagina") int numeroPagina,
                                          @RequestParam(name = "tamanho") int tamanhoPagina,
                                          @RequestParam(name = "texto", defaultValue = "", required = false) String texto,
                                          @RequestParam(name = "tags", required = false) Long[] tags)
    {
        if (texto.isEmpty() && tags == null)
        {
            return service.getArtigos(numeroPagina, tamanhoPagina);
        }
        else if (!texto.isEmpty() && tags == null)
        {
            return service.getArtigosByText(numeroPagina, tamanhoPagina, texto);
        }
        else if (texto.isEmpty() && tags != null)
        {
            return service.getArtigosByTags(numeroPagina, tamanhoPagina, tags);
        }
        else
        {
            return service.getArtigosByTextAndTags(numeroPagina, tamanhoPagina, texto, tags);
        }

    }

    @GetMapping("/artigos-publicados")
    public Iterable<Artigo> exibirArtigosPublicados(
            @RequestParam(name = "pagina") int numeroPagina,
            @RequestParam(name = "tamanho") int tamanhoPagina,
            @RequestParam(name = "texto",defaultValue = "", required = false) String texto,
            @RequestParam(name = "tags", required = false) Long[] tags)
    {
        if (texto.isEmpty() && tags == null)
        {
            return service.getArtigosPublicados(numeroPagina, tamanhoPagina);
        }
        else if (!texto.isEmpty() && tags == null)
        {
            return service.getArtigosPublicadosByText(numeroPagina, tamanhoPagina, texto);
        }
        else if (texto.isEmpty() && tags != null)
        {
            return service.getArtigosPublicadosByTags(numeroPagina, tamanhoPagina, tags);
        }
        else
        {
            return service.getArtigosPublicadosByTextAndTags(numeroPagina, tamanhoPagina, texto, tags);
        }
    }

    @GetMapping("/{id}")
    public Optional<Artigo> exibirArtigo(@PathVariable("id") Long id)
    {
        return service.getArtigoById(id);
    }

    @PostMapping
    public Optional<Artigo> salvarArtigo(
            @RequestParam(name = "titulo", required = true) String titulo,
            @RequestParam(name = "conteudo", required = true) String conteudo,
            @RequestParam(name = "descricao", required = false) String descricao,
            @RequestParam(name = "tags", required = false) Long[] tag,
            @RequestParam(name = "secao", required = false) Long secaoId,
            @RequestParam(name = "categoria", required = false) Long categoriaId
    )
    {
        Artigo artigo = new Artigo();
        artigo.setTitulo(titulo);
        artigo.setConteudo(conteudo);
        artigo.setDescricao(descricao);
        artigo.setRascunho(true);

        Usuario usuario = serviceUsuario.buscarUsuarioPorEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        if(tag != null)
        {
            Iterable<Tag> tagsIterable = serviceTag.getTagsById(tag);
            Collection<Tag> tags = new ArrayList<Tag>();
            tagsIterable.forEach(tags::add);
            artigo.setTags(tags);
        }

        if (secaoId != null)
        {
            Optional<Secao> secao = serviceSecao.getById(secaoId);
            artigo.setSecao(secao.get());
            artigo.setArtCategoria(false);
        } else if (categoriaId != null)
        {
            Optional<Categoria> categoria = serviceCategoria.getById(categoriaId);
            artigo.setCategoria(categoria.get());
            artigo.setArtCategoria(true);
        }

        artigo.setAutor(usuario);

        return service.salveArtigo(artigo);
    }
}
