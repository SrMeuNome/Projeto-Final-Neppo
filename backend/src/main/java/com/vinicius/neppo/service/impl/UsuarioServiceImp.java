package com.vinicius.neppo.service.impl;

import com.vinicius.neppo.model.Tag;
import com.vinicius.neppo.model.Usuario;
import com.vinicius.neppo.repository.UsuarioRepository;
import com.vinicius.neppo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService, UserDetailsService
{
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorEmail(String email)
    {
        return usuarioRepository.findByEmail(email).get();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> getById(Long id)
    {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> getUsuarios(int numeroPagina, int tamanhoPagina)
    {
        if (tamanhoPagina>50) tamanhoPagina = 50;
        if (tamanhoPagina<5) tamanhoPagina = 5;
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> salvarUsuario(Usuario usuario)
    {
        return Optional.of(usuarioRepository.save(usuario));
    }

    @Override
    public void deletarUsuario(Long id)
    {
        usuarioRepository.deleteById(id);
    }

    @Override @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException
    {
        Usuario usuario = buscarUsuarioPorEmail(s);
        return  new User(
                usuario.getEmail(),
                usuario.getSenha(),
                AuthorityUtils.createAuthorityList(usuario.getPerfil().getDescricao())
        );
    }
}
