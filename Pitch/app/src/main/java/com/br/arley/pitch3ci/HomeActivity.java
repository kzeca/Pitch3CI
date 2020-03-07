package com.br.arley.pitch3ci;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    TextView activity_home_tv_nome;
    Usuario user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new Usuario();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity_home_tv_nome = findViewById(R.id.activity_home_tv_nome);
        activity_home_tv_nome.setText(String.format("Bem-vindo, %s!", user.getNome()));
    }
}
