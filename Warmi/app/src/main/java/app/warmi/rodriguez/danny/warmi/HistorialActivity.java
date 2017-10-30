package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class HistorialActivity extends AppCompatActivity {

    private FirebaseAuth autentificacion;
    private FirebaseAuth.AuthStateListener autenLis;
    private RecyclerView lista;
    private DatabaseReference bdReferencia;

    @Override
    protected void onStart(){
        super.onStart();
        if (autentificacion.getCurrentUser() == null)
            return;
        autentificacion.addAuthStateListener(autenLis);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        lista = (RecyclerView) findViewById(R.id.recHis);
        lista.setLayoutManager(new LinearLayoutManager(this));
        bdReferencia = FirebaseDatabase.getInstance().getReference().child("Usuarios");
        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    Toast.makeText(getApplicationContext(),"Inicie sesion para ver el historial de sus denuncias",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(HistorialActivity.this, IniciarSesionActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    DatabaseReference bdUsuarios = bdReferencia.child(autentificacion.getCurrentUser().getUid());
                    FirebaseRecyclerAdapter<User, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(
                            User.class,
                            R.layout.vista_denuncia,
                            UserViewHolder.class,
                            bdUsuarios
                    ) {
                        @Override
                        protected void populateViewHolder(final UserViewHolder holder, User model, int position) {
                            holder.txtNomVic.setText(model.nombreVictima);
                            holder.txtNumVic.setText(model.numeroVictima);
                            holder.txtNomAgre.setText(model.nombreAgresor);
                            holder.txtRelacion.setText(model.relacion);
                            holder.txtFecha.setText(model.fecha);
                            holder.txtDescripcion.setText(model.descripcion);
                           //if (!model.urlDescarga.equals("default"))
                                Picasso.with(HistorialActivity.this).load(model.urlDescarga).into(holder.viewImagen);
                        }
                    };
                    lista.setAdapter(firebaseRecyclerAdapter);

                }
            }
        };

    }

    public static class User {

        String nombreVictima;
        String numeroVictima;
        String nombreAgresor;
        String relacion;
        String fecha;
        String descripcion;
        String urlDescarga;
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomVic;
        TextView txtNumVic;
        TextView txtNomAgre;
        TextView txtRelacion;
        TextView txtFecha;
        TextView txtDescripcion;
        ImageView viewImagen;

        public UserViewHolder(View itemView) {
            super(itemView);

            txtNomVic = (TextView) itemView.findViewById(R.id.direccion);
            txtNumVic = (TextView) itemView.findViewById(R.id.telefono);
            txtNomAgre = (TextView) itemView.findViewById(R.id.servicio);
            txtRelacion = (TextView) itemView.findViewById(R.id.pagina);
            txtFecha = (TextView) itemView.findViewById(R.id.fecha);
            txtDescripcion = (TextView) itemView.findViewById(R.id.descripcion);
            viewImagen = (ImageView) itemView.findViewById(R.id.imagen);
        }
    }
}
