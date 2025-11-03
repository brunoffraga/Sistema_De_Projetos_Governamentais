package br.gov.Governamentais.domain.projeto;

import lombok.Getter;

@Getter
public enum Status {

    PLANEJADO(0),
    EM_ANDAMENTO(1),
    EM_ESPERA(2),
    CONCLUÍDO(3),
    CANCELADO(4);

    private final int codigo;

    Status(int codigo) {
        this.codigo = codigo;
    }

    //verifica se o código é valido ou não.
    public static Status escolhaStatus(int codigo){
        for(Status tipos : Status.values()){
            if(tipos.getCodigo() == codigo){
                return tipos;
            }
        }
        throw new IllegalArgumentException("Código Invalido" + codigo);
    }
}
