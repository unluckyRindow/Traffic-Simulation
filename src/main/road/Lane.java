package main.road;

import main.vehicle.Vehicle;
import java.util.Arrays;


//single traffic lane
public class Lane {
    //quantity of 7.5 m cells representing traffic lane
    private final int SIZE;
    private Cell [] lane;

    //every lane initialized with x empty cells
    public Lane(int size){
        this.SIZE = size;
        lane = new Cell[SIZE];
        for (int i = 0; i < SIZE; i++) lane[i] = new Cell();
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

    //method maintaining movement of vehicles in specific lane
    public void moveVehicle(int position){
        Vehicle vehicle = lane[position].getVehicle();
        vehicle.setFrontNeighbourhood(Arrays.copyOfRange(lane, position + 1, position + vehicle.getMaxVelocity() + 1));

        int newPosition = vehicle.move();
        if (newPosition <= SIZE){
            lane[newPosition].setVehicle(vehicle);
            lane[newPosition].setOccupied(true);
        }
        clearCell(position);
    }
}
