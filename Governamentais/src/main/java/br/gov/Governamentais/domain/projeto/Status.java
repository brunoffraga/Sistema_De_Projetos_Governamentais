package br.gov.Governamentais.domain.projeto;

import lombok.Getter;

@Getter
public enum Status {

    PLANEJADO_INICIAL(0, "PLANEJADO_INICIAL"),
    PLANEJADO_FINALIZADO(1, "PLANEJADO_FINALIZADO"),
    EM_ANDAMENTO(2, "EM_ANDAMENTO"),
    CONCLUÍDO(3, "CONCLUÍDO"),
    EM_ESPERA(4, "EM_ESPERA"),
    CANCELADO(5, "CANCELADO");

    private final int codigo;
    private final String descricao;

    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Status escolhaStatus(int codigo){
        for(Status verificandoCodigoStatus : Status.values()){
            if(verificandoCodigoStatus.getCodigo() == codigo){
                return verificandoCodigoStatus;
            }
        }
        throw new IllegalArgumentException("Código Invalido" + codigo);
    }

}