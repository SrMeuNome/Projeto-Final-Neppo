package com.vinicius.neppo.service.impl;

import com.vinicius.neppo.model.Tag;
import com.vinicius.neppo.repository.TagRepository;
import com.vinicius.neppo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TagServiceImp implements TagService
{
    @Autowired
    TagRepository tagRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Tag> getTagsById(Long [] listIds)
    {
        Collection<Long> tagsId = new ArrayList<Long>(Arrays.asList(listIds));
        Iterable<Tag> tags = tagRepository.findAllByIdIn(tagsId);

        return tags;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tag> getById(Long id)
    {
        return tagRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Tag> getTags(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return tagRepository.findAll(pageable);
    }

    @Override
    public Optional<Tag> salvarTag(Tag tag)
    {
        return Optional.of(tagRepository.save(tag));
    }

    @Override
    public void deletarTag(Long id)
    {
        tagRepository.deleteById(id);
    }
}
