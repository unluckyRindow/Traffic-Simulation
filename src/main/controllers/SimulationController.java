package main.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import main.simulation.Simulation;

import java.io.IOException;

public class SimulationController {

    @FXML
    Label numberOfCars;
    @FXML
    Label averageVelocity;

    public Simulation simulation;
    private MenuController menuController;
    private SettingsController settingsController;
    private MainController mainController;

    Timeline updater;


    @FXML
    public void showRoadScreen(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource() ;
        int segmentId = Integer.parseInt((String) node.getUserData());

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/RoadScreen.fxml"));
        Pane pane = loader.load();

        RoadController roadController = loader.getController();
        roadController.setSettingsController(settingsController);
        roadController.setSimulation(simulation);
        roadController.setRoadId(segmentId);
        roadController.startViewUpdater();
        mainController.setScreen(pane);
    }

    @FXML
    public void backToMenu() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/MenuScreen.fxml"));
        Pane pane = loader.load();
        MenuController menuController = loader.getController();
        menuController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void startViewUpdater(){
        numberOfCars.setText(Integer.toString(getNumberOfCars()));
        averageVelocity.setText(String.format("%.2f", getAverageVelocity()));

        updater = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            numberOfCars.setText(Integer.toString(getNumberOfCars()));
            averageVelocity.setText(String.format("%.2f", getAverageVelocity()));
        }));
        updater.setCycleCount(Timeline.INDEFINITE);
        updater.play();
    }

    public int getNumberOfCars(){
        int sum = 0;

        for (int i = 0; i < simulation.getBypass().segmentsQuantity; i++){
            sum += simulation.getBypass().segmentsClockWise.get(i).getNumberOfCars()
                    + simulation.getBypass().segmentsAntiClockWise.get(i).getNumberOfCars();
        }
        return sum;
    }

    public double getAverageVelocity(){
        double avg = 0;

        for (int i = 0; i < simulation.getBypass().segmentsQuantity; i++){
            avg += simulation.getBypass().segmentsClockWise.get(i).getAverageVelocity()
                    + simulation.getBypass().segmentsAntiClockWise.get(i).getAverageVelocity();
        }
        avg /= simulation.getBypass().segmentsQuantity * 2;

        return avg*27;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public Simulation getSimulation() {
        return simulation;
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
}
