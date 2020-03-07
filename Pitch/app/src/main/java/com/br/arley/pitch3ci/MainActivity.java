package com.br.arley.pitch3ci;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.View;
        import android.view.inputmethod.EditorInfo;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText email;
    TextInputEditText password;
    static Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        user = new Usuario();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.activity_login_edt_email);
        password = findViewById(R.id.activity_login_edt_password);
        password.setOnEditorActionListener(editorListener);
        email.setOnEditorActionListener(editorListener);



        Button btLogar = (Button) findViewById(R.id.bt_entrar);
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user.setEmail(email.getText().toString());
                user.setRa(password.getText().toString());

                if(user.getEmail().isEmpty() || user.getRa().isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Os campos de email e senha são obrigatórios",
                            Toast.LENGTH_SHORT).show();
                }else {
                    TextInputEditText nomeEmail = findViewById(R.id.activity_login_edt_email);
                    user.setNome(nomeEmail.getText().toString());
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        });

    }

    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

            user.setEmail(email.getText().toString());
            user.setRa(password.getText().toString());

            switch (actionId) {
                case EditorInfo.IME_ACTION_SEND:
                    if(user.getEmail().isEmpty() || user.getRa().isEmpty()){
                        Toast.makeText(getApplicationContext(),
                                "Os campos de email e senha são obrigatórios",
                                Toast.LENGTH_SHORT).show();
                                break;
                    }else {
                        TextInputEditText nomeEmail = findViewById(R.id.activity_login_edt_email);
                        user.setNome(nomeEmail.getText().toString());
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        break;
                    }
            }
            return false;
        }
    };


}
