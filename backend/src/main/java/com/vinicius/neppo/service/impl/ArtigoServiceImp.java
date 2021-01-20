package com.vinicius.neppo.service.impl;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.model.Tag;
import com.vinicius.neppo.repository.ArtigoRepository;
import com.vinicius.neppo.service.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ArtigoServiceImp implements ArtigoService
{
    @Autowired
    ArtigoRepository artigoRepository;

    @Autowired
    TagServiceImp serviceTag;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigos(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosByText(int numeroPagina, int tamanhoPagina, String texto)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        if(texto != null) texto = "%" + texto + "%";

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAllArtigosByText(texto, pageable);
    }

    @Override
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

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicados(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository.findAllByRascunhoFalse(pageable);
        return artigos;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public Optional<Artigo> getArtigoById(Long id)
    {
        return artigoRepository.findById(id);
    }

    @Override
    public Optional<Artigo> salveArtigo(Artigo artigo)
    {
        return Optional.of(artigoRepository.save(artigo));
    }

    @Override
    public void deletarArtigo(Long id)
    {
        artigoRepository.deleteById(id);
    }
}
