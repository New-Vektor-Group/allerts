package com.nvg.allert.resourseForMap;

import com.google.android.gms.maps.model.LatLng;

public class Event {
    public LatLng latLng;

    public Event(double la , double lo) {
        latLng = new LatLng( la , lo);
    }
}
