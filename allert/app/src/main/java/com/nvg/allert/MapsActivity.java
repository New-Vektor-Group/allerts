package com.nvg.allert;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.nvg.allert.resourseForMap.Event;
import com.nvg.allert.resourseForMap.HttpHelper;
import com.nvg.allert.resourseForMap.MapsFun;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback , LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    Location userLocation;
    boolean checkLocationEnable;
    List<Event> eventList = new ArrayList<>();
    Intent intent;
    LatLng userChoice;
    boolean earthquake , landsSlites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        Update User Location
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        intent = getIntent();
        System.out.println();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        findAllAndCreateMarkers();
        if(intent.hasExtra("la")) {
            LatLng tmp = new LatLng(intent.getDoubleExtra("la", 0), intent.getDoubleExtra("lo", 0));
            findAroundAndCreateMarkers(tmp);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(tmp, 8.f));
//            MapsFun.createMarker(mMap, new LatLng(intent.getDoubleExtra("la", 0), intent.getDoubleExtra("lo", 0)));
            intent = null;
        }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                findAroundAndCreateMarkers(latLng);
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Event tmp = MapsFun.findEventInList(eventList , marker.getPosition());
                if(tmp != null)
                    marker.setSnippet(tmp.latLng.toString() + "" + tmp.country);
                return false;
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        userLocation = location;
        checkLocationEnable = true;
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        checkLocationEnable = true;
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        checkLocationEnable = false;
    }
    //http request
    private String getResponse(String endpoint) {
        HttpHelper helper = new HttpHelper();
        String result = "";
        try {
            result = helper.execute(endpoint).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }

    //Button to go setting(GPS)
    public void onClickInfoButton(View view)
    {
        if(userLocation == null && checkLocationEnable)
            Toast.makeText(getApplicationContext(), "Wait a little", Toast.LENGTH_LONG).show();
        if(userLocation == null && !checkLocationEnable) {
            Toast.makeText(getApplicationContext(), "Turn on Location", Toast.LENGTH_LONG).show();
//            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
        if(userLocation != null) {
//            MapsFun.createMarker(mMap, new LatLng(userLocation.getLatitude(), userLocation.getLongitude()));
            findAroundAndCreateMarkers(new LatLng(userLocation.getLatitude(), userLocation.getLongitude()));
        }

        Intent i = new Intent(MapsActivity.this, InfoMap.class);
        startActivity(i);
    }


    public void findAroundAndCreateMarkers(LatLng latLng){
        String url = getResponse("https://api.nvg-group.com/alert.php?la=" + latLng.latitude + "&lo="+ latLng.longitude);
        try {
            eventList.clear();
            JSONObject a = new JSONObject(url);
            JSONArray events = a.getJSONArray("dots");
            for (int i = 0 ; i < events.length() ; i++){
                JSONObject tmp = events.getJSONObject(i);
                eventList.add(new Event(tmp.getDouble("la") , tmp.getDouble("lo"),
                        tmp.getString("hazard") , tmp.getString("type") ,
                        tmp.getString("size") , tmp.getString("trigger") ,
                        tmp.getString("injuries") , tmp.getString("fatalities") ,
                        tmp.getString("prob_trig") , tmp.getString("country")));
            }
        }catch (JSONException e){
            System.out.println(e);
            System.out.println("Response parsing error");
            return;
        }
        mMap.clear();
        if(!(eventList.isEmpty())) {
            for (Event i : eventList) {
                MapsFun.createMarker(mMap , i);
            }
        }
    }
    public void findAllAndCreateMarkers(){
        String url = getResponse("https://api.nvg-group.com/alert.php?alerts");
        try {
            eventList.clear();
            JSONObject a = new JSONObject(url);
            JSONArray events = a.getJSONArray("dots");
            for (int i = 0 ; i < events.length()-1 ; i++){
                JSONObject tmp = events.getJSONObject(i);
                eventList.add(new Event(tmp.getDouble("la") , tmp.getDouble("lo"),
                        tmp.getString("hazard") , tmp.getString("type") ,
                        tmp.getString("size") , tmp.getString("trigger") ,
                        tmp.getString("injuries") , tmp.getString("fatalities") ,
                        tmp.getString("prob_trig") , tmp.getString("country")));
            }
        }catch (JSONException e){
            System.out.println(e);
            System.out.println("Response parsing error");
            return;
        }
        mMap.clear();
        if(!(eventList.isEmpty())) {
            for (Event i : eventList) {
                MapsFun.createMarker(mMap , i);
            }
        }
    }
}