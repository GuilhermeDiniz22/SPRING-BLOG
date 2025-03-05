package br.maxiprod.api_selecao.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;

public class RespostaRequest {

    @NotBlank(message = "A descrição não pode estar vazia.")
    @JsonProperty("descricao")
    private String descricao;

    public RespostaRequest() {
    }

    public RespostaRequest(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RespostaRequest descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " descricao='" + getDescricao() + "'" +
                "}";
    }

}
