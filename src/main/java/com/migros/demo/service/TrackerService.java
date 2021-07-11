package com.migros.demo.service;

import com.migros.demo.daos.TrackerDao;
import com.migros.demo.helpers.DistanceHelper;
import com.migros.demo.models.Store;
import com.migros.demo.models.Tracker;
import com.migros.demo.models.TrackerLogRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class TrackerService {
    private static final Logger logger = LoggerFactory.getLogger(TrackerService.class);
    public final Map<Store,Integer> allStores =  fromJson();

    @Autowired
    private TrackerDao trackerDao;

    DistanceHelper distanceHelper = new DistanceHelper();

    @Transactional
    public String create(TrackerLogRequest tracker) {
        int marketID = storeFinder(tracker.getLat(), tracker.getLng());
        if(marketID!=0) return trackerDao.create(tracker, marketID);
        return "No store found :(";
    }

    @Transactional
    public double calculateTotalDistance(int courierID) {
        List<Tracker> travelled= trackerDao.getTravelledStores(courierID).stream().collect(Collectors.toList());
        double totalDistance = 0.0;
        if (travelled.size() >= 2) {
            Tracker point1 = travelled.get(0);
            for (int i = 1; i < travelled.size(); i++) {
                totalDistance += distanceHelper.distance(point1.getLat(), travelled.get(i).getLat(),
                        point1.getLng(), travelled.get(i).getLng());
                point1 = travelled.get(i);
            }
        }
        return totalDistance;
    }


    public int storeFinder(BigDecimal lat, BigDecimal lng){
        for(Store store: allStores.keySet()){
            if(distanceHelper.distance(lat, store.getLat(), lng, store.getLng())<100){
                logger.info("-> {}: {}", "Courier is closed to ", store.getName());
                return allStores.get(store);
            }
        }
        return 0;
    }

    public Map<Store,Integer> fromJson() {
        try{
            Map<Store,Integer> stores = new HashMap<>();
            JSONParser jsonParser=new  JSONParser();
            File resource = new ClassPathResource("data/locations.json").getFile();
            String storeInformation = new String(Files.readAllBytes(resource.toPath()));
            JSONArray jsonArray= (JSONArray) jsonParser.parse(storeInformation);
            for(int i = 0; i<jsonArray.size();i++) {
                JSONObject jsonStore = (JSONObject) jsonArray.get(i);
                Store temp = new Store();
                temp = temp.fromJSON(jsonStore);
                stores.put(temp, i+1);
            }
            return stores;
        }catch(Exception e){
            logger.info(e.toString());
        }
        return null;
    }
}
