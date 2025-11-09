package br.gov.Governamentais.domain.usuario.dados;

import br.gov.Governamentais.domain.usuario.Usuario;

import java.util.UUID;

public record DadosListaUsuairoNome(UUID id, String nome) {

    public DadosListaUsuairoNome(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }
}
