package com.harbourSpring.Service3;

import common.Ship;
import common.TypeOfCargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service3.Unloading;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/service3")
public class Service3Controller {
    public Service3Interface service3Interface;

    @Autowired
    public Service3Controller(Service3Interface service3Interface) {
        this.service3Interface = service3Interface;
    }

    @GetMapping("/report/{jsonName}")
    public String getReport(@PathVariable("jsonName") String jsonName)
    {
        List<Ship> schedule = service3Interface.getSchedule(jsonName);
        String str = "";
        for (TypeOfCargo type: TypeOfCargo.values())
        {
            List<Ship> list = new ArrayList<>();
            for (Ship sh : schedule)
            {
                if(sh.getTypeOfCargo() == type)
                    list.add(sh);
            }

            Unloading unloading = new Unloading(list);
            str += (unloading.call());
        }

        return str;
    }
}
