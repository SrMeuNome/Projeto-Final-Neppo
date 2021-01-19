package com.vinicius.neppo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags", indexes = @Index(name = "idx_tags_nome", columnList = "nome"))
public class Tag extends AbstractEntity
{

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

    public Tag() { super(); }

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
}
