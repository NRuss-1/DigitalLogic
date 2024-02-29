package DigitalLogic;

import javax.swing.*;
import java.awt.*;

public interface Gate {
    public ImageIcon getPNG();

    public String getName();

    public Boolean getLogic();

    public void setPosition(Point p);

    public Point getPosition();

    public GateClickListener getListener();

}
