package com.example.controledeestoque.autenticacao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controledeestoque.R;
import com.example.controledeestoque.activity.ControleProdutoActivity;
import com.example.controledeestoque.helper.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {
    private EditText edit_email;
    private EditText edit_senha;
    private TextView text_criar_conta;
    private TextView text_recuperar_conta;
    private Button botaoLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        iniciaComponentes();
        configCliques();
      botaoLogar.setOnClickListener(v->logar());


    }
    public void logar(){

        String email = edit_email.getText().toString().trim();
        String senha = edit_senha.getText().toString().trim();

        if(!email.isEmpty()){
            if(!senha.isEmpty()){

                validaLogin(email, senha);

            }else {
                edit_senha.requestFocus();
                edit_senha.setError("Informe sua senha.");
            }
        }else {
            edit_email.requestFocus();
            edit_email.setError("Informe seu email.");
        }

    }

    private void validaLogin(String email, String senha){
        FirebaseHelper.getAuth().signInWithEmailAndPassword(
                email, senha
        ).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                finish();
                startActivity(new Intent(this, ControleProdutoActivity.class));
            }else{
                String error = task.getException().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configCliques(){
        text_criar_conta.setOnClickListener(view ->{
            startActivity(new Intent(this, CadastroActivity.class));

        });

        text_recuperar_conta.setOnClickListener(view ->{
            startActivity(new Intent(this, RecuperarContaActivity.class));

        });
    }

    private void iniciaComponentes() {
        edit_email = findViewById(R.id.editEmail);
        edit_senha = findViewById(R.id.editSenha);
        text_criar_conta = findViewById(R.id.textCadastro);
        text_recuperar_conta = findViewById(R.id.textRecuperaConta);
        botaoLogar = findViewById(R.id.btnLogin);
    }
}