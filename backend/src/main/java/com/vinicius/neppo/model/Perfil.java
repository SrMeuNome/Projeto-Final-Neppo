package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "perfis")
public class Perfil
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "perfil")
    private List<Usuario> usuarios;

    public Perfil() {}

    public Perfil(TipoPerfil tipoPerfil)
    {
        this.id = tipoPerfil.getCod();
        this.descricao = tipoPerfil.getDesc();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public List<Usuario> getUsuarios()
    {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios)
    {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(id, perfil.id) &&
                Objects.equals(descricao, perfil.descricao) &&
                Objects.equals(usuarios, perfil.usuarios);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, descricao, usuarios);
    }

    @Override
    public String toString()
    {
        return "Perfil{" +
                "id=" + id +
                ", desc='" + descricao + '\'' +
                ", usuarios=" + usuarios +
                '}';
    }
}
