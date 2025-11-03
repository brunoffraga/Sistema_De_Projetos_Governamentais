package br.gov.Governamentais.domain.usuario;

import br.gov.Governamentais.domain.comentarios.Comentarios;
import br.gov.Governamentais.domain.historico.Hitoricos;
import br.gov.Governamentais.domain.projetoUsuario.ProjetoUsuario;
import br.gov.Governamentais.domain.usuario.dados.DadosCadastraUsuario;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name = "tb_usuario")
@Entity(name = "Usuario")
@NoArgsConstructor
@Setter
@Getter
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
    private List<ProjetoUsuario> projetoUsuarios = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Hitoricos> hitoricos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentarios> comentarios = new ArrayList<>();

    public Usuario(DadosCadastraUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.ativo = true;
    }

}