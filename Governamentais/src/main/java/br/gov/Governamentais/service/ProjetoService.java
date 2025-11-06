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
    public Projeto atualiza(DadosEditarProjeto request){
        var projeto = new Projeto();
        projeto.setId(request.id());
        projeto.setNome(request.nome());
        projeto.setDescricao(request.descricao());
        projeto.setDataInicio(request.dataInicio());

        repository.save(projeto);

        return projeto;
    }

    @Transactional(readOnly = true)
    public Page<DadosListaProjeto> listarPorStatus(Boolean ativo, Pageable pageable) {
        if(ativo == null)
            return repository.findAll(pageable).map(DadosListaProjeto::new);

        if (Boolean.TRUE.equals(ativo))
            return repository.findAllByAtivoTrue(pageable).map(DadosListaProjeto::new);

        return repository.findAllByAtivoFalse(pageable).map(DadosListaProjeto::new);
    }

    @Transactional(readOnly = true)
    public Page<DadosListaProjetoSemDescricao> listarPorStatusSemTexto(Boolean ativo, Pageable pageable) {
        if(ativo == null)
            return repository.findAll(pageable).map(DadosListaProjetoSemDescricao::new);

        if (Boolean.TRUE.equals(ativo))
            return repository.findAllByAtivoTrue(pageable).map(DadosListaProjetoSemDescricao::new);

        return repository.findAllByAtivoFalse(pageable).map(DadosListaProjetoSemDescricao::new);
    }

    @Transactional(readOnly = true)
    public DadosListaProjeto usuarioId(@PathVariable UUID id){
        var projeto = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado com o id: " + id));
        return new DadosListaProjeto(projeto);
    }

}
