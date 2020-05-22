package main.simulation;

import main.road.Bypass;
import java.util.Timer;
import java.util.TimerTask;

public class Simulation {

    private boolean isActive;
    private Bypass bypass;


    class Updater extends TimerTask {
        @Override
        public void run() {
            for (int i = 0; i < bypass.segmentsQuantity; i++){
                bypass.segmentsClockWise.get(i).process();
                bypass.segmentsAntiClockWise.get(i).process();
            }
        }
    }

    public Simulation(Settings settings){
        bypass = new Bypass();
        bypass.init(settings);
    }


    public void start() throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new Updater(), 0 ,1000);
    }


    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean getActive(){
        return isActive;
    }

    public Bypass getBypass() {
        return bypass;
    }

    public void setBypass(Bypass bypass) {
        this.bypass = bypass;
    }
}
