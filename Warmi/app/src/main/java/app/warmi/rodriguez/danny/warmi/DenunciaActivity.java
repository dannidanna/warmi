package app.warmi.rodriguez.danny.warmi;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import app.warmi.rodriguez.danny.warmi.Objects.Denuncia;
import app.warmi.rodriguez.danny.warmi.Objects.FirebaseReferences;
import app.warmi.rodriguez.danny.warmi.Objects.LocalizacionClass;
import app.warmi.rodriguez.danny.warmi.Objects.Persona;

public class DenunciaActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnGuardar, btnCancelar, btnCamara, btnGaleria;
    private ImageView imagen;
    private EditText nomDen,ciDen,telDen,nomVic, ciVic,telVic, descrip, fecha;
    private Spinner relacion;
    private String relaciones[] = {"Relacion con victima", "Esposo", "Hermana(o)", "Prima(o)", "Mamá","Papá",
            "Jefa(e)","Empleada(o)","Conocido(a)","Ninguno"};
    private String rel="";
    private static final int CAMARA_REQ = 1;
    private static final int GALERIA_INT = 1;
    private StorageReference storageReference;
    private String latitud;
    private String longitud;
    private String direccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        relacion = (Spinner) findViewById(R.id.relacion);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relaciones);
        relacion.setAdapter(adapter);
        relacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //relacion(i);
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
                    case 9:
                        rel = relaciones[i];
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast toast = Toast.makeText(getApplicationContext(),"Seleccione una relación", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        imagen = (ImageView) findViewById(R.id.imageView);
        btnCamara = (Button) findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener(this);
        btnGaleria = (Button) findViewById(R.id.btnGaleria);
        btnGaleria.setOnClickListener(this);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALERIA_INT && resultCode== RESULT_OK){
            Uri uri = data.getData();
            StorageReference dirArch = storageReference.child("Fotos").child(uri.getLastPathSegment());
            imagen.setImageURI(uri);
            dirArch.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "IMAGEN ALMACENADA", Toast.LENGTH_LONG).show();
                }
            });
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
            case R.id.btnGaleria:;
                Intent intent1 = new Intent(Intent.ACTION_PICK);
                intent1.setType("image/'");
                startActivityForResult(intent1,GALERIA_INT);
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

        Persona denunciante = new Persona(nombreDenunciante, ciDenunciante, telefonoDenunciante);
        Persona victima = new Persona(nombreVictima, ciVictima, telefonoVictima);
        LocalizacionClass localizacionObj = new LocalizacionClass(latitud, longitud, direccion);
        Denuncia denuncia = new Denuncia(denunciante, victima, descripcionDen, fechaDen, rel, localizacionObj);
        reference.child(FirebaseReferences.DENUNCIA_REFE).push().setValue(denuncia);
        Toast.makeText(getApplicationContext(),"Denuncia registrada", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(DenunciaActivity.this, MainActivity.class);
        startActivity(intent);
    }


    private void llamarIntent(){
        Intent tomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFoto.resolveActivity(getPackageManager())!=null){
            startActivityForResult(tomarFoto, CAMARA_REQ);
        }
    }


    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        DenunciaActivity.Localizacion Local = new DenunciaActivity.Localizacion();
        Local.setDenunciaActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }

        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion = DirCalle.getAddressLine(0);
                    //latitud = String.valueOf(loc.getLatitude());
                    //longitud = String.valueOf(loc.getLongitude());
                    //longitud = loc.getLongitude();
                   // mensaje2.setText("Mi direccion es: \n" DirCalle.getAddressLine(0));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //CLASS LOCALIZACION
    public class Localizacion implements LocationListener {
        DenunciaActivity denunciaActivity;
        double lat;
        double lon;
        String dir;

        public DenunciaActivity getDenunciaActivity() {
            return denunciaActivity;
        }

        public void setDenunciaActivity(DenunciaActivity denunciaAct) {
            this.denunciaActivity = denunciaAct;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            //String Text = "Mi ubicacion actual es: " + "\n Lat = "                    + loc.getLatitude() + "\n Long = " + loc.getLongitude();
           // mensaje1.setText(Text);
            lat = loc.getLatitude();
            lon = loc.getLongitude();
            this.denunciaActivity.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
           // mensaje1.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
           // mensaje1.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }

}
