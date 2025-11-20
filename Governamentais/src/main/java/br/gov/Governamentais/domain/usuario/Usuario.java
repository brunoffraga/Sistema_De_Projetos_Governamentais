package br.gov.Governamentais.domain.usuario;

import br.gov.Governamentais.domain.comentario.Comentario;
import br.gov.Governamentais.domain.historico.Historicos;
import br.gov.Governamentais.domain.projetoUsuario.VincularProjetoUsuario;
import br.gov.Governamentais.domain.usuario.dados.DadosCadastraUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            //essa extensão garante que os identificadores sejam globais e unicamente únicos
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "usuario_id", columnDefinition = "RAW(16)")
    private UUID id;

    @Column(name = "usuario_nome")
    private String nome;

    @Column(name = "usuario_email")
    private String email;

    @Column(name = "usuario_ativo", nullable = false)
    private boolean ativo = true;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VincularProjetoUsuario> projetoUsuarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Historicos> hitoricos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios = new ArrayList<>();

    public Usuario(DadosCadastraUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.ativo = true;
    }


    public void Usuario(DadosCadastraUsuario dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
    }

}