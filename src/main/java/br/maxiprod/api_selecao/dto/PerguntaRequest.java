package br.maxiprod.api_selecao.dto;

import java.util.Objects;

public class PerguntaRequest {

    private String titulo;

    private String descricao;

    public PerguntaRequest() {
    }

    public PerguntaRequest(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
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

    public PerguntaRequest titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public PerguntaRequest descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PerguntaRequest)) {
            return false;
        }
        PerguntaRequest perguntaRequest = (PerguntaRequest) o;
        return Objects.equals(titulo, perguntaRequest.titulo) && Objects.equals(descricao, perguntaRequest.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, descricao);
    }

    @Override
    public String toString() {
        return "{" +
                " titulo='" + getTitulo() + "'" +
                ", descricao='" + getDescricao() + "'" +
                "}";
    }

}
