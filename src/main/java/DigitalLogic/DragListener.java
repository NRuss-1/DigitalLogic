package DigitalLogic;

import java.awt.event.MouseEvent;
import java.awt.*;
import java.awt.event.MouseMotionAdapter;

public class DragListener extends MouseMotionAdapter {
    private Gate gate;

    public DragListener(Gate newGate) {
        this.gate = newGate;
    }

    public void mouseDragged(MouseEvent e) {
        Point newPoint = e.getPoint();
        Point prevPoint = gate.getPosition();
        prevPoint.translate((int) newPoint.getX() - (int) prevPoint.getX(),
                (int) newPoint.getY() - (int) prevPoint.getY());
        gate.setPosition(newPoint);
    }

}