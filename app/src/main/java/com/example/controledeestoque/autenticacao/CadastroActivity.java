package com.example.controledeestoque.autenticacao;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.controledeestoque.MainActivity;
import com.example.controledeestoque.R;
import com.example.controledeestoque.helper.FirebaseHelper;
import com.example.controledeestoque.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private EditText editNome;
    private EditText editEmail;
    private EditText editSenha;
    private Button botaoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        iniciarComponentes();
        botaoCadastro.setOnClickListener(v-> validaDados());


    }


    private void validaDados(){
        String email = editEmail.getText().toString().trim();
        String senha = editSenha.getText().toString().trim();
        String nome = editNome.getText().toString().trim();

        if(!nome.isEmpty()){
            if(!email.isEmpty()){
                if(!senha.isEmpty()){
                    Usuario usuario = new Usuario();
                    usuario.setNome(nome);
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    criarContaFirebase(usuario);
                }else{
                    Toast.makeText(this, "Informe sua senha", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Informe seu em-mail", Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(this, "Informe seu nome", Toast.LENGTH_SHORT).show();
        }
    }


    private void criarContaFirebase(Usuario usuario){
        FirebaseHelper.getAuth().createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               String id = task.getResult().getUser().getUid();
               usuario.setId(id);

               finish();
               startActivity(new Intent(this, MainActivity.class));
           }
        });
    }



    private void iniciarComponentes(){
        editEmail = findViewById(R.id.editCadEmail);
        editNome = findViewById(R.id.editCadNome);
        editSenha = findViewById(R.id.editCadSenha);
        botaoCadastro = findViewById(R.id.btnCadastro);
    }
}