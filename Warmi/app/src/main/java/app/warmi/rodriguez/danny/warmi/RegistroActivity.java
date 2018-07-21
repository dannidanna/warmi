package app.warmi.rodriguez.danny.warmi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nombre;
    private EditText correo;
    private EditText celular;
    private EditText contra;
    private Button btnRegistrar;
    private TextView cuenta;

    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;
    private ProgressDialog miDialogo;

    @Override
    protected void onStart(){
        super.onStart();
        autentificacion.addAuthStateListener(autenLis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        autentificacion = FirebaseAuth.getInstance();
        nombre = (EditText) findViewById(R.id.test);
        correo = (EditText) findViewById(R.id.correo);
        celular = (EditText) findViewById(R.id.celular);
        contra = (EditText) findViewById(R.id.contra);
        btnRegistrar = (Button) findViewById(R.id.btnAtras);
        btnRegistrar.setOnClickListener(this);
        miDialogo = new ProgressDialog(this);
        cuenta = (TextView) findViewById(R.id.cuenta);
        cuenta.setOnClickListener(this);

        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Toast.makeText(getApplicationContext(),"Ya se encuentra en su sesión", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAtras:
                iniciarRegistro();
                break;
            case R.id.cuenta:
                Intent intent = new Intent(RegistroActivity.this, IniciarSesionActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void iniciarRegistro(){
        final String nom = nombre.getText().toString().trim();
        final String cor = correo.getText().toString().trim();
        final String cel = celular.getText().toString().trim();
        final String con = contra.getText().toString().trim();

        if(!TextUtils.isEmpty(nom) && !TextUtils.isEmpty(cor)&& !TextUtils.isEmpty(con)){
            miDialogo.setMessage("Registrando... por favor espere.");
            miDialogo.show();
            autentificacion.createUserWithEmailAndPassword(cor, con).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    miDialogo.dismiss();
                    if(task.isSuccessful()){
                        autentificacion.signInWithEmailAndPassword(cor,con);
                        DatabaseReference bd = FirebaseDatabase.getInstance().getReference().child("Usuarios");
                        DatabaseReference bdUsuarios = bd.child(autentificacion.getCurrentUser().getUid());
                        bdUsuarios.child("Nombre").setValue(nom);
                        bdUsuarios.child("Correo").setValue(cor);
                        bdUsuarios.child("Telefono").setValue(cel);
                        Toast.makeText(getApplicationContext(), "USUARIO REGISTRADO", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "NO SE PUDO REGISTRAR, VUELVA A INTENTARLO", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}