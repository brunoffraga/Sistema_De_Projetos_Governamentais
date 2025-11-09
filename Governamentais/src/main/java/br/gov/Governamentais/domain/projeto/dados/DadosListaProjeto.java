package br.gov.Governamentais.domain.projeto.dados;

import br.gov.Governamentais.domain.projeto.Projeto;

import java.time.LocalDate;
import java.util.UUID;

public record DadosListaProjeto(UUID id, String nome, int porcentagem, boolean ativo, String status, LocalDate data, String descricao) {

    public DadosListaProjeto(Projeto projeto){
        this(projeto.getId(),
                projeto.getNome(),
                projeto.getPorcentagem(),
                projeto.isAtivo(),
                projeto.getStatus().getDescricao(),
                projeto.getDataInicio(),
                projeto.getDescricao());
    }

}
