package br.maxiprod.api_selecao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.maxiprod.api_selecao.models.Pergunta;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {

    List<Pergunta> findByTituloContainingIgnoreCase(String titulo);
}
