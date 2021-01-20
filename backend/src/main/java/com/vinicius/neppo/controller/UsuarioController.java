package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.TipoPerfil;
import com.vinicius.neppo.model.Usuario;
import com.vinicius.neppo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController
{
    @Autowired
    UsuarioService service;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Iterable<Usuario> exibirUsuarios(
            @RequestParam(name = "pagina") int numeroPagina,
            @RequestParam(name = "tamanho") int tamanhoPagina
    )
    {
        return service.getUsuarios(numeroPagina, tamanhoPagina);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Optional<Usuario> exibirUsuario(@PathVariable(name = "id") Long id)
    {
        return service.getById(id);
    }

    @PostMapping
    public Optional<Usuario> salvarUsuario(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "senha", required = true) String senha,
            @RequestParam(value = "perfil", required = true) Integer idPerfil
    )
    {
        Usuario usuario = new Usuario();

        if (idPerfil == 1) usuario.setPerfil(TipoPerfil.ROLE_ADMIN);
        if (idPerfil == 2) usuario.setPerfil(TipoPerfil.ROLE_USUARIO);
        usuario.setEmail(email);
        usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        usuario.setAtivo(true);
        return service.salvarUsuario(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Optional<Usuario> editarUsuario(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "senha", required = false) String senha,
            @RequestParam(value = "perfil", required = false) Integer idPerfil
    )
    {
        Usuario usuario = service.getById(id).get();

        if (idPerfil != null)
        {
            if (idPerfil == 1) usuario.setPerfil(TipoPerfil.ROLE_ADMIN);
            if (idPerfil == 2) usuario.setPerfil(TipoPerfil.ROLE_USUARIO);
        }

        if (email != null) usuario.setEmail(email);
        if (senha != null) usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        return service.salvarUsuario(usuario);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deletarUsuario(
            @PathVariable(name = "id") Long id
    )
    {
        service.deletarUsuario(id);
        return "Sucess";
    }
}
