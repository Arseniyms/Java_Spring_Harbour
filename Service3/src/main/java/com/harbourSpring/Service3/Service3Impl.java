package com.harbourSpring.Service3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import common.Ship;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class Service3Impl implements Service3Interface {

    @Override
    public List<Ship> getSchedule(String jsonName) throws FileNotFoundException {
        String url = "http://localhost:8082/service2/getFromJson/" + jsonName;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        try {
            Type itemsListType = new TypeToken<List<Ship>>() {}.getType();
            Gson gson = new Gson();
            List<Ship> ships = gson.fromJson(responseEntity.getBody(), itemsListType);
            return ships;
        }
        catch(Exception e)
        {
            throw new FileNotFoundException("File was not found.");
        }

    }
}
