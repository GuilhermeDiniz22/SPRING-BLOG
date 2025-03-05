package br.maxiprod.api_selecao.controllers;

import java.util.List;

import br.maxiprod.api_selecao.dto.RespostaRequest;
import br.maxiprod.api_selecao.dto.RespostaResponse;
import br.maxiprod.api_selecao.service.RespostaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("respostas")
@Tag(name = "Respostas", description = "Endpoints para gerenciar respostas às perguntas")
public class RespostaController {

        @Autowired
        private RespostaService respostaService;

        @PutMapping("/{perguntaId}/{respostaId}")
        @Operation(summary = "Atualiza uma resposta", description = "Atualiza os dados de uma resposta existente para a pergunta especificada.")
        public ResponseEntity<RespostaResponse> updateRespostaById(
                        @PathVariable Long perguntaId,
                        @PathVariable Long respostaId,
                        @RequestBody RespostaRequest request) {
                RespostaResponse retorno = respostaService.updateResposta(request, perguntaId, respostaId);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @DeleteMapping("/{perguntaId}/{respostaId}")
        @Operation(summary = "Deleta uma resposta", description = "Remove uma resposta para a pergunta especificada.")
        public ResponseEntity<String> deleteRespostaById(@PathVariable Long perguntaId, @PathVariable Long respostaId) {
                String retorno = respostaService.deleteResposta(perguntaId, respostaId);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @GetMapping("/{perguntaId}/{respostaId}")
        @Operation(summary = "Obtém uma resposta por ID", description = "Retorna os detalhes de uma resposta específica para uma pergunta.")
        public ResponseEntity<RespostaResponse> getRespostaById(@PathVariable Long perguntaId,
                        @PathVariable Long respostaId) {
                RespostaResponse retorno = respostaService.getRespostaById(perguntaId, respostaId);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @GetMapping("/por-descricao/{perguntaId}")
        @Operation(summary = "Busca respostas por descrição", description = "Retorna uma lista de respostas para uma pergunta com base na descrição fornecida.")
        public ResponseEntity<List<RespostaResponse>> getRespostasByDescricao(@PathVariable Long perguntaId,
                        @RequestParam String descricao) {
                List<RespostaResponse> retorno = respostaService.getRespostasByDescricao(perguntaId, descricao);
                return new ResponseEntity<>(retorno, HttpStatus.OK);
        }

        @GetMapping("/{perguntaId}/{respostaId}/like")
        @Operation(summary = "Vota positivamente em uma resposta", description = "Aumenta a quantidade de votos positivos de uma resposta.")
        public ResponseEntity<Void> votarPositivo(@PathVariable Long perguntaId, @PathVariable Long respostaId) {
                respostaService.votarPositivo(perguntaId, respostaId);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping("/{perguntaId}/{respostaId}/dislike")
        @Operation(summary = "Vota negativamente em uma resposta", description = "Aumenta a quantidade de votos negativos de uma resposta.")
        public ResponseEntity<Void> votarNegativo(@PathVariable Long perguntaId, @PathVariable Long respostaId) {
                respostaService.votarNegativo(perguntaId, respostaId);
                return new ResponseEntity<>(HttpStatus.OK);
        }
}
