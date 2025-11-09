package br.gov.Governamentais.service;

import br.gov.Governamentais.domain.usuario.Usuario;
import br.gov.Governamentais.domain.usuario.UsuarioRepository;
import br.gov.Governamentais.domain.usuario.dados.DadosCadastraUsuario;
import br.gov.Governamentais.domain.usuario.dados.DadosEditarUsuario;
import br.gov.Governamentais.domain.usuario.dados.DadosListaUsuairo;
import br.gov.Governamentais.domain.usuario.dados.DadosListaUsuairoNome;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(DadosCadastraUsuario request){
        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());

        repository.save(usuario);

        return usuario;
    }

    @Transactional
    public Usuario atualizar(DadosEditarUsuario request){
        var usuario = new Usuario();
        usuario.setId(request.id());
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());

        repository.save(usuario);

        return usuario;
    }

    @Transactional
    public Usuario ativarOuDesativarUsuario(UUID id, Boolean ativo){
        var usuario = repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Usuário não encontrado com o id" + id));

        if (ativo != null) {
            usuario.setAtivo(ativo);
        }

        return usuario;
    }

    @Transactional(readOnly = true)
    public Page<DadosListaUsuairo> listarPorStatus(Boolean ativo, Pageable pageable){

        return  repository.findAllByAtivo(ativo, pageable)
                .map(DadosListaUsuairo::new);
    }

    @Transactional(readOnly = true)
    public Page<DadosListaUsuairoNome> listarPorStatusNome(Boolean ativo, Pageable pageable){

        return  repository.findAllByAtivo(ativo, pageable)
                .map(DadosListaUsuairoNome::new);
    }

    @Transactional(readOnly = true)
    public DadosListaUsuairo usuarioId(@PathVariable UUID id){
        var usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o id: " + id));
        return new DadosListaUsuairo(usuario);
    }

}
