package DigitalLogic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GateClickListener extends MouseAdapter {
    private Gate gate;
    private Point startPoint;
    private Point endPoint;
    private Board board = Board.getInstance();

    public GateClickListener(Gate newGate) {
        this.gate = newGate;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = board.getNearestGridPoint(e.getPoint());
        gate.setPosition(startPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = board.getNearestGridPoint(e.getPoint());
        gate.setPosition(endPoint);
        board.repaint();

    }
}