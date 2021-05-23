package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static common.Utils.intToDateFormat;

public class Ship implements Comparable<Ship> {
    private String nameOfShip;
    private TypeOfCargo typeOfCargo;
    private int weight;
    private Calendar timeOfArrival;
    private Calendar scheduledTimeOfArrival;
    private int timeOfUnload;

    private int timeOfWait;
    private Calendar timeOfUnloadStart;
    private int delayOfUnload;


    public Ship() {
    }


    public Ship(String nameOfShip, TypeOfCargo typeOfCargo, int weight, Calendar timeOfArrival, Calendar scheduledTimeOfArrival, int timeOfUnload, int delayOfUnload) {
        this.nameOfShip = nameOfShip;
        this.typeOfCargo = typeOfCargo;
        this.weight = weight;
        this.timeOfArrival = timeOfArrival;
        this.scheduledTimeOfArrival = scheduledTimeOfArrival;
        this.timeOfUnload = timeOfUnload;
        this.delayOfUnload = delayOfUnload;
        this.timeOfWait = 0;
        this.timeOfUnloadStart = timeOfArrival;
    }


    @Override
    public String toString() {
        DateFormat calendarFormat = new SimpleDateFormat("dd MMM HH:mm:ss", Locale.ENGLISH);
        return "Name of ship: " + nameOfShip +
                " Type of cargo: " + typeOfCargo +
                (typeOfCargo.toString() == "Container" ? " Amount of containers: " + weight : " Weight: " + weight + " tons") +
                //" Scheduled time of arrival: " + scheduledTimeOfArrival.getTime() +
                " Actual time of arrival: " + calendarFormat.format(timeOfArrival.getTime()) +
                " Time of start unloading: " + calendarFormat.format(timeOfUnloadStart.getTime()) +
                " Time of unload: " + timeOfUnload +
                " Time of waiting: " + timeOfWait + "  " + intToDateFormat(timeOfWait);
    }

    @Override
    public int compareTo(Ship o) {
        return this.timeOfArrival.compareTo(o.timeOfArrival);
    }

    public Calendar getTimeOfUnloadStart() {
        return timeOfUnloadStart;
    }

    public void setTimeOfUnloadStart(Calendar timeOfUnloadStart) {
        this.timeOfUnloadStart = timeOfUnloadStart;
    }

    public String getNameOfShip() {
        return nameOfShip;
    }

    public void setNameOfShip(String nameOfShip) {
        this.nameOfShip = nameOfShip;
    }

    public TypeOfCargo getTypeOfCargo() {
        return typeOfCargo;
    }

    public void setTypeOfCargo(TypeOfCargo typeOfCargo) {
        this.typeOfCargo = typeOfCargo;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Calendar getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(Calendar timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public Calendar getScheduledTimeOfArrival() {
        return scheduledTimeOfArrival;
    }

    public void setScheduledTimeOfArrival(Calendar scheduledTimeOfArrival) {
        this.scheduledTimeOfArrival = scheduledTimeOfArrival;
    }

    public int getTimeOfUnload() {
        return timeOfUnload;
    }

    public void setTimeOfUnload(int timeOfUnload) {
        this.timeOfUnload = timeOfUnload;
    }

    public int getTimeOfWait() {
        return timeOfWait;
    }

    public void setTimeOfWait(int timeOfWait) {
        this.timeOfWait = timeOfWait;
    }

    public int getDelayOfUnload() {
        return delayOfUnload;
    }

    public void setDelayOfUnload(int delayOfUnload) {
        this.delayOfUnload = delayOfUnload;
    }
}

