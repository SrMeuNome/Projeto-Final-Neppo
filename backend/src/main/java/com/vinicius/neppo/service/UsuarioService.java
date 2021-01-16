package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Usuario;
import com.vinicius.neppo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService
{
    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorEmail(String email)
    {
        return usuarioRepository.findByEmail(email);
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
