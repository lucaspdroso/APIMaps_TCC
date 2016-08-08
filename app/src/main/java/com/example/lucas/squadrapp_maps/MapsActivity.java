package com.example.lucas.squadrapp_maps;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    private Quadra quadra;
    //private Marker marcador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onSearch(View view)
    {
        EditText txtLocalizacao = (EditText)findViewById(R.id.txtBuscarLoc);
        String localizacao = txtLocalizacao.getText().toString();

        List<Address> lista_Endereco = null;

        if(localizacao != null || !localizacao.equals(""))
        {
            Geocoder geocoder = new Geocoder(this);
            try {
                 lista_Endereco = geocoder.getFromLocationName(localizacao, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Address endereco = lista_Endereco.get(0);
            quadra = new Quadra(endereco.getLatitude(), endereco.getLongitude(), "Marker", "Próximo evento:" + "Futeba do Mike - 18:00", "Futebol", "proximo");

            LatLng latLng = new LatLng(quadra.getLat(), quadra.getLng());

            //marcador.setPosition(latLng);
            //marcador.setTitle("Quadra Fulano de Tal");
            //marcador.setSnippet("Próximo evento: Futeba do Mike - 18:00");

            mMap.addMarker(new MarkerOptions().position(latLng).title(quadra.getTitulo()).snippet(quadra.getProxEvento()).icon(BitmapDescriptorFactory.defaultMarker(quadra.getCor())));
            mMap.setInfoWindowAdapter(this);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,4));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);
    }

    @Override
    public View getInfoWindow(Marker marker) {

        View view = getLayoutInflater().inflate(R.layout.infowindow, null, false);
        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
