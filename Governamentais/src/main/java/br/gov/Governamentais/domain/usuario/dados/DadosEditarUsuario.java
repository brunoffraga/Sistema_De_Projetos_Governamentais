package br.gov.Governamentais.domain.usuario.dados;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DadosEditarUsuario(

        @NotNull(message = "Campo id obrigatório")
        UUID id,

        @NotBlank
        String nome,

        @Email
        String email
) {


}
