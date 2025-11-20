package br.gov.Governamentais.controller;

import br.gov.Governamentais.domain.comentario.dados.DadosCadastraComentario;
import br.gov.Governamentais.domain.comentario.dados.DadosDetalhamentoComentario;
import br.gov.Governamentais.domain.comentario.dados.DadosEditarComentario;
import br.gov.Governamentais.domain.comentario.dados.DadosListaComentario;
import br.gov.Governamentais.service.ComentarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/comentario")
public class ControllerComentario {

    @Autowired
    private ComentarioService service;

    @PostMapping
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastraComentario dados,
            UriComponentsBuilder uriComponentsBuilder){

        System.out.println("aqui");
        System.out.println(dados);
        var comentario = service.salvar(dados);

        var uri = uriComponentsBuilder
                .path("/comentario/{id}")
                .buildAndExpand(comentario.getId()).toUri();

        return ResponseEntity
                .created(uri)
                .body(new DadosDetalhamentoComentario(comentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizacao(
            @PathVariable Long id,
            @RequestBody @Valid DadosEditarComentario dados,
            UriComponentsBuilder uriComponentsBuilder){

        var comentario = service.atualizar(dados);

        return ResponseEntity.ok(comentario);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<DadosDetalhamentoComentario> ativarOuDesativarUsuario (
            @PathVariable Long id,
            @RequestParam Boolean ativo){

        var comentario = service.ativarOuDesativarComentario(id, ativo);

        return ResponseEntity.ok(new DadosDetalhamentoComentario(comentario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaComentario>> listarPorStatus (
            @RequestParam(required = false) Boolean ativo,
            @PageableDefault(size = 20, sort = {"id"}) Pageable pageable){

        var comentario = service.listarPorStatus(ativo, pageable);
        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListaComentario> usuarioId (@PathVariable Long id){
        var comentario = service.comentarioId(id);
        return ResponseEntity.ok(comentario);
    }
}
