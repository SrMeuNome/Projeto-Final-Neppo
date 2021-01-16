package com.vinicius.neppo.repository;

import com.vinicius.neppo.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>
{
}
