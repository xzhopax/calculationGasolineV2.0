package calculationGasoline.test;

import calculationGasoline.cars.enumsForCar.CarSpeedAndPetrol;
import calculationGasoline.workData.Check;
import calculationGasoline.workData.WorkData;
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

    @Test
    public void checkValidLettersRussianOrEnglish(){
        String str = "DeDEfEFDFDFfsfgeDSDSa"; // true
        String str2 = "ыапвапвпвпвапКАПКПКПРЫКПпапап"; //true
        String str3 = "DeDEfEFDFDFfsfgeDSD3Sa"; //false
        String str4 = "ыапвапвпвпвапКАПК23ПКПРЫКПпапап"; // false
        String str5 = "DeDEfEFDFDFfsfgeDSD3S.a"; //false
        String str6 = "DeDEfEFDFDaываываА"; //false

        Assert.assertTrue(Check.checkOnlyLetters(str));
        Assert.assertTrue(Check.checkOnlyLetters(str2));
        Assert.assertFalse(Check.checkOnlyLetters(str3));
        Assert.assertFalse(Check.checkOnlyLetters(str4));
        Assert.assertFalse(Check.checkOnlyLetters(str5));
        Assert.assertFalse(Check.checkOnlyLetters(str6));
    }

    @Test
    public void checkEmailValid(){
        String str = "caca@mail.ru"; // true
        String str2 = "caca@mail.r"; // false
        String str3 = "cacamail.ru"; // false
        String str4 = "c23ca@mail.ru"; // true

        Assert.assertTrue(Check.checkValidEmail(str));
        Assert.assertFalse(Check.checkValidEmail(str2));
        Assert.assertFalse(Check.checkValidEmail(str3));
        Assert.assertTrue(Check.checkValidEmail(str4));
    }

    @Test
    public void checkPhoneValid(){
        String str = "+79219997755"; // true
        String str2 = "+7(921)9997755"; // true
        String str3 = "89219997755"; // true
        String str4 = "+7(921)999-77-55"; // true
        String str5 = "+7-921-999-77-55"; // true
        String str6 = "+792199977557"; // false
        String str7 = "+792157"; // false
        String str8 = "+692199977557"; // false
        String str9 = "792199977557"; // false

        Assert.assertTrue(Check.checkValidPhone(str));
        Assert.assertTrue(Check.checkValidPhone(str2));
        Assert.assertTrue(Check.checkValidPhone(str3));
        Assert.assertTrue(Check.checkValidPhone(str4));
        Assert.assertTrue(Check.checkValidPhone(str5));
        Assert.assertFalse(Check.checkValidPhone(str6));
        Assert.assertFalse(Check.checkValidPhone(str7));
        Assert.assertFalse(Check.checkValidPhone(str8));
        Assert.assertFalse(Check.checkValidPhone(str9));
    }

    @Test
    public void checkPassword(){
        String str = "DasdGASDAS%2311!sdf"; // hard
        String str2 = "Dsa"; // low
        String str3 = "DaEs1"; // mid
        String str4 = "asdw}"; // error
        String str5 = "1"; // low
        String str6 = "ssssssssdd"; // mid
        String str7 = "_123qweR#"; // error
        String str8 = "123qweEErq"; // strong
        String str9 = "#@qweEErq"; // mid
        String str11 = "";//
        String check = "Надежный пароль";
        String check2 = "Введены недопустимые символы";
        String check3 = "Хороший пароль";
        String check4 = "Средний пароль";
        String check5 = "Слабый пароль";

        Assert.assertEquals(check,Check.chekPassword(str));
        Assert.assertEquals(check5,Check.chekPassword(str2));
        Assert.assertEquals(check4,Check.chekPassword(str3));
        Assert.assertEquals(check2,Check.chekPassword(str4));
        Assert.assertEquals(check5,Check.chekPassword(str5));
        Assert.assertEquals(check4,Check.chekPassword(str6));
        Assert.assertEquals(check2,Check.chekPassword(str7));
        Assert.assertEquals(check3,Check.chekPassword(str8));
        Assert.assertEquals(check4,Check.chekPassword(str9));
        Assert.assertEquals(check2,Check.chekPassword(null));
        Assert.assertEquals(check2,Check.chekPassword(str11));
    }

    @Test
    public void chekAge(){
//        String str = "25.12.1987"; // 33
//        String str2 = "07.04.2020"; // 1
//        String str3 = "09.04.2020"; // 0
//        String str4 = "08.04.2000"; // 21
//        String str5 = "09.04.2000"; // 20
//        String strError = "32.12.1987"; //0
//        String strError2 = "1987.2.12"; //0             //если тестировать в другие даты
//                                                        //происходят ошибки из-за сдвига даты в реальном времени
//
//        Assert.assertEquals(33,WorkData.getAge(str));
//        Assert.assertEquals(1,WorkData.getAge(str2));
//        Assert.assertEquals(0,WorkData.getAge(str3));
//        Assert.assertEquals(21,WorkData.getAge(str4));
//        Assert.assertEquals(20,WorkData.getAge(str5));
//        Assert.assertEquals(0,WorkData.getAge(strError));
//        Assert.assertEquals(0,WorkData.getAge(strError2));
    }

    @Test
    public void chekStringInteger(){
        String str = null;
        String str2 = "";
        String str3 = "w12.12";
        String str4 = "12w";
        String str5 = "11112323";
        String str6 = "!11111";


        Assert.assertEquals("",Check.validIntegerInString(str));
        Assert.assertEquals("",Check.validIntegerInString(str2));
        Assert.assertEquals("",Check.validIntegerInString(str3));
        Assert.assertEquals("",Check.validIntegerInString(str4));
        Assert.assertEquals("11112323",Check.validIntegerInString(str5));
        Assert.assertEquals("",Check.validIntegerInString(str6));
    }




}
