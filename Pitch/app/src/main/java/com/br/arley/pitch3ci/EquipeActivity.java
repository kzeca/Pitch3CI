package com.br.arley.pitch3ci;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import static com.br.arley.pitch3ci.ChooseActivity.equipe;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class EquipeActivity extends AppCompatActivity {

    EditText edtValorInvestir;
    TextView tvSaldo, tvIntegrantes;
    Usuario user;
    FirebaseUser firebaseUser;
    String uid;
    Equipe equipe;
    DatabaseReference dataBase;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe);
        tvSaldo = findViewById(R.id.equipe_activity_tv_saldo);
        tvIntegrantes = findViewById(R.id.equipe_activity_tv_integrantes);
        equipe = ChooseActivity.equipe;
        Button btAdicionar1k = findViewById(R.id.equipe_activity_bt_1000);
        Button btAdicionar10k = findViewById(R.id.equipe_activity_bt_10000);
        Button btAdicionar20k = findViewById(R.id.equipe_activity_bt_20000);
        Button btAdicionar50k = findViewById(R.id.equipe_activity_bt_50000);
        Button btOutroValor = findViewById(R.id.equipe_activity_bt_outro_valor);
        Button btInvestir = findViewById(R.id.equipe_activity_bt_investir);
        ImageButton btnVoltar = findViewById(R.id.equipe_activity_bt_voltar);
        edtValorInvestir = findViewById(R.id.equipe_activity_edt_valor_investir);
        ratingBar = findViewById(R.id.equipe_activity_rating_bar);

        user = LoginActivity.user;
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = firebaseUser.getUid();
        dataBase = FirebaseDatabase.getInstance().getReference();

        dataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String saldo = dataSnapshot.child("usuarios").child(uid).child("saldo").getValue(String.class);
                String integrantes = dataSnapshot.child("equipes").child(equipe.getId()).child("integrantes").getValue(String.class);
                String n_avaliacao = dataSnapshot.child("equipes").child(equipe.getId()).child("n_avaliacao").getValue(String.class);
                String soma_avaliacao = dataSnapshot.child("equipes").child(equipe.getId()).child("soma_avaliacao").getValue(String.class);
                String media_avaliacao = dataSnapshot.child("equipes").child(equipe.getId()).child("media_avaliacao").getValue(String.class);
                String arrecadacao = dataSnapshot.child("equipes").child(equipe.getId()).child("arrecadacao").getValue(String.class);
                if(saldo != null){
                    user.setDinheiro(Integer.parseInt(saldo));
                    tvSaldo.setText("R$ " + user.getDinheiro());
                }
                if(integrantes!=null){
                    tvIntegrantes.setText(integrantes);
                }
                if (n_avaliacao!=null&&soma_avaliacao!=null&&media_avaliacao!=null){
                    equipe.setMediaAvaliacao(Double.parseDouble(media_avaliacao));
                    equipe.setNumeroAvaliadores(Integer.parseInt(n_avaliacao));
                    equipe.setSomaAvaliacao(Integer.parseInt(soma_avaliacao));
                    equipe.setArrecadacao(Integer.parseInt(arrecadacao));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btInvestir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtValor = (edtValorInvestir.getText().toString());
                if(txtValor.equals("")) edtValorInvestir.setText("0");
                if(Integer.parseInt(edtValorInvestir.getText().toString())>user.getDinheiro()){
                    Toast.makeText(EquipeActivity.this, "Os valores ultrapassam sua quantia máxima",Toast.LENGTH_SHORT).show();
                }else {
                    if(ratingBar.getRating()!=0) {
                        int avaliacao = (int) ratingBar.getRating();
                        int saldo = user.getDinheiro();
                        int valor_a_pagar = Integer.parseInt(edtValorInvestir.getText().toString());
                        saldo -= valor_a_pagar;
                        if(saldo!=0){
                            dataBase.child("usuarios").child(uid).child("saldo").setValue(Integer.toString(saldo));
                            dataBase.child("equipes").child(equipe.getId()).child("arrecadacao").setValue(Integer.toString(valor_a_pagar+equipe.getArrecadacao()));
                        }
                        dataBase.child("equipes").child(equipe.getId()).child("soma_avaliacao").setValue(Integer.toString(avaliacao+equipe.getSomaAvaliacao()));
                        dataBase.child("equipes").child(equipe.getId()).child("n_avaliacao").setValue(Integer.toString(equipe.getNumeroAvaliadores()));
                        double media = (equipe.getSomaAvaliacao()+avaliacao)/(equipe.getNumeroAvaliadores());
                        dataBase.child("equipes").child(equipe.getId()).child("media_avaliacao").setValue(Double.toString(media));
                        finish();
                        Toast.makeText(EquipeActivity.this, "Obrigado por avaliar", Toast.LENGTH_SHORT).show();
                    }else Toast.makeText(EquipeActivity.this, "Por favor, avalie a equipe!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btAdicionar1k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor = 0;
                int valorSomar = 1000;
                String txtValor = (edtValorInvestir.getText().toString());
                if(txtValor.equals("")) txtValor = "0";
                valor = Integer.parseInt(txtValor);
                valor += valorSomar;
                if(valor>user.getDinheiro()){
                    Toast.makeText(EquipeActivity.this, "Os valores ultrapassam sua quantia máxima",Toast.LENGTH_SHORT).show();
                    valor -= valorSomar;
                }
                edtValorInvestir.setText(Integer.toString(valor));
            }
        });

        btAdicionar10k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor;
                int valorSomar = 10000;
                String txtValor = (edtValorInvestir.getText().toString());
                if(txtValor.equals("")) txtValor = "0";
                valor = Integer.parseInt(txtValor);
                valor += valorSomar;
                if(valor>user.getDinheiro()){
                    Toast.makeText(EquipeActivity.this, "Os valores ultrapassam sua quantia máxima",Toast.LENGTH_SHORT).show();
                    valor -= valorSomar;
                }
                edtValorInvestir.setText(Integer.toString(valor));
            }
        });

        btAdicionar20k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor;
                int valorSomar = 20000;
                String txtValor = (edtValorInvestir.getText().toString());
                if(txtValor.equals("")) txtValor = "0";
                valor = Integer.parseInt(txtValor);
                valor += valorSomar;
                if(valor>user.getDinheiro()){
                    Toast.makeText(EquipeActivity.this, "Os valores ultrapassam sua quantia máxima",Toast.LENGTH_SHORT).show();
                    valor -= valorSomar;
                }
                edtValorInvestir.setText(Integer.toString(valor));
            }
        });

        btAdicionar50k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor;
                int valorSomar = 50000;
                String txtValor = (edtValorInvestir.getText().toString());
                if(txtValor.equals("")) txtValor = "0";
                valor = Integer.parseInt(txtValor);
                valor += valorSomar;
                if(valor>user.getDinheiro()){
                    Toast.makeText(EquipeActivity.this, "Os valores ultrapassam sua quantia máxima",Toast.LENGTH_SHORT).show();
                    valor -= valorSomar;
                }
                edtValorInvestir.setText(Integer.toString(valor));
            }
        });

        btOutroValor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtValorInvestir.requestFocus();
                UIUtil.showKeyboard(EquipeActivity.this, edtValorInvestir);
            }
        });
    }
}
