package br.gov.Governamentais.controller;

import br.gov.Governamentais.domain.comentario.dados.DadosListaComentario;
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
@RequestMapping("/api/projeto")
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
                .body(new DadosDetalhamentoProjeto(projeto));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DadosDetalhamentoProjeto> ativarOuDesativar (
            @PathVariable UUID id,
            @RequestParam Boolean ativo){

        var projeto = service.ativarOuDesativarProjeto(id, ativo);

        return ResponseEntity.ok(new DadosDetalhamentoProjeto(projeto));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizacao(
            @PathVariable UUID id,
            @RequestBody @Valid DadosEditarProjeto dados,
            UriComponentsBuilder uriComponentsBuilder){
        var projeto = service.atualiza(id, dados);

        return ResponseEntity.ok(new DadosDetalhamentoProjeto(projeto));
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

    @Operation(summary = "Lista projetos sem comentario")
    @GetMapping("/semDescricao")
    public ResponseEntity<Page<DadosListaProjetoSemDescricao>> listarPorStatusSemTexto (
            @RequestParam(required = false) Boolean ativo,
            @ParameterObject
            @PageableDefault(size = 10, sort = {"id"}) Pageable pageable) {

        var projeto = service.listarPorStatusSemTexto(ativo, pageable);
        return ResponseEntity.ok(projeto);
    }

    @Operation(summary = "Lista só com nome")
    @GetMapping("/nome")
    public ResponseEntity<Page<DadosListaProjetoNome>> listarPorStatusNome (
            @RequestParam(required = false) Boolean ativo,
            @ParameterObject
            @PageableDefault(size = 40, sort = {"id"}) Pageable pageable) {

        var projeto = service.listarPorStatusNome(ativo, pageable);
        return ResponseEntity.ok(projeto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListaProjeto> projetoId (@PathVariable UUID id) {
        var projeto = service.projetoId(id);
        return ResponseEntity.ok(projeto);
    }

    @Operation(summary = "Listar projetos")
    @GetMapping("/comentarios")
    public ResponseEntity<Page<DadosListaComentario>> listaProjetoComentarios (
            @RequestParam(required = false) Boolean ativo,
            @RequestParam UUID projetoId,
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable) {

        var projeto = service.listaProjetoComentarios(ativo, projetoId, pageable);
        return ResponseEntity.ok(projeto);
    }

}
