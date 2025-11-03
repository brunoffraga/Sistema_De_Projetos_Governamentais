package br.gov.Governamentais.domain.usuario.dados;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosUsuarioCadastra(

        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 10, message = "O nome deve ter no mínimo é 10 caracteres.")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres.")
        String nome,

        @Email(message = "O e-mail é obrigatório.")
        @NotBlank(message = "O e-mail é obrigatório.")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres.")
        String email

) {
}
