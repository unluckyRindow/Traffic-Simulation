package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.simulation.Settings;
import main.simulation.Simulation;

import java.io.IOException;

public class SettingsController {

    private Settings settings;

    private MainController mainController;
    private MenuController menuController;
    private SimulationController simulationController;

    @FXML
    public void startSimulation() throws IOException, InterruptedException {
        System.out.println("simulation start from settings");
        Simulation simulation = new Simulation(settings);
        setSimulationScreen();
        simulationController.setSimulation(simulation);
        simulationController.getSimulation().start();
    }

    @FXML
    public void exit(){
        Platform.exit();
    }

    public void setSimulationScreen() throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/SimulationScreen.fxml"));
        Pane pane = loader.load();
        SimulationController simulationController = loader.getController();
        simulationController.setMainController(mainController);
        simulationController.setMenuController(menuController);
        this.setSimulationController(simulationController);
        simulationController.setSettingsController(this);
        mainController.setScreen(pane);
    }

    public void setSimulationController(SimulationController simulationController) {
        this.simulationController = simulationController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }
}
