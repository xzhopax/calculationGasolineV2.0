package calculationGasoline.cars;


import calculationGasoline.cars.enumsForCar.CarSpeedAndPetrol;
import calculationGasoline.cars.enumsForCar.NameCar;
import calculationGasoline.cars.enumsForCar.RoadLoad;

public class VolkswagenPolo extends Car {

    private final String name = NameCar.VOLKSWAGEN_POLO.getName();
    private final double maxSpeed = 200;
    private double gasolineCosts  = 0;
    private boolean conditioner = true, dynamicDriving = true;

    //Explanations in the class Car
    @Override
    public void drivingInCity(int traffic) {
//        int thisTraffic = CheckingEnteredData.fixErrorTraffic(traffic); // fix out bound array
        gasolineConsumption(RoadLoad.VOLKSWAGEN_POLO.getFuelConsumptionFromRoadLoad()[traffic]);
    }

    //Explanations in the class Car
    @Override
    public void drivingOnHighway(double speed) {
        gasolineConsumption(CarSpeedAndPetrol.VOLKSWAGEN_POLO.returnGasolineConsumptionWithCarSpeed(speed));
    }


    //Getter and Setter
    @Override
    public double getGasolineCosts() {
        return gasolineCosts;
    }
    @Override
    public void setGasolineCosts(double gasolineCosts) {
        this.gasolineCosts = gasolineCosts;
    }
    @Override
    public boolean isConditioner() {
        return conditioner;
    }
    @Override
    public void setConditioner(boolean conditioner) {
        this.conditioner = conditioner;
    }
    @Override
    public boolean isDynamicDriving() {
        return dynamicDriving;
    }
    @Override
    public void setDynamicDriving(boolean dynamicDriving) {
        this.dynamicDriving = dynamicDriving;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public double getMaxSpeed() {
        return maxSpeed;
    }
    //End Getter and Setter
}
