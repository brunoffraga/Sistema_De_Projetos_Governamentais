package br.gov.Governamentais.domain.projeto.dados;

import br.gov.Governamentais.domain.projeto.Projeto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record DadosListaProjetoSemDescricao(UUID id, String nome, int porcentagem, String status, String data) {

    public DadosListaProjetoSemDescricao(Projeto projeto){
        this(projeto.getId(),
                projeto.getNome(),
                projeto.getPorcentagem(),
                projeto.getStatus().getDescricao(),
                projeto.getDataInicio().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
