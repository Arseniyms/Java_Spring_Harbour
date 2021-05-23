package com.harbourSpring.Service2;

import common.Ship;

import java.io.FileNotFoundException;
import java.util.List;

public interface Service2Interface {
    void getShipsJson(String ships, int amount);
    List<Ship> getShipsFromJson(String json) throws FileNotFoundException;
    List<Ship> addShipFromConsoleToJson(List<Ship> schedule) throws FileNotFoundException;
    String postReport(String jsonName);
}
