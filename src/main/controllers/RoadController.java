package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;

public class RoadController {

    private SettingsController settingsController;
    private MenuController menuController;
    private MainController mainController;

    @FXML
    public void openSettings() throws IOException {
        menuController.openSettings();
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
}
