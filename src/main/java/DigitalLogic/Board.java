package DigitalLogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.*;
import javax.swing.border.LineBorder;
import java.awt.event.MouseEvent;

public class Board extends JPanel {
    private static Board INSTANCE;
    private int cellSize = 20;

    private static ArrayList<Wire> wires = new ArrayList<Wire>();
    private static ArrayList<Gate> gatesGraphics = new ArrayList<Gate>();
    private static ArrayList<Point> connections = new ArrayList<Point>();

    private Board() {
        this.setLayout(null);
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
        checkConnections(g);
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
        Graphics2D g2 = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(2);

        for (Wire wire : wires) {
            g2.setColor(wire.getColor());
            g2.setStroke(stroke);
            Point start = wire.getStartPoint();
            Point end = wire.getEndPoint();
            g2.drawLine(start.x, start.y, end.x, end.y);
        }
        consolidateWireStates();
    }

    private void drawConnections(Graphics g) {
        for (Point point : connections) {
            g.fillOval(point.x, point.y - 3, 8, 8);
        }

    }

    private boolean validateCollision(Point p1, Point p2) {
        if ((p1.getX() == p2.getX()) && (p1.getY() == p2.getY())) {
            return true;
        } else {
            return false;
        }
    }

    private boolean wiresCollide(Wire outputWire, Wire wireToChange) {
        Point trueStart = outputWire.getStartPoint();
        Point trueEnd = outputWire.getEndPoint();

        Point changeStart = wireToChange.getStartPoint();
        Point changeEnd = wireToChange.getEndPoint();

        if (validateCollision(trueStart, changeStart)) {
            return true;
        }
        if (validateCollision(trueStart, changeEnd)) {
            return true;
        }
        if (validateCollision(trueEnd, changeStart)) {
            return true;
        }
        if (validateCollision(trueEnd, changeEnd)) {
            return true;
        }
        return false;
    }

    private ArrayList<Wire> traceWire(Wire headWire) {
        ArrayList<Wire> lst = new ArrayList<Wire>();
        Wire head = headWire;
        for (Wire nextWire : wires) {
            if (wiresCollide(head, nextWire)) {
                lst.add(nextWire);
                head = nextWire;
            }
        }
        return lst;
    }

    private void consolidateWireStates() {
        for (Wire wire : wires) {
            if (wire.isOutput) {
                ArrayList<Wire> traceback = traceWire(wire);
                for (Wire trace : traceback) {
                    trace.setState(wire.getState());
                }
            }
        }
        repaint();
    }

    /*
     * Validate that all drawn connections are legitmate.
     * Draw those that are, remove those that aren't.
     */
    private void checkConnections(Graphics g) {
        connections.clear();
        for (Wire wire : wires) {
            Point end = wire.getEndPoint();
            Point start = wire.getStartPoint();
            for (Gate gate : gatesGraphics) {
                Point opt1 = gate.getConnections().get(0);
                Point opt2 = gate.getConnections().get(1);
                Point output = gate.getConnections().get(2);
                if (validateCollision(end, opt1)) {
                    connections.add(opt1);
                    gate.addInput1(wire);
                }
                if (validateCollision(end, opt2)) {
                    connections.add(opt2);
                    gate.addInput2(wire);
                }
                if (validateCollision(start, opt1)) {
                    connections.add(opt1);
                    gate.addInput1(wire);
                }
                if (validateCollision(start, opt2)) {
                    connections.add(opt2);
                    gate.addInput2(wire);
                }
                if (validateCollision(end, output)) {
                    wire.setState(gate.getOutputState());
                    connections.add(output);
                    wire.isOutput = true;
                }
                if (validateCollision(start, output)) {
                    wire.setState(gate.getOutputState());
                    connections.add(output);
                    wire.isOutput = true;
                }
            }
        }
        drawConnections(g);

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
        INSTANCE.add(newGate);

        newGate.setBorder(new LineBorder(Color.black, 1));

        int delta = INSTANCE.getWidth() / 4;
        int cx = INSTANCE.getWidth() / 2;
        int cy = INSTANCE.getHeight() / 2;
        newGate.setSize(80, 40);
        newGate.setLocation(cx + getRandom(delta / 2) - newGate.getWidth() / 2,
                cy + getRandom(delta / 2) - newGate.getHeight() / 2);
        gatesGraphics.add(newGate);
        newGate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    showPopUpMenuGate(newGate, e.getX(), e.getY());
                }
            }
        });
    }

    private void showPopUpMenuGate(Gate gate, int x, int y) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem1 = new JMenuItem("Delete");
        menuItem1.addActionListener(e -> removeGate(gate));
        if (gate.getName() == "InputComponent") {
            JMenuItem menuItem2 = new JMenuItem("Power on");
            menuItem2.addActionListener(e -> gate.setOutputState(true));
            popupMenu.add(menuItem2);
            JMenuItem menuItem3 = new JMenuItem("Power off");
            menuItem3.addActionListener(e -> gate.setOutputState(false));
            popupMenu.add(menuItem3);
        }

        popupMenu.add(menuItem1);
        popupMenu.show(gate, x, y);
    }

    // Use of random instancing so that gates should not spawn on top of eachother.
    public int getRandom(int range) {
        int r = (int) (Math.random() * range) - range;
        return r;
    }

    public void removeGate(Gate del_gate) {
        INSTANCE.remove(del_gate);
        gatesGraphics.remove(del_gate);
        INSTANCE.repaint();
    }

    public void removeWire() {
        wires.remove(wires.size() - 1);
        INSTANCE.repaint();
    }

    // iterate through gates and remove them from the board
    public void Clear() {
        wires.clear();
        for (Gate gate : gatesGraphics) {
            INSTANCE.remove(gate);
        }

        gatesGraphics.clear();
        INSTANCE.revalidate();
        INSTANCE.repaint();
    }

}