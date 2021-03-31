package calculationGasoline.cars.enumsForCar;


public enum CarSpeedAndPetrol {

    /**
     * Instances cars with
     * array an average consumption per 100 km, depending on speed
     * and
     * array speed for double consumption of gasoline[]
     */
    CAR(new double[]{0,9.5, 6.0, 4.8, 4.0, 3.5, 4.0, 5.0, 6.0, 8.0, 10.5},
        new double[]{0, 10,  20,  30,  40,  60,  80, 100, 120, 150, 200} ),
    VOLKSWAGEN_POLO(new double[]{0,9.5, 6.0, 4.8, 4.0, 3.5, 4.0, 5.0, 6.0, 8.0, 10.5},
                    new double[]{0, 10,  20,  30,  40,  60,  80, 100, 120, 150, 200} ),
    FORD_FOCUS_3(new double[]{0,8.5, 5.9, 4.8, 4.0, 3.5, 4.0, 4.8, 5.9, 8.0, 10.5, 12},
                 new double[]{0, 10,  20,  30,  40,  60,  80, 100, 120, 150, 200, 220});

    /**
     * double[] gasolineConsumption - Average consumption per 100 km, depending on speed
     * double[] speedCar - Speed values for double[] gasolineConsumption
     */
    private final double[] gasolineConsumption; // Average consumption per 100 km, depending on speed
    private final double[] speedCar; // Speed values for double[] gasolineConsumption

    CarSpeedAndPetrol(double[] gasolineConsumption, double[] speedCar){ //constructor
        this.gasolineConsumption = gasolineConsumption;
        this.speedCar = speedCar;
    }

    //getter
    public double[] getSpeedCar() {
        return speedCar;
    }
    public double[] getGasolineConsumption() {
        return gasolineConsumption;
    }
    //end getter

    /**
     * returnGasolineConsumptionWithCarSpeed - Looking for a comparison of incoming velocity
     * with a cell double[] speedCar, if it finds, writes the result of double[] gasolineConsumption
     * and comes out of the cycle using "drop:"
     *
     * @param speed - On the entrance takes the average speed of the machine
     * @return - Returns the average consumption per 100km, depending on the speed
     */
    public double returnGasolineConsumptionWithCarSpeed(double speed){
        double result = 0;
        drop: {
            for (int i = 0; i < getGasolineConsumption().length; i++){
                if (speed <= getSpeedCar()[i]){
                    result = getGasolineConsumption()[i];
                    break drop;
                }
            }
        }
        return result;
    }
}


