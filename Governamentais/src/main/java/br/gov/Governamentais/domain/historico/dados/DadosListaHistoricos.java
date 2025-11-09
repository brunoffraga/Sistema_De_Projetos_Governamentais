package br.gov.Governamentais.domain.historico.dados;

import br.gov.Governamentais.domain.historico.Historicos;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosListaHistoricos(
        Long id, String nomeProjeto, String nomeUsuario, String usuario, LocalDateTime dataPulicada
) {

    public DadosListaHistoricos(Historicos historicos){
        this(historicos.getId(), historicos.getProjeto().getNome(),
                historicos.getUsuario().getNome(), historicos.getTexto(), historicos.getDataPublicada());
    }
}