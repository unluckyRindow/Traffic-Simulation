package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;

public class SettingsController {
    @FXML
    public void startSimulation(){

    }

    @FXML
    public void exit(){
        Platform.exit();
    }
}
