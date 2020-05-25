package main.vehicle;

import main.road.Cell;

import java.util.Arrays;
import java.util.Random;

public class Car implements Vehicle {
    private int velocity;
    private int maxVelocity;
    private double slowProbability;
    private Cell[] frontNeighbourhood;
    private int posX;
    private int posY;
    private boolean changedLane;

    public Car(int velocity, int maxVelocity, double slowProbability, int posX, int posY) {
        this.velocity = velocity;
        this.maxVelocity = maxVelocity;
        this.slowProbability = slowProbability;
        this.posX = posX;
        this.posY = posY;
        this.changedLane = false;
    }

    public Cell[] getFrontNeighbourhood() {
        return frontNeighbourhood;
    }

    public void setFrontNeighbourhood(Cell[] frontNeighbourhood) {
        this.frontNeighbourhood = frontNeighbourhood;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public double getSlowProbability() {
        return slowProbability;
    }

    public void setSlowProbability(double slowProbability) {
        this.slowProbability = slowProbability;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void increaseVelocity(){
        velocity++;
    }

    public void decreaseVelocity(){
        velocity--;
    }

    public void changeLaneAttempt(){
        posY = posY == 0 ? 1 : posY - 1;

    }

    public int move(){

        //increase velocity if possible
        if (velocity < maxVelocity) increaseVelocity();

        //null in front neighbourhood means end of road so return unreachable high value for lane object
        if (Arrays.asList(frontNeighbourhood).contains(null)) return 999999;

        //if cell in front is closer than max velocity than decrease velocity in loop to avoid collision
        for(Cell cell: frontNeighbourhood){
            if (cell.isOccupied()){
                int distance = cell.getVehicle().getPosX() - this.getPosX();
                if (new Random().nextDouble() <= 0.25 && !changedLane) {
                    changeLaneAttempt();
                    changedLane = true;
                    return posX;
                }
                while(velocity >= distance){
                    decreaseVelocity();
                }
            }
        }
        //random slow with give probability
        if (velocity > 1){
            if(new Random().nextDouble() <= slowProbability) decreaseVelocity();
        }
        //change position and returns new coordinates
        posX += velocity;
        return posX;
    }

    public boolean isChangedLane() {
        return changedLane;
    }

    public void setChangedLane(boolean changedLane) {
        this.changedLane = changedLane;
    }
}
