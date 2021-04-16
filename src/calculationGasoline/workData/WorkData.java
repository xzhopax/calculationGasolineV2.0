package calculationGasoline.workData;

import calculationGasoline.connection.ConnectionBD;
import calculationGasoline.LoginPanel;
import calculationGasoline.MenuGUI;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorkData {


    private static double allGas;
    private static double allMoney;

    /**
     * resetAllMoneyAndAllGas() - For setAllGas and setAllMoney sets values to 0
     */
    private static void resetAllMoneyAndAllGas() {
        setAllGas(0);
        setAllMoney(0);
    }// end resetAllMoneyAndAllGas

    /**
     * countNumber - accepts two double numbers as input to add one to the other
     * @param number - the number to be added
     * @param resultNumber - to it you need to add
     * @return - will return the result of adding numbers
     */
    public static double countNumber(double number, double resultNumber) {
        resultNumber = resultNumber + number;
        return resultNumber;
    }// end countNumber

    /**
     * findInFileGas(String str) - searches the history file for gasoline spent on it,
     * for writing his into variable to display all costs.
     * @param str - accepts a string as input to search for gasoline.
     */
    public static double findInFileGas(String str){
        String regex1 = "(Бензин\\s*:\\s*(\\d+,?(\\d*)))";
        Pattern pattern = Pattern.compile(regex1);
        Matcher matcher1 = pattern.matcher(str);
        return findFiles(matcher1,getAllGas());
    }//end find gas

    /**
     * findInFileMoney(String str) - searches the history file for money   spent ,
     * for writing them into variable to display all costs.
     * @param str - accepts a string as input to search for money.
     */
    public static double findInFileMoney(String str){
        String regex2 = "(Денег\\s*:\\s*(\\d+,?(\\d*)))";
        Matcher matcher2 = Pattern.compile(regex2).matcher(str);
        return findFiles(matcher2,getAllMoney());
    } //end find money

    public static double findFiles(Matcher matcher, double writeResult){
        String str;
        while (matcher.find()) {
            str = matcher.group();
            String[] arrOfStr = str.split(":\\s?");
            for (String s : arrOfStr) {
                s = s.replaceAll(",", ".").trim();
                if (s.matches("(\\d+(\\.?\\d+))")) {
                    writeResult = countNumber(Double.parseDouble(s), writeResult);
                }
            }
        }
        return writeResult;
    }

    /**
     * cleanResult() - delete records from the file and reset the static
     * fields to display the report history on the screen
     */
    public static void cleanResult() {// start clean
        resetAllMoneyAndAllGas();
        if (Check.checkFindFile()) {
            try (Writer reportFile = new FileWriter
                    (getPathNameFile(), false)) {
                reportFile.write("");
                saveFileInBD();

            } catch (IOException | SQLException e) {
                MenuGUI.error();
                e.printStackTrace();
            }
        }
    }// end clean

    /**
     * reportTheTotal() - displays the total amount of consumed gasoline and money spent on it
     * @return - returns a string with information about parameters of all gasoline costs and money for it
     */
    public static String reportTheTotal() {
        String report = String.format("Общая сумма денег была потрачена на бензин : %.2f\n", getAllMoney()) +
                String.format("Общее количество бензина израсходаванно : %.2f", getAllGas());
        resetAllMoneyAndAllGas();
        return report;

    }//end reportTheTotal

    public static String getPathNameFile(){
       return "resources//reportFile"+ LoginPanel.getUser().getID() + ".txt";
    }
    public static String getNameFile(){
        return "reportFile"+ LoginPanel.getUser().getID();
    }

    /**
     * saveReport(String line) - save the report to a file
     * @param line - takes a string as input to be written to a file
     */
    public static void saveReport(String line) {
        try (Writer reportFile = new FileWriter
                (getPathNameFile(), true)) {
            reportFile.write(line);
            reportFile.flush();
            saveFileInBD();
            reportFile.close();

        } catch (IOException | SQLException e) {
            MenuGUI.error();
            e.printStackTrace();
        }
    }// end save report

    /**
     * outDisplayReport() - reading history from a file to display it on the screen
     * @return - returns a string with information read from a file
     */
    public static String outDisplayReport() {
        StringBuilder sb = new StringBuilder();
        if (Check.checkFindFile()) {
            try (Reader reader = new FileReader
                    (getPathNameFile())) {
                int data = reader.read();
                while (data != -1) {
                    sb.append((char) data);
                    data = reader.read();
                }
                WorkData.setAllGas(WorkData.findInFileGas(sb.toString()));
                WorkData.setAllMoney(WorkData.findInFileMoney(sb.toString()));
            } catch (IOException e) {
                MenuGUI.error();
                e.printStackTrace();
            }
        }
        return sb.toString();
    }//end outDisplayReport

    public static int getAge(String dateString){
        if(Check.isDateValidInString(dateString)){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate endDate = LocalDate.parse(LocalDate.now().format(formatter), formatter);
            LocalDate startDate = LocalDate.parse(dateString, formatter);
            Period period = Period.between(startDate, endDate);
            return period.getYears() ;
        } else return 0;
    }



    public static void saveFileInBD() throws IOException, SQLException {
        File saveFile = new File(getPathNameFile());
        byte[] saveData = new byte[(int) saveFile.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(saveFile));
        dis.readFully(saveData);  // read from file into byte[] array
        dis.close();

       if (Check.checkFileInBD("gasoline.files","name",getNameFile()))
          {
              PreparedStatement ps = ConnectionBD.getStatement().getConnection().prepareStatement(
                      "UPDATE gasoline.files SET content = ? WHERE name = ?");
              ps.setBytes(1, saveData);
              ps.setString(2, getNameFile());  // byte[] array
              ps.executeUpdate();
          }
       else
          {
              PreparedStatement ps = ConnectionBD.getStatement().getConnection().prepareStatement(
                      "INSERT INTO files (name, content) VALUES (?,?)");
              ps.setString(1, getNameFile());
              ps.setBytes(2, saveData);  // byte[] array
              ps.executeUpdate();
          }
    }

    public static void downloadFileFromBD() throws IOException, SQLException {
        File file = new File(getPathNameFile());

            try (PreparedStatement ps = ConnectionBD.getStatement().getConnection().prepareStatement(
                    "SELECT * FROM gasoline.files WHERE name = ?")) {
                ps.setString(1,getNameFile());
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (!resultSet.next()){
                        throw new RuntimeException("No file with name =" + getNameFile());
                    }
                    try(InputStream content = resultSet.getBinaryStream("content")) {
                        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
                            byte[] buffer = new byte[8 * 1024];

                            while (true) {
                                int byteCount = content.read(buffer);
                                if (byteCount < 0) {
                                    break;
                                }
                                fileOutputStream.write(buffer, 0, byteCount);
                            }

                        }
                    }

                }
            } catch (IOException | SQLException throwables) {
                throwables.printStackTrace();
        }
    }

    public static void saveFileInBD2() throws SQLException {
        File file = new File(getPathNameFile());
        if (Check.checkFileInBD("gasoline.files","name",getNameFile())) {
            try (PreparedStatement ps = ConnectionBD.getStatement().getConnection().prepareStatement(
                    "INSERT INTO gasoline.files (name,content) VALUES (?,?)")) {
                try (FileInputStream fileInputStream = new FileInputStream(file)) {
                    ps.setString(1, getNameFile());
                    ps.setBinaryStream(2, fileInputStream);
                    ps.executeUpdate();
                }
            } catch (IOException | SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void createFile(){
        File file = new File(getPathNameFile());
        if (!file.exists()) {
            File newFile = new File(getPathNameFile());
        }
    }

    //getters and setters
    public static double getAllGas() {
        return allGas;
    }
    public static void setAllGas(double allGas) {
        WorkData.allGas = allGas;
    }
    public static double getAllMoney() {
        return allMoney;
    }
    public static void setAllMoney(double allMoney) {
        WorkData.allMoney = allMoney;
    }
    //end getters and setters

}
