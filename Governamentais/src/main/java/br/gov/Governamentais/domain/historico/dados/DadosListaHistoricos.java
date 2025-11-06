package br.gov.Governamentais.domain.historico.dados;

import br.gov.Governamentais.domain.historico.Historicos;

import java.util.UUID;

public record DadosListaHistoricos(
        UUID id, String nomeProjeto, String nomeUsuario, String usuario
) {

    public DadosListaHistoricos(Historicos historicos){
        this(historicos.getId(), historicos.getProjeto().getNome(),
                historicos.getUsuario().getNome(), historicos.getTexto());
    }
}