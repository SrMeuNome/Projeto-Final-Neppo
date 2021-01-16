package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categorias")
public class Categoria
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public Categoria() {}

    public Categoria(long id, @Length(max = 100) String nome, @Length(max = 100) String descricao, String link, List<Secao> secoes, List<Artigo> artigos)
    {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.link = link;
        this.secoes = secoes;
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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id &&
                Objects.equals(nome, categoria.nome) &&
                Objects.equals(descricao, categoria.descricao) &&
                Objects.equals(link, categoria.link) &&
                Objects.equals(secoes, categoria.secoes) &&
                Objects.equals(artigos, categoria.artigos);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, nome, descricao, link, secoes, artigos);
    }

    @Override
    public String toString()
    {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", link='" + link + '\'' +
                ", secoes=" + secoes +
                ", artigos=" + artigos +
                '}';
    }
}
