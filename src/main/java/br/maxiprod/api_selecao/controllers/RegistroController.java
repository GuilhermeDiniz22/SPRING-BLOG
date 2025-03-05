package br.maxiprod.api_selecao.controllers;

import br.com.vendas_api.dto.RegistroDtoSaida;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/registros")
@Tag(
        name = "API Registros",
        description = "API responsável pelos serviços da entidade Registro no sistema."
)
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("{id}")
    @Operation(
            summary = "Obter detalhes de um registro",
            description = "Retorna os detalhes de um registro específico identificado pelo ID fornecido."
    )
    public ResponseEntity<RegistroDtoSaida> getRegistroById(@PathVariable Long id) {
        RegistroDtoSaida response = registroService.getRegistroById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/socio/{id}")
    @Operation(
            summary = "Obter registros de um sócio",
            description = "Retorna uma lista de registros associados a um sócio específico, identificado pelo ID."
    )
    public ResponseEntity<List<RegistroDtoSaida>> getRegistroBySocioId(@PathVariable Long id) {
        List<RegistroDtoSaida> response = registroService.getRegistrosBySocioId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os registros",
            description = "Retorna uma lista com todos os registros cadastrados no sistema."
    )
    public ResponseEntity<List<RegistroDtoSaida>> getRegistros() {
        List<RegistroDtoSaida> response = registroService.getAllRegistros();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
