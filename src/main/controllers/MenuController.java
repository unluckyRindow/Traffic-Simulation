package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class MenuController {

    private MainController mainController;
    private SettingsController settingsController;

    @FXML
    public void startSimulation() throws IOException, InterruptedException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/SettingsScreen.fxml"));
        loader.load();
        settingsController = loader.getController();
        settingsController.setMainController(mainController);
        settingsController.setMenuController(this);
        setSettingsController(settingsController);
        settingsController.setMenuStart(true);
        settingsController.startSimulation();
    }

    @FXML
    public void openSettings() throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/SettingsScreen.fxml"));
        Pane pane = loader.load();
        SettingsController settingsController = loader.getController();
        settingsController.setDateValue();
        settingsController.setMainController(mainController);
        settingsController.setMenuController(this);
        setSettingsController(settingsController);
        mainController.setScreen(pane);
    }

    @FXML
    public void exit(){
        Platform.exit();
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
