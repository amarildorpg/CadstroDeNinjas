package dev.datanorte.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class NinjaController {
    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Boas vindas";
    }
    //Adicionar ninja (CREATE)

    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja Criado";
    }

    //Mostrar todos os ninjas (READ)

    @GetMapping("/todos")
    public String todosOsNinjas() {
        return "Todos os Ninjas";
    }

    //Procurar Ninja por ID (READ)

    @GetMapping("/todosID")
    public String todosOsNinjasPorId() {
        return "O ninja escolhido por ID";
    }
    //Alterar dados (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjaPorId() {
        return "O ninja escolhido por ID foi alterado";
    }

    //Deletar ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorId() {
        return "O ninja escolhido por ID foi deletado";
    }

}
