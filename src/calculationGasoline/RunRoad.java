package calculationGasoline;

import calculationGasoline.onBoardComputerCar.workData.Check;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class RunRoad {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPanel();
            }
        });
    }
}

