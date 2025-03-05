package br.maxiprod.api_selecao.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.maxiprod.api_selecao.dto.PerguntaRequest;
import br.maxiprod.api_selecao.dto.PerguntaResponse;
import br.maxiprod.api_selecao.dto.RespostaRequest;
import br.maxiprod.api_selecao.dto.RespostaResponse;
import br.maxiprod.api_selecao.models.Pergunta;
import br.maxiprod.api_selecao.models.Resposta;

@Component
public class Mapper {

    public Pergunta mapDtoToPergunta(PerguntaRequest request) {
        Pergunta retorno = new Pergunta();
        retorno.setCreatedAt(LocalDate.now());
        retorno.setDescricao(request.getDescricao());
        retorno.setTitulo(request.getTitulo());
        retorno.setAtivo(Boolean.TRUE);
        return retorno;
    }

    public PerguntaResponse mapPerguntaToDto(Pergunta pergunta) {
        PerguntaResponse retorno = new PerguntaResponse();
        retorno.setDescricao(pergunta.getDescricao());
        retorno.setTitulo(pergunta.getTitulo());
        retorno.setId(pergunta.getId());
        retorno.setCreatedAt(pergunta.getCreatedAt());

        List<RespostaResponse> respostasDto = pergunta.getRespostas().stream()
                .map(this::mapRespostaToDto)
                .collect(Collectors.toList());

        retorno.setRespostas(respostasDto);

        return retorno;
    }

    public RespostaResponse mapRespostaToDto(Resposta resposta) {
        RespostaResponse retorno = new RespostaResponse();
        retorno.setCreatedAt(resposta.getCreatedAt());
        retorno.setDescricao(resposta.getDescricao());
        retorno.setVotos(resposta.getVotos());
        retorno.setId(resposta.getId());
        return retorno;
    }

    public Resposta mapRespostaDTO(RespostaRequest request) {
        Resposta retorno = new Resposta();
        retorno.setAtivo(Boolean.TRUE);
        retorno.setCreatedAt(LocalDate.now());
        retorno.setDescricao(request.getDescricao());
        retorno.setVotos(0);
        return retorno;
    }
}
