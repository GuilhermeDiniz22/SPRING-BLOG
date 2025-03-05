package br.maxiprod.api_selecao.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.maxiprod.api_selecao.dto.PerguntaRequest;
import br.maxiprod.api_selecao.dto.PerguntaResponse;
import br.maxiprod.api_selecao.dto.RespostaRequest;
import br.maxiprod.api_selecao.mapper.Mapper;
import br.maxiprod.api_selecao.models.Pergunta;
import br.maxiprod.api_selecao.models.Resposta;
import br.maxiprod.api_selecao.repository.PerguntaRepository;
import br.maxiprod.api_selecao.repository.RespostaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private RespostaRepository respostaRepository;

    @Autowired
    private Mapper mapper;

    public String addPergunta(PerguntaRequest request) {

        Pergunta pergunta = mapper.mapDtoToPergunta(request);

        perguntaRepository.save(pergunta);

        return "pergunta adiconada com sucesso!";
    }

    public PerguntaResponse getPerguntaById(Long id) {
        return mapper.mapPerguntaToDto(perguntaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + id)));
    }

    public List<PerguntaResponse> getPerguntasByTitulo(String titulo) {
        List<Pergunta> perguntas = perguntaRepository.findByTituloContainingIgnoreCase(titulo);

        List<PerguntaResponse> retorno = perguntas.stream()
                .map(mapper::mapPerguntaToDto)
                .collect(Collectors.toList());

        return retorno;
    }

    @Transactional
    public String addResposta_Pergunta(Long perguntaId, RespostaRequest respostaRequest) {
        Pergunta pergunta = perguntaRepository.findById(perguntaId)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + perguntaId));

        Resposta resposta = mapper.mapRespostaDTO(respostaRequest);

        resposta.setPergunta(pergunta);

        resposta = respostaRepository.save(resposta);

        pergunta.addResposta(resposta);

        return "resposta adicionada com sucesso!";

    }

    @Transactional
    public PerguntaResponse updatePergunta(PerguntaRequest request, Long id) {
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + id));

        pergunta.setDescricao(request.getDescricao());
        pergunta.setTitulo(request.getTitulo());

        Pergunta retorno = perguntaRepository.save(pergunta);

        return mapper.mapPerguntaToDto(retorno);

    }

    @Transactional
    public String deletePergunta(Long id) {
        Pergunta pergunta = perguntaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pergunta não encontrada com id: " + id));

        List<Resposta> respostas = pergunta.getRespostas();

        respostas.forEach(resposta -> {
            resposta.setAtivo(Boolean.FALSE);
        });

        respostaRepository.saveAll(respostas);

        pergunta.setAtivo(Boolean.FALSE);

        perguntaRepository.save(pergunta);

        return "Pergunta deletada com sucesso!";
    }
}
