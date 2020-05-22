package main.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import main.simulation.Simulation;

import java.io.IOException;

public class SimulationController {

    public Simulation simulation;
    private MenuController menuController;
    private SettingsController settingsController;
    private MainController mainController;


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
