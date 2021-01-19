package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.model.Tag;
import com.vinicius.neppo.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ArtigoService
{
    @Autowired
    ArtigoRepository artigoRepository;

    @Autowired
    TagService serviceTag;

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigos(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosByText(int numeroPagina, int tamanhoPagina, String texto)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        if(texto != null) texto = "%" + texto + "%";

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAllArtigosByText(texto, pageable);
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosByTags(int numeroPagina, int tamanhoPagina, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Collection<Tag> tags = new ArrayList<Tag>();
        Iterable<Tag> tagsInterable = serviceTag.getTagsById(listTags);
        tagsInterable.forEach(tags::add);

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAllArtigosByTag(tags, pageable);
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosByTextAndTags(int numeroPagina, int tamanhoPagina, String texto, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        if(texto != null) texto = "%" + texto + "%";

        Collection<Tag> tags = new ArrayList<Tag>();
        Iterable<Tag> tagsInterable = serviceTag.getTagsById(listTags);
        tagsInterable.forEach(tags::add);

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAllArtigosByParameters(texto, tags, pageable);
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicados(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository.findAllByRascunhoFalse(pageable);
        return artigos;
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicadosByText(int numeroPagina, int tamanhoPagina, String texto)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        if(texto != null) texto = "%" + texto + "%";

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository.findAllArtigosPublicadosByText(texto, pageable);
        return artigos;
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicadosByTags(int numeroPagina, int tamanhoPagina, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Collection<Tag> tags = new ArrayList<Tag>();
        Iterable<Tag> tagsInterable = serviceTag.getTagsById(listTags);
        tagsInterable.forEach(tags::add);

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository.findAllArtigosPublicadosByTag(tags, pageable);
        return artigos;
    }


    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicadosByTextAndTags(int numeroPagina, int tamanhoPagina, String texto, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        if(texto != null) texto = "%" + texto + "%";

        Collection<Tag> tags = new ArrayList<Tag>();
        Iterable<Tag> tagsInterable = serviceTag.getTagsById(listTags);
        tagsInterable.forEach(tags::add);

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository.findAllArtigosPublicadosByParameters(texto, tags, pageable);
        return artigos;
    }

    @Transactional(readOnly = true)
    public Optional<Artigo> getArtigoById(Long id)
    {
        return artigoRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public Optional<Artigo> salveArtigo(Artigo artigo)
    {
        artigoRepository.save(artigo);
        return Optional.of(artigo);
    }
}
