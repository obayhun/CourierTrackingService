package com.migros.demo.models;

import org.json.simple.JSONObject;

import java.math.BigDecimal;

public class Store {
    private String name;
    private BigDecimal lat;
    private BigDecimal lng;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public Store fromJSON(JSONObject obj){
        Store store = new Store();
        store.setLat(BigDecimal.valueOf((Double) obj.get("lat")));
        store.setLng(BigDecimal.valueOf((Double) obj.get("lng")));
        store.setName((String) obj.get("name"));
        return store;
    }
}
