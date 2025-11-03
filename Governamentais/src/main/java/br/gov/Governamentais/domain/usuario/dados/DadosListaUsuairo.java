package br.gov.Governamentais.domain.usuario.dados;

import br.gov.Governamentais.domain.usuario.Usuario;

import java.util.UUID;

public record DadosListaUsuairo(UUID id, String nome, String email) {

    public DadosListaUsuairo(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }
}
