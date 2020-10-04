package com.nvg.allert.resourseForMap;

import android.app.ActionBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nvg.allert.R;

import java.util.List;

public class MapsFun {
    public static void createMarker(GoogleMap mMap, Event tmp){
        MarkerOptions marker = new MarkerOptions().position(tmp.latLng).title(tmp.hazard);
        if(tmp.hazard.equals("earthquakes")){
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon8treaska));
        }else
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.icon8opolzeni));
        marker.snippet("Type : " + tmp.type +
                "/Size : " + tmp.size + "/Trigger : " + tmp.trigger +
                "/Injuries : " + tmp.injuries + "/Fatalities : " + tmp.fatalities +
                "/Probability : " + tmp.prob_trig  );
        mMap.addMarker(marker);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tmp.latLng));
    }
    public static Event findEventInList(List<Event> events , LatLng latLng){
        for (Event i : events){
            if(i.latLng.equals(latLng))
                return i;
        }
        return null;
    }
}
