package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.Simulation;

import java.io.IOException;

public class SettingsController {

    private MainController mainController;
    private MenuController menuController;

    @FXML
    public void startSimulation() throws IOException {
        System.out.println("witam");
        Simulation simulation = new Simulation();
        setSimulationScreen().setSimulation(simulation);
//        simulationController.getSimulation().start();

    }

    @FXML
    public void exit(){
        Platform.exit();
    }

    public SimulationController setSimulationScreen() throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/SimulationScreen.fxml"));
        Pane pane = loader.load();
        SimulationController simulationController = loader.getController();

        simulationController.setMainController(mainController);
        simulationController.setMenuController(menuController);
        simulationController.setSettingsController(this);
        mainController.setScreen(pane);
        return simulationController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
