package br.gov.Governamentais.domain.projeto;

import br.gov.Governamentais.domain.projeto.dados.DadosListaProjetoNome;
import br.gov.Governamentais.domain.projeto.dados.DadosListaProjetoSemDescricao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    Page<Projeto> findAllByAtivo(Boolean ativo, Pageable pageable);

    @Query("""
        SELECT new br.gov.Governamentais.domain.projeto.dados.DadosListaProjetoNome (
            p.id,
            p.nome
        )
        FROM Projeto p
        WHERE (:ativo IS NULL OR p.ativo = :ativo)
    """)
    Page<DadosListaProjetoNome> PesquisaRetornaIdNome(
            @Param("ativo") Boolean ativo,
            Pageable pageable
    );

    @Query("""
        SELECT new br.gov.Governamentais.domain.projeto.dados.DadosListaProjetoSemDescricao (
            p.id,
            p.nome,
            p.status,
            p.porcentagem,
            p.dataInicio
        )
        FROM Projeto p
        WHERE (:ativo IS NULL OR p.ativo = :ativo)
    """)
    Page<DadosListaProjetoSemDescricao> PesquisaRetornaSemComentario(
            @Param("ativo") Boolean ativo,
            Pageable pageable
    );



}
