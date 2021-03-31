package calculationGasoline.cars;

import java.util.Map;
import java.util.TreeMap;

/**
 * CreateCar - Class with auxiliary tools to create a class car
 */
public class CreateCar {

    /**
     * static final Map<Integer, Car> mapCreateCars - To get an instance of a class car.
     * For use in methods choosingCar.addActionListener in InCityPanel and OnHighwayPanel
     */
    private static final Map<Integer, Car> mapCreateCars = new TreeMap<>();
    static {
        mapCreateCars.put(1,new VolkswagenPolo());
        mapCreateCars.put(2,new FordFocus3());
    }

    //getter
    public static Map<Integer, Car> getMapCreateCars() {
        return mapCreateCars;
    }
    //end getter
}
