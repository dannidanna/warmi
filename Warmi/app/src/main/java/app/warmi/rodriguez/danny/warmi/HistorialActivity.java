package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import app.warmi.rodriguez.danny.warmi.Objects.Denuncia;


public class HistorialActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_historial);
        lista = (RecyclerView) findViewById(R.id.recHis);
        lista.setLayoutManager(new LinearLayoutManager(this));
        bdReferencia = FirebaseDatabase.getInstance().getReference("Usuarios");
        bdReferencia.keepSynced(true);
        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                    if(firebaseAuth.getCurrentUser()!=null){
                    DatabaseReference bdUsuarios = bdReferencia.child(autentificacion.getCurrentUser().getUid()).child("Denuncias");
                    FirebaseRecyclerAdapter<Denuncia, UserViewHolder> firebaseRecyclerAdapter =
                            new FirebaseRecyclerAdapter<Denuncia, UserViewHolder>(
                            Denuncia.class,
                            R.layout.vista_denuncia,
                            UserViewHolder.class,
                            bdUsuarios
                    ) {
                        @Override
                        protected void populateViewHolder(final UserViewHolder holder, Denuncia model , int position) {
                            holder.txtNomVic.setText(model.getNombreVictima());
                            holder.txtNumVic.setText(model.getNumeroVictima());
                            holder.txtNomAgre.setText(model.getNombreAgresor());
                            holder.txtRelacion.setText(model.getRelacion());
                            holder.txtFecha.setText(model.getFecha());
                            holder.txtDescripcion.setText(model.getDescripcion());
                            Picasso.with(HistorialActivity.this).load(model.getUrlDescarga()).into(holder.viewImagen);
                        }
                    };
                    lista.setAdapter(firebaseRecyclerAdapter);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Inicie sesion para ver el historial de sus denuncias",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(HistorialActivity.this, IniciarSesionActivity.class);
                        startActivity(intent);
                        finish();
                    }
            }
        };

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

            txtNomVic = (TextView) itemView.findViewById(R.id.nombre);
            txtNumVic = (TextView) itemView.findViewById(R.id.telefono);
            txtNomAgre = (TextView) itemView.findViewById(R.id.servicio);
            txtRelacion = (TextView) itemView.findViewById(R.id.pagina);
            txtFecha = (TextView) itemView.findViewById(R.id.fecha);
            txtDescripcion = (TextView) itemView.findViewById(R.id.test);
            viewImagen = (ImageView) itemView.findViewById(R.id.imagen);
        }
    }
}
