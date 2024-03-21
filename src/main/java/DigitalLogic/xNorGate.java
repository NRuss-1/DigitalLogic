package DigitalLogic;

import java.awt.*;
import java.awt.Toolkit;
import java.util.ArrayList;

public class xNorGate extends Gate {
    public xNorGate() {

        Image image = Toolkit.getDefaultToolkit()
                .createImage("C:\\Users\\n" + //
                        "ewby\\Programming\\ECS160\\DigitalLogic\\src\\Images\\xNorGate.png");
        setImage(image);
    }

    @Override
    public boolean getOutputState() {
        ArrayList<Wire> inputs = super.getInputs();
        int size = inputs.size();
        int count = 0;
        for (Wire input : inputs) {
            boolean state = input.getState();
            if (state) {
                count += 1;
            }
        }
        if (size == count || count == 0) {
            return true;
        }
        return false;
    }
}
