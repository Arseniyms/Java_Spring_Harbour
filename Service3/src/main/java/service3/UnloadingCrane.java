package service3;

import common.Ship;
import common.Utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UnloadingCrane implements Callable<Object> {
    private Calendar currentTime;
    private int fine = 0;
    private int size = 0;

    private ConcurrentLinkedQueue<Ship> ships;

    public UnloadingCrane(ConcurrentLinkedQueue<Ship> ships) {
        this.ships = ships;
        currentTime = new GregorianCalendar(2021, Calendar.MARCH, 31);
        size += ships.size();
    }

    @Override
    public Object call() {
        while (!ships.isEmpty()) {
            Ship currentShip = ships.poll();
            if(currentShip == null)
            {
                break;
            }

            currentTime.setTimeInMillis(Math.max(currentShip.getTimeOfArrival().getTimeInMillis(), currentTime.getTimeInMillis()));
            Calendar temp = (Calendar) currentTime.clone();
            temp.add(Calendar.MINUTE, currentShip.getTimeOfWait());
            currentShip.setTimeOfUnloadStart(temp);
            currentShip.setTimeOfWait((int) (currentTime.getTimeInMillis() - currentShip.getTimeOfArrival().getTimeInMillis()) / 1000 / 60);
            currentTime.add(Calendar.MINUTE, currentShip.getTimeOfUnload());

            Ship nextShip = ships.peek();
            if (nextShip == null)
            {
                break;
            }

            if (nextShip.getTimeOfArrival().compareTo(currentTime) < 0)
            {
                fine += calculateFine((int) ((currentTime.getTimeInMillis() - nextShip.getTimeOfArrival().getTimeInMillis()) / 1000 / 60));
            }

            Utils.pause(Utils.STOP_TIME);
        }
        return null;
    }

    public int calculateFine(int minutes) {
        if (minutes % Utils.MINUTES_OF_WAIT == 0) {
            return Utils.PENALTY_PER_HOUR * (minutes / Utils.MINUTES_OF_WAIT);
        }

        return Utils.PENALTY_PER_HOUR * (minutes / Utils.MINUTES_OF_WAIT + 1);
    }

    public int getFine() {
        return fine;
    }
}

