package calculationGasoline.test;

import calculationGasoline.InCityPanel;
import calculationGasoline.cars.enumsForCar.CarSpeedAndPetrol;
import calculationGasoline.onBoardComputerCar.workData.WorkData;
import org.junit.Assert;
import org.junit.Test;

public class TestProgram {

    @Test
   public void returnGasolineConsumptionWithCarSpeed(){

       Assert.assertEquals(
               10.5, CarSpeedAndPetrol.VOLKSWAGEN_POLO.returnGasolineConsumptionWithCarSpeed(200),0.1);
       Assert.assertEquals(
               8.0,CarSpeedAndPetrol.VOLKSWAGEN_POLO.returnGasolineConsumptionWithCarSpeed(150),0.1);
       Assert.assertEquals(
               0,CarSpeedAndPetrol.VOLKSWAGEN_POLO.returnGasolineConsumptionWithCarSpeed(210),0.1);
       Assert.assertEquals(
               0,CarSpeedAndPetrol.VOLKSWAGEN_POLO.returnGasolineConsumptionWithCarSpeed(0),0.1);
       Assert.assertEquals(
               6,CarSpeedAndPetrol.VOLKSWAGEN_POLO.returnGasolineConsumptionWithCarSpeed(11),0.1);
       Assert.assertEquals(
               4.8,CarSpeedAndPetrol.VOLKSWAGEN_POLO.returnGasolineConsumptionWithCarSpeed(21),0.1);
//       Assert.assertEquals(0,car.gasolineConsumptionWithCarSpeed(-10),0.1);
    }

    @Test
    public void testFindInFileGas(){
        String str = "25.12.1111\n" +
                "За пройденный путь по трассе вы потратили:\n" +
                "Бензин : 10,20 литров\n" +
                "Денег: 499,80 рублей\n" +
                "=============================================\n" +
                "\n" +
                "=============================================\n" +
                "25.12.1111\n" +
                "За пройденный путь по трассе вы потратили:\n" +
                "Бензин : 7,20 литров\n" +
                "Денег: 352,80 рублей\n" +
                "=============================================";
        double result = WorkData.findInFileGas(str);

        Assert.assertEquals(17.40, result, 0.01);
    }


}
