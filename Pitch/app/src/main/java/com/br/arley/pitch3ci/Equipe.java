package com.br.arley.pitch3ci;

import java.util.ArrayList;

public class Equipe {
    private String nome;
    private ArrayList<String> integrantes;
    private int arrecadacao;
    private int somaAvaliacao;
    private int numeroAvaliadores;
    private double mediaAvaliacao;

    public Equipe(){

    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<String> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(ArrayList<String> integrantes) {
        this.integrantes = integrantes;
    }

    public int getArrecadacao() {
        return arrecadacao;
    }

    public void setArrecadacao(int arrecadacao) {
        this.arrecadacao = arrecadacao;
    }

    public int getSomaAvaliacao() {
        return somaAvaliacao;
    }

    public void setSomaAvaliacao(int somaAvaliacao) {
        this.somaAvaliacao = somaAvaliacao;
    }

    public int getNumeroAvaliadores() {
        return numeroAvaliadores;
    }

    public void setNumeroAvaliadores(int numeroAvaliadores) {
        this.numeroAvaliadores = numeroAvaliadores;
    }

    public double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }
}
