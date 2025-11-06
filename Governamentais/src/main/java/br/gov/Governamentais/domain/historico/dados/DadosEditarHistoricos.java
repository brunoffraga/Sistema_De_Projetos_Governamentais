package br.gov.Governamentais.domain.historico.dados;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DadosEditarHistoricos(

        @NotNull(message = "Obrigadotrio id do Historico")
        UUID id,

        @NotNull(message = "Obrigatorio o id do usuario")
        UUID projetoId,

        @NotNull(message = "Obrigatorio o id do usuario")
        UUID usuarioId,

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 10, message = "O nome deve ter no mínimo é 10 caracteres.")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String texto
) {
}
