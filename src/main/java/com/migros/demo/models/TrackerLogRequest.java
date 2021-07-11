package com.migros.demo.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TrackerLogRequest {

    private int courierID;
    private BigDecimal lat;
    private BigDecimal lng;

    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
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
}
