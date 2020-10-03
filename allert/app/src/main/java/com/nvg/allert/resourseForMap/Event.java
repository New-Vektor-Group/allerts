package com.nvg.allert.resourseForMap;

import com.google.android.gms.maps.model.LatLng;

public class Event {
    public LatLng latLng;
    public String hazard;
    public String type;
    public String size;
    public String trigger;
    public int injuries;
    public int fatalities;
    public double prob_trig;
    public String country;

    public Event(double la , double lo, String hazard, String type,
                 String size, String trigger, int injuries,
                 int fatalities, double prob_trig, String country) {
        this.latLng = new LatLng( la , lo);
        this.hazard = hazard;
        this.type = type;
        this.size = size;
        this.trigger = trigger;
        this.injuries = injuries;
        this.fatalities = fatalities;
        this.prob_trig = prob_trig;
        this.country = country;
    }
}
