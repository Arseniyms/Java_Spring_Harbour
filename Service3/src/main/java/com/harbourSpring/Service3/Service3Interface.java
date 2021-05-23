package com.harbourSpring.Service3;


import common.Ship;

import java.util.List;

public interface Service3Interface {
    List<Ship> getSchedule(String jsonName);
}
