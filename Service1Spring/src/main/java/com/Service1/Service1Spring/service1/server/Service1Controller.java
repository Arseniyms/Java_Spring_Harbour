package com.Service1.Service1Spring.service1.server;

import com.Service1.Service1Spring.common.Ship;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service1")
public class Service1Controller {
    private Service1Interface service1Interface;

    @Autowired
    public void setService1Interface(Service1Interface service1Interface) {
        this.service1Interface = service1Interface;
    }


    @GetMapping("")
    public String getSchedule()
    {
        List<Ship> list = service1Interface.getSchedule(100);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);

        return json;
    }

    @GetMapping("/{amount}")
    public String getScheduleWithAmount(@PathVariable("amount") int amount) {
        List<Ship> list = service1Interface.getSchedule(amount);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(list);

        return json;
    }
}
