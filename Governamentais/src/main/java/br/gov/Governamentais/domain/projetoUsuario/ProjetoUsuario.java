package br.gov.Governamentais.domain.projetoUsuario;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Table(name = "tb_projeto_usuario")
@Entity(name = "ProjetoUsuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ProjetoUsuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            //essa extensão garante que os identificadores sejam globais e unicamente únicos
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "tb_projeto_usuario_id", columnDefinition = "RAW(16)")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "tb_projeto_projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "tb_usuario_usuario_id")
    private Usuario usuario;

    @Column(name = "projeto_usuario_ativo")
    private boolean ativo = true;
}
