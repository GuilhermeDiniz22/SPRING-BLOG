package br.maxiprod.api_selecao.models;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "perguntas")
@SQLRestriction("ativo = true")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "pergunta")
    private List<Resposta> respostas;

    @Column(nullable = false)
    private boolean ativo;

    public void addResposta(Resposta resposta) {
        resposta.setPergunta(this);
        this.respostas.add(resposta);
    }

    public void removerResposta(Resposta resposta) {
        resposta.setPergunta(null);
        this.respostas.remove(resposta);
    }

    public Pergunta() {
    }

    public Pergunta(Long id, String titulo, String descricao, LocalDate createdAt, List<Resposta> respostas,
            boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.createdAt = createdAt;
        this.respostas = respostas;
        this.ativo = ativo;
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

    public List<Resposta> getRespostas() {
        return this.respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
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

    public Pergunta id(Long id) {
        setId(id);
        return this;
    }

    public Pergunta titulo(String titulo) {
        setTitulo(titulo);
        return this;
    }

    public Pergunta descricao(String descricao) {
        setDescricao(descricao);
        return this;
    }

    public Pergunta createdAt(LocalDate createdAt) {
        setCreatedAt(createdAt);
        return this;
    }

    public Pergunta respostas(List<Resposta> respostas) {
        setRespostas(respostas);
        return this;
    }

    public Pergunta ativo(boolean ativo) {
        setAtivo(ativo);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pergunta)) {
            return false;
        }
        Pergunta pergunta = (Pergunta) o;
        return Objects.equals(id, pergunta.id) && Objects.equals(titulo, pergunta.titulo)
                && Objects.equals(descricao, pergunta.descricao) && Objects.equals(createdAt, pergunta.createdAt)
                && Objects.equals(respostas, pergunta.respostas) && ativo == pergunta.ativo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, createdAt, respostas, ativo);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", titulo='" + getTitulo() + "'" +
                ", descricao='" + getDescricao() + "'" +
                ", createdAt='" + getCreatedAt() + "'" +
                ", respostas='" + getRespostas() + "'" +
                ", ativo='" + isAtivo() + "'" +
                "}";
    }

}
