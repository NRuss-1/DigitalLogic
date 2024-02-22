package DigitalLogic;

import java.awt.*;
import javax.swing.*;

public class Wire extends JPanel {
    private Point startPoint;
    private Point endPoint;

    public Wire(Point start, Point end) {
        startPoint = start;
        endPoint = end;
    }

}
