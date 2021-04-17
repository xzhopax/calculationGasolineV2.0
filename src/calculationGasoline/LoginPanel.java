package calculationGasoline;

import calculationGasoline.users.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.TreeMap;

public class LoginPanel extends JFrame {

    public static Statement statement;
    public static String connectDB = "jdbc:mysql://localhost:3306/gasoline";
    public static String login = "root";
    public static String password = "root";

    private JPanel panel;
    private JTextField textLogin;
    private JLabel nameLogin;
    private JLabel namePassword;
    private JTextField textPassword;
    private JButton buttonLogin;
    private JButton buttonRegistr;
    private JButton buttonExitProgram;
    private JLabel errorLogin;

    public static User user;

    public LoginPanel() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("images/tunnel.jpg")));
        setLayout(new FlowLayout());

        this.setBounds(550, 300, 500, 320); // initial window size
        this.setResizable(false); // you can make the window wider
        this.setLocationRelativeTo(null);

        setTitle("Авторизация");//window title
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);// show panel

        add(getPanel());// add the panel MenuGUI


        try {
           statement = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/gasoline","root","root").createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //I catch the program cross to confirm the exit
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent arg0) {
                int result = JOptionPane
                        .showConfirmDialog(null,
                                "Закрыть программу?", "Выход из программы",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }// end windowClosing
        }); //end this.addWindowListener(new WindowAdapter()

        textLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        textPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ResultSet resultSet = LoginPanel.statement.executeQuery(
                            "SELECT" +
                                    " * " +
                                 "FROM" +
                                    " gasoline.users " +
                                 "WHERE " +
                                    "login =\'" + textLogin.getText() + "\' " +
                                 "AND" +
                                    " password = \'" + textPassword.getText() + "\'");
                    if (resultSet.next()){
                        setUser(new User(resultSet.getInt("id")));
                        getUser().setName(resultSet.getString("nick_name"));
                        getUser().setPhone(resultSet.getString("phone"));
                        getUser().setAccess(resultSet.getString("access_id"));
                        getUser().setCity(resultSet.getString("city_id"));
                        getUser().setRegion(resultSet.getString("region_id"));
                        getUser().setAge(resultSet.getInt("age"));

                        if (resultSet.getInt("access_id") == 1){
                            new MenuAdminGUI();
                        } else {
                            new MenuGUI();
                        }
                        setVisible(false); // close the current frame
                        dispose();

                    } else {
                        getErrorLogin().setForeground(Color.RED);
                        getErrorLogin().setText("Неверный логин или пароль");
                    }
                } catch (SQLException throwables) {throwables.printStackTrace();}
            }
        });

        buttonRegistr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Registration();
                setVisible(false); // close the current frame
                dispose();
            }
        });
        buttonExitProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog
                        (null, "Вы точно хотите выйти?", "Выход", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }


    //getter
    public JPanel getPanel() {
        return panel;
    }
    public JTextField getTextLogin() {
        return textLogin;
    }
    public JTextField getTextPassword() {
        return textPassword;
    }
    public JButton getButtonLogin() {
        return buttonLogin;
    }
    public JButton getButtonRegistr() {
        return buttonRegistr;
    }
    public JButton getButtonExitProgram() {
        return buttonExitProgram;
    }
    public JLabel getErrorLogin() {
        return errorLogin;
    }
    public static User getUser() {
        return user;
    }
    public static void setUser(User user) {
        LoginPanel.user = user;
    }


    //end getter
}
