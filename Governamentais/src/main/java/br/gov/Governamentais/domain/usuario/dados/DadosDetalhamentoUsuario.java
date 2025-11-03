package br.gov.Governamentais.domain.usuario.dados;

import br.gov.Governamentais.domain.usuario.Usuario;

import java.util.UUID;

public record DadosDetalhamentoUsuario(UUID Id, String nome, String email) {

    public DadosDetalhamentoUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(),
                usuario.getEmail() + "Cadastro feito com sucesso!");
    }
}
