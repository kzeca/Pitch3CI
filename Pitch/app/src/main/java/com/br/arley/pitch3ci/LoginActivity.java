package com.br.arley.pitch3ci;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText editEmail;
    TextInputEditText editPassword;
    Usuario user;
    ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new Usuario();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = findViewById(R.id.activity_login_edt_email);
        progressBar = findViewById(R.id.activity_login_progressBar);
        editPassword = findViewById(R.id.activity_login_edt_password);
        editPassword.setOnEditorActionListener(editorListener);
        editEmail.setOnEditorActionListener(editorListener);



        Button btLogar = (Button) findViewById(R.id.bt_entrar);
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setEmail(editEmail.getText().toString());
                user.setRa(editPassword.getText().toString());

                if(user.getEmail().isEmpty() || user.getRa().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Os campos de email e senha são obrigatórios",
                            Toast.LENGTH_SHORT).show();
                }else {
                    String email = user.getEmail();
                    String password = user.getRa();
                    progressBar.setVisibility(View.VISIBLE);
                    login(email, password);
                }
            }
        });

    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    progressBar.setVisibility(View.GONE);
                    finish();
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Dica: Login seu email e senha seu RA", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            user.setEmail(editEmail.getText().toString());
            user.setRa(editPassword.getText().toString());

            switch (actionId) {
                case EditorInfo.IME_ACTION_SEND:
                    if(user.getEmail().isEmpty() || user.getRa().isEmpty()){
                        Toast.makeText(getApplicationContext(),
                                "Os campos de email e senha são obrigatórios",
                                Toast.LENGTH_SHORT).show();
                                break;
                    }else {
                        String email = user.getEmail();
                        String password = user.getRa();
                        progressBar.setVisibility(View.VISIBLE);
                        login(email, password);
                        break;
                    }
            }
            return false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
