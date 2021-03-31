package calculationGasoline.onBoardComputerCar;

import calculationGasoline.cars.Car;
import calculationGasoline.onBoardComputerCar.workData.Check;
import calculationGasoline.onBoardComputerCar.workData.WorkData;


public class OnBoardComputerCar implements ActionOnBoardComputerCar {
    private String date;
    private double resultGas = 0;
    private StringBuilder sb = new StringBuilder();

    private Car car;

    public OnBoardComputerCar(Car car){//constructor
        this.car = car;
    }

    /**
     * priceOnGasolineCosts - The method calculates the spent gasoline liters and the cost of this gasoline.
     * 1. Formula for calculating the spent gasoline:
     * Average consumption per 100 km * all the way (km) / 100 = spent liters.
     * 2. The value formula of the spent gasoline = spent gasoline * on the price per liter
     * @param distance - Distance traveled on car
     * @param price - Cost 1 liter of gasoline
     */
    @Override
    public void priceOnGasolineCosts (double distance, double price) {
        getCar().setGasolineCosts((getCar().getGasolineCosts() * distance) / 100);
        setResultGas(getCar().getGasolineCosts() * price);
    }// end priceOnGasolineCosts

    /**
     * Checks the method CheckingEnteredData.isDateValidInString(date) that the date is entered correctly
     * and if true, then writes the result
     * else leaves the field empty
     *
     * @param date - Takes the date on the line in the form of a string
     */
    @Override
    public void todayDate(String date) {
        Check.isDateValidInString(date);
        if (Check.isDateValidInString(date)) {
            setDate(date);
        } else {
            setDate("");
        }
    }// end todayDate

    /**
     * resetGasAndResultGas() - reset static variable total gasoline and total money
     */
    @Override
    public void resetGasAndResultGas() {
        getCar().setGasolineCosts(0);
        setResultGas(0);
    }// end resetGasAndResultGas

    /**
     * reportCity() - city driving report for display
     * @return - returns a line with a report of the cost of city driving on the specified day
     */
    @Override
    public String reportCity() {
        sb.setLength(0);
        sb.append("\n=============================================\n");
        sb.append(getDate()).append("\n");
        sb.append("Машина : ").append(getCar().getName()).append("\n");
        sb.append("За пройденный путь в населенном пункте вы потратили:\n");
        sb.append(String.format("Бензин : %.2f литров\n", getCar().getGasolineCosts()));
        sb.append(String.format("Денег: %.2f рублей\n", getResultGas()));
        sb.append("=============================================\n");
        WorkData.saveReport(sb.toString());
        return sb.toString();
    } // end reportCity

    /**
     * reportHighway() - track driving report for display
     * @return - returns a line with a report of the cost of driving on the highway on the specified day
     */
    @Override
    public String reportHighway() {
        sb.setLength(0);
        sb.append("\n=============================================\n");
        sb.append(getDate()).append("\n");
        sb.append("Машина : ").append(getCar().getName()).append("\n");
        sb.append("За пройденный путь по трассе вы потратили:\n");
        sb.append(String.format("Бензин : %.2f литров\n", getCar().getGasolineCosts()));
        sb.append(String.format("Денег: %.2f рублей\n", getResultGas()));
        sb.append("=============================================\n");
        WorkData.saveReport(sb.toString());
        return sb.toString();
    } //end reportHighway

    //getter and setter
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public double getResultGas() {
        return resultGas;
    }
    public void setResultGas(double resultGas) {
        this.resultGas = resultGas;
    }
    public Car getCar() {
        return car;
    }
    //end getter and setter

}
