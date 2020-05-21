package main;

import main.road.Cell;
import main.road.Lane;
import main.road.Road;
import main.vehicle.Car;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Simulation {

    private boolean isActive;
    private ArrayList<Road> roads;


    class Updater extends TimerTask {
        @Override
        public void run() {
            for (Road road: roads){
                road.process();
            }
        }
    }

    public Simulation(){
        roads = new ArrayList<>();
        System.out.println("simulation initialized");
    }


    //TODO it works for now lol
    public void start() throws InterruptedException {

        System.out.println("Simulation method start");

//        SAMPLE SIMULATION

        Road road = new Road(3, 500);

        road.getLanes()
                .get(0)
                .getLane()[0]
                .setVehicle(new Car(4, 5, 0.1, 0,0));
        road.getLanes()
                .get(0)
                .getLane()[0]
                .setOccupied(true);


        road.getLanes()
                .get(0)
                .getLane()[10]
                .setVehicle(new Car(2, 4, 0.1, 10,0));
        road.getLanes()
                .get(0)
                .getLane()[10]
                .setOccupied(true);

        road.getLanes()
                .get(0)
                .getLane()[12]
                .setVehicle(new Car(3, 5, 0.1, 12,0));
        road.getLanes()
                .get(0)
                .getLane()[12]
                .setOccupied(true);

        road.getLanes()
                .get(2)
                .getLane()[10]
                .setVehicle(new Car(3, 5, 0.1, 10,2));
        road.getLanes()
                .get(2)
                .getLane()[10]
                .setOccupied(true);

        road.getLanes()
                .get(2)
                .getLane()[20]
                .setVehicle(new Car(3, 6, 0.1, 20,2));
        road.getLanes()
                .get(2)
                .getLane()[20]
                .setOccupied(true);

        roads.add(road);
        roads.add(road);

        Timer timer = new Timer();
        timer.schedule(new Updater(), 0 ,1000);


        /*
        while (isActive = true) {
            TimeUnit.SECONDS.sleep(1);
            road.process();

            for(Lane lane: road.getLanes()){
                for (Cell cell: lane.getLane()) System.out.print(cell.isOccupied() ? "@" : "-");
                System.out.println();
            }
        System.out.println();
        }
*/

    }


    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getActive(){
        return isActive;
    }

    public ArrayList<Road> getRoads() {
        return roads;
    }

    public void setRoads(ArrayList<Road> roads) {
        this.roads = roads;
    }
}
