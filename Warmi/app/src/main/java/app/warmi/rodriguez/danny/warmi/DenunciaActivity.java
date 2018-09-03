package app.warmi.rodriguez.danny.warmi;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import app.warmi.rodriguez.danny.warmi.Objects.Denuncia;
import app.warmi.rodriguez.danny.warmi.Objects.Denuncias;
import app.warmi.rodriguez.danny.warmi.Objects.LocalizacionClass;

public class DenunciaActivity extends AppCompatActivity implements View.OnClickListener {


    private Button btnGuardar, btnCancelar, btnCamara, btnGaleria;
    private ImageView imagen;
    private EditText nomVic, numVic, nomAgre, descrip;
    private Spinner relacion, tipoDen;
    private String relaciones[] = {"Relación con la afectada", "Esposo", "Hermana(o)", "Prima(o)", "Mamá","Papá",
            "Jefa(e)","Empleada(o)","Conocido(a)","Ninguno"};
    private String tipos[] = {"Tipo de violencia", "Violencia física", "Violencia psicológica", "Violencia sexual", "Violencia económica","Violencia patrimonial",
            "Violencia simbólica", "Violencia doméstica","Violencia institucional","Violencia laboral","Otro"};
    private String rel="";
    private String tip="";
    private static final int CAMARA_REQ = 1;
    private static final int GALERIA_INT = 1;
    private DatabaseReference bdReferencia;
    private DatabaseReference bdReferenciaUsuarios;
    private StorageReference storageReference;
    private String latitud;
    private String longitud;
    private String direccion;
    private String urlImagenD;

    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;
    private ProgressDialog miDialogo;

    private Denuncias denuncias;
    private String nombreUsuario;
    private String numeroUsuario;
    private String ubicacion;

    @Override
    protected void onStart(){
        super.onStart();
        autentificacion.addAuthStateListener(autenLis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

        miDialogo = new ProgressDialog(this);

        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Toast.makeText(getApplicationContext(),"Inicie sesión para realizar una denuncia", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DenunciaActivity.this, IniciarSesionActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    if(firebaseAuth.getCurrentUser()!=null){
                        bdReferencia = FirebaseDatabase.getInstance().getReference().child("Usuarios");
                        bdReferencia.keepSynced(true);
                        bdReferencia.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                nombreUsuario = String.valueOf(dataSnapshot.child("Nombre").getValue());
                                numeroUsuario = String.valueOf(dataSnapshot.child("Telefono").getValue());
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

            }
        };

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference reference = database.getReference();
        storageReference = FirebaseStorage.getInstance().getReference();

        relacion = (Spinner) findViewById(R.id.pagina);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relaciones);
        relacion.setAdapter(adapter);
        relacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
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

        //TIPO DE DENUNCIA
        tipoDen = (Spinner) findViewById(R.id.pagina2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos);
        tipoDen.setAdapter(adapter2);
        tipoDen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        tip =  tipos[i];
                        break;
                    case 2:
                        tip =  tipos[i];
                        break;
                    case 3:
                        tip =  tipos[i];
                        break;
                    case 4:
                        tip =  tipos[i];
                        break;
                    case 5:
                        tip =  tipos[i];
                        break;
                    case 6:
                        tip =  tipos[i];
                        break;
                    case 7:
                        tip =  tipos[i];
                        break;
                    case 8:
                        tip =  tipos[i];
                        break;
                    case 9:
                        tip = tipos[i];
                        break;
                    case 10:
                        tip =  tipos[i];
                        break;
                    case 11:
                        tip = tipos[i];
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast toast = Toast.makeText(getApplicationContext(),"Seleccione el tipo de violencia", Toast.LENGTH_LONG);
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
            miDialogo.setMessage("Subiendo imagen...");
            miDialogo.show();
           StorageReference dirArch = storageReference.child("Fotos").child(uri.getLastPathSegment());
            imagen.setImageURI(uri);
            dirArch.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") Uri urlImagen = taskSnapshot.getDownloadUrl();
                    urlImagenD = String.valueOf(urlImagen);
                    miDialogo.dismiss();
                    Toast.makeText(getApplicationContext(), "IMAGEN ALMACENADA", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    private void subirFoto(final Uri uri){
        if (autentificacion.getCurrentUser() == null)
            return;

        if (storageReference == null)
            storageReference = FirebaseStorage.getInstance().getReference();
        if (bdReferencia == null)
            bdReferencia = FirebaseDatabase.getInstance().getReference().child("Usuarios");

        final StorageReference filepath = storageReference.child("Fotos denuncias").child(getRandomString());
        final DatabaseReference currentUserDB = bdReferencia.child(autentificacion.getCurrentUser().getUid());

        miDialogo.setMessage("Subiendo imagen...");
        miDialogo.show();

        currentUserDB.child("image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String image = dataSnapshot.getValue().toString().trim();

                if (!image.equals("default") && !image.isEmpty()) {
                    Task<Void> task = FirebaseStorage.getInstance().getReferenceFromUrl(image).delete();
                    task.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                                Toast.makeText(getApplicationContext(), "Eliminación satisfactoria", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext(), "Eliminación erronea", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                currentUserDB.child("image").removeEventListener(this);

                filepath.putFile(uri).addOnSuccessListener(DenunciaActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        miDialogo.dismiss();
                        @SuppressWarnings("VisibleForTests") Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        Toast.makeText(getApplicationContext(), "Finished", Toast.LENGTH_SHORT).show();
                        DatabaseReference currentUserDB = bdReferencia.child(autentificacion.getCurrentUser().getUid());
                        currentUserDB.child("Imagen").setValue(downloadUrl.toString());

                    }
                }).addOnFailureListener(DenunciaActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String getRandomString() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
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
        nomVic = (EditText) findViewById(R.id.nombre);
        String nombreVictima = nomVic.getText().toString().trim();
        numVic = (EditText) findViewById(R.id.telefono);
        String numeroVictima= numVic.getText().toString().trim();
        nomAgre = (EditText) findViewById(R.id.servicio);
        String nombreAgresor = nomAgre.getText().toString().trim();
        descrip = (EditText) findViewById(R.id.descrip);
        String descripcionDen = descrip.getText().toString();


        Date d = new Date();
        String fechaDen  = (String) DateFormat.format("MMMM d, yyyy ", d.getTime());

        bdReferencia = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        bdReferenciaUsuarios = FirebaseDatabase.getInstance().getReference().child("DenunciasAll");
        final DatabaseReference currentUserDB = bdReferencia.child(autentificacion.getCurrentUser().getUid()).child("Denuncias");

        LocalizacionClass localizacionObj = new LocalizacionClass(latitud, longitud, direccion);
        String dire = localizacionObj.getDireccion();
        if(!descripcionDen.isEmpty() && !rel.isEmpty()) {
            Denuncia denuncia = new Denuncia(nombreVictima, numeroVictima, nombreAgresor, descripcionDen, fechaDen, rel, dire, urlImagenD, tip);
            currentUserDB.push().setValue(denuncia);

            //all denuncias
            denuncias = new Denuncias(nombreVictima, numeroVictima, nombreAgresor, descripcionDen, fechaDen, rel, dire,
                    urlImagenD, nombreUsuario, numeroUsuario, tip);
            bdReferenciaUsuarios.push().setValue(denuncias);
            Toast.makeText(getApplicationContext(),"Denuncia registrada", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DenunciaActivity.this, MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(),"Rellene todos los campos necesarios", Toast.LENGTH_SHORT).show();
        }

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

    //CLASE LOCALIZACION
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
