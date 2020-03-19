package com.br.arley.pitch3ci.Modelo;

public class Investimento {

    private Integer avaliacao;
    private String justificativa;
    private Integer investido;

    public Investimento() {
    }

    public Investimento(Integer avaliacao, String justificativa, Integer investido) {
        this.avaliacao = avaliacao;
        this.justificativa = justificativa;
        this.investido = investido;
    }

    public Integer getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Integer avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public Integer getInvestido() {
        return investido;
    }

    public void setInvestido(Integer investido) {
        this.investido = investido;
    }
}
