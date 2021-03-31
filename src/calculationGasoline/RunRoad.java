package calculationGasoline;

import calculationGasoline.users.CreateUsers;
import calculationGasoline.users.User;

import javax.swing.*;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class RunRoad {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPanel loginPanel = new LoginPanel();// creat panel MenuGUI
            loginPanel.setVisible(true); // show panel
        });
    }
//
//    public static void main(String[] args) {
//        User user1 = new User();
//        User user2 = new User();
//        User user3 = new User();
//        User user4 = new User();
//
//         CreateUsers.addUser(user1.getID(), user1);
//         CreateUsers.addUser(user2.getID(), user2);
//         CreateUsers.addUser(user3.getID(), user3);
//         CreateUsers.addUser(user4.getID(), user4);
//
//         CreateUsers.saveUsersInMap();
//////         User.cleanerFile();
////        System.out.println(User.getMapCreateCars());
////
////        System.out.println(CreateUsers.readUsersFile());
//
//        System.out.println(CreateUsers.getCreateId());
//    }
}
