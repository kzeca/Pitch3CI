package com.br.arley.pitch3ci;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import static com.br.arley.pitch3ci.LoginActivity.user;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.SQLOutput;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    DatabaseReference dataBase;
    TextView tvNome;
    TextView tvSaldo;
    Usuario user;
    String uid;
    FirebaseUser firebaseUser;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvNome = findViewById(R.id.activity_home_tv_nome);
        tvSaldo = findViewById(R.id.activity_home_tv_saldo);
        user = LoginActivity.user;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = firebaseUser.getUid();
        dataBase = FirebaseDatabase.getInstance().getReference();

        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nome = dataSnapshot.child("usuarios").child(uid).child("nome").getValue(String.class);
                if (nome != null) {
                    user.setNome(nome);
                    tvNome.setText("Bem Vindo, "+user.getNome());
                }else{
                    System.out.println("NAO DEU NOME");
                }
                String saldo = dataSnapshot.child("usuarios").child(uid).child("saldo").getValue(String.class);
                if(saldo != null){
                    user.setDinheiro(Integer.parseInt(saldo));
                    tvSaldo.setText("R$ " + user.getDinheiro());
                }else {
                    System.out.println("NAO DEU SALDO");
                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //tvNome.setText(String.format("Olá, %s", user.getNome()));


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
