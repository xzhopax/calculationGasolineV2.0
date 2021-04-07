package calculationGasoline;

import calculationGasoline.users.CreateUsers;
import calculationGasoline.users.User;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.*;

public class RunRoad extends JFrame {

    private RunRoad() {
        super("Singin Singup");
        setSize(800,627);
        add(new LoginPanel());
        setLocation(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }





    public static void main(String[] args) throws SQLException {


            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/users","root","root");
            Connection connection2 = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/vehicle","root","root");

            Statement statement = connection.createStatement();
            Statement statement2 = connection2.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts");
            ResultSet resultSet2 = statement2.executeQuery("SELECT * FROM brand");


//        while (resultSet.next()){
//            System.out.println(resultSet.getString("nick_name"));
//        }



        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPanel();
            }
        });


    }
}

