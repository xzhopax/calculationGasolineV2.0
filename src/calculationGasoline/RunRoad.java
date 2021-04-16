package calculationGasoline;

import javax.swing.*;

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

