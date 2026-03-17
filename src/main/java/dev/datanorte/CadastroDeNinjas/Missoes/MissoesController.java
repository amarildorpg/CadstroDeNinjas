package dev.datanorte.CadastroDeNinjas.Missoes;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissoesController {
    private MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        return ResponseEntity.ok(missoesService.listarMissoes());
    }
    @PostMapping("/criar")
    public ResponseEntity<MissoesDTO> criarMissao(@RequestBody MissoesDTO missaoDTO) {
        return ResponseEntity.ok(missoesService.criarMissao(missaoDTO));
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<MissoesDTO> alterarMissao(
            @PathVariable Long id,
            @RequestBody MissoesDTO missaoDTO) {

        return ResponseEntity.ok(missoesService.atualizarMissao(id, missaoDTO));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id) {

        missoesService.deletarMissaoPorId(id);
        return ResponseEntity.ok("Missão deletada com sucesso");
    }


}
