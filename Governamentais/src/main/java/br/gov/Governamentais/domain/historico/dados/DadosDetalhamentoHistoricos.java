package br.gov.Governamentais.domain.historico.dados;

import br.gov.Governamentais.domain.historico.Historicos;

import java.time.LocalDate;
import java.util.UUID;

public record DadosDetalhamentoHistoricos(UUID id, String nomeProjeto, String nomeUsuario, String text,
        LocalDate dataPublicada, Boolean ativo) {

    public DadosDetalhamentoHistoricos(Historicos historico){
        this(historico.getId(), historico.getProjeto().getNome(), historico.getUsuario().getNome(),
                historico.getTexto(), historico.getDataPublicada(), historico.getAtivo());
    }
}
