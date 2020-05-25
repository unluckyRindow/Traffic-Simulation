package main.vehicle;

import main.road.Cell;
import main.road.Road;

public interface Vehicle {
    void increaseVelocity();
    void decreaseVelocity();
    Cell[] getFrontNeighbourhood();
    void setFrontNeighbourhood(Cell[] frontNeighbourhood);
    int getVelocity();
    void setVelocity(int velocity);
    int getMaxVelocity();
    void setMaxVelocity(int maxVelocity);
    double getSlowProbability();
    void setSlowProbability(double slowProbability);
    int getPosX();
    void setPosX(int posX);
    int getPosY();
    void setPosY(int posY);
    int move();
    boolean isChangedLane();
    void setChangedLane(boolean changedLane);
    }
