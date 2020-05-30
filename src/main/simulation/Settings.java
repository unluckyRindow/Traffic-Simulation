package main.simulation;

public class Settings {

    private double slowProbability;
    private int vMaxH;
    private int vMaxL;
    private int nCars;

    public void setnCars(String time){

        switch(time){
            case("rano"):
            case("wczesne popołudnie"):
            case("wieczór"):
                nCars = 25;
                break;
            case("wczesne rano"):
                nCars = 40;
                break;
            case("przedpołudnie"):
                nCars = 20;
                break;
            case("późne popołudnie"):
                nCars = 45;
                break;
            case("noc"):
                nCars = 10;
                break;
        }
    }

    public void setnCarsWTime(){
        int time = java.time.LocalDateTime.now().getHour();
        if(time < 6){
           nCars = 10;
        } else if(time == 6){
            nCars = 40;
        } else if(time < 10){
            nCars = 25;
        } else if(time < 12){
            nCars = 20;
        } else if(time < 14){
            nCars = 25;
        } else if(time < 17){
            nCars = 45;
        } else if(time < 21){
            nCars = 25;
        } else {
            nCars = 10;
        }
    }

    public void setSlowProbabilityAndvMax(String weather) {

        switch(weather){
            case("clear"):
                slowProbability = 0.05;
                vMaxH = 6;
                vMaxL = 4;
                break;
            case("clouds"):
                slowProbability = 0.05;
                vMaxH = 5;
                vMaxL = 4;
                break;
            case("rain"):
            case("atmosphere"):
                slowProbability = 0.15;
                vMaxH = 4;
                vMaxL = 3;
                break;
            case("snow"):
                slowProbability = 0.1;
                vMaxH = 4;
                vMaxL = 3;
                break;
            case("thunderstorm"):
                slowProbability = 0.1;
                vMaxH = 3;
                vMaxL = 2;
                break;
        }
    }

    public double getSlowProbability() {
        return slowProbability;
    }

    public int getvMaxH() {
        return vMaxH;
    }

    public int getvMaxL() {
        return vMaxL;
    }

    public int getnCars() {
        return nCars;
    }
}
