package br.gov.Governamentais.domain.projeto.dados;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record DadosListaProjetoSemDescricao(
        UUID id,
        String nome,
        Status status,
        int porcentagem,
        @JsonFormat(pattern =  "dd/MM/yyyy")
        LocalDate data
) {

    public DadosListaProjetoSemDescricao(Projeto projeto){
        this(
                projeto.getId(),
                projeto.getNome(),
                projeto.getStatus(),
                projeto.getPorcentagem(),
                projeto.getDataInicio()
        );
    }

}
