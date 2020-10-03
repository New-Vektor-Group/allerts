package com.nvg.allert.resourseForMap;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFun {
    public static void CreateMarker(GoogleMap mMap, LatLng latLng){
        mMap.addMarker(new MarkerOptions().position(latLng).title("dot"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
