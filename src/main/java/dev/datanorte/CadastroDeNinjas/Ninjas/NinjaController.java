package dev.datanorte.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
@Tag(name = "Ninjas", description = "API para gerenciamento de ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(
            summary = "Mensagem de Boas Vindas",
            description = "Retorna uma mensagem simples de boas-vindas da API"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mensagem retornada com sucesso")
    })
    public String boasVindas() {
        return "Boas vindas";
    }

    // CREATE
    @PostMapping("/criar")
    @Operation(
            summary = "Criar Ninja",
            description = "Cria um novo ninja no sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição enviada")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninja) {
        NinjaDTO novoNinja = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome());
    }

    // READ - LISTAR TODOS
    @GetMapping("/listar")
    @Operation(
            summary = "Listar todos os Ninjas",
            description = "Retorna uma lista com todos os ninjas cadastrados"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    // READ - POR ID
    @GetMapping("/listar/{id}")
    @Operation(
            summary = "Buscar Ninja por ID",
            description = "Retorna um ninja específico baseado no ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> listarNinjasPorId(
            @Parameter(description = "ID do ninja que será buscado")
            @PathVariable Long id) {

        NinjaDTO ninja = ninjaService.listarNinjasPorId(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado");
        }
    }

    // UPDATE
    @PutMapping("/alterar/{id}")
    @Operation(
            summary = "Atualizar Ninja",
            description = "Atualiza os dados de um ninja existente"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<?> alterarNinjaPorId(
            @Parameter(description = "ID do ninja que será atualizado")
            @PathVariable Long id,
            @RequestBody NinjaDTO ninjaAlterado) {

        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaAlterado);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja não encontrado");
        }
    }

    // DELETE
    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar Ninja",
            description = "Remove um ninja do sistema pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<String> deletarNinjaPorId(
            @Parameter(description = "ID do ninja que será deletado")
            @PathVariable Long id) {

        if (ninjaService.deletarNinjaPorId(id)) {
            return ResponseEntity.ok("Ninja deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja não foi localizado");
        }
    }
}