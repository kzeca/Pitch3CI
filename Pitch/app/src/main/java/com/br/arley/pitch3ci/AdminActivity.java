package com.br.arley.pitch3ci;

import android.content.ActivityNotFoundException;
import android.media.audiofx.EnvironmentalReverb;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TextView;

import com.br.arley.pitch3ci.Modelo.Investimento;
import com.br.arley.pitch3ci.Modelo.Unidade;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import android.widget.Toast;
import android.util.Log;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    private static final int STORAGE_CODE = 1000;

    DatabaseReference dataBase;
    ArrayList<Unidade> usuarios;
    ArrayList<String> arrecadado;
    ArrayList<String> avaliacaoMedia;
    TextView texto;
    TextView txtPdfText;
    Button btnVerPdf;
    public static String pdfText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        dataBase = FirebaseDatabase.getInstance().getReference();
        usuarios = new ArrayList<>();
        arrecadado = new ArrayList<>();
        avaliacaoMedia = new ArrayList<>();
        btnVerPdf = findViewById(R.id.btnVerPdf);

        btnVerPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminActivity.this, VisualizarPdfActivity.class));
            }
        });

        dataBase.child("usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Unidade unidade = new Unidade();
                        unidade.setNome(ds.child("nome").getValue().toString());
                        ArrayList<Investimento> historico = new ArrayList<>();
                        for(int i=0;i<12;i++){
                            Investimento investimento = new Investimento();
                            investimento.setAvaliacao(Integer.parseInt(ds.child("historico").child("avaliacoes").child(Integer.toString(i)).getValue().toString()));
                            investimento.setInvestido(Integer.parseInt(ds.child("historico").child("investimentos").child(Integer.toString(i)).getValue().toString()));
                            investimento.setJustificativa(ds.child("historico").child("justificativas").child(Integer.toString(i)).getValue().toString());
                            historico.add(investimento);
                        }
                        unidade.setHistorico(historico);
                        usuarios.add(unidade);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        dataBase.child("equipes").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        avaliacaoMedia.add(ds.child("media_avaliacao").getValue().toString());
                        arrecadado.add(ds.child("arrecadacao").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button btDownload = (Button) findViewById(R.id.button_download);
        texto = findViewById(R.id.textView2);
        ImageButton btnVoltar = findViewById(R.id.equipe_activity_bt_voltar);

        btnVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED){
                    String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                    requestPermissions(permissions, STORAGE_CODE);
                }
                else {
                    createPdf();
                    System.out.println(pdfText);
                    btnVerPdf.setVisibility(View.VISIBLE);
                    texto.setText("- Local do download: " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/RelatórioPitch3CI.pdf");
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case STORAGE_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission was granted from popup, call savepdf method
                    createPdf();
                    texto.setText("- Local do download: " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/RelatórioPitch3CI.pdf");
                }
                else {
                    //permission was denied from popup, show error message
                    Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void createPdf(){
        // create a new document
        PdfDocument document = new PdfDocument();
        // crate a page description
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300,600,1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setTextSize(8);
        paint.setColor(Color.BLACK);
        canvas.drawText("Relatório do Pitch - 3CI", 20, 50, paint);
        canvas.drawText("- Equipes", 20, 80, paint);
        pdfText="- Equipes \n\n\n";

        canvas.drawText("Equipe 1: José, Natália C, Tainá", 20, 110, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(0) + ",00", 20, 125, paint);
        pdfText+="Equipe 1: José, Natália C, Tainá \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(0) + ",00" + "\n\n";

        canvas.drawText("Equipe 2: Arley, Isabella, Rebeca", 20, 145, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(1) + " - Arrecadado: R$" + arrecadado.get(1) + ",00", 20, 160, paint);
        pdfText+="Equipe 2: Arley, Isabella, Rebeca \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(1) + " - Arrecadado: R$" + arrecadado.get(1) + ",00" + "\n\n";

        canvas.drawText("Equipe 3: Felipe, Diogo, Gabrielle", 20, 180, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(2) + " - Arrecadado: R$" + arrecadado.get(2) + ",00", 20, 195, paint);
        pdfText+="Equipe 3: Felipe, Diogo, Gabrielle \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(2) + ",00" + "\n\n";

        canvas.drawText("Equipe 4: Débora, Washington, Victor", 20, 215, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(3) + " - Arrecadado: R$" + arrecadado.get(3) + ",00", 20, 230, paint);
        pdfText+="Equipe 4: Débora, Washington, Victor \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(3) + ",00" + "\n\n";

        canvas.drawText("Equipe 5: Yasmim, Louan, Laura", 20, 250, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(4) + " - Arrecadado: R$" + arrecadado.get(4) + ",00", 20, 265, paint);
        pdfText+="Equipe 5: Yasmim, Louan, Laura \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(4) + ",00" + "\n\n";

        canvas.drawText("Equipe 6: Luiz, Brenner, Brenda", 20, 285, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(5) + " - Arrecadado: R$" + arrecadado.get(5) + ",00", 20, 300, paint);
        pdfText+="Equipe 6: Luiz, Brenner, Brenda \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(5) + ",00" + "\n\n";

        canvas.drawText("Equipe 7: Isaac, Arnely, Kevin", 20, 320, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(6) + " - Arrecadado: R$" + arrecadado.get(6) + ",00", 20, 335, paint);
        pdfText+="Equipe 7: Isaac, Arnely, Kevin \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(6) + ",00" + "\n\n";

        canvas.drawText("Equipe 8: João, Júlio, Gabriel", 20, 355, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(7) + " - Arrecadado: R$" + arrecadado.get(7) + ",00", 20, 370, paint);
        pdfText+="Equipe 8: João, Júlio, Gabriel \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(7) + ",00" + "\n\n";

        canvas.drawText("Equipe 9: Airton, Carine, Bruno", 20, 390, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(8) + " - Arrecadado: R$" + arrecadado.get(8) + ",00", 20, 405, paint);
        pdfText+="Equipe 9: Airton, Carine, Bruno \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(8) + ",00" + "\n\n";

        canvas.drawText("Equipe 10: Lorenzo, Samuel, Vinicius", 20, 425, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(9) + " - Arrecadado: R$" + arrecadado.get(9) + ",00", 20, 440, paint);
        pdfText+="Equipe 10: Lorenzo, Samuel, Vinicius \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(9) + ",00" + "\n\n";


        canvas.drawText("Equipe 11: Sarah, Eduarda, Esther", 20, 460, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(10) + " - Arrecadado: R$" + arrecadado.get(10) + ",00",20, 475, paint);
        pdfText+="Equipe 11: Sarah, Eduarda, Esther \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(10) + ",00" + "\n\n";



        canvas.drawText("Equipe 12: Natália B, Raffaela, Thiilyn", 20, 495, paint);
        canvas.drawText("Avaliação média: " + avaliacaoMedia.get(11) + " - Arrecadado: R$" + arrecadado.get(11) + ",00", 20, 510, paint);
        pdfText+="Equipe 12: Natália B, Raffaela, Thiilyn \n";
        pdfText+="Avaliação média: " + avaliacaoMedia.get(0) + " - Arrecadado: R$" + arrecadado.get(11) + ",00" + "\n\n";


        document.finishPage(page);
        for(int i=2;i<=38;i++){
            pageInfo = new PdfDocument.PageInfo.Builder(300,600,i).create();
            page = document.startPage(pageInfo);
            canvas = page.getCanvas();
            paint = new Paint();
            paint.setTextSize(8);
            paint.setColor(Color.BLACK);
            String nome = usuarios.get(i-2).getNome();
            canvas.drawText("Nome: " + nome, 20, 50, paint);
            canvas.drawText("- Dinheiro investido", 20, 80, paint);
            pdfText+=" \n\n";
            pdfText+= "Nome: " + nome+" \n\n";
            pdfText+="- Dinheiro investido \n\n\n";

            paint.setTextSize(5);
            int equipePropria = -1;
            for(int j=0;j<12;j++){
                String investido = Integer.toString(usuarios.get(i-2).getHistorico().get(j).getInvestido());
                String avaliacao = Integer.toString(usuarios.get(i-2).getHistorico().get(j).getAvaliacao());
                if(avaliacao.equals("-1")){
                    equipePropria = j;
                    avaliacao = "Equipe própria";
                    canvas.drawText("Equipe " + Integer.toString(j+1) + ": " + avaliacao, 20, 110 + (j*15), paint);
                    pdfText+= "Equipe " + Integer.toString(j+1) + ": " + avaliacao + " \n\n";
                }
                else{
                    canvas.drawText("Equipe " + Integer.toString(j+1) + ": " + avaliacao + " - R$" + investido + ",00", 20, 110 + (j*15), paint);
                    pdfText+= "Equipe " + Integer.toString(j+1) + ": " + avaliacao + " - R$" + investido + ",00" + " \n\n";
                }
            }
            paint.setTextSize(8);
            canvas.drawText("- Justificativa", 20, 305, paint);
            pdfText+=" \n\n";
            pdfText+="- Justificativa \n\n";
            pdfText+=" \n";
            paint.setTextSize(5);
            for(int j=0;j<12;j++){
                if(equipePropria == j){
                    canvas.drawText("Equipe " + Integer.toString(j+1) + ": " + "Equipe própria", 20, 335 + (j*15), paint);
                    pdfText+= "Equipe " + Integer.toString(j+1) + ": " + "Equipe própria" + " \n\n";
                }
                else{
                    String justificativa = usuarios.get(i-2).getHistorico().get(j).getJustificativa();
                    canvas.drawText("Equipe " + Integer.toString(j+1) + ": " + justificativa, 20, 335 + (j*15), paint);
                    pdfText+= "Equipe " + Integer.toString(j+1) + ": " + justificativa + " \n\n";
                }
            }

            document.finishPage(page);
        }
        // write the document content
        String directory_path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String pathDir = directory_path + "/Download/";
        File file = new File(pathDir);
        if (!file.exists()) {
            file.mkdirs();
        }
        String targetPdf = pathDir+"RelatórioPitch3CI.pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(this, "PDF created", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error "+e.toString());
            Toast.makeText(this, "Something wrong: " + e.toString(),  Toast.LENGTH_LONG).show();
        }
        // close the document
        document.close();
    }

}






