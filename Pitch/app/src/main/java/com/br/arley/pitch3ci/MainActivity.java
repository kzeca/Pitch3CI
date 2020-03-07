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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password.setOnEditorActionListener(editorListener);
        email.setOnEditorActionListener(editorListener);



        Button btLogar = (Button) findViewById(R.id.bt_entrar);
        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().length()==0 || password.getText().length()==0){
                    Toast.makeText(getApplicationContext(),
                            "Os campos de email e senha são obrigatórios",
                            Toast.LENGTH_SHORT).show();
                }else {

                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        });

    }

    private TextView.OnEditorActionListener editorListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEND:
                    if(email.getText().length()==0 || password.getText().length()==0){
                        Toast.makeText(getApplicationContext(),
                                "Os campos de email e senha são obrigatórios",
                                Toast.LENGTH_SHORT).show();
                                break;
                    }else {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        break;
                    }
            }
            return false;
        }
    };


}
