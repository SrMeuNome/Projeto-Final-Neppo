package com.vinicius.neppo.service.impl;

import com.vinicius.neppo.model.Secao;
import com.vinicius.neppo.repository.SecaoRepository;
import com.vinicius.neppo.service.SecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecaoServiceImp implements SecaoService
{
    @Autowired
    SecaoRepository secaoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Secao> getById(Long id)
    {
        return secaoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Secao> getSecoes(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return secaoRepository.findAll(pageable);
    }

    @Override
    public Optional<Secao> salvarSecao(Secao secao)
    {
        return Optional.of(secaoRepository.save(secao));
    }

    @Override
    public void deletarSecao(Long id)
    {
        secaoRepository.deleteById(id);
    }
}
