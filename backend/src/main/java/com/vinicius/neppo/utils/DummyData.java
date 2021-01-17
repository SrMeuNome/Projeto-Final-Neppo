package com.vinicius.neppo.utils;

import com.vinicius.neppo.model.*;
import com.vinicius.neppo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData
{
    @Autowired
    ArtigoRepository artigoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    SecaoRepository secaoRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostConstruct
    public void savePerfil()
    {
        Perfil perfil = new Perfil(TipoPerfil.ADMIN);
        perfilRepository.save(perfil);

        perfil = new Perfil(TipoPerfil.USER);
        perfilRepository.save(perfil);
    }

    @PostConstruct
    public void saveUsuario()
    {
        Usuario usuario = new Usuario();
        Perfil perfil = perfilRepository.findById(new Long(1)).get();
        usuario.setId(new Long(1));
        usuario.setEmail("teste@teste.com");
        usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));
        usuario.setAtivo(true);
        usuario.setPerfil(perfil);
        usuarioRepository.save(usuario);

        perfil = perfilRepository.findById(new Long(2)).get();
        usuario.setId(new Long(4));
        usuario.setEmail("user@teste.com");
        usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));
        usuario.setAtivo(true);
        usuario.setPerfil(perfil);
        usuarioRepository.save(usuario);
    }

    @PostConstruct
    public void saveCategoria()
    {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria1");
        categoria.setDescricao("Testando uma descrição");
        categoria.setLink("Teste Link");
        categoria.setId(new Long(1));
        categoriaRepository.save(categoria);

        categoria.setNome("Categoria2");
        categoria.setDescricao("Testando uma descrição 2");
        categoria.setLink("Teste Link 2");
        categoria.setId(new Long(2));
        categoriaRepository.save(categoria);
    }

    @PostConstruct
    public void saveSecao()
    {
        Secao secao = new Secao();
        secao.setNome("Seção1");
        secao.setCategoria(categoriaRepository.findById(new Long(2)).get());
        secao.setId(new Long(1));
        secaoRepository.save(secao);

        secao.setNome("Seção2");
        secao.setCategoria(categoriaRepository.findById(new Long(1)).get());
        secao.setId(new Long(2));
        secaoRepository.save(secao);

        secao.setNome("Seção3");
        secao.setCategoria(categoriaRepository.findById(new Long(1)).get());
        secao.setId(new Long(3));
        secaoRepository.save(secao);
    }

    @PostConstruct
    public void saveTag()
    {
        List<Artigo> artigos = new ArrayList<Artigo>();
        artigos.add(artigoRepository.findById(new Long(1)).get());
        artigos.add(artigoRepository.findById(new Long(3)).get());

        Tag tag = new Tag();
        tag.setNome("UmaTagDaPesada");
        tag.setId(new Long(1));
        tagRepository.save(tag);

        tag = new Tag();
        tag.setNome("UmaTagDaPesada2");
        tag.setId(new Long(2));
        tagRepository.save(tag);

        tag = new Tag();
        tag.setNome("UmaTagDaPesada3");
        tag.setId(new Long(3));
        tag.setArtigos(artigos);
        tagRepository.save(tag);
    }

    @PostConstruct
    public void saveArtigo()
    {
        Artigo artigo = new Artigo();
        artigo.setTitulo("Como ter um artigo de sucesso!");
        artigo.setDescricao("Um teste de descrição1");
        artigo.setAutor(usuarioRepository.findByEmail("teste@teste.com"));
        artigo.setConteudo("Esse é um artigo sobre como ser um cara legal meu irmão. Heheheheheheh texto para encher linguiça");
        artigo.setIs_categoria(true);
        artigo.setIs_rascunho(true);
        artigo.setCategoria(categoriaRepository.findById(new Long(1)).get());
        artigo.setId(new Long(1));
        artigoRepository.save(artigo);

        artigo = new Artigo();
        artigo.setTitulo("Como ter um artigo de sucesso 2!");
        artigo.setDescricao("Um teste de descrição2");
        artigo.setAutor(usuarioRepository.findByEmail("user@teste.com"));
        artigo.setConteudo("Esse é mais um artigo sobre como ser um cara legal meu irmão. Mais texto para encher linguiça");
        artigo.setIs_categoria(true);
        artigo.setIs_rascunho(true);
        artigo.setCategoria(categoriaRepository.findById(new Long(1)).get());
        artigo.setId(new Long(2));
        artigoRepository.save(artigo);

        artigo = new Artigo();
        artigo.setTitulo("Esse artigo é de uma seção!");
        artigo.setAutor(usuarioRepository.findByEmail("teste@teste.com"));
        artigo.setConteudo("Esse foi feito para pertencer a uma seção");
        artigo.setIs_categoria(false);
        artigo.setIs_rascunho(false);
        artigo.setSecao(secaoRepository.findById(new Long(1)).get());
        artigo.setId(new Long(3));
        artigoRepository.save(artigo);

        artigo = new Artigo();
        artigo.setTitulo("Esse artigo é de uma seção 2!");
        artigo.setAutor(usuarioRepository.findByEmail("teste1@teste.com"));
        artigo.setConteudo("Esse foi feito para pertencer a uma seção 2");
        artigo.setIs_categoria(false);
        artigo.setIs_rascunho(true);
        artigo.setSecao(secaoRepository.findById(new Long(1)).get());
        artigo.setId(new Long(4));
        artigoRepository.save(artigo);
    }
}
