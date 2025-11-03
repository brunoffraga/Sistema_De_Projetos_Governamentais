package br.gov.Governamentais.controller;

import br.gov.Governamentais.domain.usuario.Usuario;
import br.gov.Governamentais.domain.usuario.UsuarioRespository;
import br.gov.Governamentais.domain.usuario.dados.DadosDetalhamentoUsuario;
import br.gov.Governamentais.domain.usuario.dados.DadosListaUsuairo;
import br.gov.Governamentais.domain.usuario.dados.DadosUsuarioCadastra;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("usuario")
public class ControllerUsuairos {

    @Autowired
    UsuarioRespository respository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastroUsuario(@RequestBody @Valid DadosUsuarioCadastra dados, UriComponentsBuilder uriComponentsBuilder){
        var usuario = new Usuario(dados);
        respository.save(usuario);

        var uri = uriComponentsBuilder.path("/cartao/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body("Cadastro do usuário feito com sucesso. " +
                new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListaUsuairo>> lista(
            @ParameterObject @PageableDefault(size = 10, sort = {"id"})Pageable pageable){
        var page = respository.findAllByAtivoTrue(pageable)
                .map(DadosListaUsuairo::new);
        return ResponseEntity.ok(page);
    }

}
