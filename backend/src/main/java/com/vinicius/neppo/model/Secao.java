package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "secoes")
public class Secao extends AbstractEntity
{
    @Column(name = "nome")
    @Length(max = 100)
    private String nome;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @JsonIgnore
    @OneToMany(mappedBy = "secao")
    private List<Artigo> artigos;

    public Secao() { super(); }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
    {
        this.categoria = categoria;
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
