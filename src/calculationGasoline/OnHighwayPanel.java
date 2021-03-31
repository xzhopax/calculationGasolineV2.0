package calculationGasoline;

import calculationGasoline.cars.Car;
import calculationGasoline.cars.CreateCar;
import calculationGasoline.onBoardComputerCar.OnBoardComputerCar;
import calculationGasoline.cars.VolkswagenPolo;
import calculationGasoline.onBoardComputerCar.workData.Check;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Aleksey Ilin
 *
 * class OnHighwayPanel - creates a panel for calculating the cost of gas and money when driving on the highway
 */

public class OnHighwayPanel extends JFrame {

    private JLabel namePanel;
    private JLabel inDate;
    private JTextField textDate;
    private JLabel inSpeed;
    private JTextField textSpeed;
    private JLabel inDistance;
    private JTextField textDistance;
    private JLabel inPrice;
    private JTextField textPrice;
    private JLabel isConditioner;
    private JRadioButton conditionerON;
    private JRadioButton conditionerOFF;
    private JLabel isDynamicDriving;
    private JRadioButton dynamicDrivingON;
    private JRadioButton dynamicDrivingOFF;
    private JButton start;
    private JPanel panel;
    private JLabel errorDate;
    private JLabel errorSpeed;
    private JLabel errorDistance;
    private JLabel errorPrice;
    private JLabel errorButton;
    private JButton returnMenu;
    private JLabel authorProgram;
    private JLabel inDateQuestion;
    private JLabel speedQuestion;
    private JLabel distanceQuestion;
    private JLabel priceQuestion;
    private JComboBox choosingCar;
    private JLabel errorChoosingCar;
    private JLabel choosingCarQuestion;

    private Car car = new VolkswagenPolo();
    private OnBoardComputerCar computerCar = new OnBoardComputerCar(car);

    /**
     *1.  The constructor creates a panel with the specified parameters;
     *2.  allows you to stretch it;
     *3.  sets the name of the panel;
     *4.  makes it visible;
     *5.  adds a panel to a container;
     *6.  sets the action when you click on the cross;
     *7.  through a getter sets the value for the field selection of the car
     *8.  through a getter sets the action for the date input field;
     *9.  through the getter sets the action for the speed input field;
     *10. through a getter sets the action for the distance input field;
     *11. through a getter sets the action for the price entry field;
     *12. combines buttons for turning on and off the air conditioner into a group;
     *13. setting the values of buttons for the air conditioner from the car class;
     *14. groups the buttons for the use or absence of dynamic driving;
     *15. setting button values for dynamic driving from the car class;
     *16. sets the action when pressing the button for counting the entered values;
     *17. sets the action when pressing the button to return to the main menu
     */

    protected OnHighwayPanel() {
        this.setBounds(400, 200, 600, 500);// initial window size
        this.setResizable(true); // you can make the window wider
        setTitle("расчет затрат бензина на трассе");//window title
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
            } // end windowClosing
        }); //end anonymous class WindowAdapter (X)

        //Action on the choice of the car, if you leave the field blank,
        // will display an error to choose a car if you choose a machine,
        // clearing all fields for filling
        getChoosingCar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getChoosingCar().getSelectedIndex() == 0) {
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
                    getTextSpeed().setText("");
                    getTextPrice().setText("");
                    getConditionerOFF().setSelected(true);
                    getCar().setConditioner(false);
                    getDynamicDrivingOFF().setSelected(true);
                    getCar().setDynamicDriving(false);
                }
            }
        });//end choosingCar.addActionListener

        //When the text field catches the focus, erases everything from the field
        //when the textDate field loses focus, it checks if the date is correct,
        // If not, it introduces in the field "01.01.1970"
        getTextDate().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                getTextDate().setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                getComputerCar().todayDate(getTextDate().getText());
                if (getComputerCar().getDate().equals("")) {
                    getErrorDate().setForeground(Color.RED);
                    getErrorDate().setText("Неправильно введена дата");
                    getTextDate().setText("01.01.1970");
                } else {
                    getErrorDate().setText("");
                }
            } // end focusLost
        }); // end anonymous class ActionListener (textDate)

        //when the textSpeed field loses focus, it checks if the speed is correct,
        // if not, it clears the value and asks to re-enter
        getTextSpeed().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                // if the string number is double and less than maxSpeed this car, then we write it to a variable speed
                if (Check.checkStringContainceDouble(getTextSpeed().getText()) &&
                    Double.parseDouble(getTextSpeed().getText()) <= getCar().getMaxSpeed() ||
                    Check.checkStringContainceInteger(getTextSpeed().getText()) &&
                    Integer.parseInt(getTextSpeed().getText()) <= getCar().getMaxSpeed())
                {
                    getTextSpeed().setText(Check.validDoubleInString(getTextSpeed().getText()));
                    getErrorSpeed().setText("");
                } else {
                    getErrorSpeed().setForeground(Color.RED);
                    getErrorSpeed().setText("Неправильно введена скорость");
                    getTextSpeed().setText("");

                }
            }
        }); // end anonymous class ActionListener (textSpeed)

        //when the textDistance field loses focus, it checks if the distance is correct,
        // if not, it erases the value and asks to re-enter
        getTextDistance().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                // check, if the string number is double or an integer, if false - removes value
                if (Check.checkStringContainceDoubleOrInteger(getTextDistance().getText()))
                {
                    getTextDistance().setText(Check.validDoubleInString(getTextDistance().getText()));
                    getErrorDistance().setText("");
                } else {
                    getErrorDistance().setForeground(Color.RED);
                    getErrorDistance().setText("Неправильно введена дистанция");
                    getTextDistance().setText("");
                }
            }
        }); // end anonymous class ActionListener (textDistance)

        //when the textPrice field loses focus, it checks if the price was entered correctly,
        // if not, it erases the value and asks to re-enter
        getTextPrice().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                //if the string number is double or an integer, then leaves the field content or cleaner field if false
                if (Check.checkStringContainceDoubleOrInteger(getTextPrice().getText()))
                {
                    getTextPrice().setText(Check.validDoubleInString(getTextPrice().getText()));
                    getErrorPrice().setText("");
                } else {
                    getErrorPrice().setForeground(Color.RED);
                    getErrorPrice().setText("Неправильно введена цена");
                    getTextPrice().setText("");
                }
            }
        }); // end anonymous class ActionListener (textPrice)

        // combine the conditionerON and conditionerOFF buttons into the group, set the initial value to ON
        ButtonGroup groupConditioner = new ButtonGroup();
        groupConditioner.add(getConditionerON());
        groupConditioner.add(getConditionerOFF());
        getConditionerON().setSelected(true);

        //if the conditionerON button is enabled, then we assign the conditioner variable of the car class (true)
        getConditionerON().addActionListener(e -> getCar().setConditioner(true));

        //if the conditionerOFF button is enabled, then we assign the conditioner variable of the car class (false)
        getConditionerOFF().addActionListener(e -> getCar().setConditioner(false));

        //combine the dynamicDrivingON and dynamicDrivingOFF buttons into a group, set the initial value to ON
        ButtonGroup groupDynamicDriving = new ButtonGroup();
        groupDynamicDriving.add(getDynamicDrivingON());
        groupDynamicDriving.add(getDynamicDrivingOFF());
        getDynamicDrivingON().setSelected(true);

        //if the dynamicDrivingON button is enabled, then assign the variable dynamicDriving of the car class (true)
        getDynamicDrivingON().addActionListener(e -> getCar().setDynamicDriving(true));

        //if the dynamicDrivingOFF button is enabled, then assign the variable dynamicDriving of the car class (false)
        getDynamicDrivingOFF().addActionListener(e -> getCar().setDynamicDriving(false));

        //when you click on the start button, it checks whether all the fields are filled,
        // if not, then asks to fill in the fields, and if all the fields are filled in correctly,
        // it calculates the result and displays it
        getStart().addActionListener(e -> {
            if (getTextDate().getText().equals("") || getTextDistance().getText().equals("") ||
                getTextSpeed().getText().equals("") || getTextPrice().getText().equals("") ||
                getChoosingCar().getSelectedIndex() == 0 )
            {
                getErrorButton().setForeground(Color.RED);
                getErrorButton().setText("Заполните все поля");
            } else {
                getErrorButton().setText("");
                getCar().drivingOnHighway(Double.parseDouble(getTextSpeed().getText()));
                getCar().drivingWithOrNotConditioning(getCar().isConditioner());
                getCar().drivingWithDynamicStyle(getCar().isDynamicDriving());
                getComputerCar().priceOnGasolineCosts(Double.parseDouble(getTextDistance().getText()),
                                                      Double.parseDouble(getTextPrice().getText()));

                JOptionPane.showMessageDialog(null, getComputerCar().reportHighway());
                getComputerCar().resetGasAndResultGas();   // reset in car gas = 0 and resultGas = 0
            }
        }); // end anonymous class ActionListener (button start)

        //when you press the returnMenu button and confirm, it closes the current window and returns to the main menu
        getReturnMenu().addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog
                    (null, "Вы точно хотите вернуться в меню?",
                            "вернуться в меню", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                MenuGUI menuGUI = new MenuGUI();
                setVisible(false);//hide window
                dispose();//clear memory main.gasProject.resources after hiding the window
            }
        }); // end anonymous class ActionListener (returnMenu)
    }// end constructor OnHighwayPanel

    // down getters and setters
    protected JTextField getTextDate() {
        return textDate;
    }
    protected JTextField getTextSpeed() {
        return textSpeed;
    }
    protected JTextField getTextDistance() {
        return textDistance;
    }
    protected JTextField getTextPrice() {
        return textPrice;
    }
    protected JRadioButton getConditionerON() {
        return conditionerON;
    }
    protected JRadioButton getConditionerOFF() {
        return conditionerOFF;
    }
    protected JRadioButton getDynamicDrivingON() {
        return dynamicDrivingON;
    }
    protected JRadioButton getDynamicDrivingOFF() {
        return dynamicDrivingOFF;
    }
    protected JButton getStart() {
        return start;
    }
    protected JPanel getPanel() {
        return panel;
    }
    protected JLabel getErrorDate() {
        return errorDate;
    }
    protected JLabel getErrorSpeed() {
        return errorSpeed;
    }
    protected JLabel getErrorDistance() {
        return errorDistance;
    }
    protected JLabel getErrorPrice() {
        return errorPrice;
    }
    protected JLabel getErrorButton() {
        return errorButton;
    }
    protected JButton getReturnMenu() {
        return returnMenu;
    }
    protected JComboBox getChoosingCar() {
        return choosingCar;
    }
    protected JLabel getErrorChoosingCar() {
        return errorChoosingCar;
    }
    protected Car getCar() {
        return car;
    }
    protected void setCar(Car car) {
        this.car = car;
    }
    protected OnBoardComputerCar getComputerCar() {
        return computerCar;
    }
    protected void setComputerCar(OnBoardComputerCar computerCar) {
        this.computerCar = computerCar;
    }
    //end getters and setters
}// end class OnHighwayPanel
