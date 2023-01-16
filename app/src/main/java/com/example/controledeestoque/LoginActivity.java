package com.example.controledeestoque;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.controledeestoque.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityLoginBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.textCadastro.setOnClickListener(view ->{
            startActivity(new Intent(this,CadastroActivity.class));

        });

        binding.textRecuperaConta.setOnClickListener(view ->{
            startActivity(new Intent(this,RecuperarContaActivity.class));

        });

    }
}