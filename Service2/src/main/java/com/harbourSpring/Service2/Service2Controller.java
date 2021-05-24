package com.harbourSpring.Service2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Ship;
import common.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import service2.JsonWriter;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/service2")
public class Service2Controller {

    public Service2Interface service2Interface;

    @Autowired
    public Service2Controller(Service2Interface service2Interface) {
        this.service2Interface = service2Interface;
    }

    @GetMapping("/getJson/{amount}")
    public String getShipsJson(String ships, @PathVariable("amount") int amount) {
        String url = "http://localhost:8081/service1/" + amount;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        service2Interface.getShipsJson(responseEntity.getBody(), amount);

        return "Json with " + amount + " ships was created.";
    }

    @GetMapping("/getFromJson/{json}")
    public String getShipsFromJson(@PathVariable("json") String json) {
        List<Ship> list = null;
        try {
            list = service2Interface.getShipsFromJson(json);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String ships = gson.toJson(list);
            return ships;
        } catch (FileNotFoundException e) {
            return "File was not found.";
        }

    }

    @GetMapping("/addShipToJson/{json}")
    public String addShipToJson(@PathVariable("json") String json) {
        List<Ship> list = null;
        try {
            list = service2Interface.getShipsFromJson(json);
            list = service2Interface.addShipFromConsoleToJson(list);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String ships = gson.toJson(list);
            JsonWriter.writeToJson(ships);
            return "Ship(s) were added.";
        } catch (FileNotFoundException e) {
            return "File was not found.";
        }
    }

    @PostMapping("/postReport")
    public String postReport(@RequestBody String string) {
        return service2Interface.postReport(string);
    }
}
