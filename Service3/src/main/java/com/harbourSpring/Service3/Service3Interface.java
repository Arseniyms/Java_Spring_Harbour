package com.harbourSpring.Service3;


import common.Ship;

import java.io.FileNotFoundException;
import java.util.List;

public interface Service3Interface {
    List<Ship> getSchedule(String jsonName) throws FileNotFoundException;
}
