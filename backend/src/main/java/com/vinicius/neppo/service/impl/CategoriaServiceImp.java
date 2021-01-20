package com.vinicius.neppo.service.impl;

import com.vinicius.neppo.model.Categoria;
import com.vinicius.neppo.repository.CategoriaRepository;
import com.vinicius.neppo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CategoriaServiceImp implements CategoriaService
{
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Categoria> getById(Long id)
    {
        return categoriaRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Categoria> getCategorias(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return categoriaRepository.findAll(pageable);
    }

    @Override
    public Optional<Categoria> salvarCategoria(Categoria categoria)
    {
        return Optional.of(categoriaRepository.save(categoria));
    }

    @Override
    public void deletarCategoria(Long id)
    {
        categoriaRepository.deleteById(id);
    }
}
