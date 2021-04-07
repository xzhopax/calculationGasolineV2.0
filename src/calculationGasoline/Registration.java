package calculationGasoline;

import calculationGasoline.onBoardComputerCar.workData.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Registration extends JFrame {
    private JPanel panel;
    private JLabel name;
    private JLabel nameLogin;
    private JTextField textLogin;
    private JLabel namePassword;
    private JLabel nameNick;
    private JTextField textNick;
    private JLabel nameAge;
    private JTextField textAge;
    private JLabel nameRegion;
    private JPasswordField textPassword;
    private JComboBox comboRegion;
    private JLabel nameCity;
    private JComboBox comboCity;
    private JLabel namePhone;
    private JTextField textPhone;
    private JLabel nameMail;
    private JTextField textMail;
    private JButton buttonRegistration;
    private JButton buttonExit;
    private JLabel errorLogin;
    private JLabel errorPassword;
    private JLabel errorMail;
    private JLabel errorRegistr;
    private JLabel errorPhone;

    public Registration(){
        this.setBounds(400, 200, 600, 400); // initial window size
        this.setResizable(true); // you can make the window wider
        setTitle("Регистрация");//window title
//        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);// show panel
        add(getPanel());// add the panel MenuGUI

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

        getTextLogin().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    ResultSet resultSet = LoginPanel.statement.executeQuery(
                            "SELECT id FROM accounts " +
                                    "WHERE login= \'" + getTextLogin().getText() + "\'" );
                    if (resultSet.next()){
                        getErrorLogin().setForeground(Color.RED);
                        getErrorLogin().setText("Уже существует");
                    } else getErrorLogin().setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        textPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        textNick.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        textAge.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        comboRegion.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        comboCity.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        textPhone.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        textMail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        buttonRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getErrorRegistr().setText("");
                try {
                    ResultSet resultSet = LoginPanel.statement.executeQuery(
                            "SELECT id FROM accounts " +
                                    "WHERE login= \'" + getTextLogin().getText() + "\'" );
                    if (resultSet.next()){
                        getErrorRegistr().setText("Такой пользователь уже существует");
                    } else {
                        LoginPanel.statement.execute("INSERT INTO accounts(login,password,nick_name,email," +
                                "age,phone) VALUES (" +
                                "\'" + getTextLogin().getText() + "\'," +
                                "\'" + getTextPassword().getText() + "\'," +
                                "\'" + getTextNick().getText() + "\'," +
                                "\'" + getTextMail().getText() + "\'," +
                                ""   + getTextAge().getText()  + "," +
                                "\'" + getTextPhone().getText() + "\'" +
                                ")");
//                        new LoginPanel();
//                        setVisible(false);
//                        dispose();
                    }
                } catch (SQLException throwables) {throwables.printStackTrace();}
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog
                        (null, "Вы точно хотите вернуться в меню?",
                                "вернуться в меню", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    new LoginPanel();
                    setVisible(false);
                    dispose();
                }
            }
        });
    }

    //get
    public JPanel getPanel() {
        return panel;
    }

    public JTextField getTextLogin() {
        return textLogin;
    }

    public JTextField getTextNick() {
        return textNick;
    }

    public JTextField getTextAge() {
        return textAge;
    }

    public JPasswordField getTextPassword() {
        return textPassword;
    }

    public JComboBox getComboRegion() {
        return comboRegion;
    }

    public JComboBox getComboCity() {
        return comboCity;
    }

    public JTextField getTextPhone() {
        return textPhone;
    }

    public JTextField getTextMail() {
        return textMail;
    }

    public JButton getButtonRegistration() {
        return buttonRegistration;
    }

    public JButton getButtonExit() {
        return buttonExit;
    }

    public JLabel getErrorLogin() {
        return errorLogin;
    }

    public JLabel getErrorPassword() {
        return errorPassword;
    }

    public JLabel getErrorMail() {
        return errorMail;
    }

    public JLabel getErrorRegistr() {
        return errorRegistr;
    }

    public JLabel getErrorPhone() {
        return errorPhone;
    }


}
