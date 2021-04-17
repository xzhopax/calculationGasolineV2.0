package calculationGasoline.workData;

import calculationGasoline.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Check {

    /**
     * validDouble - checking a string for a number double
     * @param stringNumPrice - accepts a string as input to check it for double
     * @return - if the input parameter is correct, then it returns the number of String
     */
    public static String validDoubleInString(String stringNumPrice) {
        if (stringNumPrice == null || stringNumPrice.isEmpty()){
            return "";
        }
        if (stringNumPrice.matches("^(\\d+\\.(\\d+))$") && Double.parseDouble(stringNumPrice) > 0 ||
                    stringNumPrice.matches("^\\d+$") && Integer.parseInt(stringNumPrice) > 0) {
            return stringNumPrice;
        } else
        return "";
    }// end validDouble


    /**
     * validInteger(String stringNumPrice) - checking a string for a number integer
     * @param stringNumPrice - accepts a string as an input to check it for an integer
     * @return - if the input parameter is correct, then it returns String
     */
     public static String validIntegerInString(String stringNumPrice) {
         if (stringNumPrice == null || stringNumPrice.isEmpty()){
             return "";
         }

        if (stringNumPrice.matches("\\d+") &&
                        Integer.parseInt(stringNumPrice) > 0) {
            return stringNumPrice;
        } else
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
     * checkStringContainDoubleOrInteger - Checking string to check it on the contain double or integer
     *
     * @param str - takes the string to check it on the contain double (1 Sign after the comma) or integer
     * @return - true if contains and false if not contains
     */
    public static boolean checkStringContainDoubleOrInteger(String str){
        if (str == null || str.isEmpty()){
            return false;
        }
        return str.matches("^(\\d+(\\.?[\\d]))$") || str.matches("^\\d+$");
    }


    /**
     * checkStringContainDouble - Checking string to check it on the contain double
     *
     * @param str - takes the string to check it on the contain double
     * @return - true if contains and false if not contains
     */
    public static boolean checkStringContainDouble(String str){
        if (str == null || str.isEmpty()){
            return false;
        }
        return str.matches("^(\\d+(\\.?\\d+))$");
    }

    /**
     * checkStringContainInteger - Checking string to check it on the contain integer
     *
     * @param str - takes the string to check it on the contain integer
     * @return - true if contains and false if not contains
     */
    public static boolean checkStringContainInteger(String str){
        if (str == null || str.isEmpty()){
            return false;
        }
        return str.matches("^\\d+$");
    }

    public static String chekEnterDate(String stringDate){
        if (stringDate == null ||
                stringDate.isEmpty()){
            return "01.01.1970";
        }else return stringDate;
    }

    public static boolean checkOnlyLetters(String checkString){
        if (checkString == null || checkString.isEmpty()){
            return false;
        }
        return checkString.matches("^[a-zA-Z]*$") ||
                checkString.matches("^[а-яА-ЯЁё]*$");
    }

    public static boolean checkOnlyLettersEnglish(String checkString){
        if (checkString == null || checkString.isEmpty()){
            return false;
        }
        return checkString.matches("^[a-zA-Z](.[a-zA-Z0-9_-]*)$");
    }

    public static boolean checkValidEmail(String checkString){
        if (checkString == null || checkString.isEmpty()){
            return false;
        }
       return checkString.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b" );
    }

    public static boolean checkValidPhone(String checkString){
        if (checkString == null || checkString.isEmpty()){
            return false;
        }
        return checkString.matches("^\\+?[78][-\\(]?\\d{3}\\)?-?\\d{3}-?\\d{2}-?\\d{2}$");
    }

    public static void getColorPassword(String checkString, JPasswordField passwordField){
        if (checkString == null || checkString.isEmpty() ||!checkString.matches(("^[a-zA-Z0-9!@#$%^ &*]*$"))) {
            passwordField.setBackground(Color.RED);
        } else
        if (checkString.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$")){
            passwordField.setBackground(Color.GREEN);
        } else
        if (checkString.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$")) {
            passwordField.setBackground(Color.YELLOW);
        } else
        if (checkString.matches("^[0-9a-zA-Z!@#$%^&*]{4,}$")) {
            passwordField.setBackground(Color.ORANGE);
        } else
            passwordField.setBackground(Color.PINK);
    }

    public static String chekPassword(String checkString){
        if (checkString == null || checkString.isEmpty() ||!checkString.matches(("^[a-zA-Z0-9!@#$%^ &*]*$"))) {
            return "Введены недопустимые символы";
        }
        if (checkString.matches("^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$")){
            return "Надежный пароль";
        }
        if (checkString.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}$")) {
            return "Хороший пароль";
        }
        if (checkString.matches("^[0-9a-zA-Z!@#$%^&*]{4,}$")) {
            return "Средний пароль";
        }
        return "Слабый пароль";
    }

    public static boolean checkSpeedAndCostsGasInString(String stringNumPrice) {
        if (stringNumPrice == null || stringNumPrice.isEmpty()){
            return false;
        }

        return stringNumPrice.matches("(^\\d{0,3}\\.[\\d])") && Double.parseDouble(stringNumPrice) > 0 ||
                        stringNumPrice.matches("^[\\d]{0,3}") && Integer.parseInt(stringNumPrice) > 0;
    }// end checkSpeed

    public static boolean checkPriceInString(String stringNumPrice) {
        if (stringNumPrice == null || stringNumPrice.isEmpty()){
            return false;
        }

        return stringNumPrice.matches("^(\\d+(\\.?[\\d]{1,2}))$") ||
                    stringNumPrice.matches("^\\d+$");
    }// end checkPrice



    public static boolean errorPassword(String checkString){
        if (checkString == null || checkString.isEmpty()){
            return true;
        }

        return !checkString.matches("^[a-zA-Z0-9!@#$%^ &*]*$");
    }

    public static boolean checkRegistration(boolean flag){
        return flag;
    }

    public static boolean checkFileInBD(String nameFile, String nameColumn, String nameField) throws SQLException {
        ResultSet resultSet = LoginPanel.statement.executeQuery(
                String.format("SELECT content FROM %s WHERE %s = \'%s\';",nameFile,nameColumn,nameField));
        return resultSet.next();
    }

    public static boolean checkFindFile(){
        File file = new File(WorkData.getPathNameFile());
        return file.exists();
    }









}
