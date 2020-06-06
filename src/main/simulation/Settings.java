package main.simulation;

public class Settings {

    private double slowProbability;
    private int vMaxH;
    private int vMaxL;
    private int nCars;
    private String hour;

    public void setnCars(String time){

        switch(time){
            case("early afternoon"):
                hour = "13";
                nCars = 25;
                break;
            case("evening"):
                hour = "20";
                nCars = 25;
                break;
            case("early morning"):
                hour = "06";
                nCars = 40;
                break;
            case("forenoon"):
                hour = "11";
                nCars = 20;
                break;
            case("late afternoon"):
                hour = "16";
                nCars = 45;
                break;
            case("night"):
                hour = "02";
                nCars = 10;
                break;
            default:
                hour = "09";
                nCars = 25;
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
            case("clouds"):
            case("cloudy"):
            case("wind"):
                slowProbability = 0.05;
                vMaxH = 5;
                vMaxL = 3;
                break;
            case("rain"):
            case("atmosphere"):
            case("fog"):
                slowProbability = 0.15;
                vMaxH = 4;
                vMaxL = 3;
                break;
            case("snow"):
            case("sleet"):
                slowProbability = 0.2;
                vMaxH = 4;
                vMaxL = 3;
                break;
            case("thunderstorm"):
            case("hail"):
            case("tornado"):
                slowProbability = 0.3;
                vMaxH = 3;
                vMaxL = 2;
                break;
            default:
                slowProbability = 0.05;
                vMaxH = 6;
                vMaxL = 3;
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

    public String getHour() {
        return hour;
    }

    public int getnCars() {
        return nCars;
    }
}
