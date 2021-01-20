package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.Tag;
import com.vinicius.neppo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/tags")
public class TagController
{
    @Autowired
    TagService service;

    @GetMapping
    public Iterable<Tag> exibirTags(
            @RequestParam(name = "pagina") int numeroPagina,
            @RequestParam(name = "tamanho") int tamanhoPagina
    )
    {
        return service.getTags(numeroPagina, tamanhoPagina);
    }

    @GetMapping("/{id}")
    public Optional<Tag> exibirTag(@PathVariable(name = "id") Long id)
    {
        return service.getById(id);
    }

    @PostMapping
    public Optional<Tag> salvarTag(@RequestParam(value = "nome", required = true) String nome)
    {
        Tag tag = new Tag();
        String nomeTratado = nome.replaceAll("[^A-Za-z0-9]*", "");
        tag.setNome(nomeTratado);

        return service.salvarTag(tag);
    }

    @PutMapping("/{id}")
    public Optional<Tag> editarTag(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "nome", required = false) String nome
    )
    {
        Tag tag = service.getById(id).get();
        if (nome != null)
        {
            String nomeTratado = nome.replaceAll("[^A-Za-z0-9]*", "");
            tag.setNome(nomeTratado);
        }

        return service.salvarTag(tag);
    }

    @DeleteMapping("/{id}")
    public String deletarTag(
            @PathVariable(name = "id") Long id
    )
    {
        service.deletarTag(id);
        return "Sucess";
    }
}
