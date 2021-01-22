package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario extends AbstractEntity
{

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Email não é valido")
    private String email;

    @JsonIgnore
    @Column(name = "senha", nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private TipoPerfil perfil;

    @Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private List<Artigo> artigos;

    public Usuario() { super(); }

    public Usuario(@Email(message = "Email não é valido") String email, String senha)
    {
        super();
        this.email = email;
        this.senha = senha;
        this.perfil = TipoPerfil.ROLE_USUARIO;
    }

    public Usuario(@Email(message = "Email não é valido") String email, String senha, TipoPerfil perfil)
    {
        super();
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public TipoPerfil getPerfil()
    {
        return perfil;
    }

    public void setPerfil(TipoPerfil perfil)
    {
        this.perfil = perfil;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

    public List<Artigo> getArtigos()
    {
        return artigos;
    }

    public void setArtigos(List<Artigo> artigos)
    {
        this.artigos = artigos;
    }
}
