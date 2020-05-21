package main.simulation;

import main.road.Bypass;
import main.road.Road;
import main.vehicle.Car;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {

    private boolean isActive;
    //old
    //private ArrayList<Road> roads;
    private Bypass bypass;


    class Updater extends TimerTask {
        @Override
        public void run() {
            //old
            //for (Road road: roads){
            //  road.process();
            //}
            //new
            for (int i = 0; i < bypass.segmentsQuantity; i++){
                bypass.segmentsClockWise.get(i).process();
                bypass.segmentsClockWise.get(i).process();
            }
        }
    }

    public Simulation(Settings settings){
        //old
        //roads = new ArrayList<>();
        //new
        bypass = new Bypass();
        bypass.init(settings);
        System.out.println("simulation initialized");
    }


    public void start() throws InterruptedException {

        System.out.println("Simulation method start");

//        SAMPLE SIMULATION
// code below should be executed by SimulationInitializer instance based on Setting object
       /* Road road = new Road(3, 500);

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
*/
        //code below should be only body of this method

        Timer timer = new Timer();
        timer.schedule(new Updater(), 0 ,1000);
    }


    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getActive(){
        return isActive;
    }

    public Bypass getBypass() {
        return bypass;
    }

    public void setBypass(Bypass bypass) {
        this.bypass = bypass;
    }
}
