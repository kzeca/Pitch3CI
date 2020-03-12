package com.br.arley.pitch3ci;

public class Usuario {
    private String ra;
    private int dinheiro = 100000;
    public String email;
    private String nome;


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

}