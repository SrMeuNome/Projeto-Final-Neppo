package com.vinicius.neppo.repository;

import com.vinicius.neppo.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>
{
    List<Tag> findAllByIdIn(Collection<Long> ids);
    Page<Tag> findAllByIdIn(Collection<Long> ids, Pageable pageable);
}
