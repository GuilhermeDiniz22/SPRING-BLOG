package br.maxiprod.api_selecao.dto;

import java.time.LocalDate;
import java.util.List;

import java.util.Objects;

public class PerguntaResponse {

    private Long id;

    private String titulo;

    private String descricao;

    private LocalDate createdAt;

    private List<RespostaResponse> respostas;

    public PerguntaResponse() {
    }

    public PerguntaResponse(Long id, String titulo, String descricao, LocalDate createdAt,
            List<RespostaResponse> respostas) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.respostas = respostas;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public List<RespostaResponse> getRespostas() {
        return this.respostas;
    }

    public void setRespostas(List<RespostaResponse> respostas) {
        this.respostas = respostas;
    }

    public PerguntaResponse id(Long id) {
        setId(id);
        return this;
    }

    public PerguntaResponse titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public PerguntaResponse descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    public PerguntaResponse createdAt(LocalDate createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public PerguntaResponse respostas(List<RespostaResponse> respostas) {
        setRespostas(respostas);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PerguntaResponse)) {
            return false;
        }
        PerguntaResponse perguntaResponse = (PerguntaResponse) o;
        return Objects.equals(id, perguntaResponse.id) && Objects.equals(titulo, perguntaResponse.titulo)
                && Objects.equals(descricao, perguntaResponse.descricao)
                && Objects.equals(createdAt, perguntaResponse.createdAt)
                && Objects.equals(respostas, perguntaResponse.respostas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, createdAt, respostas);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", titulo='" + getTitulo() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", respostas='" + getRespostas() + "'" +
                "}";
    }

}
