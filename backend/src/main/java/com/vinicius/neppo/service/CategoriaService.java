package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Artigo;
import com.vinicius.neppo.model.Categoria;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategoriaService
{
    public Optional<Categoria> getById(Long id);

    public Page<Categoria> getCategorias(int numeroPagina, int tamanhoPagina);


    public Optional<Categoria> salvarCategoria(Categoria categoria);

    public void deletarCategoria(Long id);
}
