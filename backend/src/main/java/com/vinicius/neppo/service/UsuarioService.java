package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UsuarioService
{
    public Optional<Usuario> buscarUsuarioPorEmail(String email);

    public Optional<Usuario> getById(Long id);

    public Page<Usuario> getUsuarios(int numeroPagina, int tamanhoPagina);

    public Optional<Usuario> salvarUsuario(Usuario usuario);

    public void deletarUsuario(Long id);
}
