package com.migros.demo.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Tracker {

    private Long id;
    private LocalDateTime time;
    private BigDecimal lat;
    private BigDecimal lng;
    private Courier courier;
    private int marketID;

    public LocalDateTime getTime() {
        return time;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMarketID() {
        return marketID;
    }

    public void setMarketID(int marketID) {
        this.marketID = marketID;
    }
}
