package app.warmi.rodriguez.danny.warmi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnDenuncia;
    private Button btnInstMaps;
    private Button btnInstInfo;
    private Button btnHistorial;
    private TextView btnCuenta;
    private Button btnDdhh;
    private Button btnDelitos;

    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;
    private DatabaseReference bdReferencia;

    @Override
    protected void onStart(){
        super.onStart();
        autentificacion.addAuthStateListener(autenLis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnDenuncia = (Button) findViewById(R.id.btnDenuncia);
        btnDenuncia.setOnClickListener(this);
        btnInstMaps = (Button) findViewById(R.id.btnInstMaps);
        btnInstMaps.setOnClickListener(this);
        btnInstInfo = (Button) findViewById(R.id.btnInstInfo);
        btnInstInfo.setOnClickListener(this);
        btnHistorial = (Button) findViewById(R.id.btnHistorial);
        btnHistorial.setOnClickListener(this);
        btnCuenta = (TextView) findViewById(R.id.btnCuenta);
        btnCuenta.setOnClickListener(this);
        btnDdhh = (Button) findViewById(R.id.btnDdhh);
        btnDdhh.setOnClickListener(this);
        btnDelitos = (Button) findViewById(R.id.btnDelitos);
        btnDelitos.setOnClickListener(this);

        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    bdReferencia = FirebaseDatabase.getInstance().getReference().child("Usuarios");
                    bdReferencia.keepSynced(true);
                    bdReferencia.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            btnCuenta.setText(String.valueOf(dataSnapshot.child("Nombre").getValue()));
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }

            }
        };

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnDenuncia:
                Intent intent = new Intent(MainActivity.this, DenunciaActivity.class);
                startActivity(intent);
                break;
            case R.id.btnInstMaps:
                Intent intent1 = new Intent(MainActivity.this, InstMapsActivity.class);
                startActivity(intent1);
                break;
            case R.id.btnInstInfo:
                Intent intent2 = new Intent(MainActivity.this, InstInfoActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnHistorial:
                Intent intent3 = new Intent(MainActivity.this, HistorialActivity.class);
                startActivity(intent3);
                break;
            case R.id.btnCuenta:
                Intent intentCuenta = new Intent(MainActivity.this, UsuarioActivity.class);
                startActivity(intentCuenta);
                break;
            case R.id.btnDdhh:
                Intent intentDdhh = new Intent(MainActivity.this, DDHHActivity.class);
                startActivity(intentDdhh);
                break;
            case R.id.btnDelitos:
                Intent intentDelitos = new Intent(MainActivity.this, DelitosActivity.class);
                startActivity(intentDelitos);
                break;
        }
    }
}
