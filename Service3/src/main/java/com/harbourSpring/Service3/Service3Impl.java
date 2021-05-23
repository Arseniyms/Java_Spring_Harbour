package com.harbourSpring.Service3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Ship;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class Service3Impl implements Service3Interface {

    @Override
    public List<Ship> getSchedule(String jsonName) {
        String url = "http://localhost:8082/service2/getFromJson/" + jsonName;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        Type itemsListType = new TypeToken<List<Ship>>() {}.getType();//Сеня лох
        Gson gson = new Gson();
        List<Ship> ships = gson.fromJson(responseEntity.getBody(), itemsListType);

        return ships;
    }
}
