package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.model.Tag;
import com.vinicius.neppo.repository.ArtigoRepository;
import com.vinicius.neppo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ArtigoService
{
    @Autowired
    ArtigoRepository artigoRepository;

    @Autowired
    TagRepository tagRepository;

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

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCase(texto, texto, pageable);
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosByTags(int numeroPagina, int tamanhoPagina, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Collection<Tag> tags = new ArrayList<Tag>();

        for (int i = 0; i < listTags.length; i++)
        {
            Tag tagAux = tagRepository.findById(listTags[i]).get();
            if(tagAux != null) tags.add(tagAux);
        }

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return artigoRepository.findAllByTagsIn(tags, pageable);
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosByTextAndTags(int numeroPagina, int tamanhoPagina, String texto, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        if(texto != null) texto = "%" + texto + "%";

        Collection<Tag> tags = new ArrayList<Tag>();
        //Collection<Long> tags = new ArrayList<Long>();

        for (int i = 0; i < listTags.length; i++)
        {
            Tag tagAux = tagRepository.findById(listTags[i]).get();
            if(tagAux != null && tagAux.getArtigos() != null) tags.add(tagAux);
            System.out.println(tagAux.toString());
            //tags.add(listTags[i]);
        }

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

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository
                .findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCaseAndRascunhoFalse(texto, texto, pageable);
        return artigos;
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicadosByTags(int numeroPagina, int tamanhoPagina, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Collection<Tag> tags = new ArrayList<Tag>();

        for (int i = 0; i < listTags.length; i++)
        {
            Tag tagAux = tagRepository.findById(listTags[i]).get();
            if(tagAux != null) tags.add(tagAux);
        }

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository
                .findAllByRascunhoFalseAndTagsIn(tags, pageable);
        return artigos;
    }


    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicadosByTextAndTags(int numeroPagina, int tamanhoPagina, String texto, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Collection<Tag> tags = new ArrayList<Tag>();

        for (int i = 0; i < listTags.length; i++)
        {
            Tag tagAux = tagRepository.findById(listTags[i]).get();
            if(tagAux != null) tags.add(tagAux);
        }

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository
                .findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCaseAndRascunhoFalseAndTagsIn(texto, texto, tags, pageable);
        return artigos;
    }

    @Transactional(readOnly = true)
    public Iterable<Artigo> getArtigosPublicadosByTextAndTagsTeste(int numeroPagina, int tamanhoPagina, String texto, Long[] listTags)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;

        Collection<Tag> tags = new ArrayList<Tag>();

        for (int i = 0; i < listTags.length; i++)
        {
            Tag tagAux = tagRepository.findById(listTags[i]).get();
            if(tagAux != null) tags.add(tagAux);
        }

        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Iterable<Artigo> artigos = artigoRepository
                .findAllByTituloContainingIgnoreCaseOrConteudoContainingIgnoreCaseAndRascunhoFalseAndTagsIn(texto, texto, tags, pageable);
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
