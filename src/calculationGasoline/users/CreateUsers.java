package calculationGasoline.users;

import calculationGasoline.MenuGUI;

import java.io.*;
import java.util.*;

public class CreateUsers implements Serializable {

    private static Integer createId;
    private final Map<Integer, User> mapUsers2 = new Hashtable<>();
    private final Map<Integer, User> mapUsers = new Hashtable<>();


    public static String readUsersFile(){
        StringBuilder sb = new StringBuilder();
        try (Reader reader = new FileReader
                ("usersFile.txt")) {
            int data = reader.read();
            while (data != -1) {
                sb.append((char) data);
                data = reader.read();
            }
        } catch (IOException e) {
            MenuGUI.error();
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void getNextId() {
        String tmp;
        List<Integer> list = new ArrayList<>();
        String readFile = readUsersFile();
        String[] arrayString = readFile.split("\n");
        for (String s : arrayString){
            if (s.matches("ID\\s*\\d+,?:\\s*")){
                tmp = s.substring(s.indexOf('D') + 2, s.indexOf(':'));
                list.add(Integer.parseInt(tmp));
                createId = Collections.max(list);
            }
        }
    }

    public void addUser(int id, User user){
        mapUsers2.put(id, user);
    }

    public void saveUsersInMap(){
        String str = "";
        for (Map.Entry<Integer,User> entry : mapUsers2.entrySet()){
            try (Writer reportFile = new FileWriter
                    ("usersFile.txt", true)) {
                reportFile.write("Id " + entry.getKey() + " : User " + entry.getValue() + "\n");
            } catch (IOException e) {
                MenuGUI.error();
                e.printStackTrace();
            }
        }
    }



    public static void cleanerFile(){
        try (Writer reportFile = new FileWriter
                ("usersFile.txt", false)) {
            reportFile.write("Id 0 : User null");
        } catch (IOException e) {
            MenuGUI.error();
            e.printStackTrace();
        }
    }

   public static void saveObject(User user) {
        try {
            FileOutputStream outputStream = new FileOutputStream("saveFile.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(user);
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

   public static void readFileUsers(File file){
       try {
          ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("saveFile.txt"));

       } catch (IOException exception) {
           exception.printStackTrace();
       }
   }


    // getter
    public static Integer getCreateId() {
        return ++createId;
    }
    public Map<Integer, User> getMapUsers2() {
        return mapUsers2;
    }
    //end getter


}
