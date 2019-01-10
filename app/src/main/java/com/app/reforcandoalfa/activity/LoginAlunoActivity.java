package com.app.reforcandoalfa.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.reforcandoalfa.R;
import com.app.reforcandoalfa.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class LoginAlunoActivity extends AppCompatActivity {

    private EditText campoEmail,campoSenha;
    private FirebaseAuth autenticacao;
    private Button botaoEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_aluno);

        campoEmail = findViewById(R.id.editLoginEmailAl);
        campoSenha = findViewById(R.id.editLoginSenhaAl);
        botaoEntrar = findViewById(R.id.buttonLoginEntrar2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void telaCadastroAluno(View view){
        startActivity(new Intent(this,CadastroAlunoActivity.class));
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
