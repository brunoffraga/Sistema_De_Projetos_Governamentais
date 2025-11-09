package br.gov.Governamentais.domain.comentarios.dados;

import br.gov.Governamentais.validation.groups.Create;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record DadosCadastraComentario(

        @NotNull(message = "O numero do projeto")
        UUID projetoId,

        @NotNull(message = "O numero do usuário")
        UUID usuarioId,

        @NotBlank(groups = Create.class)
        @Size(min = 10, message = "A descrição deve ter no mínimo é 10 caracteres.")
        @Size(max = 4000, message = "A descrição deve ter no máximo 4000 caracteres.")
        String comentario

) {
}
