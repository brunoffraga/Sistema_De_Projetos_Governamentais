package br.gov.Governamentais.controller;

import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.projeto.dados.DadosCadastroProjeto;
import br.gov.Governamentais.domain.projeto.dados.DadosDetalhamentoProjeto;
import br.gov.Governamentais.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

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

    @PostMapping("/{id}/status")
    public ResponseEntity<DadosDetalhamentoProjeto> ativarOuDesativar (
            @PathVariable UUID id,
            @RequestParam Boolean ativo){

        var projeto = service.ativarOuDesativarProjeto(id, ativo);

        return ResponseEntity.ok(new DadosDetalhamentoProjeto(projeto));
    }

}
