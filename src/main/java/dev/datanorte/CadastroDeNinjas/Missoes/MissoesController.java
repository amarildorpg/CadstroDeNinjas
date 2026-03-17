package dev.datanorte.CadastroDeNinjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/missoes")
@Tag(name = "Missões", description = "API para gerenciamento de missões dos ninjas")
public class MissoesController {

    private final MissoesService missoesService;

    public MissoesController(MissoesService missoesService) {
        this.missoesService = missoesService;
    }

    // LISTAR
    @GetMapping("/listar")
    @Operation(
            summary = "Listar missões",
            description = "Retorna todas as missões cadastradas no sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de missões retornada com sucesso",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))
            )
    })
    public ResponseEntity<List<MissoesDTO>> listarMissoes() {
        return ResponseEntity.ok(missoesService.listarMissoes());
    }

    // READ - POR ID
    @GetMapping("/listar/{id}")
    @Operation(
            summary = "Buscar Missão por ID",
            description = "Retorna uma Missão específico baseado no ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<?> listarMissoesPorId(
            @Parameter(description = "ID da missão que será buscada")
            @PathVariable Long id) {

        MissoesDTO ninja = missoesService.listarMissoesPorId(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão não encontrada");
        }
    }

    // CRIAR
    @PostMapping("/criar")
    @Operation(
            summary = "Criar missão",
            description = "Cria uma nova missão no sistema"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Missão criada com sucesso",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))
            ),
            @ApiResponse(responseCode = "400", description = "Erro na requisição enviada")
    })
    public ResponseEntity<MissoesDTO> criarMissao(

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto Missão que será criado",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))
            )
            @RequestBody MissoesDTO missaoDTO) {

        return ResponseEntity.ok(missoesService.criarMissao(missaoDTO));
    }

    // ALTERAR
    @PutMapping("/alterar/{id}")
    @Operation(
            summary = "Atualizar missão",
            description = "Atualiza os dados de uma missão existente"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Missão atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<MissoesDTO> alterarMissao(

            @Parameter(description = "ID da missão que será atualizada", example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto missão com dados atualizados",
                    required = true,
                    content = @Content(schema = @Schema(implementation = MissoesDTO.class))
            )
            @RequestBody MissoesDTO missaoDTO) {

        return ResponseEntity.ok(missoesService.atualizarMissao(id, missaoDTO));
    }

    // DELETAR
    @DeleteMapping("/deletar/{id}")
    @Operation(
            summary = "Deletar missão",
            description = "Remove uma missão do sistema pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada")
    })
    public ResponseEntity<String> deletarMissao(

            @Parameter(description = "ID da missão que será deletada", example = "1")
            @PathVariable Long id) {

        if(missoesService.deletarMissaoPorId(id)){
            return ResponseEntity.ok("Missão deletada");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Missão não encontrada");
    }
}