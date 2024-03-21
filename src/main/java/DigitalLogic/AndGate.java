package DigitalLogic;

import java.awt.*;
import java.awt.Toolkit;
import java.util.ArrayList;

public class AndGate extends Gate {

    public AndGate() {

        Image image = Toolkit.getDefaultToolkit()
                .createImage("C:\\Users\\n" + //
                        "ewby\\Programming\\ECS160\\DigitalLogic\\src\\Images\\andGate.png");
        setImage(image);
    }

    @Override
    public boolean getOutputState() {
        ArrayList<Wire> inputs = getInputs();

        int size = inputs.size();
        int count = 0;
        for (Wire input : inputs) {
            boolean state = input.getState();
            if (state) {
                count += 1;
            }
        }
        if (size == count && count > 0) {
            return true;
        }
        return false;
    }
}
