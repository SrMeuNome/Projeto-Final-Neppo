package com.vinicius.neppo.service;

import com.vinicius.neppo.model.Artigo;

import java.util.Optional;

public interface ArtigoService
{
    public Iterable<Artigo> getArtigos(int numeroPagina, int tamanhoPagina);

    public Iterable<Artigo> getArtigosByText(int numeroPagina, int tamanhoPagina, String texto);

    public Iterable<Artigo> getArtigosByTags(int numeroPagina, int tamanhoPagina, Long[] listTags);

    public Iterable<Artigo> getArtigosByTextAndTags(int numeroPagina, int tamanhoPagina, String texto, Long[] listTags);

    public Iterable<Artigo> getArtigosPublicados(int numeroPagina, int tamanhoPagina);

    public Iterable<Artigo> getArtigosPublicadosByText(int numeroPagina, int tamanhoPagina, String texto);

    public Iterable<Artigo> getArtigosPublicadosByTags(int numeroPagina, int tamanhoPagina, Long[] listTags);

    public Iterable<Artigo> getArtigosPublicadosByTextAndTags(int numeroPagina, int tamanhoPagina, String texto, Long[] listTags);

    public Optional<Artigo> getArtigoById(Long id);

    public Optional<Artigo> salveArtigo(Artigo artigo);

    public void deletarArtigo(Long id);
}
