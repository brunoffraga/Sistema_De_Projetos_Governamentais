package br.gov.Governamentais.domain.projeto;


import br.gov.Governamentais.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<Projeto, UUID> {

    Page<Usuario> findAllByAtivoTrue(Pageable pageable);

    Page<Usuario> findAllByAtivoFalse(Pageable pageable);

}
