package com.vinicius.neppo.service.impl;

import com.vinicius.neppo.model.Perfil;
import com.vinicius.neppo.repository.PerfilRepository;
import com.vinicius.neppo.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PerfilServiceImp implements PerfilService
{
    @Autowired
    PerfilRepository perfilRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Perfil> getById(Long id)
    {
        return perfilRepository.findById(id);
    }
}
