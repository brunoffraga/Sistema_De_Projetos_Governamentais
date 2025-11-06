package br.gov.Governamentais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/criar-projeto")
    public String criarProjeto(){
        return "criarprojeto";
    }

}
