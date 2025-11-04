package br.gov.Governamentais.domain.projeto.dados;

import br.gov.Governamentais.validation.groups.Create;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record DadosEditarProjeto(

        @NotNull(message = "O ID não pode ser nulo.")
        UUID id,

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @Size(min = 10, message = "A descrição deve ter no mínimo é 10 caracteres.")
        @Size(max = 4000, message = "A descrição deve ter no máximo 4000 caracteres.")
        String descricao,

        //TODO: resolver esse erro
        @NotNull(message = "A data deve ser presente ou futura.")
        @FutureOrPresent( message = "A data deve ser presente ou futura.")
        LocalDate dataInicio
) {
}
