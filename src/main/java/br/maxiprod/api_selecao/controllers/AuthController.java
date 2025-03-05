package br.maxiprod.api_selecao.controllers;

import br.maxiprod.api_selecao.dto.JWTAuthResponse;
import br.maxiprod.api_selecao.dto.RegistroRequest;
import br.maxiprod.api_selecao.dto.LoginRequest;
import br.maxiprod.api_selecao.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação", description = "Endpoints para login e registro de usuários")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Realiza login", description = "Autentica o usuário e retorna um token JWT.")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/registro")
    @Operation(summary = "Registra um novo usuário", description = "Cria uma nova conta de usuário.")
    public ResponseEntity<String> registro(@RequestBody RegistroRequest request) {
        String response = authService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
