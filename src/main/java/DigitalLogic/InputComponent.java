package DigitalLogic;

import java.awt.*;

public class InputComponent extends Gate {

    private String gateName;

    public InputComponent() {
        this.gateName = "InputComponent";
        Image image = Toolkit.getDefaultToolkit()
                .createImage("C:\\Users\\n" + //
                        "ewby\\Programming\\ECS160\\DigitalLogic\\src\\Images\\Power.png");
        setImage(image);
    }

    public String getName() {
        return gateName;
    }

}
