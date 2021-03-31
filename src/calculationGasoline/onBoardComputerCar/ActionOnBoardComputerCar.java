package calculationGasoline.onBoardComputerCar;

public interface ActionOnBoardComputerCar {

    default void priceOnGasolineCosts (double distance, double price) {}
    default void todayDate(String date) {}
    default void resetGasAndResultGas() {}
    default String reportCity() { return "";}
    default String reportHighway() {return "";}


}
