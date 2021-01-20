package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Categoria;
import com.vinicius.neppo.model.Secao;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SecaoService
{
    public Optional<Secao> getById(Long id);

    public Page<Secao> getSecoes(int numeroPagina, int tamanhoPagina);

    public Optional<Secao> salvarSecao(Secao secao);

    public void deletarSecao(Long id);
}
