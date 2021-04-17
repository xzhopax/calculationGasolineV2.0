package calculationGasoline;

import calculationGasoline.cars.Car;
import calculationGasoline.cars.CreateCar;
import calculationGasoline.onBoardComputerCar.OnBoardComputerCar;
import calculationGasoline.workData.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Aleksey Ilin
 * <p>
 * class InCityPanel - this is a panel for calculating the cost of gas and money when driving around the city
 */

public class InCityPanel extends JFrame {

    private JPanel panel;
    private JLabel namePanel;
    private JLabel inDate;
    private JLabel inTraffic;
    private JLabel inDistance;
    private JLabel isConditioner;
    private JLabel isDynamicDriving;
    private JLabel inPrice;
    private JLabel authorProgram;
    private JLabel inDateQuestion;
    private JLabel distanceQuestion;
    private JLabel trafficQuestion;
    private JLabel priceQuestion;
    private JLabel errorDate;
    private JLabel errorDistance;
    private JLabel errorTraffic;
    private JLabel errorPrice;
    private JLabel errorButton;
    private JTextField textDate;
    private JTextField textTraffic;
    private JTextField textPrice;
    private JTextField textDistance;
    private JRadioButton conditionerON;
    private JRadioButton conditionerOFF;
    private JRadioButton dynamicDrivingON;
    private JRadioButton dynamicDrivingOFF;
    private JButton start;
    private JButton returnMenu;
    private JTextField textMidGasoline;
    private JLabel inMidGasoline;
    private JLabel midGasolineQuestion;
    private JLabel errorMidGasoline;
    private JRadioButton beMidGasoline;
    private JRadioButton beTraffic;
    private JLabel nameCar;
    private JComboBox choosingCar;
    private JLabel errorChoosingCar;
    private JLabel choosingCarQuestion;
    private JRadioButton disableRadioButton;

    private Car car = CreateCar.getMapCreateCars().get(1);
    private OnBoardComputerCar computerCar = new OnBoardComputerCar(getCar());
    private boolean flag = false;

    /**
     * 1.  The constructor creates a panel with the specified parameters;
     * 2.  allows you to stretch it;
     * 3.  sets the name of the panel;
     * 4.  makes it visible;
     * 5.  adds a panel to a container;
     * 6.  sets the action when you click on the cross;
     * 7.  through a getter sets the value for the field selection of the car
     * 8.  through a getter sets the action for the date input field;
     * 9.  through a getter sets the action for the distance input field;
     * 10. Buttons combining BeTraffic and BeMidGasoline
     * 11. through the getter sets the action for the traffic input field;
     * 12. through the getter sets the action for the midGasoline input field;                                                               MidGasoline
     * 13. through a getter sets the action for the price entry field;
     * 14. combines buttons for turning on and off the air conditioner into a group;
     * 15. setting the values of buttons for the air conditioner from the car class;
     * 16. groups the buttons for the use or absence of dynamic driving;
     * 17. setting button values for dynamic driving from the car class;
     * 18. sets the action when pressing the button for counting the entered values;
     * 19. sets the action when you press the button to return to the main menu.
     */

    public InCityPanel() {
        setLayout(new BorderLayout());
        setContentPane(new JLabel(new ImageIcon("images/city.jpg")));
        setLayout(new FlowLayout());

        this.setBounds(400, 200, 850, 630);// initial window size
        this.setResizable(false); // you can make the window wider
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        setTitle("расчет затрат бензина в городе");//window title
        setVisible(true);// show panel
        add(getPanel());

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
        });//end anonymous class WindowAdapter (X)

        //Action on the choice of the car, if you leave the field blank,
        // will display an error to choose a car if you choose a machine,
        // clearing all fields for filling
        getChoosingCar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getChoosingCar().getSelectedIndex()==0)
                {// if error
                    getErrorChoosingCar().setFont(new Font("TimesRoman", Font.BOLD, 14));
                    getErrorChoosingCar().setForeground(Color.RED);
                    getErrorChoosingCar().setText("Выберите машину");
                    getChoosingCar().setSelectedIndex(0);
                    setCar(CreateCar.getMapCreateCars().get(1));
                    setComputerCar(new OnBoardComputerCar(getCar()));
                } else {
                    getErrorChoosingCar().setText("");
                    setCar(CreateCar.getMapCreateCars().get(getChoosingCar().getSelectedIndex()));
                    setComputerCar(new OnBoardComputerCar(getCar()));
                    getTextDate().setText("01.01.1970");
                    getTextDistance().setText("");
                    getTextTraffic().setText("0");
                    getTextTraffic().setEnabled(false);
                    getBeTraffic().setSelected(false);
                    getTextPrice().setText("");
                    getTextMidGasoline().setText("0");
                    getTextMidGasoline().setEnabled(false);
                    getBeMidGasoline().setSelected(false);
                    getConditionerOFF().setSelected(true);
                    getCar().setConditioner(false);
                    getDynamicDrivingOFF().setSelected(true);
                    getCar().setDynamicDriving(false);
                }
            }
        });//end choosingCar.addActionListener

        //when the textDate field loses focus, it checks if the date is correct,
        // if not, it erases the value and asks to re-enter
        getTextDate().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getTextDate().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                getComputerCar().todayDate(getTextDate().getText());
                // if error
                if (getComputerCar().getDate().equals("")) {
                    getErrorDate().setFont(new Font("TimesRoman", Font.BOLD, 14));
                    getErrorDate().setForeground(Color.RED);
                    getErrorDate().setText("Неправильно введена дата");
                    getTextDate().setText("");
                } else {
                    getErrorDate().setText("");
                }
            }// end focusLost
        });//end of field textDate


        //when the textDistance field loses focus, it checks if the distance is correct,
        // if not, it erases the value and asks to re-enter
        getTextDistance().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                // if the string number is double or an integer, then we write it to the variable distance
                if (Check.checkStringContainDoubleOrInteger(getTextDistance().getText()))
                {
                    getTextDistance().setText(Check.validDoubleInString(getTextDistance().getText()));
                    getErrorDistance().setText("");
                } else { // else error
                    getErrorDistance().setFont(new Font("TimesRoman", Font.BOLD, 14));
                    getErrorDistance().setForeground(Color.RED);
                    getErrorDistance().setText("Неправильно введена дистанция");
                    getTextDistance().setText("");
                }
            }// end focusLost
        });//end of field textDistance

        //Buttons combining BeTraffic and BeMidGasoline
        ButtonGroup groupTraffic = new ButtonGroup();
        groupTraffic.add(getBeTraffic());
        groupTraffic.add(getBeMidGasoline());
        getTextMidGasoline().setEnabled(false);
        getTextTraffic().setEnabled(false);


        //put a tick on the traffic field and make it active for entering and disable midGasoline
        getBeTraffic().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getTextTraffic().setEnabled(true);
                getTextMidGasoline().setEnabled(false);
                getTextTraffic().setText("0");
                getTextMidGasoline().setText("0");
                getBeTraffic().setSelected(true);

                //when the textTraffic field loses focus, it checks if the traffic was entered correctly,
                // if not, it erases the value and asks to enter it again
                getTextTraffic().addFocusListener(new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) {
                        getTextTraffic().setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                        // if the string number an integer, then we write it to the variable traffic
                        if (getTextTraffic().getText() != null &&
                            !getTextTraffic().getText().isEmpty() &&
                            getTextTraffic().getText().matches("\\d+") &&
                            Integer.parseInt(getTextTraffic().getText()) < 11 &&
                            Integer.parseInt(getTextTraffic().getText()) >= 0)
                        {
                            getTextTraffic().setText(
                                    Check.validIntegerInString(getTextTraffic().getText()));
                            getErrorTraffic().setText("");
                            getErrorMidGasoline().setText("");

                        } else {
                            getErrorTraffic().setFont(new Font("TimesRoman", Font.BOLD, 14));
                            getErrorTraffic().setForeground(Color.RED);
                            getErrorTraffic().setText("Неправильно введен трафик");
                            getTextTraffic().setText("0");
                        }
                    }// end focusLost
                });//end of field textTraffic
            } // end actionPerformed
        }); // end beTraffic.addActionListener

        //put a tick on the MidGasoline field and make it active for entering and disable traffic
        getBeMidGasoline().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                getTextMidGasoline().setEnabled(true);
                getTextTraffic().setEnabled(false);
                getTextMidGasoline().setText("0");
                getTextTraffic().setText("0");
                getBeMidGasoline().setSelected(true);

                //when the TextMidGasoline field loses focus, it checks if the traffic was entered correctly,
                // if not, it erases the value and asks to enter it again
                getTextMidGasoline().addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        getTextMidGasoline().setText("");
                    }

                    @Override
                    public void focusLost(FocusEvent e) {

                        // if the string number is double or an integer, then we write it to the variable distance
                       if (getTextMidGasoline().getText() != null &&
                           Check.checkStringContainDoubleOrInteger(getTextMidGasoline().getText()))
                       {
                           getTextMidGasoline().setText(
                                   Check.validDoubleInString(getTextMidGasoline().getText()));
                           getErrorMidGasoline().setText("");
                           getErrorTraffic().setText("");
                       } else {
                           getErrorMidGasoline().setFont(new Font("TimesRoman", Font.BOLD, 14));
                           getErrorMidGasoline().setForeground(Color.RED);
                           getErrorMidGasoline().setText("Неправильно введен расход");
                           getTextMidGasoline().setText("0");
                       }
                    }// end focusLost
                });//end of field textMidGasoline
            } // end actionPerformed
        });// end beMidGasoline.addActionListener

        //when the textPrice field loses focus, it checks if the price was entered correctly,
        // if not, it erases the value and asks to re-enter
        getTextPrice().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                // if the string number is double or an integer, then we write it to the variable price
                if (getTextPrice().getText().matches("^(\\d+(\\.?[\\d]{1,2}))$") ||
                    getTextPrice().getText().matches("^\\d+$"))
                {
                    getTextPrice().setText(Check.validDoubleInString(getTextPrice().getText()));
                    getErrorPrice().setText("");
                } else {
                    getErrorPrice().setFont(new Font("TimesRoman", Font.BOLD, 14));
                    getErrorPrice().setForeground(Color.RED);
                    getErrorPrice().setText("Неправильно введена цена");
                    getTextPrice().setText("");
                }
            }// end focusLost
        });//end of field textPrice

        // combine the conditionerON and conditionerOFF buttons into the group, set the initial value to ON
        ButtonGroup groupConditioner = new ButtonGroup();
        groupConditioner.add(getConditionerON());
        groupConditioner.add(getConditionerOFF());
        getConditionerON().setSelected(true);

        //if the conditionerON button is enabled, then we assign the conditioner variable of the car class (true)
        getConditionerON().addActionListener(e -> car.setConditioner(true));

        //if the conditionerOFF button is enabled, then we assign the conditioner variable of the car class (false)
        getConditionerOFF().addActionListener(e -> car.setConditioner(false));

        //combine the dynamicDrivingON and dynamicDrivingOFF buttons into a group, set the initial value to ON
        ButtonGroup groupDynamicDriving = new ButtonGroup();
        groupDynamicDriving.add(getDynamicDrivingON());
        groupDynamicDriving.add(getDynamicDrivingOFF());
        getDynamicDrivingON().setSelected(true);

        //if the dynamicDrivingON button is enabled, then assign the variable dynamicDriving of the car class (true)
        getDynamicDrivingON().addActionListener(e -> car.setDynamicDriving(true));

        //if the dynamicDrivingOFF button is enabled, then assign the variable dynamicDriving of the car class (false)
        getDynamicDrivingOFF().addActionListener(e -> car.setDynamicDriving(false));

        //when you click on the start button, it checks whether all the fields are filled,
        // if not, then asks to fill in the fields, and if all the fields are filled in correctly,
        // it calculates the result and displays it
        getStart().addActionListener(e -> {
            // write the entered arguments into the method and get the result:
            setFlag(getTextDistance().getText().equals(""));
            setFlag(getTextPrice().getText().equals(""));
            setFlag(getTextMidGasoline().getText().equals("0") && getTextTraffic().getText().equals("0"));
            setFlag(getChoosingCar().getSelectedIndex() == 0);

            if (isFlag())
            {
                getErrorButton().setForeground(Color.RED);
                getErrorButton().setFont(new Font("TimesRoman", Font.BOLD, 14));
                getErrorButton().setText("Заполните все поля");
            } else {
                getErrorButton().setText("");
                getComputerCar().todayDate(Check.chekEnterDate(getTextDate().getText()));
                if (getTextTraffic().getText().equals("0"))
                {
                    getCar().setGasolineCosts(Double.parseDouble(getTextMidGasoline().getText()));
                } else if (getTextMidGasoline().getText().equals("0")){
                    getCar().drivingInCity(Integer.parseInt(getTextTraffic().getText()));
                }
                getCar().drivingWithOrNotConditioning(getCar().isConditioner());
                getCar().drivingWithDynamicStyle(getCar().isDynamicDriving());
                getComputerCar().priceOnGasolineCosts(Double.parseDouble(getTextDistance().getText()),
                                                      Double.parseDouble(getTextPrice().getText()));

                JOptionPane.showMessageDialog(null, getComputerCar().reportCity());
                getComputerCar().resetGasAndResultGas();
            }
        });// end button start

        //when you press the returnMenu button and confirm, it closes the current window and returns to the main menu
        getReturnMenu().addActionListener(e -> {//
            int result = JOptionPane.showConfirmDialog
                    (null, "Вы точно хотите вернуться в меню?",
                            "вернуться в меню", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (LoginPanel.getUser().getAccess().equals("1")) {
                    new MenuAdminGUI();
                }else {
                    new MenuGUI();
                }
                setVisible(false);//hide window
                dispose();//clear memory main.gasProject.resources after hiding the window
            }
        });// end button returnMenu

    }// end constructor InCityPanel

    // down getter and setter
    private JTextField getTextDate() {
        return textDate;
    }
    private JPanel getPanel() {
        return panel;
    }
    private JTextField getTextDistance() {
        return textDistance;
    }
    private JTextField getTextTraffic() {
        return textTraffic;
    }
    private JRadioButton getConditionerON() {
        return conditionerON;
    }
    private JRadioButton getConditionerOFF() {
        return conditionerOFF;
    }
    private JRadioButton getDynamicDrivingON() {
        return dynamicDrivingON;
    }
    private JRadioButton getDynamicDrivingOFF() {
        return dynamicDrivingOFF;
    }
    private JButton getStart() {
        return start;
    }
    private JTextField getTextPrice() {
        return textPrice;
    }
    private JLabel getErrorDate() {
        return errorDate;
    }
    private JLabel getErrorDistance() {
        return errorDistance;
    }
    private JLabel getErrorTraffic() {
        return errorTraffic;
    }
    private JLabel getErrorPrice() {
        return errorPrice;
    }
    private JLabel getErrorButton() {
        return errorButton;
    }
    private JButton getReturnMenu() {
        return returnMenu;
    }
    private JTextField getTextMidGasoline() {
        return textMidGasoline;
    }
    private JLabel getInMidGasoline() {
        return inMidGasoline;
    }
    private JLabel getErrorMidGasoline() {
        return errorMidGasoline;
    }
    private JRadioButton getBeMidGasoline() {
        return beMidGasoline;
    }
    private JRadioButton getBeTraffic() {
        return beTraffic;
    }
    private JComboBox getChoosingCar() {
        return choosingCar;
    }
    private JLabel getErrorChoosingCar() {
        return errorChoosingCar;
    }
    private Car getCar() {
        return car;
    }
    private OnBoardComputerCar getComputerCar() {
        return computerCar;
    }
    private void setCar(Car car) {
        this.car = car;
    }
    private void setComputerCar(OnBoardComputerCar computerCar) {
        this.computerCar = computerCar;
    }
    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}// end class InCityPanel
