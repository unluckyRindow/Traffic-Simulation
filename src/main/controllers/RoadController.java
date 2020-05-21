package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import main.Simulation;
import main.road.Road;

import java.io.IOException;

public class RoadController {

    @FXML
    ScrollPane firstRoad;
    @FXML
    ScrollPane secondRoad;

    private Simulation simulation;
    private SettingsController settingsController;
    private MenuController menuController;
    private MainController mainController;



    //method to draw empty road at start
    public void init(Road firstRoad, Road secondRoad){

        GridPane firstGrid = new GridPane();
        GridPane secondGrid = new GridPane();
        Rectangle rectangle1, rectangle2;

        for (int i = 0; i < firstRoad.getLanes().size(); i++){
            for (int j = 0; j < firstRoad.getSIZE(); j++){
                rectangle1 = new Rectangle();
                rectangle1.setHeight(30);
                rectangle1.setWidth(30);
                rectangle1.setFill(Color.ROYALBLUE);
                rectangle2 = new Rectangle();
                rectangle2.setHeight(30);
                rectangle2.setWidth(30);
                rectangle2.setFill(Color.ROYALBLUE);

                firstGrid.add(rectangle1, j, i,1,1);
                firstGrid.setHgap(7);
                firstGrid.setVgap(7);
                secondGrid.add(rectangle2, j, i,1,1);
                secondGrid.setHgap(7);
                secondGrid.setVgap(7);

            }
        }
        this.firstRoad.setContent(firstGrid);
        this.secondRoad.setContent(secondGrid);
    }

    //method to update view every second
    public void update(){

    }

    @FXML
    public void openSettings() throws IOException {
        //settings not needed here imo, button used for testing

        //menuController.openSettings();

        Road road1 = new Road(3,200);
        Road road2 = new Road(3,200);

        init(road1, road2);
    }

    @FXML
    public void showSimulationScreen() throws IOException {
        settingsController.setSimulationScreen();
    }

    @FXML
    public void exit(){
        Platform.exit();
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }


    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
