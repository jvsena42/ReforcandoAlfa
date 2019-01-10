package com.app.reforcandoalfa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.reforcandoalfa.R;
import com.app.reforcandoalfa.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class LoginProfessorActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void telaCadastroProfessor (View view){
        startActivity(new Intent(getApplicationContext(),CadastroProfessorActivity.class));
    }

    public void abrirTelaPrincipal(){
        startActivity(new Intent(this,TelaPrincipalActivity.class));
        finish();
    }

    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if (autenticacao.getCurrentUser() != null){
            startActivity(new Intent(this,TelaPrincipalActivity.class));
            finish();
        }
    }
}
