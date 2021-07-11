package com.migros.demo.service;


import com.migros.demo.daos.CourierDao;
import com.migros.demo.models.Courier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CourierService {

    @Autowired
    private CourierDao courierDao;

    @Transactional
    public int create(Courier courier) {
        return courierDao.create(courier);
    }
}
