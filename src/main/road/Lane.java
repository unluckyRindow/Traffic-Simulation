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
        int laneIndex = vehicle.getPosY();
        vehicle.setFrontNeighbourhood(Arrays.copyOfRange(lane, position + 1, position + vehicle.getMaxVelocity() + 1));

        int newPosition = vehicle.move();

        if (vehicle.isChangedLane() && (!roadSegment.getLanes().get(vehicle.getPosY()).getLane()[position].isOccupied())) {
            roadSegment.getLanes().get(vehicle.getPosY()).getLane()[position].setVehicle(vehicle);
            roadSegment.getLanes().get(vehicle.getPosY()).getLane()[position].setOccupied(true);
            roadSegment.getLanes().get(vehicle.getPosY()).moveVehicle(position);
        } else {
            vehicle.setPosY(laneIndex);
            vehicle.setChangedLane(false);
            if (newPosition <= SIZE){
                lane[newPosition].setVehicle(vehicle);
                lane[newPosition].setOccupied(true);
            } else {
                int distanceToEnd = SIZE - position > 6 ? 5 : SIZE - position;
                if (vehicle.getPosY() >= roadSegment.getNextSegment().getLanes().size()) vehicle.setPosY(1);
                changeSegment(vehicle, distanceToEnd);
            }
        }
        clearCell(position);
    }

    public void changeSegment(Vehicle vehicle, int distanceToEnd){
        int maxNewPosX = vehicle.getVelocity() - distanceToEnd;
        for (int i = maxNewPosX; i >= 0; i--){
            if (!roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[i].isOccupied() ){
                roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[i].setOccupied(true);
                roadSegment.getNextSegment().getLanes().get(vehicle.getPosY()).getLane()[i].setVehicle(vehicle);
                vehicle.setPosX(i);
                break;
            }
        }

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
