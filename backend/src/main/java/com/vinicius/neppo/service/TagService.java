package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Tag;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface TagService
{
    public Iterable<Tag> getTagsById(Long [] listIds);

    public Optional<Tag> getById(Long id);

    public Page<Tag> getTags(int numeroPagina, int tamanhoPagina);

    public Optional<Tag> salvarTag(Tag tag);

    public void deletarTag(Long id);
}
