package br.gov.Governamentais.domain.projeto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    Page<Projeto> findAllByAtivo(Boolean ativo, Pageable pageable);

}
