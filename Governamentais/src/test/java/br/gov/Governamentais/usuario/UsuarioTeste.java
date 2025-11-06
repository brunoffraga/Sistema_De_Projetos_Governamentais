package br.gov.Governamentais.usuario;

import br.gov.Governamentais.domain.usuario.Usuario;
import br.gov.Governamentais.domain.usuario.UsuarioRepository;
import br.gov.Governamentais.domain.usuario.dados.DadosCadastraUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UsuarioTeste {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    @DisplayName("Deve salvar um usuário corretamente no banco")
    void deveSalvarUsuarioComSucesso() {

        DadosCadastraUsuario dados = new DadosCadastraUsuario("Maria", "maria@email.com");
        Usuario usuario = new Usuario(dados);

        Usuario salvo = usuarioRepository.save(usuario);

        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Maria");
        assertThat(salvo.getEmail()).isEqualTo("maria@email.com");
        assertThat(salvo.isAtivo()).isTrue();
    }

    @Test
    @DisplayName("Deve atualizar apenas os campos não nulos")
    void deveAtualizarSomenteCamposNaoNulos() {

        Usuario usuario = new Usuario(new DadosCadastraUsuario("João", "joao@email.com"));
        usuarioRepository.save(usuario);

        usuario.Usuario(new DadosCadastraUsuario("João Atualizado", null));
        usuarioRepository.save(usuario);

        Optional<Usuario> atualizado = usuarioRepository.findById(usuario.getId());
        assertThat(atualizado).isPresent();
        assertThat(atualizado.get().getNome()).isEqualTo("João Atualizado");
        assertThat(atualizado.get().getEmail()).isEqualTo("joao@email.com"); // não alterou
    }
}
