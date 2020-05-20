package main.road;

import main.vehicle.Vehicle;

//single 7.5 m square, might contain vehicle or be empty
public class Cell {
    private boolean occupied;
    private Vehicle vehicle;

    public Cell(){
        this.occupied = false;
        this.vehicle = null;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
