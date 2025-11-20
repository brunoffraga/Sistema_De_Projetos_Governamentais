package br.gov.Governamentais.domain.comentario.dados;

import br.gov.Governamentais.domain.comentario.Comentario;

import java.time.LocalDateTime;

public record DadosListaComentario(
        Long id,
        String usuarioNome,
        String descricao,
        LocalDateTime dataComentario
) {

    public DadosListaComentario (Comentario comentario){
        this(
                comentario.getId(),
                comentario.getUsuario().getNome(),
                comentario.getDescricao(),
                comentario.getDataComentario()
        );
    }
}
