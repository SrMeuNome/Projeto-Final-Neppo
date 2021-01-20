package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.Categoria;
import com.vinicius.neppo.model.Secao;
import com.vinicius.neppo.service.CategoriaService;
import com.vinicius.neppo.service.SecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/secoes")
public class SecaoController
{
    @Autowired
    SecaoService service;

    @Autowired
    CategoriaService categoriaService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Iterable<Secao> exibirSecoes(
            @RequestParam(name = "pagina") int numeroPagina,
            @RequestParam(name = "tamanho") int tamanhoPagina
    )
    {
        return service.getSecoes(numeroPagina, tamanhoPagina);
    }


    @GetMapping("/{id}")
    public Optional<Secao> exibirSecao(@PathVariable(name = "id") Long id)
    {
        return service.getById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Optional<Secao> salvarSecao(
            @RequestParam(value = "nome", required = true) String nome,
            @RequestParam(value = "categoria", required = true) Long categoriaId
    )
    {
        Secao secao = new Secao();
        secao.setNome(nome);

        Categoria categoria = categoriaService.getById(categoriaId).get();

        secao.setCategoria(categoria);

        return service.salvarSecao(secao);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Optional<Secao> editarSecao(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "categoria", required = false) Long categoriaId
    )
    {
        Secao secao = service.getById(id).get();
        if (nome != null) secao.setNome(nome);

        if(categoriaId != null)
        {
            Categoria categoria = categoriaService.getById(categoriaId).get();
            secao.setCategoria(categoria);
        }

        return service.salvarSecao(secao);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deletarSecao(
            @PathVariable(name = "id") Long id
    )
    {
        service.deletarSecao(id);
        return "Sucess";
    }
}
