package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    private List<Artigo> artigos;

    public Usuario() {}

    public Usuario(long id, String email, String senha, Perfil perfil, boolean ativo, List<Artigo> artigos)
    {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ativo = ativo;
        this.artigos = artigos;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id &&
                ativo == usuario.ativo &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(senha, usuario.senha) &&
                Objects.equals(perfil, usuario.perfil) &&
                Objects.equals(artigos, usuario.artigos);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, email, senha, perfil, ativo, artigos);
    }

    @Override
    public String toString()
    {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", perfil=" + perfil +
                ", ativo=" + ativo +
                ", artigos=" + artigos +
                '}';
    }
}
