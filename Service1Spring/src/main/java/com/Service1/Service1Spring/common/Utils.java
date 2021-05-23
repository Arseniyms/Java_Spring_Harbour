package com.Service1.Service1Spring.common;

import java.util.List;

public class Utils {
    public static final int STOP_TIME = 1;

    public static final int PRICE_OF_CRANE = 30000;
    public static final int PENALTY_PER_HOUR = 100;
    public static final int MINUTES_OF_WAIT = 60;


    public static void pause(long millis)
    {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printTimeTable(List<Ship> ships)
    {
        for (Ship ship: ships)
        {
            System.out.println(ship.toString());
        }
    }

    public static String intToDateFormat(int minutes)
    {
        return minutes/1440 + ":" + minutes/60%24 + ":" + minutes%60;
    }

}
