package calculationGasoline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import java.util.TreeMap;

public class LoginPanel extends JFrame {

    private JPanel panel;
    private JTextField textLogin;
    private JLabel nameLogin;
    private JLabel namePassword;
    private JTextField textPassword;
    private JButton buttonLogin;
    private JButton buttonRegistr;
    private JButton buttonExitProgram;
    private JLabel errorLogin;

    private static final Map<String, String> mapUsers = new TreeMap<>();
    static {
        mapUsers.put("","");
        mapUsers.put("admin","admin");
        mapUsers.put("user","");
    }

    public LoginPanel() {

        this.setBounds(550, 300, 300, 220); // initial window size
        this.setResizable(true); // you can make the window wider
        setTitle("Авторизация");//window title
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
                SwingUtilities.invokeLater(() -> {
                    if (getTextLogin().getText().equals("admin") &&
                        getTextPassword().getText().equals("admin"))
                    {
                        MenuAdminGUI menuAdminGUI = new MenuAdminGUI();
                        setVisible(false); // close the current frame
                        dispose();
                        getErrorLogin().setText("Производится вход, подождите...");
                    }
                    if (getTextLogin().getText().equals("") &&
                        getTextPassword().getText().equals(""))
                    {
                        MenuGUI menuGUI = new MenuGUI();
                        setVisible(false); // close the current frame
                        dispose();
                        getErrorLogin().setText("Производится вход, подождите...");
                    } else {
                        getErrorLogin().setForeground(Color.RED);
                        getErrorLogin().setText("Неверное имя или пароль");
                    }

                });

            }
        });
        buttonRegistr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"В процессе разработки");
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

    public static Map<String, String> getMapCreateCars() {
        return mapUsers;
    }

    public JLabel getErrorLogin() {
        return errorLogin;
    }
    //end getter
}
