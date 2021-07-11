package com.migros.demo.daos;

import com.migros.demo.data.TrackerExtractor;
import com.migros.demo.models.Tracker;
import com.migros.demo.models.TrackerLogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;

@Component
public class TrackerDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String create(TrackerLogRequest tracker, int marketID) {
        //time difference between records and insert operation possibly
        //could be done in single query with `if diff` i believe
        //however i managed to make it with 2 queries :((
        Timestamp lastRecord = jdbcTemplate.query("SELECT MAX(ENTRANCE_TIME) AS 'MAX_DATE' FROM tracker_logs WHERE courier_id = ?", new ResultSetExtractor<Timestamp>() {
            @Override
            public Timestamp extractData(ResultSet result) throws SQLException, DataAccessException {
                if (result.next()) {
                    return result.getTimestamp("MAX_DATE");
                }
                return null;
            }
        }, new Object[]{tracker.getCourierID()});
        if((lastRecord!=null && Timestamp.valueOf(LocalDateTime.now()).getTime() - lastRecord.getTime() > 60 *1000)||
        lastRecord==null){
            jdbcTemplate.update("INSERT INTO tracker_logs(COURIER_ID, LAT, LNG, ENTRANCE_TIME, MARKET_ID) VALUES(?, ?, ?, ?, ?)",
                    new Object[]{tracker.getCourierID(), tracker.getLat(), tracker.getLng(), LocalDateTime.now(), marketID});
            return "Successfully added";
        }
        return "Entries within 1 Minute wont be counted and recorded";
    }

    public Collection<Tracker> getTravelledStores(int courierID) {
        return jdbcTemplate.query("SELECT MARKET_ID, courier_id, LAT, LNG FROM tracker_logs WHERE courier_id = ?", new TrackerExtractor(), new Object[]{courierID});
    }
}
