package br.gov.Governamentais.domain.projeto;

import br.gov.Governamentais.domain.comentarios.Comentarios;
import br.gov.Governamentais.domain.historico.Historicos;
import br.gov.Governamentais.domain.projeto.dados.DadosCadastroProjeto;
import br.gov.Governamentais.domain.projeto.dados.DadosEditarProjeto;
import br.gov.Governamentais.domain.projetoUsuario.VincularProjetoUsuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_projeto")
@Entity(name = "Projeto")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Projeto {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            //essa extensão garante que os identificadores sejam globais e unicamente únicos
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "projeto_id", columnDefinition = "RAW(16)")
    private UUID id;

    @Column(name = "projeto_name")
    private String nome;

    //Limitar o varchar de 4000
    @Column(name = "projeto_descricao", length = 4000)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "projeto_status")
    private Status status = Status.PLANEJADO_INICIAL;

    //TODO: Fazer limitacao de 0 a 100
    @Column(name = "projeto_porcentagem")
    private Integer porcentagem = 0;

    //TODO: fazer esse
    @Column(name = "projeto_data_inicio")
    private LocalDate dataInicio;

    @Column(name = "projeto_data_final")
    private LocalDate dataFinal;

    @Column(name = "projeto_ativo")
    private boolean ativo = true;

    //      Relacionamento

    //TODO: colocar o relacionamento no usuairo.
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VincularProjetoUsuario> projetoUsuarios = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Historicos> hitoricos = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Comentarios> comentarios = new ArrayList<>();

    public Projeto(DadosCadastroProjeto dados) {
        this.nome = dados.nome();
        this.porcentagem = 0;
        this.descricao = dados.descricao();
        this.dataInicio = dados.dataInicio();
        this.ativo = true;
        this.status = Status.PLANEJADO_INICIAL;
        this.porcentagem = 0;
    }

    public Projeto(DadosEditarProjeto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if (dados.dataInicio() != null) {
            this.dataInicio = dados.dataInicio();
        }
    }


}
