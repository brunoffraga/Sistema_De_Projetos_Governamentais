package br.gov.Governamentais.service;

import br.gov.Governamentais.domain.historico.Historicos;
import br.gov.Governamentais.domain.historico.HistoricosRepository;
import br.gov.Governamentais.domain.historico.dados.DadosCadastraHistoricos;
import br.gov.Governamentais.domain.historico.dados.DadosEditarHistoricos;
import br.gov.Governamentais.domain.historico.dados.DadosListaHistoricos;
import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.ProjetoRepository;
import br.gov.Governamentais.domain.usuario.Usuario;
import br.gov.Governamentais.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class HistoricosServer {

    @Autowired
    private HistoricosRepository repository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Historicos salvar(DadosCadastraHistoricos dados){
        var historicos = new Historicos();

        Projeto projeto = projetoRepository.findById(dados.projetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        historicos.setProjeto(projeto);
        historicos.setUsuario(usuario);
        historicos.setTexto(dados.texto());

        repository.save(historicos);

        return historicos;
    }

    @Transactional
    public Historicos atualizar(DadosEditarHistoricos dados){
        var historicos = new Historicos();

        Projeto projeto = projetoRepository.findById(dados.projetoId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        historicos.setProjeto(projeto);
        historicos.setUsuario(usuario);
        historicos.setTexto(dados.texto());

        repository.save(historicos);

        return historicos;
    }

    @Transactional
    public Historicos ativarOuDesativarHistoricos(UUID id, Boolean ativo){
        var historicos = repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Historicos não encontrado com o id" + id));

        if (ativo != null) {
            historicos.setAtivo(ativo);
        }

        return historicos;
    }

    @Transactional(readOnly = true)
    public Page<DadosListaHistoricos> listarPorStatus(Boolean ativo, Pageable pageable){
        if(ativo == null)
            return repository.findAll(pageable).map(DadosListaHistoricos::new);

        if (Boolean.TRUE.equals(ativo))
            return repository.findAllByAtivoTrue(pageable).map(DadosListaHistoricos::new);

        return  repository.findAllByAtivoFalse(pageable)
                .map(DadosListaHistoricos::new);
    }

    @Transactional(readOnly = true)
    public DadosListaHistoricos historicosId(@PathVariable UUID id){
        var historicos = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Historicos não encontrado com o id: " + id));
        return new DadosListaHistoricos(historicos);
    }

}
