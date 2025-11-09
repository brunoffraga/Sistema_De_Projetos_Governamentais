package br.gov.Governamentais.domain.comentarios.dados;

import br.gov.Governamentais.domain.comentarios.Comentario;
import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;

public record DadosDetalhamentoComentario(Long id, UUID idProjeto, String nomeProjeto, String nomeUsuario,
                                          String comentario, Boolean ativo) {

    public DadosDetalhamentoComentario (Comentario comentario){
        this (comentario.getId(), comentario.getProjeto().getId(), comentario.getProjeto().getNome(), comentario.getUsuario().getNome(),
                comentario.getComentario(), comentario.getAtivo());
    }
}
