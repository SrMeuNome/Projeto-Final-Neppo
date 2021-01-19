package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Secao;
import com.vinicius.neppo.repository.SecaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SecaoService
{
    @Autowired
    SecaoRepository secaoRepository;

    @Transactional(readOnly = true)
    public Optional<Secao> getById(Long id)
    {
        return secaoRepository.findById(id);
    }
}
