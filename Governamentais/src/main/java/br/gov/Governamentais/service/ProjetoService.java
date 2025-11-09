package br.gov.Governamentais.service;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.ProjetoRepository;
import br.gov.Governamentais.domain.projeto.dados.DadosCadastroProjeto;
import br.gov.Governamentais.domain.projeto.dados.DadosEditarProjeto;
import br.gov.Governamentais.domain.projeto.dados.DadosListaProjeto;
import br.gov.Governamentais.domain.projeto.dados.DadosListaProjetoSemDescricao;
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

    public Page<DadosListaProjetoSemDescricao> listarPorStatusSemTexto(Boolean ativo, Pageable pageable) {

        return repository.findAllByAtivo(ativo, pageable)
                .map(DadosListaProjetoSemDescricao::new);
    }

    public DadosListaProjeto usuarioId(@PathVariable UUID id){
        var projeto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com o id: " + id));
        return new DadosListaProjeto(projeto);
    }

}
