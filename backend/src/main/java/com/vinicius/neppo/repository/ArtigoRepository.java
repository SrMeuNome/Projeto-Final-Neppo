package com.vinicius.neppo.repository;

import com.vinicius.neppo.model.Artigo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtigoRepository extends JpaRepository<Artigo, Long>
{
}
