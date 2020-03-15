package com.br.arley.pitch3ci.Modelo;

import com.google.firebase.database.DatabaseReference;

public class Usuario {
    private String ra;
    private int dinheiro;
    public static String email;
    DatabaseReference reference;
    private String nome;
    private String time;


    public void setDinheiro(int dinheiro) {
        this.dinheiro = dinheiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario(){

    }
    public Usuario(String ra, String email){
        this.setRa(ra);
        this.setEmail(email);
    }

    public int getDinheiro() {
        return dinheiro;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}