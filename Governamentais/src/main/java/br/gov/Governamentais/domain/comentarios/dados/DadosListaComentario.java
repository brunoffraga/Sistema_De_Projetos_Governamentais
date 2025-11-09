package br.gov.Governamentais.domain.comentarios.dados;

import br.gov.Governamentais.domain.comentarios.Comentario;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosListaComentario(
        Long id, UUID idprojeto,String projetoNome, String usuairoNome, Boolean ativo, LocalDateTime dataComentario
) {

    public DadosListaComentario (Comentario comentario){
        this(comentario.getId(), comentario.getProjeto().getId(), comentario.getProjeto().getNome(), comentario.getUsuario().getNome(),
            comentario.getAtivo(), comentario.getDataComentario());
    }
}
