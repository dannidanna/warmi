package app.warmi.rodriguez.danny.warmi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DDHHActivity extends AppCompatActivity {

    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;
    private RecyclerView lista;
    private DatabaseReference bdReferencia;

    @Override
    protected void onStart(){
        super.onStart();
        autentificacion.addAuthStateListener(autenLis);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddhh);

        lista = (RecyclerView) findViewById(R.id.ddhh);
        lista.setLayoutManager(new LinearLayoutManager(this));
        bdReferencia = FirebaseDatabase.getInstance().getReference().child("DDHH");
        bdReferencia.keepSynced(true);
        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseRecyclerAdapter<DDHHActivity.User, DDHHActivity.UserViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<User, UserViewHolder>(
                                DDHHActivity.User.class,
                                R.layout.vista_ddhh,
                                DDHHActivity.UserViewHolder.class,
                                bdReferencia
                        ) {
                    @Override
                    protected void populateViewHolder(UserViewHolder viewHolder, User model, int position) {
                        viewHolder.nombre.setText(model.Nombre);
                        viewHolder.articulo.setText(model.Articulo);
                        viewHolder.descripcion.setText(model.Descripcion);

                    }
                };
                lista.setAdapter(firebaseRecyclerAdapter);
            }
        };

    }

    public static class User {

        String Nombre;
        String Articulo;
        String Descripcion;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView articulo;
        TextView descripcion;

        public UserViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre);
            articulo = (TextView) itemView.findViewById(R.id.articulo);
            descripcion = (TextView) itemView.findViewById(R.id.descripcion);
        }
    }
}


