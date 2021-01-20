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
    SecaoRepository secaoRepository;

    @Autowired
    TagRepository tagRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostConstruct
    public void saveData()
    {
        saveUsuario();
        saveCategoria();
        saveSecao();
        saveTag();
        saveArtigo();
    }

    public void saveUsuario()
    {
        Usuario usuario = new Usuario();
        usuario.setId(new Long(1));
        usuario.setEmail("admin@teste.com");
        usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));
        usuario.setAtivo(true);
        usuario.setPerfil(TipoPerfil.ROLE_ADMIN);
        usuarioRepository.save(usuario);

        usuario = new Usuario();
        usuario.setId(new Long(2));
        usuario.setEmail("user@teste.com");
        usuario.setSenha(new BCryptPasswordEncoder().encode("123456"));
        usuario.setAtivo(true);
        usuario.setPerfil(TipoPerfil.ROLE_USUARIO);
        usuarioRepository.save(usuario);
    }

    public void saveCategoria()
    {
        Categoria categoria = new Categoria();
        categoria.setNome("Categoria1");
        categoria.setDescricao("Testando uma descrição");
        categoria.setLink("Teste Link");
        categoria.setId(new Long(1));
        categoriaRepository.save(categoria);

        categoria = new Categoria();
        categoria.setNome("Categoria2");
        categoria.setDescricao("Testando uma descrição 2");
        categoria.setLink("Teste Link 2");
        categoria.setId(new Long(2));
        categoriaRepository.save(categoria);
    }

    public void saveSecao()
    {
        Secao secao = new Secao();
        secao.setNome("Seção1");
        secao.setCategoria(categoriaRepository.findById(new Long(2)).get());
        secao.setId(new Long(1));
        secaoRepository.save(secao);

        secao = new Secao();
        secao.setNome("Seção2");
        secao.setCategoria(categoriaRepository.findById(new Long(1)).get());
        secao.setId(new Long(2));
        secaoRepository.save(secao);

        secao = new Secao();
        secao.setNome("Seção3");
        secao.setCategoria(categoriaRepository.findById(new Long(1)).get());
        secao.setId(new Long(3));
        secaoRepository.save(secao);
    }

    public void saveTag()
    {
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
        tagRepository.save(tag);
    }

    public void saveArtigo()
    {
        List<Tag> tags = new ArrayList<Tag>();
        tags.add(tagRepository.findById(new Long(1)).get());
        tags.add(tagRepository.findById(new Long(2)).get());

        Artigo artigo = new Artigo();
        artigo.setTitulo("Como ter um artigo de sucesso!");
        artigo.setDescricao("Um teste de descrição1");
        artigo.setAutor(usuarioRepository.findByEmail("admin@teste.com").get());
        artigo.setConteudo("Esse é um artigo sobre como ser um cara legal meu irmão. Heheheheheheh texto para encher linguiça");
        artigo.setArtCategoria(true);
        artigo.setRascunho(true);
        artigo.setCategoria(categoriaRepository.findById(new Long(1)).get());
        artigo.setTags(tags);
        artigo.setId(new Long(1));
        artigoRepository.save(artigo);

        tags = new ArrayList<Tag>();
        tags.add(tagRepository.findById(new Long(3)).get());
        tags.add(tagRepository.findById(new Long(2)).get());

        artigo = new Artigo();
        artigo.setTitulo("Como ter um artigo de sucesso 2!");
        artigo.setDescricao("Um teste de descrição2");
        artigo.setAutor(usuarioRepository.findByEmail("user@teste.com").get());
        artigo.setConteudo("Esse é mais um artigo sobre como ser um cara legal meu irmão. Mais texto para encher linguiça");
        artigo.setArtCategoria(true);
        artigo.setRascunho(true);
        artigo.setCategoria(categoriaRepository.findById(new Long(1)).get());
        artigo.setTags(tags);
        artigo.setId(new Long(2));
        artigoRepository.save(artigo);

        artigo = new Artigo();
        artigo.setTitulo("Esse artigo é de uma seção!");
        artigo.setAutor(usuarioRepository.findByEmail("admin@teste.com").get());
        artigo.setConteudo("Esse foi feito para pertencer a uma seção");
        artigo.setArtCategoria(false);
        artigo.setRascunho(false);
        artigo.setSecao(secaoRepository.findById(new Long(1)).get());
        artigo.setId(new Long(3));
        artigoRepository.save(artigo);

        artigo = new Artigo();
        artigo.setTitulo("Esse artigo é de uma seção 2!");
        artigo.setAutor(usuarioRepository.findByEmail("user@teste.com").get());
        artigo.setConteudo("Esse foi feito para pertencer a uma seção 2");
        artigo.setArtCategoria(false);
        artigo.setRascunho(true);
        artigo.setSecao(secaoRepository.findById(new Long(1)).get());
        artigo.setId(new Long(4));
        artigoRepository.save(artigo);
    }
}
