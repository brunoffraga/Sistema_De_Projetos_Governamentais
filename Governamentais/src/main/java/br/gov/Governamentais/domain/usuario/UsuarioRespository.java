package br.gov.Governamentais.domain.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRespository extends JpaRepository<Usuario, UUID> {

    Page<Usuario> findAllByAtivoTrue(Pageable pageable);

    Page<Usuario> findAllByAtivoFalse(Pageable pageable);

}
