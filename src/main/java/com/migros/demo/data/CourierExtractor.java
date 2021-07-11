package com.migros.demo.data;

import com.migros.demo.models.Courier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourierExtractor implements ResultSetExtractor<Courier> {
    @Override
    public Courier extractData(ResultSet result) throws SQLException, DataAccessException {
        Courier courier = null;
        while (result.next()) {
            courier.setId(result.getInt("ID"));
            courier.setName(result.getString("NAME"));
        }
        return courier;
    }
}
