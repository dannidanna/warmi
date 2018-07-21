package app.warmi.rodriguez.danny.warmi;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import app.warmi.rodriguez.danny.warmi.Objects.InstInfo;

public class InstMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FirebaseAuth.AuthStateListener autenLis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inst_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        misPuntos(googleMap);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-17.3890035,-66.1594936), 13.0f));

        }

    public void misPuntos(GoogleMap googleMap){
        mMap = googleMap;

        LatLng oficinaMujer = new LatLng(-17.3833,-66.1667);
        mMap.addMarker(new MarkerOptions().position(oficinaMujer).title("SLIM").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oficinaMujer));

        LatLng slim = new LatLng(-17.3533,-66.1967);
        mMap.addMarker(new MarkerOptions().position(slim).title("DEFENSORIA DE LA NIÑEZ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(slim));

        LatLng felcc = new LatLng(-17.4009921,-66.1860303);
        mMap.addMarker(new MarkerOptions().position(felcc).title("FELCC").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(felcc));

        LatLng of = new LatLng(-17.40921,-66.14603);
        mMap.addMarker(new MarkerOptions().position(of).title("OFICINA JURÍDICA DE LA MUJER").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(of));

        LatLng umss = new LatLng(-17.4009921,-66.1260303);
        mMap.addMarker(new MarkerOptions().position(umss).title("OFICINA JURÍDICA DERECHO-UMSS").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(umss));

        LatLng univalle = new LatLng(-17.4329921,-66.1190303);
        mMap.addMarker(new MarkerOptions().position(univalle).title("OFICINA JURÍDICA DERECHO-UNIVALLE").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(univalle));

        LatLng felcv = new LatLng(-17.4007821,-66.1063403);
        mMap.addMarker(new MarkerOptions().position(felcv).title("FELCV").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(felcv));

        //revisados
        /*
        FirebaseDatabase.getInstance().getReference().child("Instituciones")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            InstInfo inst = snapshot.getValue(InstInfo.class);
                            System.out.println("INTITUCIONS"+ inst.getnombre()+ " -> LAT" + inst.getlat() + "LONG" + inst.getlg());
                            LatLng isntEnMapa = new LatLng(inst.getlat(), inst.getlg());
                            mMap.addMarker(new MarkerOptions().position(isntEnMapa).title(inst.getnombre()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(isntEnMapa));
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        /*LatLng ofCasa = new LatLng(Latitud, Longitud);
        mMap.addMarker(new MarkerOptions().position(ofCasa).title(" Test").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ofCasa));*/

    }

}
