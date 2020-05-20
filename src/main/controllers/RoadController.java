package main.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class RoadController {

    private SettingsController settingsController;
    private MenuController menuController;
    private MainController mainController;

    @FXML
    public GridPane road;

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

    public void drawRoad(){

        for(int i = 0; i < 150; ++i){
            for(int j = 0; j < 7; ++j){
            Rectangle r = new Rectangle();
            r.setHeight(15);
            r.setWidth(15);
            if(j == 3){
                r.setFill(Color.DARKGREEN);
            } else {
                r.setFill(Color.LIGHTGRAY);
            }
            road.add(r, i, j);
            }
        }
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
