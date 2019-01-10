package com.app.reforcandoalfa.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.reforcandoalfa.R;
import com.app.reforcandoalfa.config.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class TelaPrincipalActivity extends AppCompatActivity {

    private Button botaoDeslogar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);

        botaoDeslogar = findViewById(R.id.buttonDeslogar);
        botaoDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
                firebaseAuth.signOut();
                finish();
            }
        });
    }
}
