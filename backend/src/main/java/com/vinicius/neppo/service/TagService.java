package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Tag;
import com.vinicius.neppo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class TagService
{
    @Autowired
    TagRepository tagRepository;

    @Transactional(readOnly = true)
    public Iterable<Tag> getTagsById(Long [] listIds)
    {
        Collection<Long> tagsId = new ArrayList<Long>(Arrays.asList(listIds));
        Iterable<Tag> tags = tagRepository.findAllByIdIn(tagsId);

        return tags;
    }
}
