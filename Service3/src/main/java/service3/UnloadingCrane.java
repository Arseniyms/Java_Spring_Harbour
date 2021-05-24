package service3;

import common.Ship;
import common.Utils;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UnloadingCrane implements Callable<Integer> {
    private Calendar currentTime;
    private int fine = 0;

    private ConcurrentLinkedQueue<Ship> ships;

    public UnloadingCrane(ConcurrentLinkedQueue<Ship> ships) {
        this.ships = ships;
        currentTime = new GregorianCalendar(2021, Calendar.MARCH, 31);
    }

    @Override
    public Integer call() {
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
                int delay = ((int) ((currentTime.getTimeInMillis() - nextShip.getTimeOfArrival().getTimeInMillis()) / 1000 / 60));
                fine += Utils.PENALTY_PER_HOUR * (delay / Utils.MINUTES_OF_WAIT + (delay % Utils.MINUTES_OF_WAIT != 0 ? 1 : 0));
            }

            Utils.pause(Utils.STOP_TIME);
        }
        return fine;
    }

//    public int getFine() {
//        return fine;
//    }
}

