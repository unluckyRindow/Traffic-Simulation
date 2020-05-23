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

    public void setSlowProbabilityAndvMax(String weather) {

        switch(weather){
            case("słońce"):
                slowProbability = 0.05;
                vMaxH = 6;
                vMaxL = 4;
                break;
            case("deszcz"):
            case("mgła"):
                slowProbability = 0.15;
                vMaxH = 4;
                vMaxL = 3;
                break;
            case("śnieg"):
                slowProbability = 0.1;
                vMaxH = 4;
                vMaxL = 3;
                break;
            case("lód"):
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
