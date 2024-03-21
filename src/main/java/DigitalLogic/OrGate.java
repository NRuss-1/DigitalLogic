package DigitalLogic;

import java.util.ArrayList;
import java.awt.*;

public class OrGate extends Gate {

    public OrGate() {

        Image image = Toolkit.getDefaultToolkit()
                .createImage("C:\\Users\\n" + //
                        "ewby\\Programming\\ECS160\\DigitalLogic\\src\\Images\\orGate.png");
        setImage(image);
    }

    @Override
    public boolean getOutputState() {
        ArrayList<Wire> inputs = super.getInputs();
        for (Wire input : inputs) {
            boolean state = input.getState();
            if (state) {
                return true;
            }
        }
        return false;
    }
}
