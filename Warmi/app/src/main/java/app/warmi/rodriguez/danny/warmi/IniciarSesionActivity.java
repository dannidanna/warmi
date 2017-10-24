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

public class IniciarSesionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText correo;
    private EditText contra;
    private Button btnIngresar;
    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;
    private ProgressDialog miDialogo;
    private TextView crearCuenta;

    @Override
    protected void onStart(){
        super.onStart();
        autentificacion.addAuthStateListener(autenLis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);

        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Toast.makeText(getApplicationContext(),"Ya se encuentra en su sesion", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(IniciarSesionActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        correo = (EditText) findViewById(R.id.correo);
        contra = (EditText) findViewById(R.id.contra);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
        miDialogo = new ProgressDialog(this);
        crearCuenta = (TextView) findViewById(R.id.crearCuenta);
        crearCuenta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnIngresar:
                iniciarSesion();
                break;
            case R.id.crearCuenta:
                Intent intentCrear = new Intent(IniciarSesionActivity.this, RegistroActivity.class);
                startActivity(intentCrear);
                finish();
                break;
        }
    }

    private void iniciarSesion(){
        String cor = correo.getText().toString().trim();
        String con = contra.getText().toString().trim();

        if(!TextUtils.isEmpty(cor) && !TextUtils.isEmpty(con)){
            miDialogo.setMessage("Iniciando sesion... por favor espere!");
            miDialogo.show();
            autentificacion.signInWithEmailAndPassword(cor,con).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    miDialogo.dismiss();
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Inicio sesion correctamente!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(IniciarSesionActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No se pudo iniciar sesion, vuelva a intentarlo", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }

    }
}
