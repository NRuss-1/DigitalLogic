package DigitalLogic;

import java.awt.*;
import java.util.ArrayList;

public class XOrGate extends Gate {

    public XOrGate() {

        Image image = Toolkit.getDefaultToolkit()
                .createImage("C:\\Users\\n" + //
                        "ewby\\Programming\\ECS160\\DigitalLogic\\src\\Images\\xOrGate.png");
        setImage(image);
    }

    @Override
    public boolean getOutputState() {
        ArrayList<Wire> inputs = getInputs();
        int count = 0;
        for (Wire input : inputs) {
            boolean state = input.getState();
            if (state) {
                count += 1;
            }
        }
        if (count % 2 != 0) {
            return true;
        }
        return false;
    }

}
