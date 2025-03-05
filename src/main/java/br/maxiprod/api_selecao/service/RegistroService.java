package br.com.vendas_api.service;

import br.com.vendas_api.dto.RegistroDtoSaida;
import br.com.vendas_api.exception.RegistroNaoEncontradoException;
import br.com.vendas_api.mapper.Mapper;
import br.com.vendas_api.model.Registro;
import br.com.vendas_api.repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private Mapper mapper;

    public RegistroDtoSaida getRegistroById(Long id){
        Registro registro = registroRepository.findById(id)
                .orElseThrow(
                        ()-> new RegistroNaoEncontradoException(String.format("Registro não encontrado com id: %d", id)));

        return mapper.converToRegistroDtoSaida(registro);
    }

    public List<RegistroDtoSaida> getRegistrosBySocioId(Long id){
        List<Registro> registro = registroRepository.findAllBySocioId(id)
                .stream().toList();

        if(registro.isEmpty())
            throw new RegistroNaoEncontradoException(String.format("Registro não encontrado com id: %d", id));

        List<RegistroDtoSaida> retorno = registro.stream().map(mapper::converToRegistroDtoSaida)
                .collect(Collectors.toUnmodifiableList());

        return retorno;
    }

    public List<RegistroDtoSaida> getAllRegistros(){
        List<RegistroDtoSaida> registros = registroRepository.findAll()
                .stream().map(mapper::converToRegistroDtoSaida)
                .sorted(Comparator.comparing(RegistroDtoSaida::getDataAluguel))
                .collect(Collectors.toList());

        if(registros.isEmpty()){
            throw new RegistroNaoEncontradoException("Nenhum registro encontrado!");
        }

        return registros;
    }

    public String deleteRegistroById(Long id){
        Registro registro = registroRepository.findById(id)
                .orElseThrow(
                        ()-> new RegistroNaoEncontradoException(String.format("Registro não encontrado com id: %d", id)));

        registro.setAtivo(Boolean.FALSE);

        return String.format("Registro com id: %d deletado com sucesso", id);
    }

}
