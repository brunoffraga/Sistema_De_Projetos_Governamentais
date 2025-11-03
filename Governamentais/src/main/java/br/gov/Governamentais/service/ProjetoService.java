package br.gov.Governamentais.service;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.ProjetoRepository;
import br.gov.Governamentais.domain.projeto.dados.DadosCadastroProjeto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
