package com.br.arley.pitch3ci;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ChooseActivity extends AppCompatActivity{

    CardView Arley;CardView Felipe;CardView Debora;
    CardView Yasmin;CardView Luiz;CardView Isaac;
    CardView Natalia;CardView Joao;CardView Airton;
    CardView Lorenzo;CardView Jose;CardView Sarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        putId();
        ImageButton btVoltar = findViewById(R.id.activity_choose_bt_voltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, HomeActivity.class));
            }
        });
        clickIt();
    }

    private void clickIt() {
        Natalia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               actionClick();
            }
        });
        Arley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Jose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Joao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Sarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Yasmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Lorenzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Luiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Debora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Felipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Airton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        Isaac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
    }

    private void actionClick() {
        startActivity(new Intent(ChooseActivity.this, EquipeActivity.class));
    }

    private void putId() {
        Natalia = findViewById(R.id.activity_choose_tv_Natalia);
        Arley = findViewById(R.id.activity_choose_tv_Arley);
        Jose = findViewById(R.id.activity_choose_tv_José);
        Joao = findViewById(R.id.activity_choose_tv_Joao);
        Sarah = findViewById(R.id.activity_choose_tv_Sarah);
        Lorenzo = findViewById(R.id.activity_choose_tv_Lorenzo);
        Luiz = findViewById(R.id.activity_choose_tv_Luiz);
        Debora = findViewById(R.id.activity_choose_tv_Debora);
        Felipe = findViewById(R.id.activity_choose_tv_Felipe);
        Yasmin = findViewById(R.id.activity_choose_tv_Yasmin);
        Airton = findViewById(R.id.activity_choose_tv_Airton);
        Isaac = findViewById(R.id.activity_choose_tv_Isaac);
    }

}
