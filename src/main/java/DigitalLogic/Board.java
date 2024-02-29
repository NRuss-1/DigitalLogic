package DigitalLogic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {
    private static Board INSTANCE;
    private int cellSize = 20;

    // keep track of all the wires and gates
    private static ArrayList<Wire> wires = new ArrayList<Wire>();
    private static ArrayList<Gate> gates = new ArrayList<Gate>();

    private Board() {

    }

    public static Board getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Board();
        }
        return INSTANCE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGrid(g);
        drawWires(g);
        drawGates(g);
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= getWidth(); i += cellSize) {
            g.drawLine(i, 0, i, getHeight());
        }
        for (int i = 0; i <= getHeight(); i += cellSize) {
            g.drawLine(0, i, getWidth(), i);
        }

    }

    private void drawWires(Graphics g) {
        g.setColor(Color.blue);
        for (Wire wire : wires) {
            Point start = wire.getStartPoint();
            Point end = wire.getEndPoint();
            g.drawLine(start.x, start.y, end.x, end.y);
        }
    }

    private void drawGates(Graphics g) {
        System.out.println(gates.size());
        for (Gate gate : gates) {
            ImageIcon image = gate.getPNG();
            Point position = gate.getPosition();

            image.paintIcon(this, g, position.x, position.y);
        }
    }

    public void addWire(Point x, Point y) {
        Wire newWire = new Wire(x, y);
        wires.add(newWire);
    }

    public Point getNearestGridPoint(Point p) {
        int x = Math.round((float) p.x / cellSize) * cellSize;
        int y = Math.round((float) p.y / cellSize) * cellSize;
        return new Point(x, y);
    }

    public void addGate(Gate newGate) {
        gates.add(newGate);
        this.repaint();
    }

    public void removeGate(Gate gate) {

    }

    public void removeWire(Wire wire) {
        for (Wire wireTodelete : wires) {
            if (wireTodelete == wire) {
                wires.remove(wireTodelete);
            }
        }
        this.repaint();
    }

    // iterate through gates and remove them from the board
    public void Clear() {
        wires.clear();
        gates.clear();
        this.repaint();
    }

}