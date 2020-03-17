package com.br.arley.pitch3ci;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.br.arley.pitch3ci.Modelo.Equipe;
import com.br.arley.pitch3ci.Modelo.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.collection.LLRBNode;

public class ChooseActivity extends AppCompatActivity {

    DatabaseReference dataBase;
    static Equipe equipe;
    Usuario user;
    FirebaseUser firebaseUser;
    String uid;


    CardView cvArley;
    CardView cvFelipe;
    CardView cvDebora;
    CardView cvYasmin;
    CardView cvLuiz;
    CardView cvIsaac;
    CardView cvNatalia;
    CardView cvJoao;
    CardView cvAirton;
    CardView cvLorenzo;
    CardView cvJose;
    CardView cvSarah;

    TextView investimentoArley;
    TextView investimentoFelipe;
    TextView investimentoDebora;
    TextView investimentoYasmin;
    TextView investimentoLuiz;
    TextView investimentoIsaac;
    TextView investimentoNatalia;
    TextView investimentoJoao;
    TextView investimentoAirton;
    TextView investimentoLorenzo;
    TextView investimentoJose;
    TextView investimentoSarah;

    TextView mediaStarArley;
    TextView mediaStarFelipe;
    TextView mediaStarDebora;
    TextView mediaStarYasmin;
    TextView mediaStarLuiz;
    TextView mediaStarIsaac;
    TextView mediaStarNatalia;
    TextView mediaStarJoao;
    TextView mediaStarAirton;
    TextView mediaStarLorenzo;
    TextView mediaStarJose;
    TextView mediaStarSarah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        equipe = new Equipe();
        user = new Usuario();
        dataBase = FirebaseDatabase.getInstance().getReference();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = firebaseUser.getUid();
        putId();
        ImageButton btVoltar = findViewById(R.id.activity_choose_bt_voltar);
        btVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        clickIt();

        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mediaStarJose.setText(dataSnapshot.child("equipes").child("0").child("media_avaliacao").getValue(String.class));
                mediaStarArley.setText(dataSnapshot.child("equipes").child("1").child("media_avaliacao").getValue(String.class));
                mediaStarFelipe.setText(dataSnapshot.child("equipes").child("2").child("media_avaliacao").getValue(String.class));
                mediaStarDebora.setText(dataSnapshot.child("equipes").child("3").child("media_avaliacao").getValue(String.class));
                mediaStarYasmin.setText(dataSnapshot.child("equipes").child("4").child("media_avaliacao").getValue(String.class));
                mediaStarLuiz.setText(dataSnapshot.child("equipes").child("5").child("media_avaliacao").getValue(String.class));
                mediaStarIsaac.setText(dataSnapshot.child("equipes").child("6").child("media_avaliacao").getValue(String.class));
                mediaStarJoao.setText(dataSnapshot.child("equipes").child("7").child("media_avaliacao").getValue(String.class));
                mediaStarAirton.setText(dataSnapshot.child("equipes").child("8").child("media_avaliacao").getValue(String.class));
                mediaStarLorenzo.setText(dataSnapshot.child("equipes").child("9").child("media_avaliacao").getValue(String.class));
                mediaStarSarah.setText(dataSnapshot.child("equipes").child("10").child("media_avaliacao").getValue(String.class));
                mediaStarNatalia.setText(dataSnapshot.child("equipes").child("11").child("media_avaliacao").getValue(String.class));

                investimentoJose.setText("R$ " + dataSnapshot.child("equipes").child("0").child("arrecadacao").getValue(String.class));
                investimentoArley.setText("R$ " + dataSnapshot.child("equipes").child("1").child("arrecadacao").getValue(String.class));
                investimentoFelipe.setText("R$ " + dataSnapshot.child("equipes").child("2").child("arrecadacao").getValue(String.class));
                investimentoDebora.setText("R$ " + dataSnapshot.child("equipes").child("3").child("arrecadacao").getValue(String.class));
                investimentoYasmin.setText("R$ " + dataSnapshot.child("equipes").child("4").child("arrecadacao").getValue(String.class));
                investimentoLuiz.setText("R$ " + dataSnapshot.child("equipes").child("5").child("arrecadacao").getValue(String.class));
                investimentoIsaac.setText("R$ " + dataSnapshot.child("equipes").child("6").child("arrecadacao").getValue(String.class));
                investimentoJoao.setText("R$ " + dataSnapshot.child("equipes").child("7").child("arrecadacao").getValue(String.class));
                investimentoAirton.setText("R$ " + dataSnapshot.child("equipes").child("8").child("arrecadacao").getValue(String.class));
                investimentoLorenzo.setText("R$ " + dataSnapshot.child("equipes").child("9").child("arrecadacao").getValue(String.class));
                investimentoSarah.setText("R$ " + dataSnapshot.child("equipes").child("10").child("arrecadacao").getValue(String.class));
                investimentoNatalia.setText("R$ " + dataSnapshot.child("equipes").child("11").child("arrecadacao").getValue(String.class));
                String time = dataSnapshot.child("usuarios").child(uid).child("time").getValue(String.class);
                if(time!=null){
                    user.setTime(time);
                }
                for(int cont=0;cont<12;cont++){
                    String equipe = dataSnapshot.child("usuarios").child(uid).child("historico").child("avaliacoes").child(Integer.toString(cont)).getValue(String.class);
                    switch (cont){
                        case 0:
                            if(!equipe.equals("0")){
                                cvJose.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 1:
                            if(!equipe.equals("0")){
                                cvArley.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 2:
                            if(!equipe.equals("0")){
                                cvFelipe.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 3:
                            if(!equipe.equals("0")){
                                cvDebora.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 4:
                            if(!equipe.equals("0")){
                                cvYasmin.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 5:
                            if(!equipe.equals("0")){
                                cvLuiz.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 6:
                            if(!equipe.equals("0")){
                                cvIsaac.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 7:
                            if(!equipe.equals("0")){
                                cvJoao.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 8:
                            if(!equipe.equals("0")){
                                cvAirton.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 9:
                            if(!equipe.equals("0")){
                                cvLorenzo.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 10:
                            if(!equipe.equals("0")){
                                cvSarah.setCardBackgroundColor(Color.GRAY);
                            }
                            break;
                        case 11:
                            if(!equipe.equals("0")){
                                cvNatalia.setCardBackgroundColor(0xFF888888);
                            }
                            break;
                    }
                }

}

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
    }
});

        }


private void clickIt() {

        cvJose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvJose.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("0");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvArley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvArley.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("1");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvFelipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvFelipe.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("2");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvDebora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvDebora.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("3");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvYasmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvYasmin.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("4");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvLuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvLuiz.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("5");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvIsaac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvIsaac.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("6");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvJoao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvJoao.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("7");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvAirton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvAirton.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("8");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvLorenzo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvLorenzo.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)){
                    actionClick("9");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvSarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvSarah.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!=(Color.GRAY)) {
                    actionClick("10");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
        cvNatalia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int backgroundColor = cvNatalia.getCardBackgroundColor().getDefaultColor();
                if(backgroundColor!= (Color.GRAY)){
                    actionClick("11");
                }else Toast.makeText(ChooseActivity.this, "Você já avaliou essa equipe", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actionClick(String i) {
        if (user.getTime().equals(i)) {
            Toast.makeText(this, "Você participa dessa equipe!", Toast.LENGTH_SHORT).show();
        } else {
            equipe.setId(i);
            startActivity(new Intent(ChooseActivity.this, EquipeActivity.class));
        }
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

        investimentoNatalia = findViewById(R.id.activity_choose_tv_moneyNatalia);
        investimentoArley = findViewById(R.id.activity_choose_tv_moneyArley);
        investimentoJose = findViewById(R.id.activity_choose_tv_moneyJose);
        investimentoJoao = findViewById(R.id.activity_choose_tv_moneyJoao);
        investimentoSarah = findViewById(R.id.activity_choose_tv_moneySarah);
        investimentoLorenzo = findViewById(R.id.activity_choose_tv_moneyLorenzo);
        investimentoLuiz = findViewById(R.id.activity_choose_tv_moneyLuiz);
        investimentoDebora = findViewById(R.id.activity_choose_tv_moneyDebora);
        investimentoFelipe = findViewById(R.id.activity_choose_tv_moneyFelipe);
        investimentoYasmin = findViewById(R.id.activity_choose_tv_moneyYasmim);
        investimentoAirton = findViewById(R.id.activity_choose_tv_moneyAirton);
        investimentoIsaac = findViewById(R.id.activity_choose_tv_moneyIsaac);

        mediaStarNatalia = findViewById(R.id.activity_choose_tv_avaliacaoNatalia);
        mediaStarArley = findViewById(R.id.activity_choose_tv_avaliacaoArley);
        mediaStarJose = findViewById(R.id.activity_choose_tv_avaliacaoJose);
        mediaStarJoao = findViewById(R.id.activity_choose_tv_avaliacaoJoao);
        mediaStarSarah = findViewById(R.id.activity_choose_tv_avaliacaoSarah);
        mediaStarLorenzo = findViewById(R.id.activity_choose_tv_avaliacaoLorenzo);
        mediaStarLuiz = findViewById(R.id.activity_choose_tv_avaliacaoLuiz);
        mediaStarDebora = findViewById(R.id.activity_choose_tv_avaliacaoDebora);
        mediaStarFelipe = findViewById(R.id.activity_choose_tv_avaliacaoFelipe);
        mediaStarYasmin = findViewById(R.id.activity_choose_tv_avaliacaoYasmim);
        mediaStarAirton = findViewById(R.id.activity_choose_tv_avaliacaoAirton);
        mediaStarIsaac = findViewById(R.id.activity_choose_tv_avaliacaoIsaac);
    }
}

