package br.maxiprod.api_selecao.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.maxiprod.api_selecao.dto.RespostaRequest;
import br.maxiprod.api_selecao.dto.RespostaResponse;
import br.maxiprod.api_selecao.mapper.Mapper;
import br.maxiprod.api_selecao.models.Pergunta;
import br.maxiprod.api_selecao.models.Resposta;
import br.maxiprod.api_selecao.repository.PerguntaRepository;
import br.maxiprod.api_selecao.repository.RespostaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private Mapper mapper;

    public RespostaResponse updateResposta(RespostaRequest request, Long perguntaId, Long respostaId) {

        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + perguntaId));

        List<Resposta> respostas = Optional.ofNullable(pergunta.getRespostas()).orElse(Collections.emptyList());

        Resposta retorno = new Resposta();

        for (Resposta resposta : respostas) {
            if (resposta.getId().equals(respostaId)) {
                resposta.setDescricao(request.getDescricao());
                retorno = respostaRepository.save(resposta);
                return mapper.mapRespostaToDto(retorno);
            }
        }

        throw new EntityNotFoundException("Resposta não encontrada.");

    }

    public String deleteResposta(Long perguntaId, Long respostaId) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + perguntaId));

        List<Resposta> respostas = Optional.ofNullable(pergunta.getRespostas()).orElse(Collections.emptyList());

        boolean respostaEncontrada = false;

        for (Resposta resposta : respostas) {
            if (resposta.getId().equals(respostaId)) {
                resposta.setAtivo(Boolean.FALSE);
                respostaRepository.save(resposta);
                respostaEncontrada = true;
                break;
            }

        }

        if (!respostaEncontrada) {
            throw new EntityNotFoundException("Resposta não encontrada com id: " + respostaId);
        }

        return "Resposta deletada com sucesso!";
    }

    public RespostaResponse getRespostaById(Long perguntaId, Long respostaId) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + perguntaId));

        List<Resposta> respostas = Optional.ofNullable(pergunta.getRespostas()).orElse(Collections.emptyList());

        for (Resposta resposta : respostas) {
            if (resposta.getId().equals(respostaId)) {
                return mapper.mapRespostaToDto(resposta);
            }
        }

        throw new EntityNotFoundException("Resposta não encontrada com id: " + respostaId);
    }

    public List<RespostaResponse> getRespostasByDescricao(Long perguntaId, String descricao) {

        List<Resposta> respostas = respostaRepository.findByPerguntaIdAndDescricaoContainingIgnoreCase(perguntaId,
                descricao);

        List<RespostaResponse> retorno = respostas.stream()
                .map(resposta -> mapper.mapRespostaToDto(resposta))
                .collect(Collectors.toList());

        return retorno;
    }

    public void votarPositivo(Long perguntaId, Long respostaId) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + perguntaId));

        List<Resposta> respostas = Optional.ofNullable(pergunta.getRespostas()).orElse(Collections.emptyList());

        boolean respostaEncontrada = false;

        for (Resposta resposta : respostas) {
            if (resposta.getId().equals(respostaId)) {
                resposta.votarPositivo();
                respostaRepository.save(resposta);
                respostaEncontrada = true;
                break;
            }
        }

        if (!respostaEncontrada) {
            throw new EntityNotFoundException("Resposta não encontrada com id: " + respostaId);
        }
    }

    public void votarNegativo(Long perguntaId, Long respostaId) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + perguntaId));

        List<Resposta> respostas = Optional.ofNullable(pergunta.getRespostas()).orElse(Collections.emptyList());

        boolean respostaEncontrada = false;

        for (Resposta resposta : respostas) {
            if (resposta.getId().equals(respostaId)) {
                resposta.votarNegativo();
                respostaRepository.save(resposta);
                respostaEncontrada = true;
                break;
            }
        }

        if (!respostaEncontrada) {
            throw new EntityNotFoundException("Resposta não encontrada com id: " + respostaId);
        }
    }

}
