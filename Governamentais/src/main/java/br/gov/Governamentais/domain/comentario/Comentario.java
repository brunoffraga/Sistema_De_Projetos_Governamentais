package br.gov.Governamentais.domain.comentario;


import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "tb_comentario")
@Entity(name = "Comentario")
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_seq")
    @SequenceGenerator(name = "comentario_seq", sequenceName = "SEQ_COMENTARIO", allocationSize = 1)
    @Column(name = "comentario_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "projeto_projeto_id", referencedColumnName = "projeto_id")
    @JsonBackReference
    private Projeto projeto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_usuario_id", referencedColumnName = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    @Column(name = "comentario_descricao", length = 4000, nullable = false)
    private String descricao;

    @Column(name = "comentario_data")
    private LocalDateTime dataComentario;

    @Column(name = "comentario_ativo")
    private Boolean ativo;

    public Comentario(Usuario usuario, Long id, String comentario, LocalDateTime dataComentario) {
        this.usuario = usuario;
        this.id = id;
        this.descricao = getDescricao();
        this.dataComentario = dataComentario;
    }
}
