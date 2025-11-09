package br.gov.Governamentais.domain.projeto.dados;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public record DadosDetalhamentoProjeto (UUID id, String nome, String descricao){

    public DadosDetalhamentoProjeto (Projeto projeto){
        this(projeto.getId(), projeto.getNome(), projeto.getDescricao());
    }
}
