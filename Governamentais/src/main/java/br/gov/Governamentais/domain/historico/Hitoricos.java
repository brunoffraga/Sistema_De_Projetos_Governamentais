package br.gov.Governamentais.domain.historico;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Table(name = "tb_hitoricos")
@Entity(name = "Hitorico")
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Hitoricos {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            //essa extensão garante que os identificadores sejam globais e unicamente únicos
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "historicos_id", columnDefinition = "RAW(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "projeto_projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "usuario_usuario_id")
    private Usuario usuario;

    //TODO: Limitar o varchar de 4000 e mínimo de 10fazer regra no DTO
    @Column(name = "historico_descricao")
    private String texto;


}
