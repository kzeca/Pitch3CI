package com.br.arley.pitch3ci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.br.arley.pitch3ci.MainActivity.user;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView txtBemVindo = (TextView) findViewById(R.id.activity_home_tv_nome);

        txtBemVindo.setText("Olá, " + user.getNome());

    }
}
