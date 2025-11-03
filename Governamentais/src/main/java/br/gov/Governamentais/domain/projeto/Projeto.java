package br.gov.Governamentais.domain.projeto;

import br.gov.Governamentais.domain.comentarios.Comentarios;
import br.gov.Governamentais.domain.historico.Hitoricos;
import br.gov.Governamentais.domain.projeto.dados.DadosCadastroProjeto;
import br.gov.Governamentais.domain.projetoUsuario.ProjetoUsuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_projeto")
@Entity(name = "Projeto")
@NoArgsConstructor
@Getter
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
    @Column(name = "projeto_descricao")
    private String descricao;

    @Column(name = "projeto_status")
    private Status status;

    @Column(name = "projeto_progresso")
    private int progresso;

    @Column(name = "projeto_data_inicio")
    private LocalDate dataInicio;

    @Column(name = "projeto_data_final")
    private LocalDate dataFinal;

    @Column(name = "projeto_ativo")
    private boolean ativo = true;

    //      Relacionamento

    //TODO: colocar o relacionamento no usuairo.
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjetoUsuario> projetoUsuarios = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Hitoricos> hitoricos = new ArrayList<>();

    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Comentarios> comentarios = new ArrayList<>();

    //TODO: fazer dto
    public Projeto(DadosCadastroProjeto dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.dataInicio = dados.dataInicio();
        this.ativo = true;
        //this.usuario
    }

    public Integer porcentagemProgresso(Status identificandoStatus){
        if(identificandoStatus.equals("PLANEJADO")){
            return 25;
        }

        if(identificandoStatus.equals("EM_ANDAMENTO")){
            return 50;
        }

        if(identificandoStatus.equals("CONCLUÍDO")){
            return 100;
        }
        return getProgresso();
    }
}
