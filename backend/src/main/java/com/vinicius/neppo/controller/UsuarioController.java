package com.vinicius.neppo.controller;

import com.vinicius.neppo.model.Perfil;
import com.vinicius.neppo.model.Usuario;
import com.vinicius.neppo.service.PerfilService;
import com.vinicius.neppo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    PerfilService perfilService;

    @GetMapping
    public Iterable<Usuario> exibirTags(
            @RequestParam(name = "pagina") int numeroPagina,
            @RequestParam(name = "tamanho") int tamanhoPagina
    )
    {
        return service.getUsuarios(numeroPagina, tamanhoPagina);
    }

    @GetMapping("/{id}")
    public Optional<Usuario> exibirUsuario(@PathVariable(name = "id") Long id)
    {
        return service.getById(id);
    }

    @PostMapping
    public Optional<Usuario> salvarUsuario(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "senha", required = true) String senha,
            @RequestParam(value = "perfil", required = true) Long idPerfil
    )
    {
        Usuario usuario = new Usuario();

        Perfil perfil = perfilService.getById(idPerfil).get();
        usuario.setEmail(email);
        usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        usuario.setAtivo(true);
        usuario.setPerfil(perfil);
        return service.salvarUsuario(usuario);
    }

    @PutMapping("/{id}")
    public Optional<Usuario> editarUsuario(
            @PathVariable(name = "id") Long id,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "senha", required = false) String senha,
            @RequestParam(value = "perfil", required = false) Long idPerfil
    )
    {
        Usuario usuario = service.getById(id).get();

        if (idPerfil != null)
        {
            Perfil perfil = perfilService.getById(idPerfil).get();
            usuario.setPerfil(perfil);
        }

        if (email != null) usuario.setEmail(email);
        if (senha != null) usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
        return service.salvarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public String deletarUsuario(
            @PathVariable(name = "id") Long id
    )
    {
        service.deletarUsuario(id);
        return "Sucess";
    }
}
