package com.migros.demo.daos;


import com.migros.demo.data.CourierExtractor;
import com.migros.demo.models.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.*;


@Component
public class CourierDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int create(Courier courier){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO couriers(name) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, courier.getName());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Courier getCourier(int id){
        return jdbcTemplate.query("SELECT NAME FROM couriers WHERE ID = ?", new CourierExtractor(),new Object[] { id });
    }
}
