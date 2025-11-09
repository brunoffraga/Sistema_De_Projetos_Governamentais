package br.gov.Governamentais.controller;

import br.gov.Governamentais.domain.usuario.dados.*;
import br.gov.Governamentais.service.UsuarioService;
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

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "http://localhost:63342")
public class ControllerUsuairos {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity cadastrar (
            @RequestBody @Valid DadosCadastraUsuario dados,
            UriComponentsBuilder uriComponentsBuilder){

        var usuario = service.salvar(dados);

        var uri = uriComponentsBuilder
                .path("/usuario/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity atualizacao (
            @RequestBody @Valid DadosEditarUsuario dados,
            UriComponentsBuilder uriComponentsBuilder){

        var usuario = service.atualizar(dados);

        return ResponseEntity.noContent().build();

    }

    @PostMapping("/{id}/status")
    public ResponseEntity<DadosDetalhamentoUsuario> ativarOuDesativarUsuario (
            @PathVariable UUID id,
            @RequestParam Boolean ativo){

        var usuario = service.ativarOuDesativarUsuario(id, ativo);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaUsuairo>> listarPorStatus (
            @RequestParam(required = false) Boolean ativo,
            @ParameterObject
            @PageableDefault(size = 10, sort = {"id"}) Pageable pageable){

        var usuario = service.listarPorStatus(ativo, pageable);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/nome")
    public ResponseEntity<Page<DadosListaUsuairoNome>> listarPorStatusNome (
            @RequestParam(required = false) Boolean ativo,
            @ParameterObject
            @PageableDefault(size = 10, sort = {"id"}) Pageable pageable){

        var usuario = service.listarPorStatusNome(ativo, pageable);
        return ResponseEntity.ok(usuario);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosListaUsuairo> usuarioId (@PathVariable UUID id){
        var usuario = service.usuarioId(id);
        return ResponseEntity.ok(usuario);
    }

}
