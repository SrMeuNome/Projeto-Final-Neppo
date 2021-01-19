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

    @ManyToOne()
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean ativo;

    @JsonIgnore
    @OneToMany(mappedBy = "autor")
    private transient List<Artigo> artigos;

    public Usuario() { super(); }

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

    public Perfil getPerfil()
    {
        return perfil;
    }

    public void setPerfil(Perfil perfil)
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
