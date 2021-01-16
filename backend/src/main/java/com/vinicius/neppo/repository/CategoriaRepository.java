package com.vinicius.neppo.repository;

import com.vinicius.neppo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{
}
