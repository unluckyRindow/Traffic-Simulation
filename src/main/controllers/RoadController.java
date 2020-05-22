package main.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.road.Bypass;
import main.simulation.Settings;
import main.simulation.Simulation;
import main.road.Road;

import java.io.IOException;

public class RoadController {

    @FXML
    ScrollPane firstRoad;
    @FXML
    ScrollPane secondRoad;

    Timeline updater;

    private Simulation simulation;
    private SettingsController settingsController;
    private int roadId;


    public void startViewUpdater(){
        draw(simulation.getBypass().segmentsClockWise.get(roadId), simulation.getBypass().segmentsAntiClockWise.get(roadId));
        updater = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                draw(simulation.getBypass().segmentsClockWise.get(roadId), simulation.getBypass().segmentsAntiClockWise.get(roadId));
            }
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

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

                rectangle1.setFill(
                        simulation.getBypass().segmentsClockWise.get(roadId).getLanes().get(i).getLane()[j].isOccupied() ?
                                new ImagePattern(new Image("src/main/resources/car_clockwise.png")) :
                                Color.ROYALBLUE
                );

                rectangle2.setFill(
                        simulation.getBypass().segmentsAntiClockWise.get(roadId).getLanes().get(i).getLane()[simulation.getBypass().segmentsAntiClockWise.get(roadId).getSIZE() - j - 1].isOccupied() ?
                                new ImagePattern(new Image("src/main/resources/car_anticlockwise.png")) :
                                Color.ROYALBLUE
                );

                firstGrid.add(rectangle1, j, i,1,1);
                secondGrid.add(rectangle2, j, i,1,1);

            }
        }
        this.firstRoad.setContent(firstGrid);
        this.secondRoad.setContent(secondGrid);
    }


    @FXML
    public void showSimulationScreen() throws IOException {
        updater.stop();
        settingsController.setSimulationScreen();
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }


    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public int getRoadId() {
        return roadId;
    }

    public void setRoadId(int roadId) {
        this.roadId = roadId;
    }
}
