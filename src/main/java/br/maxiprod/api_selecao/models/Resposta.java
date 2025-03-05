package br.maxiprod.api_selecao.models;

import java.time.LocalDate;

import org.hibernate.annotations.SQLRestriction;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "respostas")
@SQLRestriction("ativo = true")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private int votos;

    @ManyToOne
    @JoinColumn(name = "pergunta_id")
    private Pergunta pergunta;

    @Column(nullable = false)
    private boolean ativo;

    public void votarPositivo() {
        this.votos += 1;
    }

    public void votarNegativo() {
        this.votos -= 1;
    }

    public Resposta() {
    }

    public Resposta(Long id, String descricao, LocalDate createdAt, int votos, Pergunta pergunta, boolean ativo) {
        this.id = id;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.votos = votos;
        this.pergunta = pergunta;
        this.ativo = ativo;
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

    public Pergunta getPergunta() {
        return this.pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Resposta id(Long id) {
        setId(id);
        return this;
    }

    public Resposta descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    public Resposta createdAt(LocalDate createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Resposta votos(int votos) {
        setVotos(votos);
        return this;
    }

    public Resposta pergunta(Pergunta pergunta) {
        setPergunta(pergunta);
        return this;
    }

    public Resposta ativo(boolean ativo) {
        setAtivo(ativo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Resposta)) {
            return false;
        }
        Resposta resposta = (Resposta) o;
        return Objects.equals(id, resposta.id) && Objects.equals(descricao, resposta.descricao)
                && Objects.equals(createdAt, resposta.createdAt) && votos == resposta.votos
                && Objects.equals(pergunta, resposta.pergunta) && ativo == resposta.ativo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, createdAt, votos, pergunta, ativo);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", votos='" + getVotos() + "'" +
                ", pergunta='" + getPergunta() + "'" +
                ", ativo='" + isAtivo() + "'" +
                "}";
    }

}
