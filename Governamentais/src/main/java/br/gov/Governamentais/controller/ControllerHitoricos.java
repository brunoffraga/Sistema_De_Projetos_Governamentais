package br.gov.Governamentais.controller;

import br.gov.Governamentais.domain.historico.dados.DadosCadastraHistoricos;
import br.gov.Governamentais.domain.historico.dados.DadosDetalhamentoHistoricos;
import br.gov.Governamentais.domain.historico.dados.DadosEditarHistoricos;
import br.gov.Governamentais.domain.historico.dados.DadosListaHistoricos;
import br.gov.Governamentais.service.HistoricosServer;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost:63342") //Permite requisições do seu frontend
@RestController
@RequestMapping("/historicos")
public class ControllerHitoricos {

    @Autowired
    private HistoricosServer service;

    @PostMapping
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastraHistoricos dados,
            UriComponentsBuilder uriComponentsBuilder){

        var historicos = service.salvar(dados);

        var uri = uriComponentsBuilder
                .path("/historicos/{id}")
                .buildAndExpand(historicos.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoHistoricos(historicos));
    }

    @PutMapping
    public ResponseEntity atualizacao(
            @RequestBody @Valid DadosEditarHistoricos dados,
            UriComponentsBuilder uriComponentsBuilder){

        var historicos = service.atualizar(dados);

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/{id}/status")
    public ResponseEntity<DadosDetalhamentoHistoricos> ativarOuDesativarUsuario (
            @PathVariable UUID id,
            @RequestParam Boolean ativo){

        var historicos = service.ativarOuDesativarHistoricos(id, ativo);

        return ResponseEntity.ok(new DadosDetalhamentoHistoricos(historicos));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListaHistoricos>> listarPorStatus (
            @RequestParam(required = false) Boolean ativo,
            @ParameterObject
            @PageableDefault(size = 10, sort = {"id"}) Pageable pageable){

        var historicos = service.listarPorStatus(ativo, pageable);
        return ResponseEntity.ok(historicos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosListaHistoricos> usuarioId (@PathVariable UUID id){
        var historicos = service.historicosId(id);
        return ResponseEntity.ok(historicos);
    }
}
