package app.warmi.rodriguez.danny.warmi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import app.warmi.rodriguez.danny.warmi.Objects.Denuncia;
import app.warmi.rodriguez.danny.warmi.Objects.FirebaseReferences;
import app.warmi.rodriguez.danny.warmi.Objects.Localizacion;
import app.warmi.rodriguez.danny.warmi.Objects.Persona;

public class DenunciaActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnGuardar, btnCancelar, btnCamara;
    private ImageView imagen;
    private EditText nomDen,ciDen,telDen,nomVic, ciVic,telVic, descrip, fecha;
    private Spinner relacion;
    private String relaciones[] = {"Relacion con victima", "Esposo", "Hermana(o)", "Prima(o)", "Mamá","Papá",
            "Jefa(e)","Empleada(o)","Ninguno"};
    private String rel="";
    private static final int CAMARA_REQ = 1;
    private StorageReference storageReference;
    private ProgressDialog dialogo;
    private String mCurrentPhotoPath;
    private double latitud;
    private double longitud;
    private String direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        //Localizacion localizacion = new Localizacion(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();

        relacion = (Spinner) findViewById(R.id.relacion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relaciones);
        relacion.setAdapter(adapter);
        relacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    relacion(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast toast = Toast.makeText(getApplicationContext(),"Seleccione una relacion", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        storageReference = FirebaseStorage.getInstance().getReference();
        imagen = (ImageView) findViewById(R.id.imageView);
        btnCamara = (Button) findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener(this);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMARA_REQ && resultCode== RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imgBit = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imgBit);
        }
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnGuardar:
                guardarDenuncia();
                break;
            case R.id.btnCancelar:
                Intent intent = new Intent(DenunciaActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCamara:
                llamarIntent();
                break;
        }
   }

    private void guardarDenuncia(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
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

        latitud = 40.75594;
        longitud = -73.98703835;
        direccion = "582 7th. Ave";


        Persona denunciante = new Persona(nombreDenunciante, ciDenunciante, telefonoDenunciante);
        Persona victima = new Persona(nombreVictima, ciVictima, telefonoVictima);
        Localizacion localizacion = new Localizacion(latitud, longitud, direccion);
        Denuncia denuncia = new Denuncia(denunciante, victima, descripcionDen, fechaDen, rel, localizacion);
        reference.child(FirebaseReferences.DENUNCIA_REFE).push().setValue(denuncia);
        Toast.makeText(getApplicationContext(),"Denuncia registrada", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DenunciaActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private String relacion(int i){
        String rel ="";
            switch (i){
                case 1:
                rel =  relaciones[i];
                break;
            case 2:
                rel =  relaciones[i];
                break;
            case 3:
                rel =  relaciones[i];
                break;
            case 4:
                rel =  relaciones[i];
                break;
            case 5:
                rel =  relaciones[i];
                break;
            case 6:
                rel =  relaciones[i];
                break;
            case 7:
                rel =  relaciones[i];
                break;
            case 8:
                rel =  relaciones[i];
                break;
        }
        return rel;
    }

    private void llamarIntent(){
        Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFoto.resolveActivity(getPackageManager())!=null){
            startActivityForResult(tomarFoto, CAMARA_REQ);
        }

    }


}
