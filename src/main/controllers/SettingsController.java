package main.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import main.simulation.Settings;
import main.simulation.Simulation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;

public class SettingsController {

    //init here not to get null pointer, to fix later
    private Settings settings = new Settings();
    private Simulation simulation;
    private boolean menuStart = false;

    private MainController mainController;
    private MenuController menuController;
    private SimulationController simulationController;

    @FXML
    private ChoiceBox time;

    @FXML
    private ChoiceBox weather;

    @FXML
    private DatePicker date;

    @FXML
    private ChoiceBox time1;

    @FXML
    private CheckBox withDate;

    @FXML
    public void startSimulation() throws IOException, InterruptedException {
        if(menuStart){
            settings.setSlowProbabilityAndvMax(getCurrentWeather().toLowerCase());
            settings.setnCarsWTime();
        } else {
            if(withDate.isSelected()){
                settings.setnCars((String) time1.getValue());
                settings.setSlowProbabilityAndvMax(getHistoricalWeather().toLowerCase());
            } else {
                settings.setnCars((String) time.getValue());
                settings.setSlowProbabilityAndvMax((String) weather.getValue());
            }
        }
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
        simulationController.startViewUpdater();
    }

    public String getCurrentWeather() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://community-open-weather-map.p.rapidapi.com/weather?units=%2522metric%2522&mode=JSON&q=Krakow")
                .get()
                .addHeader("x-rapidapi-host", "community-open-weather-map.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "6d4ce46db1mshfe089c60ff3f7d9p176410jsne78d142b5a5f")
                .build();

        Response response = client.newCall(request).execute();
        if(response.code() == 200){

            JsonReader reader = Json.createReader(response.body().byteStream());
            JsonObject obj = reader.readObject();
            reader.close();

            return obj.getJsonArray("weather").get(0).asJsonObject().getString("main");
        } else {
            throw new Error("https://www.youtube.com/watch?v=Q8Afvt0o3yE");
        }
    }

    public String getHistoricalWeather() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://dark-sky.p.rapidapi.com/50.064651,19.944981,"+date.getValue()+"T"+settings.getHour()+":00:00")
                .get()
                .addHeader("x-rapidapi-host", "dark-sky.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "6d4ce46db1mshfe089c60ff3f7d9p176410jsne78d142b5a5f")
                .build();

        Response response = client.newCall(request).execute();
        if(response.code() == 200){
            JsonReader reader = Json.createReader(response.body().byteStream());
            JsonObject obj = reader.readObject();
            reader.close();
            return obj.getJsonObject("currently").getString("icon");
        } else {
            throw new Error("https://www.youtube.com/watch?v=Q8Afvt0o3yE");
        }
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

    public void setMenuStart(boolean menuStart) {
        this.menuStart = menuStart;
    }

    public Settings getSettings() {
        return settings;
    }

    public void setDateValue(){
        date.setValue(java.time.LocalDate.now());
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
