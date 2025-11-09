package br.gov.Governamentais.domain.comentarios;

import br.gov.Governamentais.domain.comentarios.dados.DadosCadastraComentario;
import br.gov.Governamentais.domain.comentarios.dados.DadosEditarComentario;
import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "tb_comentarios")
@Entity(name = "Comentarios")
@Data //TODO: FAZER NAS OUTRAS ENTIDADES.
@EqualsAndHashCode(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comentario_seq")
    @SequenceGenerator(name = "comentario_seq", sequenceName = "SEQ_COMENTARIO", allocationSize = 1)
    @Column(name = "comentarios_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "projeto_projeto_id", referencedColumnName = "projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "usuario_usuario_id", referencedColumnName = "usuario_id")
    private Usuario usuario;

    //TODO: Limitar o varchar de 4000 e mínimo de 10fazer regra no DTO
    @Column(name = "comentarios_descricao")
    private String comentario;

    @Column(name = "comentario_data_comentario")
    private LocalDateTime dataComentario;

    @Column(name = "comentario_ativo")
    private Boolean ativo;

}
