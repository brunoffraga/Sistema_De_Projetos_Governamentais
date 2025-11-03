package br.gov.Governamentais;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GovernamentaisApplication {


    //Para acessar o swagger acesse: http://localhost:8080/swagger-ui/index.html#/
	public static void main(String[] args) {
        SpringApplication.run(GovernamentaisApplication.class, args);
	}

}
