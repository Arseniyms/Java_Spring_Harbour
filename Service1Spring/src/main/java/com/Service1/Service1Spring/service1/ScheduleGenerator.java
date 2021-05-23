package com.Service1.Service1Spring.service1;
import com.Service1.Service1Spring.common.Ship;
import com.Service1.Service1Spring.common.TypeOfCargo;

import java.util.*;
import java.util.List;

public class ScheduleGenerator {
    private List<Ship> schedule = new ArrayList<>();

    private int amount;
    private  int bulkerCount = 0;
    private  int tankerCount = 0;
    private  int containerCount = 0;

    private String countForName;

    public ScheduleGenerator(int amount) {
        if(amount > 0) {
            Random random = new Random();
            this.amount = amount;
            for (int i = 0; i < this.amount; i++) {
                TypeOfCargo type = TypeOfCargo.values()[random.nextInt(TypeOfCargo.values().length)];
                int weight;
                switch (type) {
                    case BULKER -> {
                        bulkerCount++;
                        countForName = Integer.toString(bulkerCount);
                        weight = random.nextInt(10000);
                    }
                    case TANKER -> {
                        tankerCount++;
                        countForName = Integer.toString(tankerCount);
                        weight = random.nextInt(10000);
                    }
                    case CONTAINER -> {
                        containerCount++;
                        countForName = Integer.toString(containerCount);
                        weight = random.nextInt(5000);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + type);
                }
                String name = type.toString() + "-" + countForName;
                Calendar scheduledTimeOfArrival = new GregorianCalendar(2021, Calendar.APRIL, random.nextInt(30) + 1, random.nextInt(24), random.nextInt(60));
                Calendar actualTimeOfArrival = (Calendar) scheduledTimeOfArrival.clone();
                actualTimeOfArrival.add(Calendar.DAY_OF_MONTH, random.nextInt(14) - 7);
                if (actualTimeOfArrival.get(Calendar.MONTH) != Calendar.APRIL) {
                    continue;
                }
                int timeOfUnload = (int) (weight / type.getProductivityOfCrane());
                int delayOfUnload = random.nextInt(1440);
                Ship ship = new Ship(name, type, weight, actualTimeOfArrival, scheduledTimeOfArrival, timeOfUnload + delayOfUnload, delayOfUnload);
                schedule.add(ship);
            }
        }

        if(!schedule.isEmpty())
        {
            Collections.sort(schedule);
        }
    }

    public List<Ship> getSchedule() {
        return schedule;
    }
}
