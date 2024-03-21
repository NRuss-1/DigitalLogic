package DigitalLogic;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JComponent;

public class GateHandler extends JComponent {

    protected Point initPoint = new Point(0, 0);
    protected Wire initWire = new Wire(initPoint, initPoint);

    private ArrayList<Point> connections = new ArrayList<Point>(3);

    private Wire input1;
    private Wire input2;

    private ArrayList<Wire> inputs = new ArrayList<Wire>();

    private int numberInputsAllowed = 2;

    private boolean state;

    private static Board board = Board.getInstance();

    private boolean draggable = true;

    protected Point anchorPoint;

    protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);

    public GateHandler() {
        addDragListeners();

        input1 = initWire;
        input2 = initWire;
        connections.add(initPoint);
        connections.add(initPoint);
        connections.add(initPoint);
        setOpaque(true);
        setBackground(new Color(240, 240, 240));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    private void addDragListeners() {

        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;

                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = board.getNearestGridPoint(e.getLocationOnScreen());
                Point position = new Point(mouseOnScreen.x - parentOnScreen.x - anchorX,
                        mouseOnScreen.y - parentOnScreen.y - anchorY);
                setLocation(board.getNearestGridPoint(position));
                setConnections();
            }

        });

    }

    private void removeDragListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }

        setCursor(Cursor.getDefaultCursor());
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
        if (draggable) {
            addDragListeners();
        } else {
            removeDragListeners();
        }

    }

    public Cursor getDraggingCursor() {
        return draggingCursor;
    }

    public void setDraggingCursor(Cursor draggingCursor) {
        this.draggingCursor = draggingCursor;
    }

    public void setNumOfInputs(int numberAllowed) {
        this.numberInputsAllowed = numberAllowed;
        System.out.println(numberAllowed);
    }

    /* Max of two connections allowed currently */
    private void setConnections() {
        connections.clear();
        for (int i = 0; i < this.numberInputsAllowed; i++) {
            Point connection = this.getLocation();
            connection.y = this.getLocation().y + 40 * i;
            connections.add(connection);
        }
        Point output = this.getLocation();
        output.y = output.y + 20;
        output.x = output.x + 80;
        connections.add(output);
    }

    public void addInput1(Wire wire) {
        input1 = wire;
    }

    public void addInput2(Wire wire) {
        input2 = wire;
    }

    public ArrayList<Wire> getInputs() {
        inputs.clear();
        inputs.add(input1);
        inputs.add(input2);
        return inputs;
    }

    public ArrayList<Point> getConnections() {
        return this.connections;
    }

    public boolean getOutputState() {
        return state;
    }

    public void setOutputState(boolean state) {
        this.state = state;
    }
}
