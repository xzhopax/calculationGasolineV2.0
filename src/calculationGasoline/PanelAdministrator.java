package calculationGasoline;

import calculationGasoline.cars.Car;
import calculationGasoline.cars.CreateCar;
import calculationGasoline.onBoardComputerCar.OnBoardComputerCar;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PanelAdministrator extends JFrame {
    private JLabel nameAddCar;
    private JLabel namePanel;
    private JLabel nameChoiceCar;
    private JTextField textAddCar;
    private JComboBox choiseCar;
    private JLabel nameMaxSpeed;
    private JTextField textMaxSpeed;
    private JLabel nameCreateTraffic;
    private JComboBox comboBox1;
    private JTextField textTraffic;
    private JLabel nameSpeedAndGas;
    private JButton buttonSpeedAndGas;
    private JPanel panel;

    private Car car = CreateCar.getMapCreateCars().get(1);
    private OnBoardComputerCar computerCar = new OnBoardComputerCar(getCar());

    public PanelAdministrator(){
        this.setBounds(400, 200, 600, 500);// initial window size
        this.setResizable(true); // you can make the window wider
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setTitle("Панель создания автомабиля");//window title
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
    }








    public Car getCar() {
        return car;
    }

    public JPanel getPanel() {
        return panel;
    }
}
