package br.gov.Governamentais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GovernamentaisApplication {


    //TODO: Otimizar a DadosListaProjetoSemDescricao, para pegar no banco só o que pediu.
    //TODO: Otimizar a DadosListaUsuario, para só aparecer o nome.
    //TODO: tirar usuário do projeto-selecionado.
    //TODO: resolver a data no comentário e no histórico.
    //TODO: colocar em ordem o js.
    //TODO: Otimizar o get para passar só o que está pedindo no oracle.
    //Para acessar o swagger acesse: http://localhost:8080/swagger-ui/index.html#/
	public static void main(String[] args) {
        SpringApplication.run(GovernamentaisApplication.class, args);
	}

}
