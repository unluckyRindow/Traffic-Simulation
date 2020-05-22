package main.road;

import main.vehicle.Vehicle;
import java.util.Arrays;


//single traffic lane
public class Lane {
    //quantity of 7.5 m cells representing traffic lane
    private final int SIZE;
    private Cell [] lane;
    private Road roadSegment;

    //every lane initialized with x empty cells
    public Lane(int size, Road roadSegment){
        this.roadSegment = roadSegment;
        this.SIZE = size;
        lane = new Cell[SIZE];
        for (int i = 0; i < SIZE; i++) lane[i] = new Cell();
    }

    //method maintaining movement of vehicles in specific lane
    public void moveVehicle(int position){
        Vehicle vehicle = lane[position].getVehicle();
        vehicle.setFrontNeighbourhood(Arrays.copyOfRange(lane, position + 1, position + vehicle.getMaxVelocity() + 1));

        int newPosition = vehicle.move();
        if (newPosition <= SIZE){
            lane[newPosition].setVehicle(vehicle);
            lane[newPosition].setOccupied(true);
        } else {
            int distanceToEnd = SIZE - position > 10 ? 5 : SIZE - position;
            changeSegment(vehicle, distanceToEnd);
        }
        clearCell(position);
    }

    public void changeSegment(Vehicle vehicle, int distanceToEnd){
        //on segment change place vehicle on first cell TODO place vehicle on computed cell; velocity - distance to end
        roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[0].setOccupied(true);
        roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[0].setVehicle(vehicle);
        vehicle.setPosX(0);
        //code below should decide about vehicle transfer but doesnt work for now
        /*
        int maxNewPosX = distanceToEnd - vehicle.getVelocity();
        for (int i = maxNewPosX; i >= 0; i--){
            if (!roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[i].isOccupied() ){
                System.out.println("Setting on " + roadSegment.getNextSegment().getRoadId() + " on: " + i + " " + vehicle.getPosY());
                roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[i].setOccupied(true);
                roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[i].setVehicle(vehicle);
                vehicle.setPosX(i);
                break;
            }
        }
        */

    }

    public int getSize() {
        return SIZE;
    }

    public Cell[] getLane() {
        return lane;
    }

    public void clearCell(int position){
        lane[position].setVehicle(null);
        lane[position].setOccupied(false);
    }

    public Road getRoadSegment() {
        return roadSegment;
    }

    public void setRoadSegment(Road roadSegment) {
        this.roadSegment = roadSegment;
    }
}
