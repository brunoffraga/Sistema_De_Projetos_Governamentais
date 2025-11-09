package br.gov.Governamentais.domain.historico.dados;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DadosCadastraHistoricos(

        @NotNull(message = "Obrigatório o id do usuário")
        UUID projetoId,

        @NotNull(message = "Obrigatório o id do usuário")
        UUID usuarioId,

        @NotBlank(message = "O texto é obrigatório.")
        @Size(min = 10, message = "O texto deve ter no mínimo é 10 caracteres.")
        @Size(max = 100, message = "O texto deve ter no máximo 100 caracteres.")
        String texto

) {

}
