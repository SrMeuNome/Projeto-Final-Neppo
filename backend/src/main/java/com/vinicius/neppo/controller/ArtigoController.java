package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.*;
import com.vinicius.neppo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/publicados")
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

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public Optional<Artigo> exibirArtigo(@PathVariable("id") Long id)
    {
        return service.getArtigoById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
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
        artigo.setQtdLike(0);
        artigo.setQtdNotLike(0);

        Usuario usuario = serviceUsuario.buscarUsuarioPorEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();

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

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PutMapping(path = "/{id}")
    public Optional<Artigo> editarArtigo(
            @PathVariable("id") Long id,
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "conteudo", required = false) String conteudo,
            @RequestParam(name = "descricao", required = false) String descricao,
            @RequestParam(name = "tags", required = false) Long[] tag,
            @RequestParam(name = "secao", required = false) Long secaoId,
            @RequestParam(name = "categoria", required = false) Long categoriaId,
            @RequestParam(name = "rascunho", required = false) Boolean rascunho
    )
    {
        Artigo artigo = service.getArtigoById(id).get();
        if (titulo != null) artigo.setTitulo(titulo);
        if (conteudo != null) artigo.setConteudo(conteudo);
        if (descricao != null) artigo.setDescricao(descricao);
        if (rascunho != null) artigo.setRascunho(rascunho);

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

        return service.salveArtigo(artigo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/{id}")
    public Optional<Artigo> publicarArtigo(@PathVariable("id") Long id)
    {
        Artigo artigo = service.getArtigoById(id).get();
        artigo.setRascunho(false);
        return service.salveArtigo(artigo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public String deletandoArtigo(@PathVariable("id") Long id)
    {
        service.deletarArtigo(id);
        return "Sucess";
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping(path = "/{id}/like")
    public Optional<Artigo> likeArtigo(@PathVariable("id") Long id)
    {
        Artigo artigo = service.getArtigoById(id).get();
        artigo.addLike();
        return service.salveArtigo(artigo);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PatchMapping(path = "/{id}/notlike")
    public Optional<Artigo> notLikeArtigo(@PathVariable("id") Long id)
    {
        Artigo artigo = service.getArtigoById(id).get();
        artigo.addNotLike();
        return service.salveArtigo(artigo);
    }
}
