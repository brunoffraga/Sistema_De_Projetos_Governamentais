package br.gov.Governamentais.domain.projeto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    Page<Projeto> findAllByAtivoTrue(Pageable pageable);

    Page<Projeto> findAllByAtivoFalse(Pageable pageable);

}
