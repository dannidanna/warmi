package app.warmi.rodriguez.danny.warmi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.warmi.rodriguez.danny.warmi.Objects.FirebaseReferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference(FirebaseReferences.FIRE_REFERENCE);//REFERENCIA
        //Log.i("MILLAVE", reference.getKey());
        reference.setValue(4);
        reference.addValueEventListener(new ValueEventListener() {  //OBTENEMOS SU VALOR
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                Log.i("DATO", value+"");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {  // cuando se cancela, por alguna razon no podemos obtener el valor
                Log.e("ERROR", databaseError.getMessage());
            }
        });


    }
}
