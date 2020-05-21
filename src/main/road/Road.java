package main.road;

import java.util.ArrayList;

public class Road {
    private ArrayList<Lane> lanes;
    //length of each lane
    private final int SIZE;

    //generates given quantity of lanes with given length of road
    public Road(int lanesQuantity, int length){
        SIZE = length;
        lanes = new ArrayList<Lane>();
        for (int i = 0; i < lanesQuantity; i++){
            lanes.add(new Lane(SIZE));
        }
    }

    public ArrayList<Lane> getLanes() {
        return lanes;
    }

    public void setLanes(ArrayList<Lane> lanes) {
        this.lanes = lanes;
    }

    //iterate through every lane from the end to beginning, if cell is not empty then calls moveVehicle method
    // to process vehicle movement
    public void process(){
        for (Lane lane: lanes){

            for (int i = lane.getSize() - 1; i >= 0; i--){
                if (lane.getLane()[i].isOccupied()) lane.moveVehicle(i);
            }
        }
    }

    public int getSIZE() {
        return SIZE;
    }
}
