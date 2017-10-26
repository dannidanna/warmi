package app.warmi.rodriguez.danny.warmi;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class InstMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        mMap.addMarker(new MarkerOptions().position(oficinaMujer).title("OFICINA JURIDICA DE LA MUJER").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oficinaMujer));

        LatLng slim = new LatLng(-17.3833,-66.1667);
        mMap.addMarker(new MarkerOptions().position(slim).title("SLIM").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(slim));

        LatLng felcc = new LatLng(-17.4009921,-66.1460303);
        mMap.addMarker(new MarkerOptions().position(felcc).title("FELCC").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(felcc));

        //revisados

        LatLng coordMujer = new LatLng(-17.3890035,-66.1594936);
        mMap.addMarker(new MarkerOptions().position(coordMujer).title("COORDINADORA DE LA MUJER").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordMujer));

        LatLng brigada = new LatLng(-17.391874, -66.157887);
        mMap.addMarker(new MarkerOptions().position(brigada).title("BRIGADA DE PROTECCION A LA FAMILIA").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(brigada));

        LatLng ofUmss = new LatLng(-17.393687, -66.148728);
        mMap.addMarker(new MarkerOptions().position(ofUmss).title(" Oficina Jurídica Popular de la UMSS").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ofUmss));

        LatLng ofUniValle = new LatLng(-17.393687, -66.148728);
        mMap.addMarker(new MarkerOptions().position(ofUniValle).title(" Oficina Jurídica Popular de Univalle").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ofUniValle));

    }


}
