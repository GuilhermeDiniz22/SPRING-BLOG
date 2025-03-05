package br.maxiprod.api_selecao.controllers;

import java.util.List;

import br.maxiprod.api_selecao.dto.PerguntaRequest;
import br.maxiprod.api_selecao.dto.PerguntaResponse;
import br.maxiprod.api_selecao.dto.RespostaRequest;
import br.maxiprod.api_selecao.service.PerguntaService;
import io.swagger.v3.oas.annotations.Operation;
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
        public ResponseEntity<String> novaPergunta(@RequestBody PerguntaRequest request) {
                String retorno = perguntaService.addPergunta(request);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @PostMapping("/{perguntaId}")
        @Operation(summary = "Adiciona uma resposta a uma pergunta", description = "Registra uma resposta para a pergunta especificada.")
        public ResponseEntity<String> novaResposta(@PathVariable Long perguntaId,
                        @RequestBody RespostaRequest request) {
                String retorno = perguntaService.addResposta_Pergunta(perguntaId, request);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @PutMapping("/{perguntaId}")
        @Operation(summary = "Atualiza uma pergunta", description = "Modifica os dados de uma pergunta existente.")
        public ResponseEntity<PerguntaResponse> updatePergunta(@PathVariable Long perguntaId,
                        @RequestBody PerguntaRequest request) {
                PerguntaResponse retorno = perguntaService.updatePergunta(request, perguntaId);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @GetMapping
        @Operation(summary = "Busca perguntas por título", description = "Retorna uma lista de perguntas que contenham o título especificado.")
        public ResponseEntity<List<PerguntaResponse>> getPerguntasPorTitulo(
                        @RequestParam(required = false, defaultValue = "") String titulo) {
                List<PerguntaResponse> retorno = perguntaService.getPerguntasByTitulo(titulo);
                return ResponseEntity.ok(retorno);
        }

        @GetMapping("{perguntaId}")
        @Operation(summary = "Obtém uma pergunta por ID", description = "Retorna os detalhes de uma pergunta específica.")
        public ResponseEntity<PerguntaResponse> getPerguntaById(@PathVariable Long perguntaId) {
                PerguntaResponse retorno = perguntaService.getPerguntaById(perguntaId);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @DeleteMapping("{perguntaId}")
        @Operation(summary = "Exclui uma pergunta", description = "Remove uma pergunta do sistema pelo ID informado.")
        public ResponseEntity<String> deletePerguntaById(@PathVariable Long perguntaId) {
                String retorno = perguntaService.deletePergunta(perguntaId);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }
}
