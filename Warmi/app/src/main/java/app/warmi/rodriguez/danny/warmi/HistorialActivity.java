package app.warmi.rodriguez.danny.warmi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;


public class HistorialActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAtras;
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
                    FirebaseRecyclerAdapter<User, UserViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(
                            User.class,
                            R.layout.vista_denuncia,
                            UserViewHolder.class,
                            bdReferencia
                    ) {
                        @Override
                        protected void populateViewHolder(final UserViewHolder holder, User model, int position) {
                            holder.txtName.setText(model.Nombre);
                            holder.txtCorreo.setText(model.Correo);
                           /* if (!model.image.equals("default"))
                                Picasso.with(HistorialActivity.this).load(model.image).into(holder.imgProfile);*/
                        }
                    };
                    lista.setAdapter(firebaseRecyclerAdapter);

                }
            }
        };



        btnAtras = (Button) findViewById(R.id.btnAtras);
        btnAtras.setOnClickListener(this);
    }

    public static class User {
        String Nombre;
        String Correo;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAtras:
                Intent intent = new Intent(HistorialActivity.this, MainActivity.class);
                startActivity(intent);
                break;
    }
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        TextView txtCorreo;

        public UserViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.descripcion);
            txtCorreo = (TextView) itemView.findViewById(R.id.fecha);
        }
    }
}
