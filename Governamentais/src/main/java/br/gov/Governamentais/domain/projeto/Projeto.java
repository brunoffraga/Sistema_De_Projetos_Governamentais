package br.gov.Governamentais.domain.projeto;

import br.gov.Governamentais.domain.comentario.Comentario;
import br.gov.Governamentais.domain.historico.Historicos;
import br.gov.Governamentais.domain.projeto.dados.DadosCadastroProjeto;
import br.gov.Governamentais.domain.projeto.dados.DadosEditarProjeto;
import br.gov.Governamentais.domain.projetoUsuario.VincularProjetoUsuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_projeto")
@Entity(name = "Projeto")
@Getter
@Setter
@NoArgsConstructor
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

    @Convert(converter = Converter.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "projeto_status")
    private Status status;

    //TODO: Fazer limitação de 0 a 100
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

    //TODO: colocar o relacionamento no usuário.
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<VincularProjetoUsuario> projetoUsuarios = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Historicos> hitoricos = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comentario> comentario = new ArrayList<>();

    public Projeto(DadosCadastroProjeto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.dataInicio = dados.dataInicio();
        this.ativo = true;
        this.status = Status.PLANEJADO_INICIAL;
        this.porcentagem = 0;
    }

    public Projeto(DadosEditarProjeto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.dataInicio = dados.dataInicio();
    }


}
