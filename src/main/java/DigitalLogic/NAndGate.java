package DigitalLogic;

import java.awt.*;
import java.awt.Toolkit;
import java.util.ArrayList;

public class NAndGate extends Gate {

    public NAndGate() {
        Image image = Toolkit.getDefaultToolkit()
                .createImage("C:\\Users\\n" + //
                        "ewby\\Programming\\ECS160\\DigitalLogic\\src\\Images\\NAndGate.png");
        setImage(image);
    }

    @Override
    public boolean getOutputState() {
        ArrayList<Wire> inputs = super.getInputs();
        for (Wire input : inputs) {
            boolean state = input.getState();
            if (state) {
                return false;
            }
        }
        return true;
    }
}
