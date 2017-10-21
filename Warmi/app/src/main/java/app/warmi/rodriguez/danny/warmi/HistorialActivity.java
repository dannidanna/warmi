package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HistorialActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAtras;
    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;

    @Override
    protected void onStart(){
        super.onStart();
        autentificacion.addAuthStateListener(autenLis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Toast.makeText(getApplicationContext(),"Inicie sesion para ver el historial de sus denuncias",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HistorialActivity.this, IniciarSesionActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };

        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAtras:
                Intent intent = new Intent(HistorialActivity.this, MainActivity.class);
                startActivity(intent);
                break;
    }}
}
