package br.gov.Governamentais.service;

import br.gov.Governamentais.domain.comentario.ComentarioRepository;
import br.gov.Governamentais.domain.comentario.dados.DadosListaComentario;
import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.ProjetoRepository;
import br.gov.Governamentais.domain.projeto.dados.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository repository;

    @Autowired
    private ComentarioRepository repositoryComentario;

    @Transactional
    public Projeto salvar(DadosCadastroProjeto request){
        var projeto = new Projeto();
        projeto.setNome(request.nome());
        projeto.setDescricao(request.descricao());
        projeto.setDataInicio(request.dataInicio());

        repository.save(projeto);

        return projeto;
    }

    @Transactional
    public Projeto ativarOuDesativarProjeto(UUID id, Boolean ativo){
        var projeto = repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Usuario não encontrado com o id" + id));

        if (ativo != null){
            projeto.setAtivo(ativo);
        }

        return projeto;
    }

    @Transactional
    public Projeto atualiza(UUID id, DadosEditarProjeto request){
        var projeto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        if (request.nome() != null) {
            projeto.setNome(request.nome());
        }
        if (request.descricao() != null) {
            projeto.setDescricao(request.descricao());
        }
        if (request.dataInicio() != null) {
            projeto.setDataInicio(request.dataInicio());
        }

        repository.save(projeto);

        return projeto;
    }

    @Transactional(readOnly = true)
    public Page<DadosListaProjeto> listarPorStatus(Boolean ativo, Pageable pageable) {
        return repository.findAllByAtivo(ativo, pageable).map(DadosListaProjeto::new);
    }

    //Lista sem o comentario do projeto
    public Page<DadosListaProjetoSemDescricao> listarPorStatusSemTexto(Boolean ativo, Pageable pageable) {

        return repository.PesquisaRetornaSemComentario(ativo, pageable);
    }

    public Page<DadosListaProjetoNome> listarPorStatusNome(Boolean ativo, Pageable pageable) {

        return repository.PesquisaRetornaIdNome(ativo, pageable);
    }


    public DadosListaProjeto projetoId(@PathVariable UUID id){
        var projeto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com o id: " + id));
        return new DadosListaProjeto(projeto);
    }

    public Page<DadosListaComentario> listaProjetoComentarios(Boolean ativo, UUID projetoId, Pageable pageable) {
        return repositoryComentario.findAllByAtivo(ativo, projetoId, pageable);
    }

}
