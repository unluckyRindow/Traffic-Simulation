package main.simulation;

import main.road.Road;

import java.util.ArrayList;

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
            road.setNextSegmentId(i == 17 ? 0 : i + 1);
            roads.add(road);
        }
    }

    public void initRoadsAntiClockWise(ArrayList<Road> roads){
        for (int i = 0; i < segmentsQuantity; i++){
            Road road = new Road(i, lanesOnSegmentsQuantity[i], segmentsLengths[i], roads);
            road.setNextSegmentId(i == 0 ? 17 : i - 1);
            roads.add(road);
        }
    }

    public void insertVehicles(Settings settigns){
        this.settings = settigns;
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
