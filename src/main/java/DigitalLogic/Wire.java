package DigitalLogic;

import java.awt.*;
import javax.swing.*;

public class Wire extends JComponent {
    private Point startPoint;
    private Point endPoint;
    private boolean state;
    private Color color;
    /* This is essentially denoting linked list head */
    public boolean isOutput = false;

    public Wire(Point start, Point end) {
        startPoint = start;
        endPoint = end;
        color = Color.red;
        state = false;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setState(boolean st) {
        this.state = st;
        setColor();
    }

    public boolean getState() {
        return state;
    }

    private void setColor() {
        if (state == false) {
            this.color = Color.red;
        } else {
            this.color = Color.green;
        }
    }

    public Color getColor() {
        return this.color;
    }

}
