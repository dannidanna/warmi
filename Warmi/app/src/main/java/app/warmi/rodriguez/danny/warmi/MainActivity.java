package app.warmi.rodriguez.danny.warmi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import app.warmi.rodriguez.danny.warmi.Objects.Denuncia;
import app.warmi.rodriguez.danny.warmi.Objects.FirebaseReferences;
import app.warmi.rodriguez.danny.warmi.Objects.Persona;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Persona denunciante = new Persona("Danny", 2345, 4365141);
        Persona victima = new Persona("Maria", 8473, 7349924);

        Denuncia denuncia = new Denuncia(denunciante, victima, "Mujer golpeada", "15/09/17", "Ninguna");
        Denuncia denuncia2 = new Denuncia(denunciante, victima, "Abuso de poder", "15/09/17", "Ninguna");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(FirebaseReferences.DENUNCIA_REFE);//REFERENCIA
        reference.child(FirebaseReferences.DENUNCIA_REFE).push().setValue(denuncia);
        reference.child(FirebaseReferences.DENUNCIA_REFE).push().setValue(denuncia2);

    }
}
