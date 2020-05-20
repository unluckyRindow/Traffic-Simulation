package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class StartScreenController {

    private MainController mainController;

    @FXML
    public void openMenu() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/main/resources/layouts/MenuScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController menuController = loader.getController();
        menuController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
