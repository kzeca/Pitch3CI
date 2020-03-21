package com.br.arley.pitch3ci;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class VisualizarPdfActivity extends AppCompatActivity {

    String textPdf;
    TextView tvPdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizador_pdf);
        textPdf = AdminActivity.pdfText;
        tvPdf = findViewById(R.id.tv_pdf);
        tvPdf.setText(textPdf);

    }

}