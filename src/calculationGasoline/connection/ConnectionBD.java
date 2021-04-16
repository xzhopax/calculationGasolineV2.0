package calculationGasoline.connection;

import calculationGasoline.workData.Entity;

import java.io.Serial;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionBD extends Entity {
    @Serial
    private static final long serialVersionUID = 5410507949870448928L;

    public static Statement statement;

    public static Statement getStatement(){
        try {
            statement = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gasoline","root","root").createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  statement;
    }
    public static Statement getStatement(String url, String login, String password){
        try {
            statement = DriverManager.getConnection(
                    url, login, password).createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  statement;
    }


}
