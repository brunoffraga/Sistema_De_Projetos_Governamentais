package br.gov.Governamentais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 *Acesse o projeto no http://localhost:8080/
 */
@SpringBootApplication
public class GovernamentaisApplication {

    //TODO: Otimizar a DadosListaProjetoSemDescricao, pnara pegar no baco só o que pediu.
    //TODO: Otimizar a DadosListaUsuario, para só aparecer o nome.
    //TODO: tirar usuário do projeto-selecionado.
    //TODO: resolver a data no comentário e no histórico.
    //TODO: colocar em ordem o js.
    //TODO: Otimizar o get para passar só o que está pedindo no oracle.
    //TODO: Colocar tudo em POO no js.
    //TODO: fazer um SUAP
    //TODO: Trocar o UUID para o 7
    //TODO: Terminar o comentário, verificar o que está acontecendo no Page, para passar mais comentários
    //Para acessar o swagger acesse: http://localhost:8080/swagger-ui/index.html#/
	public static void main(String[] args) {
        SpringApplication.run(GovernamentaisApplication.class, args);
	}

}
