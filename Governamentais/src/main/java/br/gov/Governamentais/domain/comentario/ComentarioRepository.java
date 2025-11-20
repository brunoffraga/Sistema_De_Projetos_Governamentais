package br.gov.Governamentais.domain.comentario;

import br.gov.Governamentais.domain.comentario.dados.DadosListaComentario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    Page<Comentario> findAllByAtivo(Boolean ativo, Pageable pageable);

    @Query("""
        SELECT new br.gov.Governamentais.domain.comentario.dados.DadosListaComentario(
            c.id,
            u.nome,
            c.descricao,
            c.dataComentario
        )
        FROM Comentario c
        JOIN c.usuario u
        WHERE (:ativo IS NULL OR c.ativo = :ativo)
          AND c.projeto.id = :projetoId
        """)
    Page<DadosListaComentario> findAllByAtivo(
            @Param("ativo") Boolean ativo,
            @Param("projetoId") UUID projetoId,
            Pageable pageable
    );

}
