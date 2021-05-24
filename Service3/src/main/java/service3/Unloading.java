package service3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Ship;
import common.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Unloading{
    private int cranesQuantity = 0;
    private int fine = 0;
    private int amountOfShips;

    private List<Ship> ships;

    public Unloading(List<Ship> ships) {
        this.ships = ships;
        amountOfShips = ships.size();
    }

    public String startUnload() {
        while (fine >= Utils.PRICE_OF_CRANE * cranesQuantity) {
            ConcurrentLinkedQueue<Ship> queueOfShips = new ConcurrentLinkedQueue<>(ships);
            fine = 0;
            cranesQuantity++;
            List<UnloadingCrane> cranes = new ArrayList<>(cranesQuantity);

            //List<Callable<Object>> clist = new ArrayList<>(cranesQuantity);
            for (int i = 0; i < cranesQuantity; i++) {
                UnloadingCrane crane = new UnloadingCrane(queueOfShips);
                cranes.add(crane);
                //clist.add(Executors.callable(crane));
            }

            ExecutorService executor = Executors.newFixedThreadPool(cranesQuantity);
            try {
                List<Future<Integer>> futures = executor.invokeAll(cranes);
                fine = futures.stream().mapToInt(a -> {
                    try {
                        return a.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }).sum();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executor.shutdown();
//            for (UnloadingCrane crane : cranes) {
//                fine += crane.getFine();
//            }
        }

        for (Ship s : ships) {
            int temp = (int) (s.getTimeOfUnloadStart().getTimeInMillis() - s.getTimeOfArrival().getTimeInMillis()) / 1000 / 60;
            s.setTimeOfWait(temp);
        }

        String str = ships.get(0).getTypeOfCargo().toString() +
                " Fine: " + fine +
                " Amount Of Cranes: " + cranesQuantity +
                " Amount Of Ships: " + amountOfShips +
                " Max time of wait " + Utils.intToDateFormat(ships.stream().mapToInt(a->a.getTimeOfWait()).max().orElse(0)) +
                " Average time Of wait: " + Utils.intToDateFormat(ships.stream().mapToInt(a -> a.getTimeOfWait()).sum() / amountOfShips) +
                " Max time of delay unload " + Utils.intToDateFormat(ships.stream().mapToInt(a->a.getDelayOfUnload()).max().orElse(0)) +
                " Average time of delay of unload: " + Utils.intToDateFormat(ships.stream().mapToInt(a -> a.getDelayOfUnload()).sum() / amountOfShips);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(ships) + gson.toJson(str) + "\n";
    }
}
