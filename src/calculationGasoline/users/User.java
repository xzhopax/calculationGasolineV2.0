package calculationGasoline.users;

import calculationGasoline.workData.Entity;
import calculationGasoline.LoginPanel;

import java.io.*;

public class User extends Entity {
    @Serial
    private static final long serialVersionUID = 3941911514417751241L;

    private String name = "", login = "", password = "", email = "", city = "", phone = "", access = "", region = "";
    private final int ID;
    private int age = 0;
    private File file;

    public User(int id){
        this.ID = id;
    }

    //getter
    public String getName() {
        return name;
    }
    public void setName(String name) {
        if (name == null || name.isEmpty()){
            this.name = "";
        } else
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
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email == null || email.isEmpty()){
            this.email = "";
        } else
            this.email = email;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        if (city == null || city.isEmpty()){
            this.city = "";
        } else
            this.city = city;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        if (phone == null || phone.isEmpty()){
            this.phone = "";
        } else
            this.phone = phone;
    }
    public String getAccess() {
        return access;
    }
    public void setAccess(String access) {
        if (access == null || access.isEmpty()){
            this.access = "user";
        } else
            this.access = access;
    }
    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        if (region == null || region.isEmpty()){
            this.region = "";
        } else
            this.region = region;
    }
    public int getID() {
        return ID;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
            this.age = age;
    }

    public File getFile() {
        return file = new File("resources//reportFile" + LoginPanel.getUser().getID() +".txt");

    }

    public void setFile(File file) {
        this.file = file;
    }
    //end getter
}
