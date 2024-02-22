package DigitalLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class gateButton implements ActionListener {
    private Gate gate;
    private String gateName;

    public gateButton(String gateName) {
        this.gateName = gateName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        placeLogicGateImage(gateName);
    }

    private void placeLogicGateImage(String gateName) {

    }
}
