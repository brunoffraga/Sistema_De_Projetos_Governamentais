package br.gov.Governamentais.domain.historico;

import br.gov.Governamentais.domain.historico.dados.DadosCadastraHistoricos;
import br.gov.Governamentais.domain.historico.dados.DadosEditarHistoricos;
import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "tb_hitoricos")
@Entity(name = "Hitorico")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Historicos {

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

    @Column(name = "historico_data_publicada")
    private LocalDate dataPublicada = LocalDate.now();

    @Column(name = "historico_ativo")
    private Boolean ativo = true;

    public Historicos(DadosCadastraHistoricos dados) {
        this.texto = texto;
        this.dataPublicada = LocalDate.now();
        this.ativo = true;
    }

    public Historicos(DadosEditarHistoricos dados) {
        this.texto = texto;
        this.dataPublicada = LocalDate.now();
        this.ativo = true;
    }
}
