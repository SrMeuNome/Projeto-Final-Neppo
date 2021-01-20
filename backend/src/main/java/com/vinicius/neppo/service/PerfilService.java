package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Perfil;

import java.util.Optional;

public interface PerfilService
{
    public Optional<Perfil> getById(Long id);
}
