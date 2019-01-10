package com.app.reforcandoalfa.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.reforcandoalfa.R;
import com.app.reforcandoalfa.config.ConfiguracaoFirebase;
import com.app.reforcandoalfa.helper.base64Custom;
import com.app.reforcandoalfa.model.Aluno;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText campoNome,campoSobrenome,campoEmail,campoCelular,campoSenha,campoSenhaConf;
    private Button botaoCadastrar;
    private Aluno aluno;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        campoNome = findViewById(R.id.editNomeAl);
        campoSobrenome = findViewById(R.id.editSobrenomeAl);
        campoEmail = findViewById(R.id.editLoginEmail);
        campoCelular = findViewById(R.id.editCelularAl);
        campoSenha = findViewById(R.id.editSenhaAl);
        campoSenhaConf = findViewById(R.id.editSenhaConfAl);
        botaoCadastrar = findViewById(R.id.buttonCadastrarAl);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textoNome = campoNome.getText().toString();
                String textoSobrenome = campoSobrenome.getText().toString();
                String textoEmail = campoEmail.getText().toString();
                String textoCelular = campoCelular.getText().toString();
                String textoSenha = campoSenha.getText().toString();
                String textoSenhaConf = campoSenhaConf.getText().toString();

                if (textoNome.isEmpty() || textoSobrenome.isEmpty() || textoEmail.isEmpty() || textoCelular.isEmpty() || textoSenha.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Preencha todos os campos!",Toast.LENGTH_SHORT).show();
                }else if (!textoSenha.equals(textoSenhaConf) ){
                    Toast.makeText(getApplicationContext(),"Digite a senha corretamete!",Toast.LENGTH_SHORT).show();
                }else {
                    aluno = new Aluno();
                    aluno.setNome(textoNome);
                    aluno.setSobrenome(textoSobrenome);
                    aluno.setEmail(textoEmail);
                    aluno.setCelular(textoCelular);
                    aluno.setSenha(textoSenha);
                    cadastrarAluno();
                }
            }
        });
    }

    public void cadastrarAluno(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(aluno.getEmail(),aluno.getSenha())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            String idUsuario = base64Custom.codificarBase64(aluno.getEmail());
                            aluno.setIdUsuario(idUsuario);
                            aluno.salvar();
                            finish();
                        }else {
                            String excecao;

                            try {
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e){
                                excecao = "Digite uma senha mais forte!";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                excecao = "Digite um email valido!";
                            }catch (FirebaseAuthUserCollisionException e){
                                excecao = "Usuario já cadastrado!";
                            }catch (Exception e){
                                excecao = "Erro ao cadastrar usuario: " + e.getMessage();
                                e.printStackTrace();
                            }
                            Toast.makeText(getApplicationContext(),excecao,Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }

}
