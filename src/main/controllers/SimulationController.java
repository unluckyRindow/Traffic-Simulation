package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import main.Simulation;

import java.io.IOException;

public class SimulationController {

    public Simulation simulation;
    private MenuController menuController;
    private SettingsController settingsController;
    private MainController mainController;

    @FXML
    public void openSettings() throws IOException {
        menuController.openSettings();
    }

    @FXML
    public void showRoadScreen() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/RoadScreen.fxml"));
        Pane pane = loader.load();
        RoadController roadController = loader.getController();
        roadController.drawRoad();
        roadController.setMainController(mainController);
        roadController.setMenuController(menuController);
        roadController.setSettingsController(settingsController);
        mainController.setScreen(pane);
    }

    @FXML
    public void exit(){
        Platform.exit();
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
