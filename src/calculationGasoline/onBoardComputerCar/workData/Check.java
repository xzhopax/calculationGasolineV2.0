package calculationGasoline.onBoardComputerCar.workData;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Check {

    /**
     * validDouble - checking a string for a number double
     * @param StringNumPrice - accepts a string as input to check it for double
     * @return - if the input parameter is correct, then it returns the number of String
     */
    public static String validDoubleInString(String StringNumPrice) {
        if (StringNumPrice.matches("(\\d+(\\.\\d+))") && Double.parseDouble(StringNumPrice) > 0
                || StringNumPrice.matches("\\d+") && Integer.parseInt(StringNumPrice) > 0) {
            return StringNumPrice;
        }
        return "";
    }// end validDouble

    /**
     * validInteger(String StringNumPrice) - checking a string for a number integer
     * @param StringNumPrice - accepts a string as an input to check it for an integer
     * @return - if the input parameter is correct, then it returns String
     */
     public static String validIntegerInString(String StringNumPrice) {

        if (StringNumPrice.matches("\\d+") && Integer.parseInt(StringNumPrice) > 0) {
            return StringNumPrice;
        }
        return "";
    }// end validInteger

    /**
     * isDateValid(String date) - checking if the date is entered correctly
     * @param date - accepts a string as input to check it for a date
     * @return - if the input parameter is correct, then it returns true otherwise return false
     */
    public static boolean isDateValidInString(String date) {
        try {
            DateFormat df = new SimpleDateFormat("d.M.yyyy");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }// end isDateValid

    /**
     * checkStringContainceDoubleOrInteger - Checking string to check it on the containce doube or integer
     *
     * @param str - takes the string to check it on the containce doube or integer
     * @return - true if contains and false if not contains
     */
    public static boolean checkStringContainceDoubleOrInteger(String str){
        return str.matches("(\\d+(\\.?\\d+))") || str.matches("\\d+");
    }

    /**
     * checkStringContainceDouble - Checking string to check it on the containce doubel
     *
     * @param str - takes the string to check it on the containce doubel
     * @return - true if contains and false if not contains
     */
    public static boolean checkStringContainceDouble(String str){
        return str.matches("(\\d+(\\.?\\d+))");
    }

    /**
     * checkStringContainceInteger - Checking string to check it on the containce integer
     *
     * @param str - takes the string to check it on the containce integer
     * @return - true if contains and false if not contains
     */
    public static boolean checkStringContainceInteger(String str){
        return str.matches("\\d+");
    }

    public static void chekEnterDate(String stringDate){
        if(stringDate.equals("") || stringDate.isEmpty()) stringDate ="01.01.1970";
    }

    public static boolean checkOnlyLetters(String checkString){
        return checkString.matches("^[a-zA-Z]*$") ||
                checkString.matches("^[а-яА-ЯЁё]*$");
    }

    public static boolean checkOnlyLettersEnglish(String checkString){
        return checkString.matches("^[a-zA-Z](.[a-zA-Z0-9_-]*)$");
    }

    public static boolean checkValidEmail(String checkString){
       return checkString.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b" );
    }

    public static boolean checkValidPhone(String checkString){
        return checkString.matches("^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$");
    }

    public static void getColorPassword(String checkString, JPasswordField passwordField){
        if (!checkString.matches(("^[a-zA-Z0-9!@#$%^ &*]*$"))) {
            passwordField.setBackground(Color.RED);
        } else
        if (checkString.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")){
            passwordField.setBackground(Color.GREEN);
        } else
        if (checkString.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")) {
            passwordField.setBackground(Color.YELLOW);
        } else
        if (checkString.matches("[0-9a-zA-Z!@#$%^&*]{4,}")) {
            passwordField.setBackground(Color.ORANGE);
        } else
            passwordField.setBackground(Color.PINK);
    }

    public static String chekPassword(String checkString){
        if (!checkString.matches(("^[a-zA-Z0-9!@#$%^ &*]*$"))) {
            return "Введены недопустимые символы";
        }
        if (checkString.matches("(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")){
            return "Надежный пароль";
        }
        if (checkString.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}")) {
            return "Хороший пароль";
        }
        if (checkString.matches("[0-9a-zA-Z!@#$%^&*]{4,}")) {
            return "Средний пароль";
        }
        return "Слабый пароль";
    }


    public static boolean errorPassword(String checkString){
        return !checkString.matches("^[a-zA-Z0-9!@#$%^ &*]*$");
    }

    public static boolean checkRegistration(boolean flag){
        return flag;
    }







}
