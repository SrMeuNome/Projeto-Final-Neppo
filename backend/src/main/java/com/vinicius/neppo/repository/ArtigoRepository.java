package com.vinicius.neppo.repository;

import com.vinicius.neppo.model.Artigo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtigoRepository extends JpaRepository<Artigo, Long>
{
    List<Artigo> findAllByRascunhoFalse(Pageable pageable);
}
