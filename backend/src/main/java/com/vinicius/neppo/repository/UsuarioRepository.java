package com.vinicius.neppo.repository;

import com.vinicius.neppo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    Usuario findByEmail(String email);
}
