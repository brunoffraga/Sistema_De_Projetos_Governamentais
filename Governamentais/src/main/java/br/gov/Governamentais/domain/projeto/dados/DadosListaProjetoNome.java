package br.gov.Governamentais.domain.projeto.dados;


import br.gov.Governamentais.domain.projeto.Projeto;

import java.util.UUID;

public record DadosListaProjetoNome(
        UUID id,
        String nome) {

    public DadosListaProjetoNome (Projeto projeto) {
        this(
                projeto.getId(),
                projeto.getNome()
        );
    }
}
