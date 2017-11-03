package app.warmi.rodriguez.danny.warmi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InstInfoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_inst_info);

        lista = (RecyclerView) findViewById(R.id.instituciones);
        lista.setLayoutManager(new LinearLayoutManager(this));
        bdReferencia = FirebaseDatabase.getInstance().getReference().child("Instituciones");
        bdReferencia.keepSynced(true);
        autentificacion = FirebaseAuth.getInstance();
        autenLis = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseRecyclerAdapter<InstInfoActivity.User, InstInfoActivity.UserViewHolder> firebaseRecyclerAdapter =
                            new FirebaseRecyclerAdapter<InstInfoActivity.User, InstInfoActivity.UserViewHolder>(
                            InstInfoActivity.User.class,
                            R.layout.vista_instituciones,
                            InstInfoActivity.UserViewHolder.class,
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

            nombre = (TextView) itemView.findViewById(R.id.articulo);
            direccion = (TextView) itemView.findViewById(R.id.nombre);
            telefono = (TextView) itemView.findViewById(R.id.telefono);
            servicio = (TextView) itemView.findViewById(R.id.servicio);
            pagina = (TextView) itemView.findViewById(R.id.pagina);
        }
    }
}
