package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping("/artigos")
public class ArtigoController
{
    @Autowired
    ArtigoService service;

    @GetMapping("/{numeroPagina}/{tamanhoPagina}")
    public Iterable<Artigo> exibirArtigos(@PathVariable("numeroPagina") int numeroPagina, @PathVariable("tamanhoPagina") int tamanhoPagina)
    {
        return service.getArtigos(numeroPagina, tamanhoPagina);
    }

    @GetMapping("/artigos-publicados/{numeroPagina}-{tamanhoPagina}")
    public Iterable<Artigo> exibirArtigosPublicados(@PathVariable("numeroPagina") int numeroPagina, @PathVariable("tamanhoPagina") int tamanhoPagina)
    {
        return service.getArtigosPublicados(numeroPagina, tamanhoPagina);
    }

    @GetMapping("/{id}")
    public Optional<Artigo> exibirArtigo(@PathVariable("id") Long id)
    {
        return service.getArtigoById(id);
    }

    /*@GetMapping("/{tags}")
    public Artigo exibirArtigo(@PathVariable("tags") List<Long> tags)
    {
        return service.getArtigoByTags(tags);
    }*/
}
