package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.simulation.Settings;
import main.simulation.Simulation;

import java.io.IOException;

public class SettingsController {
    //init here not to get null pointer, to fix later
    private Settings settings = new Settings();
    private Simulation simulation;

    private MainController mainController;
    private MenuController menuController;
    private SimulationController simulationController;

    @FXML
    public void startSimulation() throws IOException, InterruptedException {
        simulation = new Simulation(settings);
        setSimulationScreen();
        simulationController.setSimulation(simulation);
        simulationController.getSimulation().start();
    }

    @FXML
    public void backToMenu() throws IOException{
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/MenuScreen.fxml"));
        Pane pane = loader.load();
        MenuController menuController = loader.getController();
        menuController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void setSimulationScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/SimulationScreen.fxml"));
        Pane pane = loader.load();

        SimulationController simulationController = loader.getController();
        simulationController.setMainController(mainController);
        simulationController.setMenuController(menuController);
        simulationController.setSimulation(simulation);
        setSimulationController(simulationController);
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

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }
}
