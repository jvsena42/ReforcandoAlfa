package com.app.reforcandoalfa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.reforcandoalfa.R;
import com.app.reforcandoalfa.activity.LoginAlunoActivity;
import com.app.reforcandoalfa.activity.LoginProfessorActivity;
import com.app.reforcandoalfa.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void telaProfessor (View view){
        startActivity(new Intent(getApplicationContext(),LoginProfessorActivity.class));
    }


    public void telaAluno(View view){
        startActivity(new Intent(getApplicationContext(),LoginAlunoActivity.class));
    }

    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        if (autenticacao.getCurrentUser() != null){
            startActivity(new Intent(this,TelaPrincipalActivity.class));
            finish();
        }
    }


}
