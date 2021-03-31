package calculationGasoline.users;

import calculationGasoline.MenuGUI;

import java.io.*;
import java.util.*;

public class User implements Serializable{
    private String name, login, password, email;
    private final int ID;

    public User(){
        ID = CreateUsers.getCreateId();
    }


    private static final Map<String, String> mapUsers = new TreeMap<>();
    static {
        mapUsers.put("","");
        mapUsers.put("admin","admin");
        mapUsers.put("user","");
    }

    //getter
    public static Map<String, String> getMapCreateCars() {
        return mapUsers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public int getID() {
        return ID;
    }

    //end getter
}
