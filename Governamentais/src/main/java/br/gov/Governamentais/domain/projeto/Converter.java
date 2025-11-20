package br.gov.Governamentais.domain.projeto;

import jakarta.persistence.AttributeConverter;

@jakarta.persistence.Converter(autoApply = true)
public class Converter implements AttributeConverter<Status, String>{

    @Override
    public String convertToDatabaseColumn(Status status) {
        return status == null ? null : status.getDescricao();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        if (status == null) return null;
        for (Status s : Status.values()) {
            if (s.getDescricao().equals(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status desconhecido: " + status);
    }
}
