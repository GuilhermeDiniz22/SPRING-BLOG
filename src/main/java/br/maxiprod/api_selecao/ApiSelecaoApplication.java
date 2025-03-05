package br.maxiprod.api_selecao;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(servers = {
		@Server(url = "/", description = "Uma API para um blog de perguntas e respostas") })
public class ApiSelecaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSelecaoApplication.class, args);
	}

}
