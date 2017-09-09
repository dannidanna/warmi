package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.warmi.rodriguez.danny.warmi.Objects.Denuncia;
import app.warmi.rodriguez.danny.warmi.Objects.FirebaseReferences;
import app.warmi.rodriguez.danny.warmi.Objects.Persona;

public class DenunciaActivity extends AppCompatActivity {

    Button btnGuardar;
    Button btnCancelar;

    Denuncia denuncia;
    Persona perDenunciante;
    Persona perVictima;

    EditText denunciante;
    EditText victima;
    Number ciDenunciante;
    Number ciVictima;
    Number telDenunciante;
    Number telVictima;

    public DenunciaActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();

        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Persona denunciante = new Persona("Danny", 2345, 4365141);
                Persona victima = new Persona("Maria", 8473, 7349924);
                Denuncia denuncia = new Denuncia(denunciante, victima, "Hostigamiento laboral", "06/09/17", "Ninguna");
                Denuncia denuncia2 = new Denuncia(denunciante, victima, "Mala madre ", "15/09/17", "Ninguna");

                reference.child(FirebaseReferences.DENUNCIA_REFE).push().setValue(denuncia);

            }
        });

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DenunciaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
