package br.gov.Governamentais.comentario;

import br.gov.Governamentais.domain.comentario.Comentario;
import br.gov.Governamentais.domain.comentario.ComentarioRepository;
import br.gov.Governamentais.domain.comentario.dados.DadosCadastraComentario;
import br.gov.Governamentais.domain.projeto.Projeto;
import br.gov.Governamentais.domain.usuario.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ComentarioTeste {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Test
    @DisplayName("Deve salvar um comentario corretamente no banco")
    void deveSalvarUsuarioComSucesso() {

        UUID produtoUUID = UUID.fromString("899908fb-3d2d-40a6-8471-87983c82b162");
        UUID usuarioUUID = UUID.fromString("e9f6f145-4aab-45d4-afc4-f0e8518d9690");

        DadosCadastraComentario dados = new DadosCadastraComentario(
                produtoUUID, usuarioUUID,"Teste concluido com sucesso!"
        );

        Projeto projeto = new Projeto();
        projeto.setId(produtoUUID);

        Usuario usuario = new Usuario();
        usuario.setId(usuarioUUID);

        Comentario comentario = new Comentario();
        comentario.setProjeto(projeto);
        comentario.setUsuario(usuario);
        comentario.setComentario(dados.comentario());
        comentario.setDataComentario(LocalDateTime.now());
        comentario.setAtivo(true);

        Comentario salvo = comentarioRepository.save(comentario);

    }

}
