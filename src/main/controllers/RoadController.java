package main.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import main.simulation.Simulation;

import java.io.IOException;

public class RoadController {

    @FXML
    Label numberOfCars;
    @FXML
    Label averageVelocity;
    @FXML
    Label segmentId;
    @FXML
    ScrollPane firstRoad;
    @FXML
    ScrollPane secondRoad;

    Timeline updater;

    private Simulation simulation;
    private SettingsController settingsController;
    private int roadId;


    public void startViewUpdater(){
        draw();

        setSegmentId(roadId);
        updater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            draw();
        }));

        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

    private void draw() {
        draw(simulation.getBypass().segmentsClockWise
                .get(roadId).getSIZE(), simulation.getBypass().segmentsClockWise.get(roadId).getLanes().size());

        numberOfCars.setText(Integer.toString(simulation.getBypass().segmentsClockWise.get(roadId).getNumberOfCars()
                + simulation.getBypass().segmentsAntiClockWise.get(roadId).getNumberOfCars()));

        averageVelocity.setText(String.format("%.2f",
                ((simulation.getBypass().segmentsClockWise.get(roadId).getAverageVelocity()
                        + simulation.getBypass().segmentsAntiClockWise.get(roadId).getAverageVelocity())
                / 2* 27)
        ));
    }

    public void draw(int roadWidth, int roadHeight){
        Pane firstPane = new Pane();
        Pane secondPane = new Pane();
        firstPane.getChildren().add(new Rectangle(roadWidth*24,roadHeight*24, Color.ROYALBLUE));
        secondPane.getChildren().add(new Rectangle(roadWidth*24,roadHeight*24, Color.ROYALBLUE));
        GridPane firstGrid = new GridPane();
        GridPane secondGrid = new GridPane();
        Rectangle rectangle1, rectangle2;

        for (int i = 0; i < roadHeight; i++){
            for (int j = 0; j < roadWidth; j++){

                rectangle1 = new Rectangle();
                rectangle1.setHeight(24);
                rectangle1.setWidth(24);
                rectangle2 = new Rectangle();
                rectangle2.setHeight(24);
                rectangle2.setWidth(24);

                rectangle1.setFill(
                        simulation.getBypass().segmentsClockWise.get(roadId).getLanes().get(i).getLane()[j].isOccupied() ?
                                new ImagePattern(new Image("/main/resources/car_clockwise.png")):
                                Color.ROYALBLUE
                );

                rectangle2.setFill(
                        simulation.getBypass().segmentsAntiClockWise.get(roadId).getLanes().get(i).getLane()[simulation
                                .getBypass().segmentsAntiClockWise.get(roadId).getSIZE() - j - 1].isOccupied() ?
                                new ImagePattern(new Image("/main/resources/car_anticlockwise.png")) :
                                Color.ROYALBLUE
                );

                firstGrid.add(rectangle1, j, i,1,1);
                secondGrid.add(rectangle2, j, i,1,1);

            }
        }
        firstPane.getChildren().add(firstGrid);
        secondPane.getChildren().add(secondGrid);
        this.firstRoad.setContent(firstPane);
        this.secondRoad.setContent(secondPane);
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

    public void setSegmentId(int roadId){
        String name = "";

        switch (roadId){
            case 0:
                name = "Modlnica - Zielonki";
                break;
            case 1:
                name = "Zielonki - Węgrzyce";
                break;
            case 2:
                name = "Węgrzyce - Batowice";
                break;
            case 3:
                name = "Batowice - Mistrzejowice";
                break;
            case 4:
                name = "Mistrzejowice - Grębałów";
                break;
            case 5:
                name = "Grębałów - Nowa Huta";
                break;
            case 6:
                name = "Nowa Huta - Przewóz";
                break;
            case 7:
                name = "Przewóz - Bieżanów";
                break;
            case 8:
                name = "Bieżanów - Wieliczka";
                break;
            case 9:
                name = "Wieliczka - Łagiewniki";
                break;
            case 10:
                name = "Łagiewniki - Południe";
                break;
            case 11:
                name = "Południe - Skawina";
                break;
            case 12:
                name = "Skawina - Tyniec";
                break;
            case 13:
                name = "Tyniec - Bielany";
                break;
            case 14:
                name = "Bielany - Balice";
                break;
            case 15:
                name = "Balice - Balice I";
                break;
            case 16:
                name = "Balice I - Modlniczka";
                break;
            case 17:
                name = "Modlniczka - Modlnica";
                break;
        }
        segmentId.setText(name);
        Pane pane = (Pane) segmentId.getParent();
        segmentId.layoutXProperty().bind(pane.widthProperty().subtract(segmentId.widthProperty()).divide(2));

    }
}
