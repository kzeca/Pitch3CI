package com.br.arley.pitch3ci.Modelo;

import java.util.ArrayList;

public class Unidade {

    private String nome;
    private ArrayList<Investimento> historico;

    public Unidade() {
    }

    public Unidade(String nome, ArrayList<Investimento> historico) {
        this.nome = nome;
        this.historico = historico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Investimento> getHistorico() {
        return historico;
    }

    public void setHistorico(ArrayList<Investimento> historico) {
        this.historico = historico;
    }
}
