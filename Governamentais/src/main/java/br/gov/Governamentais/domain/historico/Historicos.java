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

import java.time.LocalDateTime;

@Table(name = "tb_hitoricos")
@Entity(name = "Hitorico")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Historicos {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historicos_seq")
    @SequenceGenerator(name = "historicos_seq", sequenceName = "SEQ_HITORICO", allocationSize = 1)
    @Column(name = "historicos_id")
    private Long id;

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
    private LocalDateTime dataPublicada = LocalDateTime.now();

    @Column(name = "historico_ativo")
    private Boolean ativo = true;

    public Historicos(DadosCadastraHistoricos dados) {
        this.texto = texto;
        this.dataPublicada = LocalDateTime.now();
        this.ativo = true;
    }

    public Historicos(DadosEditarHistoricos dados) {
        this.texto = texto;
        this.dataPublicada = LocalDateTime.now();
        this.ativo = true;
    }
}
