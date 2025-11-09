package br.gov.Governamentais.domain.projeto.dados;

import br.gov.Governamentais.validation.groups.Create;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record DadosEditarProjeto(

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres.")
        @Size(max = 4000, message = "A descrição deve ter no máximo 4000 caracteres.")
        String descricao,

        @NotNull(message = "A data deve ser presente ou futura.")
        @FutureOrPresent(message = "A data deve ser presente ou futura.")// ajusta o formato da data
        LocalDate dataInicio
) {

}
