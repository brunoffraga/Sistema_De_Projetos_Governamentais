package br.gov.Governamentais.domain.usuario.dados;

import br.gov.Governamentais.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

public record DadosDetalhamentoUsuario(UUID Id, String nome, String email) {

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(),
                usuario.getEmail() + "Cadastro feito com sucesso!");
    }
}
