package com.nvg.allert.resourseForMap;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsFun {
    public static void createMarker(GoogleMap mMap, LatLng latLng){
        mMap.addMarker(new MarkerOptions().position(latLng).title("dot").snippet(latLng.latitude + " " + latLng.longitude));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.f));
    }
    public static Event findEventInList(List<Event> events , LatLng latLng){
        for (Event i : events){
            if(i.latLng.equals(latLng))
                return i;
        }
        return null;
    }
}
