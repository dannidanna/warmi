package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
                        viewHolder.direccion.setText(model.Direccion);
                        viewHolder.telefono.setText(model.Telefono);
                        viewHolder.servicio.setText(model.Servicio);
                        viewHolder.pagina.setText(model.PaginaReferencia);

                    }
                };
                lista.setAdapter(firebaseRecyclerAdapter);
            }
        };

    }

    public static class User {

        String Nombre;
        String Direccion;
        String Telefono;
        String Servicio;
        String PaginaReferencia;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView nombre;
        TextView direccion;
        TextView telefono;
        TextView servicio;
        TextView pagina;

        public UserViewHolder(View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.nombre);
            direccion = (TextView) itemView.findViewById(R.id.direccion);
            telefono = (TextView) itemView.findViewById(R.id.telefono);
            servicio = (TextView) itemView.findViewById(R.id.servicio);
            pagina = (TextView) itemView.findViewById(R.id.pagina);
        }
    }
}

