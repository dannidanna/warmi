package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.warmi.rodriguez.danny.warmi.Objects.Denuncia;
import app.warmi.rodriguez.danny.warmi.Objects.FirebaseReferences;
import app.warmi.rodriguez.danny.warmi.Objects.Persona;

public class DenunciaActivity extends AppCompatActivity {

    Button btnGuardar;
    Button btnCancelar;
    Button btnCamara;
    EditText nomDen;
    EditText ciDen;
    EditText telDen;
    EditText nomVic;
    EditText ciVic;
    EditText telVic;
    EditText descrip;
    EditText fecha;
    Spinner relacion;
    String relaciones[] = {"Relacion con victima", "Esposo", "Hermana(o)"};
    String rel="";

    public DenunciaActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();

        relacion = (Spinner) findViewById(R.id.relacion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relaciones);
        relacion.setAdapter(adapter);
        relacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i){
                    case 1:
                        rel =  relaciones[i];
                        break;
                    case 2:
                        rel =  relaciones[i];
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast toast = Toast.makeText(getApplicationContext(),"Seleccione una relacion", Toast.LENGTH_LONG);
                toast.show();
            }
        });




        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nomDen = (EditText) findViewById(R.id.nomDen);
                String nombreDenunciante = nomDen.getText().toString();
                ciDen = (EditText) findViewById(R.id.ciDen);
                String ciDenunciante = ciDen.getText().toString();
                telDen = (EditText) findViewById(R.id.numDen);
                String telefonoDenunciante = telDen.getText().toString();
                nomVic = (EditText) findViewById(R.id.nomVic);
                String nombreVictima = nomVic.getText().toString();
                ciVic = (EditText) findViewById(R.id.ciVic);
                String ciVictima = ciVic.getText().toString();
                telVic = (EditText) findViewById(R.id.numVic);
                String telefonoVictima = telVic.getText().toString();
                descrip = (EditText) findViewById(R.id.descDen);
                String descripcionDen = descrip.getText().toString();
                fecha = (EditText) findViewById(R.id.fechaDen);
                String fechaDen = fecha.getText().toString();
                if(nombreDenunciante!=""&& ciDenunciante!=""&&telefonoDenunciante!=""&& descripcionDen!=""&&fechaDen!=""){
                Persona denunciante = new Persona(nombreDenunciante, ciDenunciante, telefonoDenunciante);
                Persona victima = new Persona(nombreVictima, ciVictima, telefonoVictima);
                Denuncia denuncia = new Denuncia(denunciante, victima, descripcionDen, fechaDen, rel);
                    reference.child(FirebaseReferences.PERSONA_REFE).push().setValue(denunciante);
                    reference.child(FirebaseReferences.PERSONA_REFE).push().setValue(victima);
                reference.child(FirebaseReferences.DENUNCIA_REFE).push().setValue(denuncia);
                    Toast toast = Toast.makeText(getApplicationContext(),"Denuncia registrada", Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(DenunciaActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(),"Rellene los campos obligatorios", Toast.LENGTH_LONG);
                    toast.show();
                    System.exit(0);
                }

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
