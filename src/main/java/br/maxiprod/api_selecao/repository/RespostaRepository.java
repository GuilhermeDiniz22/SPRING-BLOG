package br.maxiprod.api_selecao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.maxiprod.api_selecao.models.Resposta;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    List<Resposta> findByPerguntaIdAndDescricaoContainingIgnoreCase(Long perguntaId, String descricao);

    List<Resposta> findByPerguntaIdOrderByVotosDesc(Long perguntaId);

}
