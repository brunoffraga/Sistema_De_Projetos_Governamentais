package br.gov.Governamentais.domain.comentario.dados;

import java.time.LocalDateTime;

public interface ComentarioResumo {

    Long getId();
    String getUsuarioNome();
    String getDescricao();
    LocalDateTime getDataComentario();

}
