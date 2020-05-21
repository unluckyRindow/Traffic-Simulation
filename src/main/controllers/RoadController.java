package main.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.Simulation;
import main.road.Road;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class RoadController {

    @FXML
    ScrollPane firstRoad;
    @FXML
    ScrollPane secondRoad;

    private Simulation simulation;
    private SettingsController settingsController;
    private MenuController menuController;
    private MainController mainController;


    //method to draw road
    public void draw(Road firstRoad, Road secondRoad){
        GridPane firstGrid = new GridPane();
        GridPane secondGrid = new GridPane();
        Rectangle rectangle1, rectangle2;

        for (int i = 0; i < firstRoad.getLanes().size(); i++){
            for (int j = 0; j < firstRoad.getSIZE(); j++){

                rectangle1 = new Rectangle();
                rectangle1.setHeight(24);
                rectangle1.setWidth(24);
                rectangle2 = new Rectangle();
                rectangle2.setHeight(24);
                rectangle2.setWidth(24);
                rectangle2.setFill(Color.ROSYBROWN);

                if (simulation.getRoads().get(0).getLanes().get(i).getLane()[j].isOccupied()) {
                    rectangle1.setFill(Color.RED);
                } else {
                    rectangle1.setFill(Color.ROYALBLUE);
                }

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


    @FXML
    public void openSettings() throws IOException {
        //settings not needed here imo, button used for testing

        //menuController.openSettings();

        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                draw(simulation.getRoads().get(0), simulation.getRoads().get(1));
            }
        }));
        fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
        fiveSecondsWonder.play();
    }

    @FXML
    public void showSimulationScreen() throws IOException {
        settingsController.setSimulationScreen();
    }

    @FXML
    public void exit(){
        //for testing aswell
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
