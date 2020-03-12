package com.br.arley.pitch3ci;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import static com.br.arley.pitch3ci.ChooseActivity.equipe;
import androidx.appcompat.app.AppCompatActivity;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class EquipeActivity extends AppCompatActivity {

    EditText edtValorInvestir;
    Equipe equipe;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipe);
        equipe = ChooseActivity.equipe;
        System.out.println("EDUARDO : "+equipe.getId());
        Button btAdicionar1k = findViewById(R.id.equipe_activity_bt_1000);
        Button btAdicionar10k = findViewById(R.id.equipe_activity_bt_10000);
        Button btAdicionar20k = findViewById(R.id.equipe_activity_bt_20000);
        Button btAdicionar50k = findViewById(R.id.equipe_activity_bt_50000);
        Button btOutroValor = findViewById(R.id.equipe_activity_bt_outro_valor);
        Button btInvestir = findViewById(R.id.equipe_activity_bt_investir);
        ImageButton btnVoltar = findViewById(R.id.equipe_activity_bt_voltar);
        edtValorInvestir = findViewById(R.id.equipe_activity_edt_valor_investir);


        btInvestir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(edtValorInvestir.getText().toString())>100000){
                    Toast.makeText(EquipeActivity.this, "Os valores ultrapassam sua quantia máxima",Toast.LENGTH_SHORT).show();
                }else {
                    finish();
                    Toast.makeText(EquipeActivity.this, "Obrigado por avaliar", Toast.LENGTH_SHORT).show();
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
                if(valor>100000){
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
                if(valor>100000){
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
                if(valor>100000){
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
                if(valor>100000){
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
