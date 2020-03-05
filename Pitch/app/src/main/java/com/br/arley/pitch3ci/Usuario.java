package com.br.arley.pitch3ci;

public class Usuario {
    private String ra;
    private int dinheiro = 100000;
    private String email;

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
