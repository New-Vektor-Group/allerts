package com.nvg.allert.itemsAdapter;

import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

public class ModelClass {

    private int imageIcon;
    String title;
    String body;
    LatLng latLng;
    Button button;



    public ModelClass(int imageIcon, String title, String body , LatLng latLng ,Button button) {
        this.imageIcon = imageIcon;
        this.title=title;
        this.body=body;
        this.latLng=latLng;
        this.button=button;
    }

    public int getImageIcon() {
        return imageIcon;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public LatLng getLatLng(){
        return latLng;
    }

    public Button getButton() {
        return button;
    }
}
