package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UsuarioActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnCerrarSesion;
    private Button btnAtras;
    private TextView txtNombre;
    private TextView txtTelefono;
    private TextView txtCorreo;
    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;
    private DatabaseReference dbReferencia;

    @Override
    protected void onStart(){
        super.onStart();
        autentificacion.addAuthStateListener(autenLis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        txtNombre = (TextView) findViewById(R.id.articulo);
        txtTelefono = (TextView) findViewById(R.id.telefono);
        txtCorreo = (TextView) findViewById(R.id.correo);

        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Toast.makeText(getApplicationContext(),"Inicie sesion para ver la informacion de su cuenta", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UsuarioActivity.this, IniciarSesionActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    if(firebaseAuth.getCurrentUser()!=null){
                        dbReferencia = FirebaseDatabase.getInstance().getReference().child("Usuarios");
                        dbReferencia.keepSynced(true);
                        dbReferencia.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                txtNombre.setText(String.valueOf(dataSnapshot.child("Nombre").getValue()));
                                txtTelefono.setText(String.valueOf(dataSnapshot.child("Telefono").getValue()));
                                txtCorreo.setText(String.valueOf(dataSnapshot.child("Correo").getValue()));

                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        };

        btnCerrarSesion = (Button) findViewById(R.id.btnCerrarSesion);
        btnCerrarSesion.setOnClickListener(this);
        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCerrarSesion:
                if (autentificacion.getCurrentUser() != null)
                    autentificacion.signOut();
                Intent intentCerrarSesion = new Intent(UsuarioActivity.this, MainActivity.class);
                startActivity(intentCerrarSesion);
                break;
            case R.id.btnAtras:
                Intent intentAtras = new Intent(UsuarioActivity.this, MainActivity.class);
                startActivity(intentAtras);
                break;
        }

    }
}
