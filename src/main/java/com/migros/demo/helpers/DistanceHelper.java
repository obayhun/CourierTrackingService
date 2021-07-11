package com.migros.demo.helpers;

import java.math.BigDecimal;

public class DistanceHelper {
    //Source: https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
    public Float distance(BigDecimal lat1,
                          BigDecimal lat2, BigDecimal lng1,
                          BigDecimal lng2)
    {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2.subtract(lat1).doubleValue());
        double dLng = Math.toRadians(lng2.subtract(lng1).doubleValue());
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1.floatValue())) * Math.cos(Math.toRadians(lat2.floatValue())) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }


}
