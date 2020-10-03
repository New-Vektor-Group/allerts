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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nvg.allert.resourseForMap.Event;
import com.nvg.allert.resourseForMap.HttpHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback , LocationListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    Location userLocation;
    List<Event> eventList = new ArrayList<>();

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
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                System.out.println(latLng.latitude + " " + latLng.longitude );
                String url = null;
                url = getResponse("https://api.nvg-group.com/alert.php?la=" + latLng.latitude + "&lo="+ latLng.longitude);
                try {
                    eventList.clear();
                    JSONObject a = new JSONObject(url);
                    JSONArray events = a.getJSONArray("dots");
                    for (int i = 0 ; i < events.length() ; i++){
                        JSONObject tmp = events.getJSONObject(i);
                        eventList.add(new Event(tmp.getDouble("la") , tmp.getDouble("lo")));
                    }
                }catch (JSONException e){
                    System.out.println(e);
                    System.out.println("Response parsing error");
                    return;
                }
                mMap.clear();
                if(!(eventList.isEmpty())) {
                    for (Event i : eventList) {
                        mMap.addMarker(new MarkerOptions().position(i.latLng).title("dot"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(i.latLng));
                    }
                }
//                mMap.addMarker(new MarkerOptions().position(latLng).title("dot"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        userLocation = location;
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
    //Work with JSon
    private static String getUrlConection(String urlAndress){
        StringBuffer content = new StringBuffer();
        try {
            URL url = new URL(urlAndress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return content.toString();
    }

    private String getResponse(String endpoint) {
        HttpHelper helper = new HttpHelper();
        String result = "";
        try {
            result = helper.execute(endpoint).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//        responseCode = HttpHelper.getResponseCode();
        return result;
    }



    //Button to go setting(GPS)
    public void onClickLocationSettings(View view) {
        startActivity(new Intent(
                android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
    }
}