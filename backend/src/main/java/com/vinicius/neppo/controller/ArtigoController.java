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
            //return service.getArtigosPublicadosByTextAndTags(numeroPagina, tamanhoPagina, texto, tags);
            return service.getArtigosPublicadosByTextAndTagsTeste(numeroPagina, tamanhoPagina, texto, tags);
        }
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
