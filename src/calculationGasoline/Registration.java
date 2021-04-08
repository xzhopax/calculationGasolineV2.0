package calculationGasoline;

import calculationGasoline.onBoardComputerCar.workData.Check;
import calculationGasoline.onBoardComputerCar.workData.WorkData;

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
    private JLabel errorNick;
    private JLabel errorAge;
    private JLabel questionLogin;
    private JLabel questionPassword;
    private JLabel questionNick;
    private JLabel questionBirthday;
    private JLabel questionPhone;
    private JLabel questionEmail;

    private boolean flag = false;

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
                getErrorLogin().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Check.checkOnlyLettersEnglish(getTextLogin().getText())){
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
                } else {
                    getErrorLogin().setForeground(Color.RED);
                    getErrorLogin().setText("Введены неверные символы");
                }
            }
        });

        textPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getErrorPassword().setText("");
                Check.getColorPassword(getTextPassword().getText(),getTextPassword());
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Check.errorPassword(getTextPassword().getText())){
                    getErrorPassword().setForeground(Color.RED);
                    getErrorPassword().setText("Введены неверные символы");
                } else {
                    Check.getColorPassword(getTextPassword().getText(),getTextPassword());
                }

            }
        });

        textNick.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getErrorNick().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Check.checkOnlyLetters(getTextNick().getText())){
                    getErrorNick().setText("");

                } else {
                    getErrorNick().setForeground(Color.RED);
                    getErrorNick().setText("Неверный формат имени");
                }
            }
        });

        textAge.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getErrorAge().setText("");

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Check.isDateValidInString(getTextAge().getText())){
                    getErrorAge().setText("");

                } else {
                    getErrorAge().setForeground(Color.RED);
                    getErrorAge().setText("Неверный фофрмат даты");
                }
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
                getErrorPhone().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Check.checkValidPhone(getTextPhone().getText())){
                    getErrorPhone().setText("");
                } else {
                    getErrorPhone().setForeground(Color.RED);
                    getErrorPhone().setText("Неверный формат номера");
                }
            }
        });

        textMail.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getErrorMail().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (Check.checkValidEmail(getTextMail().getText())){
                    getErrorMail().setText("");
                } else {
                    getErrorMail().setForeground(Color.RED);
                    getErrorMail().setText("Неверный формат почты");
                }
            }
        });

        buttonRegistration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFlag(Check.checkOnlyLettersEnglish(getTextLogin().getText()));
                setFlag(!Check.errorPassword(getTextPassword().getText()));
                setFlag(Check.checkOnlyLetters(getTextNick().getText()));
                setFlag(Check.isDateValidInString(getTextAge().getText()));
                setFlag(Check.checkValidPhone(getTextPhone().getText()));
                setFlag(Check.checkValidEmail(getTextMail().getText()));

                if (!isFlag()){
                    getErrorRegistr().setForeground(Color.RED);
                    getErrorRegistr().setText("Заполните все поля правильно");
                } else {
                    getErrorRegistr().setText("");
                    try {
                        ResultSet resultSet = LoginPanel.statement.executeQuery(
                                "SELECT id FROM accounts " +
                                        "WHERE login= \'" + getTextLogin().getText() + "\'");
                        if (resultSet.next()) {
                            getErrorRegistr().setForeground(Color.RED);
                            getErrorRegistr().setText("Такой пользователь уже существует");
                        } else {
                            LoginPanel.statement.execute("INSERT INTO accounts(login,password,nick_name,email," +
                                    "age,phone) VALUES (" +
                                    "\'" + getTextLogin().getText() + "\'," +
                                    "\'" + getTextPassword().getText() + "\'," +
                                    "\'" + getTextNick().getText() + "\'," +
                                    "\'" + getTextMail().getText() + "\'," +
                                    "" + WorkData.getAge(getTextAge().getText()) + "," +
                                    "\'" + getTextPhone().getText() + "\'" +
                                    ")");
                            JOptionPane.showMessageDialog(null, "Аккаунт успешно создан");
                            new LoginPanel();
                            setVisible(false);
                            dispose();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
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

    public JLabel getErrorNick() {
        return errorNick;
    }

    public JLabel getErrorAge() {
        return errorAge;
    }

    private boolean isFlag() {
        return flag;
    }

    private void setFlag(boolean flag) {
        this.flag = flag;
    }
}
