package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import main.road.Cell;
import main.road.Lane;
import main.road.Road;
import main.vehicle.Car;

import java.util.concurrent.TimeUnit;

public class MenuController {
    @FXML
    public void startSimulation() throws InterruptedException {

        Road road = new Road(3, 100);

        road.getLanes()
                .get(0)
                .getLane()[0]
                .setVehicle(new Car(4, 5, 0.1, 0,0));
        road.getLanes()
                .get(0)
                .getLane()[0]
                .setOccupied(true);


        road.getLanes()
                .get(0)
                .getLane()[10]
                .setVehicle(new Car(2, 4, 0.1, 10,0));
        road.getLanes()
                .get(0)
                .getLane()[10]
                .setOccupied(true);

        road.getLanes()
                .get(0)
                .getLane()[12]
                .setVehicle(new Car(3, 5, 0.1, 12,0));
        road.getLanes()
                .get(0)
                .getLane()[12]
                .setOccupied(true);

        road.getLanes()
                .get(2)
                .getLane()[10]
                .setVehicle(new Car(3, 5, 0.1, 10,2));
        road.getLanes()
                .get(2)
                .getLane()[10]
                .setOccupied(true);

        road.getLanes()
                .get(2)
                .getLane()[20]
                .setVehicle(new Car(3, 6, 0.1, 20,2));
        road.getLanes()
                .get(2)
                .getLane()[20]
                .setOccupied(true);



        while (true) {
            TimeUnit.SECONDS.sleep(1);
            road.process();

            for(Lane lane: road.getLanes()){
                for (Cell cell: lane.getLane()) System.out.print(cell.isOccupied() ? "@" : "-");
                System.out.println();
            }
        System.out.println();
        }


    }
    @FXML
    public void openSettings(){

    }
    @FXML
    public void exit(){
        Platform.exit();
    }
}
