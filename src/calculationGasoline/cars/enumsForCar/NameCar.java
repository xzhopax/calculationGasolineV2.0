package calculationGasoline.cars.enumsForCar;

public enum NameCar {
    /**
     * Enum - to provide a car name
     */
    CAR("Car"),
    VOLKSWAGEN_POLO("Volkswagen POLO"),
    FORD_FOCUS_3("Ford Focus 3");

    private final String name;

    NameCar(String name){ // constructor
          this.name = name;
    }

    //getters
    public String getName() {
        return name;
    }
    //end getters
}
