package dev.datanorte.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missoes")
public class MissoesController {
    @GetMapping("/listar")
    public String listarMissoes() {return "Todas as Missões"; }
    @PostMapping("/criar")
    public String criarMissao() {
        return "A Missão foi criada com sucesso";
    }
    @PutMapping("/alterar")
    public String alterarMissao() {
        return "Missão alterada com sucesso";
    }
    @DeleteMapping("/deletar")
    public String deletarMissao() {
        return "A missão foi deletado com sucesso";
    }


}
