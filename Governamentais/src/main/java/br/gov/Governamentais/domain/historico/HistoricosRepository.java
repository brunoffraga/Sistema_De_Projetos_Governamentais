package br.gov.Governamentais.domain.historico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoricosRepository extends JpaRepository<Historicos, Long> {

    Page<Historicos> findAllByAtivo(Boolean ativo, Pageable pageable);

}
