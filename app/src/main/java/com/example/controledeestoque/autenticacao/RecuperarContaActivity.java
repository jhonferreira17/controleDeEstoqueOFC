package com.example.controledeestoque.autenticacao;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controledeestoque.R;
import com.example.controledeestoque.helper.FirebaseHelper;

public class RecuperarContaActivity extends AppCompatActivity {

    private EditText edit_email;
    private Button buttonrecuperarConta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_conta);

        iniciaComponentes();
        buttonrecuperarConta.setOnClickListener(v->recuperarSenha());
    }

    public void recuperarSenha() {
        String email = edit_email.getText().toString().trim();

        if (!email.isEmpty()) {

            enviarEmail(email);

        } else {
            edit_email.requestFocus();
            edit_email.setError("Informe seu email.");
        }
    }

    private void enviarEmail(String email) {
        FirebaseHelper.getAuth().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Email enviado com sucess!", Toast.LENGTH_LONG).show();
            } else {
                String error = task.getException().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void iniciaComponentes() {
        edit_email = findViewById(R.id.editRecuperarConta);
        buttonrecuperarConta = findViewById(R.id.buttonRecuperarConta);

    }
}