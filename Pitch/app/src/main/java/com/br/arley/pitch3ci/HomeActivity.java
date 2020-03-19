package com.br.arley.pitch3ci;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.br.arley.pitch3ci.Modelo.Equipe;
import com.br.arley.pitch3ci.Modelo.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity {

    DatabaseReference dataBase;
    TextView tvNome;
    TextView tvSaldo;
    Usuario user;
    String uid;
    ArrayList equipes;
    TextView tvPrimeiroLugar;
    TextView tvSegundoLugar;
    TextView tvTerceiroLugar;
    TextView tvArrecadacaoPrimeiroLugar;
    TextView tvArrecadacaoSegundoLugar;
    TextView tvArrecadacaoTerceiroLugar;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        tvNome = findViewById(R.id.activity_home_tv_nome);
        tvSaldo = findViewById(R.id.activity_home_tv_saldo);
        tvPrimeiroLugar = findViewById(R.id.activity_home_tv_nome_primeira_mais_investida);
        tvSegundoLugar = findViewById(R.id.activity_home_tv_nome_segunda_mais_investida);
        tvTerceiroLugar = findViewById(R.id.activity_home_tv_nome_terceira_mais_investida);
        tvArrecadacaoPrimeiroLugar = findViewById(R.id.home_activity_tv_arrecadacao_primeiro);
        tvArrecadacaoSegundoLugar= findViewById(R.id.home_activity_tv_arrecadacao_segundo);
        tvArrecadacaoTerceiroLugar = findViewById(R.id.home_activity_tv_arrecadacao_terceiro);
        user = LoginActivity.user;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = firebaseUser.getUid();
        dataBase = FirebaseDatabase.getInstance().getReference();

        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                equipes = new ArrayList<Equipe>();
                String nome = dataSnapshot.child("usuarios").child(uid).child("nome").getValue(String.class);
                String time = dataSnapshot.child("usuarios").child(uid).child("time").getValue(String.class);

                if (nome != null) {
                    user.setNome(nome);
                    tvNome.setText(getApplication().getString(R.string.bem_vindo)+", "+user.getNome());
                }
                String saldo = dataSnapshot.child("usuarios").child(uid).child("saldo").getValue(String.class);
                if(saldo != null){
                    user.setDinheiro(Integer.parseInt(saldo));
                    tvSaldo.setTextSize(2, 43.0f);
                    tvSaldo.setText("R$ " + user.getDinheiro());
                }
                if(time!= null){
                    user.setTime(time);
                }

                for(int i=0;i<12;i++){
                    String integrantes = dataSnapshot.child("equipes").child(Integer.toString(i)).child("integrantes").getValue(String.class);
                    int arrecadacao = Integer.parseInt(dataSnapshot.child("equipes").child(Integer.toString(i)).child("arrecadacao").getValue(String.class));
                    Equipe equipe = new Equipe(integrantes, arrecadacao);
                    equipes.add(equipe);
                }
                setRanking(equipes);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ChooseActivity.class));
            }
        });
    }

    public void setRanking(ArrayList<Equipe> equipes){
        Collections.sort(equipes);
        if(equipes.get(0).getArrecadacao()>0){
            tvPrimeiroLugar.setText(equipes.get(0).getIntegrantes());
            tvArrecadacaoPrimeiroLugar.setText("R$ "+Integer.toString(equipes.get(0).getArrecadacao()));
        }
        else{
            tvPrimeiroLugar.setText(getApplication().getString(R.string.carregando));
            tvArrecadacaoPrimeiroLugar.setText(getApplication().getString(R.string.carregando));
        }
        if(equipes.get(1).getArrecadacao()>0){
            tvSegundoLugar.setText(equipes.get(1).getIntegrantes());
            tvArrecadacaoSegundoLugar.setText("R$ "+Integer.toString(equipes.get(1).getArrecadacao()));
        }
        else{
            tvSegundoLugar.setText(getApplication().getString(R.string.carregando));
            tvArrecadacaoSegundoLugar.setText(getApplication().getString(R.string.carregando));
        }
        if(equipes.get(2).getArrecadacao()>0){
            tvTerceiroLugar.setText(equipes.get(2).getIntegrantes());
            tvArrecadacaoTerceiroLugar.setText("R$ "+Integer.toString(equipes.get(2).getArrecadacao()));
        }
        else{
            tvTerceiroLugar.setText(getApplication().getString(R.string.carregando));
            tvArrecadacaoTerceiroLugar.setText(getApplication().getString(R.string.carregando));
        }
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
