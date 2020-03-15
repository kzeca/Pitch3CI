package com.br.arley.pitch3ci.Modelo;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class Equipe implements Comparable<Equipe>{
    private String id;
    private int arrecadacao;
    private int somaAvaliacao;
    private int numeroAvaliadores;
    private double mediaAvaliacao;

    public Equipe(){

    }

    public Equipe(String integrantes, int arrecadacao){
        this.integrantes = integrantes;
        this.arrecadacao = arrecadacao;
    }

    public String getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(String integrantes) {
        this.integrantes = integrantes;
    }

    String integrantes;

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

    @Override
    public int compareTo(Equipe o) {
        if(this.arrecadacao > o.getArrecadacao()){
            return -1;
        }
        else if(this.arrecadacao < o.getArrecadacao()){
            return 1;
        }
        return 0;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getIntegrantes()+", RS "+this.getArrecadacao();
    }
}
