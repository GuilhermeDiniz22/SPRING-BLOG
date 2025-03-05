package br.maxiprod.api_selecao.controllers;

import java.util.List;

import br.maxiprod.api_selecao.dto.PerguntaRequest;
import br.maxiprod.api_selecao.dto.PerguntaResponse;
import br.maxiprod.api_selecao.dto.RespostaRequest;
import br.maxiprod.api_selecao.service.PerguntaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("perguntas")
@Tag(name = "Perguntas", description = "Endpoints para gerenciar perguntas e respostas")
public class PerguntaController {

    @Autowired
    private PerguntaService perguntaService;

    @PostMapping
    @Operation(summary = "Cria uma nova pergunta", description = "Adiciona uma nova pergunta ao sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pergunta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na requisição", content = @Content)
    })
    public ResponseEntity<String> novaPergunta(@RequestBody PerguntaRequest request) {
        String retorno = perguntaService.addPergunta(request);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @PostMapping("/{perguntaId}")
    @Operation(summary = "Adiciona uma resposta a uma pergunta", description = "Registra uma resposta para a pergunta especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Resposta adicionada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pergunta não encontrada", content = @Content)
    })
    public ResponseEntity<String> novaResposta(@PathVariable Long perguntaId, @RequestBody RespostaRequest request) {
        String retorno = perguntaService.addResposta_Pergunta(perguntaId, request);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @PutMapping("/{perguntaId}")
    @Operation(summary = "Atualiza uma pergunta", description = "Modifica os dados de uma pergunta existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pergunta atualizada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerguntaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pergunta não encontrada", content = @Content)
    })
    public ResponseEntity<PerguntaResponse> updatePergunta(@PathVariable Long perguntaId,
                                                           @RequestBody PerguntaRequest request) {
        PerguntaResponse retorno = perguntaService.updatePergunta(request, perguntaId);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Busca perguntas por título", description = "Retorna uma lista de perguntas que contenham o título especificado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de perguntas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerguntaResponse.class)))
    })
    public ResponseEntity<List<PerguntaResponse>> getPerguntasPorTitulo(
            @RequestParam(required = false, defaultValue = "") String titulo) {
        List<PerguntaResponse> retorno = perguntaService.getPerguntasByTitulo(titulo);
        return ResponseEntity.ok(retorno);
    }

    @GetMapping("{perguntaId}")
    @Operation(summary = "Obtém uma pergunta por ID", description = "Retorna os detalhes de uma pergunta específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pergunta encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PerguntaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Pergunta não encontrada", content = @Content)
    })
    public ResponseEntity<PerguntaResponse> getPerguntaById(@PathVariable Long perguntaId) {
        PerguntaResponse retorno = perguntaService.getPerguntaById(perguntaId);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }

    @DeleteMapping("{perguntaId}")
    @Operation(summary = "Exclui uma pergunta", description = "Remove uma pergunta do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pergunta excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pergunta não encontrada", content = @Content)
    })
    public ResponseEntity<String> deletePerguntaById(@PathVariable Long perguntaId) {
        String retorno = perguntaService.deletePergunta(perguntaId);
        return new ResponseEntity<>(retorno, HttpStatus.OK);
    }
}
