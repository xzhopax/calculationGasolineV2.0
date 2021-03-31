package calculationGasoline.cars;

import calculationGasoline.cars.enumsForCar.CarSpeedAndPetrol;
import calculationGasoline.cars.enumsForCar.NameCar;
import calculationGasoline.cars.enumsForCar.RoadLoad;

public abstract class Car {
    private final String name = NameCar.CAR.getName();
    private final double maxSpeed = 200;
    private double gasolineCosts  = 0;
    private boolean conditioner = true, dynamicDriving = true;

    /**
     * gasolineConsumption - Adds of field class gasolineCosts transferred value
     * @param gasolineCosts - gasoline costs
     */
    public void gasolineConsumption (double gasolineCosts ){
        setGasolineCosts(getGasolineCosts() + gasolineCosts);
    }

    /**
     * drivingWithOrNotConditioning - if during the trip conditioner on, gasolineCosts +
     * fuel consumption when working conditioner
     *
      * @param conditioner - During the trip conditioner on or off
     */
    public void drivingWithOrNotConditioning(boolean conditioner){
        if (conditioner && getGasolineCosts() != 0) gasolineConsumption(getGasolineCosts() * 0.20);
    }

    /**
     * drivingWithDynamicStyle -  Fast (sharp) acceleration and sharp car brakes
     *
     * @param dynamicDriving - Dynamic ride was used or not
     */
    public void drivingWithDynamicStyle(boolean dynamicDriving) {
        if (dynamicDriving && getGasolineCosts() != 0) gasolineConsumption(getGasolineCosts() * 0.30);
    }

    /**
     * drivingWithOrNotConditioningInCity - calculates the spent amount of
     * gasoline when driving around the city with air conditioning or without air conditioning
     *
     * @param traffic - How many cork points in the city
     */
    public void drivingInCity(int traffic) {
//        int thisTraffic = CheckingEnteredData.fixErrorTraffic(traffic); // fix out bound array
        gasolineConsumption(RoadLoad.CAR.getFuelConsumptionFromRoadLoad()[traffic]);
    }

    /**
     * drivingWithOrNotConditioningOnHighway - Calculates the spent the amount of
     * gasoline when driving on the highway with air conditioning or without air conditioning
     *
     * @param speed - Average vehicle speed throughout the trip
     */
    public void drivingOnHighway( double speed) {
        gasolineConsumption(CarSpeedAndPetrol.CAR.returnGasolineConsumptionWithCarSpeed(speed));
    }

    //Getter and Setter
    public boolean isConditioner() {
        return conditioner;
    }
    public void setConditioner(boolean conditioner) {
        this.conditioner = conditioner;
    }
    public boolean isDynamicDriving() {
        return dynamicDriving;
    }
    public void setDynamicDriving(boolean dynamicDriving) {
        this.dynamicDriving = dynamicDriving;
    }
    public double getGasolineCosts() {
        return gasolineCosts;
    }
    public void setGasolineCosts(double gasolineCosts) {
        this.gasolineCosts = gasolineCosts;
    }
    public String getName() {
        return name;
    }
    public double getMaxSpeed() {
        return maxSpeed;
    }
    //End Getter and Setter
}
