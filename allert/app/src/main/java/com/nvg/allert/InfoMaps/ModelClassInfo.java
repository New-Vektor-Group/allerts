package com.nvg.allert.InfoMaps;

public class ModelClassInfo {

    private int imageIcon;
    String title;
    String body;

    String latLng, hazard, type, size, trigger, injuries, fatalities, prob_trig, country;


    public ModelClassInfo(int imageIcon, String title, String body, String latLng, String hazard){
        this.imageIcon = imageIcon;
        this.title=title;
        this.body=body;
        this.latLng = latLng;
        this.hazard = hazard;
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

    public String getHazard() {
        return hazard;
    }

    public String getLatLng() {
        return latLng;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public String getTrigger() {
        return trigger;
    }

    public String getCountry() {
        return country;
    }

    public String getFatalities() {
        return fatalities;
    }

    public String getInjuries() {
        return injuries;
    }

    public String getProb_trig() {
        return prob_trig;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setHazard(String hazard) {
        this.hazard = hazard;
    }

    public void setImageIcon(int imageIcon) {
        this.imageIcon = imageIcon;
    }

    public void setFatalities(String fatalities) {
        this.fatalities = fatalities;
    }

    public void setLatLng(String latLng) {
        this.latLng = latLng;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public void setInjuries(String injuries) {
        this.injuries = injuries;
    }

    public void setProb_trig(String prob_trig) {
        this.prob_trig = prob_trig;
    }

}

