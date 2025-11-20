package br.gov.Governamentais.domain.projeto;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static final Map<Integer, Status> MAPA = Arrays.stream(values())
            .collect(Collectors.toMap(Status::getCodigo, s -> s));

    Status(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public static Status escolhaStatus(int codigo){
        Status status = MAPA.get(codigo);

        if(status == null)
            throw new IllegalArgumentException("Código inválido: " + codigo);


        return status;
    }


}