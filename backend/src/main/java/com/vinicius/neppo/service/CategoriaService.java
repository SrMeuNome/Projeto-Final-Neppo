package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Categoria;
import com.vinicius.neppo.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoriaService
{
    @Autowired
    CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Optional<Categoria> getById(Long id)
    {
        return categoriaRepository.findById(id);
    }
}
