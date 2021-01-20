package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.model.Categoria;
import com.vinicius.neppo.model.Secao;
import com.vinicius.neppo.service.CategoriaService;
import com.vinicius.neppo.service.SecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sun.swing.SwingUtilities2;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/categorias")
public class CategoriaController
{
    @Autowired
    CategoriaService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Iterable<Categoria> exibirCategorias(
            @RequestParam(name = "pagina") int numeroPagina,
            @RequestParam(name = "tamanho") int tamanhoPagina
    )
    {
        return service.getCategorias(numeroPagina, tamanhoPagina);
    }

    @GetMapping("/publicados")
    public Iterable<Categoria> exibirCategoriasPublicados(
            @RequestParam(name = "pagina") int numeroPagina,
            @RequestParam(name = "tamanho") int tamanhoPagina
    )
    {
        return service.getCategorias(numeroPagina, tamanhoPagina);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Optional<Categoria> exibirCategoria(@PathVariable(name = "id") Long id)
    {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/secao/{id_secao}")
    public Optional<Secao> exibirSecao(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "id_secao") Long idSecao
    )
    {
        Categoria categoria = service.getById(id).get();
        return categoria.getSecoes().stream().filter(secao -> secao.getId() == idSecao).findFirst();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Optional<Categoria> salvarCategoria(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "descricao", required = false) String descricao
    )
    {
        Categoria categoria = new Categoria();
        categoria.setNome(nome);
        if (descricao != null) categoria.setDescricao(descricao);

        String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        categoria.setLink("Empty");

        categoria = service.salvarCategoria(categoria).get();

        categoria.setLink(baseURL + "/categorias/" + categoria.getId());

        return service.salvarCategoria(categoria);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Optional<Categoria> editarCategoria(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "descricao", required = false) String descricao
    )
    {
        Categoria categoria = service.getById(id).get();
        if (nome != null) categoria.setNome(nome);
        if (descricao != null) categoria.setDescricao(descricao);

        String baseURL = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        categoria.setLink(baseURL + "/categorias/" + categoria.getId());

        return service.salvarCategoria(categoria);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deletarCategoria(
            @PathVariable(name = "id") Long id
    )
    {
        service.deletarCategoria(id);
        return "Sucess";
    }
}
