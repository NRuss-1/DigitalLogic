package DigitalLogic;

import javax.imageio.ImageIO;
import javax.swing.*;

public class NotGate implements Gate {
    private ImageIcon png;
    private String gateName;

    public NotGate() {
        this.gateName = "Not Gate";
        this.png = new ImageIcon("filepath");
    }

    public ImageIcon getPNG() {
        return png;
    }

    public String getName() {
        return gateName;
    }
}
