package main.road;

import java.util.ArrayList;

public class Road {
    private int roadId;
    private ArrayList<Lane> lanes;
    private ArrayList<Road> allSegments;
    private Road nextSegment;
    private final int SIZE;
    private int numberOfCars;
    private double averageVelocity;

    //generates given quantity of lanes with given length of road
    public Road(int roadId, int lanesQuantity, int length, ArrayList<Road> allSegments){
        this.numberOfCars = 0;
        this.averageVelocity = 0;
        this.roadId = roadId;
        this.allSegments = allSegments;
        SIZE = length;
        lanes = new ArrayList<Lane>();
        for (int i = 0; i < lanesQuantity; i++){
            lanes.add(new Lane(SIZE, this));
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
        double velocitySum = 0;
        numberOfCars = 0;
        for (Lane lane: lanes){
            for (int i = lane.getSize() - 1; i >= 0; i--){
                if (lane.getLane()[i].isOccupied()){
                    numberOfCars++;
                    velocitySum += lane.getLane()[i].getVehicle().getVelocity();
                    lane.moveVehicle(i);
                }
            }
        }
        averageVelocity = velocitySum / numberOfCars;
    }

    public int getSIZE() {
        return SIZE;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }

    public ArrayList<Road> getAllSegments() {
        return allSegments;
    }

    public void setAllSegments(ArrayList<Road> allSegments) {
        this.allSegments = allSegments;
    }

    public Road getNextSegment() {
        return nextSegment;
    }

    public void setNextSegment(Road nextSegment) {
        this.nextSegment = nextSegment;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void setNumberOfCars(int numberOfCars) {
        this.numberOfCars = numberOfCars;
    }

    public double getAverageVelocity() {
        return averageVelocity;
    }

    public void setAverageVelocity(double averageVelocity) {
        this.averageVelocity = averageVelocity;
    }
}
