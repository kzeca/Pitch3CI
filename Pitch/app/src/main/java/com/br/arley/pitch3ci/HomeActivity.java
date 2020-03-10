package com.br.arley.pitch3ci;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    TextView activity_home_tv_nome;
    Usuario user;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new Usuario();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        activity_home_tv_nome = findViewById(R.id.activity_home_tv_nome);

        String[] emailseparado = user.getEmail().split("@");

        activity_home_tv_nome.setText(String.format("Olá, %s", emailseparado[0]));

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChooseActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder msgSair = new AlertDialog.Builder(this);
        msgSair.setMessage("Tem certeza que deseja sair do Pitch3CI?");
        msgSair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        msgSair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        msgSair.show();
    }
}
