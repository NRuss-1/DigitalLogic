package DigitalLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppInterface extends JFrame {
    private int cellSize = 20;
    private Color gridColor = Color.LIGHT_GRAY;
    private Color lineColor = Color.BLUE;

    private Point startPoint = null;
    private Point endPoint = null;

    private JPanel sidePanel;
    JPanel mainPanel;

    private Map<String, ImageIcon> logicGateImages;

    public AppInterface() {
        initInterface();

        // Set up the grid.
        JPanel drawingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);
                if (startPoint != null && endPoint != null) {
                    g.setColor(lineColor);
                    g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                }
            }

            private void drawGrid(Graphics g) {
                g.setColor(gridColor);
                for (int i = 0; i <= getWidth(); i += cellSize) {
                    g.drawLine(i, 0, i, getHeight());
                }
                for (int i = 0; i <= getHeight(); i += cellSize) {
                    g.drawLine(0, i, getWidth(), i);
                }
            }
        };

        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = getNearestGridPoint(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = getNearestGridPoint(e.getPoint());
                drawingPanel.repaint();
            }
        });

        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = getNearestGridPoint(e.getPoint());
                drawingPanel.repaint();
            }
        });

        mainPanel.add(drawingPanel, BorderLayout.CENTER);
        add(mainPanel);
        setupMenuBar();
        setVisible(true);
    }

    private void addLogicGateWidget(String gateName) {
        JButton gateButton = new JButton(gateName);
        gateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        gateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeLogicGateImage(gateName);
            }
        });
        sidePanel.add(gateButton);
    }

    /* This is to be done in the Gate interface/classes */
    private void placeLogicGateImage(String gateName) {
        ImageIcon icon = logicGateImages.get(gateName);
        if (icon != null) {
            JLabel label = new JLabel(icon);
            label.setSize(icon.getIconWidth(), icon.getIconHeight());
            sidePanel.add(label);
            sidePanel.revalidate();
            sidePanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "Image not found for " + gateName, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Point getNearestGridPoint(Point p) {
        int x = Math.round((float) p.x / cellSize) * cellSize;
        int y = Math.round((float) p.y / cellSize) * cellSize;
        return new Point(x, y);
    }

    private void initInterface() {
        setTitle("Grid Drawing App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        mainPanel = new JPanel(new BorderLayout());

        // Creating side panel for logic gates
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(200, getHeight()));
        sidePanel.setBackground(Color.WHITE);

        mainPanel.add(sidePanel, BorderLayout.WEST);

        logicGateImages = new HashMap<>();
        logicGateImages.put("AND Gate",
                new ImageIcon("C:/Users/newby/Programming/ECS160/DigitalLogic/src/Images/andGate.png"));

        addLogicGateWidget("AND Gate");
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        // saveItem.addActionListener(e -> saveImage());
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load");
        // loadItem.addActionListener(e -> loadImage());
        fileMenu.add(loadItem);

        fileMenu.add(new JSeparator()); // Separator

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem clearItem = new JMenuItem("Clear");
        // clearItem.addActionListener(e -> clearCanvas());
        editMenu.add(clearItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        // aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    /**
     * Main Method
     * 
     * @param args Don't use these
     */

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AppInterface::new);
    }
}