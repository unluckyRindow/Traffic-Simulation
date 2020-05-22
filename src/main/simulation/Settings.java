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

    public int getnCars() {
        return nCars;
    }

    public void setSlowProbabilityAndvMax(String weather) {
        switch(weather){
            case("słońce"):
                this.slowProbability = 0.05;
                this.vMaxH = 6;
                this.vMaxL = 4;
                break;
            case("deszcz"):
            case("mgła"):
                this.slowProbability = 0.15;
                this.vMaxH = 4;
                this.vMaxL = 3;
                break;
            case("śnieg"):
                this.slowProbability = 0.1;
                this.vMaxH = 4;
                this.vMaxL = 3;
                break;
            case("lód"):
                this.slowProbability = 0.1;
                this.vMaxH = 3;
                this.vMaxL = 2;
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
}
