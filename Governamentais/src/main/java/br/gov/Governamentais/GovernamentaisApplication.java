package br.gov.Governamentais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class GovernamentaisApplication {


    //TODO: melhorar a DadosListaProjetoSemDescricao, para pegar no banco só o que pediu.
    //Para acessar o swagger acesse: http://localhost:8080/swagger-ui/index.html#/

	public static void main(String[] args) {
        SpringApplication.run(GovernamentaisApplication.class, args);
	}

}
