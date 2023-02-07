package com.example.controledeestoque.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.controledeestoque.R;
import com.example.controledeestoque.autenticacao.LoginActivity;
import com.example.controledeestoque.helper.FirebaseHelper;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(this::verificaLogin, 3000);
    }

    private void verificaLogin() {
        if (FirebaseHelper.getAutenticado()) {
            startActivity(new Intent(this, ControleProdutoActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}