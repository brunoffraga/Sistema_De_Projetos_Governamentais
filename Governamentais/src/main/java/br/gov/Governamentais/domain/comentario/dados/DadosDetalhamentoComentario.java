package br.gov.Governamentais.domain.comentario.dados;

import br.gov.Governamentais.domain.comentario.Comentario;

import java.util.UUID;

public record DadosDetalhamentoComentario(Long id, UUID idProjeto, String nomeProjeto, String nomeUsuario,
                                          String comentario, Boolean ativo) {

    public DadosDetalhamentoComentario (Comentario comentario){
        this (comentario.getId(), comentario.getProjeto().getId(), comentario.getProjeto().getNome(), comentario.getUsuario().getNome(),
                comentario.getDescricao(), comentario.getAtivo());
    }
}
