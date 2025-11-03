package br.gov.Governamentais.domain.projeto.dados;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

public record DadosCadastroProjeto(

        @NotBlank(message = "O nome é obrigatório.")
        String nome,

        @NotBlank@Size(min = 10, message = "A descrição deve ter no mínimo é 10 caracteres.")
        @Size(max = 4000, message = "A descrição deve ter no máximo 4000 caracteres.")
        String descricao,

        @NotNull(message = "A data deve ser presente ou futura.")
        LocalDate dataInicio

) {

}
