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

    CardView cvArley;CardView cvFelipe;CardView cvDebora;
    CardView cvYasmin;CardView cvLuiz;CardView cvIsaac;
    CardView cvNatalia;CardView cvJoao;CardView cvAirton;
    CardView cvLorenzo;CardView cvJose;CardView cvSarah;
    Equipe equipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        putId();
        ImageButton btVoltar = findViewById(R.id.activity_choose_bt_voltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        clickIt();
    }

    private void clickIt() {
        cvNatalia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvArley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvJose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvJoao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvSarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvYasmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvLorenzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvLuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvDebora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvFelipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvAirton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClick();
            }
        });
        cvIsaac.setOnClickListener(new View.OnClickListener() {
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
        cvNatalia = findViewById(R.id.activity_choose_cv_Natalia);
        cvArley = findViewById(R.id.activity_choose_cv_Arley);
        cvJose = findViewById(R.id.activity_choose_cv_Jose);
        cvJoao = findViewById(R.id.activity_choose_cv_Joao);
        cvSarah = findViewById(R.id.activity_choose_cv_Sarah);
        cvLorenzo = findViewById(R.id.activity_choose_cv_Lorenzo);
        cvLuiz = findViewById(R.id.activity_choose_cv_Luiz);
        cvDebora = findViewById(R.id.activity_choose_cv_Debora);
        cvFelipe = findViewById(R.id.activity_choose_cv_Felipe);
        cvYasmin = findViewById(R.id.activity_choose_cv_Yasmim);
        cvAirton = findViewById(R.id.activity_choose_cv_Airton);
        cvIsaac = findViewById(R.id.activity_choose_cv_Isaac);
    }

}
