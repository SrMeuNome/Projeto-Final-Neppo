package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags", indexes = @Index(name = "idx_tags_nome", columnList = "nome"))
public class Tag
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false)
    @Length(max = 100)
    @Pattern(regexp = "[A-Za-z0-9]*", message = "Não é permitido caracteres especiais")
    private String nome;

    @JsonIgnore
    @ManyToMany()
    @JoinTable(
            name = "artigo_tag",
            joinColumns = @JoinColumn(name = "id_tag", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_artigo", referencedColumnName = "id")
    )
    private List<Artigo> artigos;

    public Tag() {}

    public Tag(Long id, String nome, List<Artigo> artigos)
    {
        this.id = id;
        this.nome = nome;
        this.artigos = artigos;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
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
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) &&
                Objects.equals(nome, tag.nome) &&
                Objects.equals(artigos, tag.artigos);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, nome, artigos);
    }

    @Override
    public String toString()
    {
        return "Tag{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", artigos=" + artigos +
                '}';
    }
}
