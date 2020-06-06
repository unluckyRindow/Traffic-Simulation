package main.simulation;

import main.road.Bypass;
import main.road.Lane;
import main.road.Road;
import main.vehicle.Car;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

//init simulation.roads with roads with lanes with cars etc etc
public class SimulationInitializer {
    private Settings settings;
    private int segmentsQuantity = 18;
    //bypass segments clockwise starting with Modlnica
    private int [] segmentsLengths = {400, 453, 373, 333, 240, 400, 426, 386, 453, 786, 266, 613, 453, 266, 613, 200, 493, 320};
    private int [] lanesOnSegmentsQuantity = {3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 2, 3, 2, 2};

    public void initRoadsClockWise(ArrayList<Road> roads){
        for (int i = 0; i < segmentsQuantity; i++){
            Road road = new Road(i, lanesOnSegmentsQuantity[i], segmentsLengths[i], roads);
            roads.add(road);
        }
        for (int i = 0; i < segmentsQuantity; i++){
            roads.get(i).setNextSegment(roads.get(i == 17 ? 0 : i + 1));
        }
    }

    public void initRoadsAntiClockWise(ArrayList<Road> roads){
        for (int i = 0; i < segmentsQuantity; i++){
            Road road = new Road(i, lanesOnSegmentsQuantity[i], segmentsLengths[i], roads);
            roads.add(road);
        }
        for (int i = 0; i < segmentsQuantity; i++){
            roads.get(i).setNextSegment(roads.get(i == 0 ? 17 : i - 1));
        }
    }

    public void insertVehicles(Bypass bypass, Settings settings){
        this.settings = settings;


        //code below works just fine
        for (int i = 0; i < bypass.segmentsQuantity; i++){
            int nMax = ThreadLocalRandom.current().nextInt(settings.getnCars() - 5, settings.getnCars() + 6);
            placeCars(settings, i, nMax, bypass.segmentsClockWise);
            nMax = ThreadLocalRandom.current().nextInt(settings.getnCars() - 5, settings.getnCars() + 6);
            placeCars(settings, i, nMax, bypass.segmentsAntiClockWise);
        }
    }

    private void placeCars(Settings settings, int i, int nMax, ArrayList<Road> roads) {
        for (int j = 0; j < nMax; j++){
            boolean occupied = true;
            ArrayList<Lane> currentRoadLanes = roads.get(i).getLanes();
            while(occupied){
                int posX = ThreadLocalRandom.current().nextInt(currentRoadLanes.get(0).getSize());
                int posY = ThreadLocalRandom.current().nextInt(currentRoadLanes.size());

                if (!currentRoadLanes.get(posY).getLane()[posX].isOccupied()){
                    currentRoadLanes.get(posY).getLane()[posX]
                            .setVehicle(new Car(this.settings.getvMaxL() - 1, ThreadLocalRandom.current().nextInt(this.settings.getvMaxL(), settings.getvMaxH()), this.settings.getSlowProbability(), posX, posY));
                    currentRoadLanes.get(posY).getLane()[posX].setOccupied(true);
                    occupied = false;
                }
            }
        }
    }


    public int getSegmentsQuantity() {
        return segmentsQuantity;
    }

    public void setSegmentsQuantity(int segmentsQuantity) {
        this.segmentsQuantity = segmentsQuantity;
    }

    public int[] getSegmentsLengths() {
        return segmentsLengths;
    }

    public void setSegmentsLengths(int[] segmentsLengths) {
        this.segmentsLengths = segmentsLengths;
    }

    public int[] getLanesOnSegmentsQuantity() {
        return lanesOnSegmentsQuantity;
    }

    public void setLanesOnSegmentsQuantity(int[] lanesOnSegmentsQuantity) {
        this.lanesOnSegmentsQuantity = lanesOnSegmentsQuantity;
    }
}
