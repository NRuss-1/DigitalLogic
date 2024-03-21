package DigitalLogic;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WireClickListener extends MouseAdapter {
    private Point startPoint;
    private Point endPoint;
    private Board board = Board.getInstance();

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = board.getNearestGridPoint(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = board.getNearestGridPoint(e.getPoint());
        board.addWire(startPoint, endPoint);
        board.repaint();
    }

}