package main.simulation;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
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

    //TODO vehicles insert based on settings
    public void insertVehicles(Bypass bypass, Settings settigns){
        this.settings = settigns;

/*        bypass.segmentsClockWise.get(0).getLanes()
                .get(0)
                .getLane()[350]
                .setVehicle(new Car(3, ThreadLocalRandom.current().nextInt(3, 6), 0.05, 350, 0));
        bypass.segmentsClockWise.get(0).getLanes()
                .get(0)
                .getLane()[350]
                .setOccupied(true);*/

        for (int i = 0; i < bypass.segmentsQuantity; i++){
//            for (int j = 0; j < 2; j++){
//                for (int k = 0; k < 100; k++){
//                    if (k % 10 == 0){
//                        bypass.segmentsClockWise.get(i).getLanes()
//                                .get(j)
//                                .getLane()[k]
//                                .setVehicle(new Car(3, ThreadLocalRandom.current().nextInt(3, 6), 0.05, k, j));
//                        bypass.segmentsClockWise.get(i).getLanes()
//                                .get(j)
//                                .getLane()[k]
//                                .setOccupied(true);
//
//
//                        bypass.segmentsAntiClockWise.get(i).getLanes()
//                                .get(j)
//                                .getLane()[k]
//                                .setVehicle(new Car(3, ThreadLocalRandom.current().nextInt(3, 6), 0.05, k, j));
//                        bypass.segmentsAntiClockWise.get(i).getLanes()
//                                .get(j)
//                                .getLane()[k]
//                                .setOccupied(true);
//                    }
//                }
//            }

            int nMax = ThreadLocalRandom.current().nextInt(settigns.getnCars() - 5, settigns.getnCars() + 6);
            placeCars(settigns, i, nMax, bypass.segmentsClockWise);
            nMax = ThreadLocalRandom.current().nextInt(settigns.getnCars() - 5, settigns.getnCars() + 6);
            placeCars(settigns, i, nMax, bypass.segmentsAntiClockWise);
        }
    }

    private void placeCars(Settings settigns, int i, int nMax, ArrayList<Road> roads) {
        for (int j = 0; j < nMax; j++){
            boolean occupied = true;
            ArrayList<Lane> currentRoadLanes = roads.get(i).getLanes();
            while(occupied){
                int posX = ThreadLocalRandom.current().nextInt(currentRoadLanes.get(0).getSize());
                int posY = ThreadLocalRandom.current().nextInt(/*currentRoadLanes.size()*/2);
                if (!currentRoadLanes.get(posY).getLane()[posX].isOccupied()){
                    currentRoadLanes.get(posY).getLane()[posX]
                            .setVehicle(new Car(settings.getvMaxL() - 1, ThreadLocalRandom.current().nextInt(settings.getvMaxL(), settigns.getvMaxH()), settings.getSlowProbability(), posX, posY));
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
