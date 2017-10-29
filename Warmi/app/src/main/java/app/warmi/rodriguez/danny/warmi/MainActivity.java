package app.warmi.rodriguez.danny.warmi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnDenuncia;
    Button btnInstMaps;
    Button btnInstInfo;
    Button btnHistorial;
    Button btnRegistro;
    Button btnIniciar;
    Button btnCuenta;

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
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        btnRegistro.setOnClickListener(this);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(this);
        btnCuenta = (Button) findViewById(R.id.btnCuenta);
        btnCuenta.setOnClickListener(this);

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
            case R.id.btnRegistro:
                Intent intent4 = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent4);
                break;
            case R.id.btnIniciar:
                Intent intentIni = new Intent(MainActivity.this, IniciarSesionActivity.class);
                startActivity(intentIni);
                break;
            case R.id.btnCuenta:
                Intent intentCuenta = new Intent(MainActivity.this, UsuarioActivity.class);
                startActivity(intentCuenta);
                break;
        }
    }
}
