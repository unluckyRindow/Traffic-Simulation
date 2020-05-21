package main.road;


import main.simulation.Settings;
import main.simulation.SimulationInitializer;

import java.util.ArrayList;

public class Bypass {
    public ArrayList<Road> segmentsClockWise;
    public ArrayList<Road> segmentsAntiClockWise;
    public int segmentsQuantity;

    private SimulationInitializer simulationInitializer;

    public void init(Settings settings){
        simulationInitializer = new SimulationInitializer();
        segmentsQuantity = simulationInitializer.getSegmentsQuantity();
        segmentsClockWise = new ArrayList<>();
        segmentsAntiClockWise = new ArrayList<>();

        simulationInitializer.initRoadsClockWise(segmentsClockWise);
        simulationInitializer.initRoadsAntiClockWise(segmentsAntiClockWise);
        simulationInitializer.insertVehicles(settings);
    }
}
