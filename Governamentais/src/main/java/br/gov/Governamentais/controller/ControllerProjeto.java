package br.gov.Governamentais.controller;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.dados.*;
import br.gov.Governamentais.service.ProjetoService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/projeto")
public class ControllerProjeto {

    @Autowired
    private ProjetoService service;

    @PostMapping
    public ResponseEntity cadastro(
            @RequestBody @Valid DadosCadastroProjeto dados,
            UriComponentsBuilder uriComponentsBuilder){

        var projeto = service.salvar(dados);

        var uri = uriComponentsBuilder
                .path("/porjeto/{id}")
                .buildAndExpand(projeto.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoProjeto(new Projeto()));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DadosDetalhamentoProjeto> ativarOuDesativar (
            @PathVariable UUID id,
            @RequestParam Boolean ativo){

        var projeto = service.ativarOuDesativarProjeto(id, ativo);

        return ResponseEntity.ok(new DadosDetalhamentoProjeto(projeto));
    }

    @PutMapping
    public ResponseEntity atualizacao(
            @RequestBody @Valid DadosEditarProjeto dados,
            UriComponentsBuilder uriComponentsBuilder){

        var projeto = service.atualiza(dados);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar projetos")
    @GetMapping
    public ResponseEntity<Page<DadosListaProjeto>> listarPorStatus (
            @RequestParam(required = false) Boolean ativo,
            @ParameterObject
            @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {

        var projeto = service.listarPorStatus(ativo, pageable);
        return ResponseEntity.ok(projeto);
    }

    @Operation(summary = "Listar projetos")
    @GetMapping("/semDescricao")
    public ResponseEntity<Page<DadosListaProjetoSemDescricao>> listarPorStatusSemTexto (
            @RequestParam(required = false) Boolean ativo,
            @ParameterObject
            @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {

        var projeto = service.listarPorStatusSemTexto(ativo, pageable);
        return ResponseEntity.ok(projeto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListaProjeto> projetoId (@PathVariable UUID id) {
        var projeto = service.usuarioId(id);
        return ResponseEntity.ok(projeto);
    }

}
