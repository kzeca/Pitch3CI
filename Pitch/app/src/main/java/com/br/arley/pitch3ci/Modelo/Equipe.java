package com.br.arley.pitch3ci.Modelo;

import java.util.ArrayList;

public class Equipe {
    private String id;
    private int arrecadacao;
    private int somaAvaliacao;
    private int numeroAvaliadores;
    private double mediaAvaliacao;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
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
        this.numeroAvaliadores = numeroAvaliadores+1;
    }

    public double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }
}
