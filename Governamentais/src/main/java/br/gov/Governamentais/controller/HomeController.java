package br.gov.Governamentais.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

/*
 * Para funcionar o Thymeleaf
 */

@Controller
public class HomeController {

    @GetMapping("/teste")
    public String teste(Model model) {
        return "teste";
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    /*
     * Projeto
     */
    @GetMapping("/projeto/cadastra")
    public String projeto(Model model) {
        return "projeto/cadastra-projeto";
    }

    @GetMapping("/projeto/edita")
    public String projetotEdita(Model model) {
        return "projeto/edita-projeto";
    }

    @GetMapping("/projeto/lista")
    public String projetolista(Model model) {
        return "projeto/lista-projeto";
    }

    @GetMapping("/projeto/selecionado")
    public String projetoSeleciona(Model model) {
        return "projeto/selecionado-projeto";
    }

    /*
     *  Comentario
     */
    @GetMapping("/projeto/comentario")
    public String projetoCadastraHistoricoComentario(Model model) {
        return "comentario/comentario";
    }

    /*
     *  Historico
     */
    @GetMapping("/projeto/historico")
    public String projetoHistoricoCadastra(Model model) {
        return "historico/historico";
    }

    /*
     *  Usuairo
     */
    @GetMapping("/usuario/cadastra")
    public String usuarioUsuarioCadastra(Model model) {
        return "usuario/cadastra-usuario";
    }

    @GetMapping("/usuario/edita")
    public String usuarioEdita(Model model) {
        return "usuario/edita-usuario";
    }

    @GetMapping("/usuario/lista")
    public String usuariolista(Model model) {
        return "usuario/lista-usuario";
    }

    @GetMapping("/usuario/selecionado")
    public String usuarioSeleciona(Model model) {
        return "usuario/selecionado-usuario";
    }

}
