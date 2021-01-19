package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categoria extends  AbstractEntity
{
    @Column(name = "nome", nullable = false)
    @Length(max = 100)
    private String nome;

    @Column(name = "descricao")
    @Length(max = 100)
    private String descricao;

    @Column(name = "link")
    private String link;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Secao> secoes;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria")
    private List<Artigo> artigos;

    public Categoria() { super(); }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public List<Secao> getSecoes()
    {
        return secoes;
    }

    public void setSecoes(List<Secao> secoes)
    {
        this.secoes = secoes;
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
