package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.repository.ArtigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class ArtigoService
{
    @Autowired
    ArtigoRepository artigoRepository;

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigos(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAll(pageable);
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

    /*@Transactional(readOnly = true)
    public Stream<Artigo> getArtigosPublicados(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository.findAll(pageable);
        Stream<Artigo> artigosStream = StreamSupport.stream(artigos.spliterator(), true);
        return artigosStream
                .filter(artigo -> artigo.isIs_rascunho() == false);
    }*/

    @Transactional(readOnly = true)
    public Optional<Artigo> getArtigoById(Long id)
    {
        return artigoRepository.findById(id);
    }

    /*@Transactional(readOnly = true)
    public Artigo getArtigoByTags(Set<Long> tags)
    {
        return artigoRepository.findAll().set;
    }*/
}
