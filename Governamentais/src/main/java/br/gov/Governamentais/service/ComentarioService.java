package br.gov.Governamentais.service;

import br.gov.Governamentais.domain.comentarios.Comentario;
import br.gov.Governamentais.domain.comentarios.ComentarioRepository;
import br.gov.Governamentais.domain.comentarios.dados.DadosCadastraComentario;
import br.gov.Governamentais.domain.comentarios.dados.DadosEditarComentario;
import br.gov.Governamentais.domain.comentarios.dados.DadosListaComentario;
import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.ProjetoRepository;
import br.gov.Governamentais.domain.usuario.Usuario;
import br.gov.Governamentais.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository repository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Comentario salvar(DadosCadastraComentario dados){
        var comentario = new Comentario();

        Projeto projeto = projetoRepository.findById(dados.projetoId())
                        .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        comentario.setProjeto(projeto);
        comentario.setUsuario(usuario);
        comentario.setComentario(dados.comentario());

        repository.save(comentario);

        return comentario;
    }

    @Transactional
    public Comentario atualizar(DadosEditarComentario dados){
        var comentario = new Comentario();

        Projeto projeto = projetoRepository.findById(dados.projetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        comentario.setProjeto(projeto);
        comentario.setUsuario(usuario);
        comentario.setComentario(dados.comentario());

        repository.save(comentario);

        return comentario;
    }

    @Transactional
    public Comentario ativarOuDesativarComentario(Long id, Boolean ativo){
        var comentario = repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Comentario não encontrado com o id" + id));

        if (ativo != null) {
            comentario.setAtivo(ativo);
        }

        return comentario;
    }

    /*
     *como estou só listando não utilizar o transsactional, novo registro e cadastro
     */
    public Page<DadosListaComentario> listarPorStatus(Boolean ativo, Pageable pageable){

        return  repository.findAllByAtivo(ativo, pageable)
                .map(DadosListaComentario::new);
    }

    public DadosListaComentario comentarioId(@PathVariable Long id){
        var comentario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario não encontrado com o id: " + id));
        return new DadosListaComentario(comentario);
    }

}