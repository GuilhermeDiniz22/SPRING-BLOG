package br.maxiprod.api_selecao.dto;

import java.time.LocalDate;
import java.util.Objects;

public class RespostaResponse {

    private Long id;

    private String descricao;

    private LocalDate createdAt;

    private int votos;

    public RespostaResponse() {
    }

    public RespostaResponse(Long id, String descricao, LocalDate createdAt, int votos) {
        this.id = id;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.votos = votos;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public int getVotos() {
        return this.votos;
    }

    public void setVotos(int votos) {
        this.votos = votos;
    }

    public RespostaResponse id(Long id) {
        setId(id);
        return this;
    }

    public RespostaResponse descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    public RespostaResponse createdAt(LocalDate createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public RespostaResponse votos(int votos) {
        setVotos(votos);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RespostaResponse)) {
            return false;
        }
        RespostaResponse respostaResponse = (RespostaResponse) o;
        return Objects.equals(id, respostaResponse.id) && Objects.equals(descricao, respostaResponse.descricao)
                && Objects.equals(createdAt, respostaResponse.createdAt) && votos == respostaResponse.votos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, createdAt, votos);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", votos='" + getVotos() + "'" +
                "}";
    }

}
