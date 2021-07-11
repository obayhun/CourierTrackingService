package com.migros.demo.data;

import com.migros.demo.models.Courier;
import com.migros.demo.models.Tracker;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class TrackerExtractor implements ResultSetExtractor<Collection<Tracker>> {
        @Override
        public Collection<Tracker> extractData(ResultSet result) throws SQLException, DataAccessException {
            Collection<Tracker> trackers = new ArrayList<>();
            Courier courier = new Courier();
            while (result.next()) {
                Tracker current = new Tracker();
                current.setLat(result.getBigDecimal("LAT"));
                current.setLng(result.getBigDecimal("LNG"));
                courier.setId(result.getInt("COURIER_ID"));
                current.setCourier(courier);
                current.setMarketID(result.getInt("MARKET_ID"));
                trackers.add(current);
            }
            return trackers;
        }
}
