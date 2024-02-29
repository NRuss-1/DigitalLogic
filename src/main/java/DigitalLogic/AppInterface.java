package DigitalLogic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppInterface extends JFrame {

    private JPanel sidePanel;
    private JPanel mainPanel;
    private Board board = Board.getInstance();
    private WireClickListener wireListener = new WireClickListener();

    public AppInterface() {
        initInterface();
        mainPanel.add(board, BorderLayout.CENTER);
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
                if (gateName == "And Gate") {
                    AndGate gate = new AndGate();
                    board.addGate(gate);
                    board.addMouseListener(gate.getListener());
                    // board.addMouseMotionListener(new DragListener(gate));
                }
                if (gateName == "Or Gate") {
                    OrGate gate = new OrGate();
                    board.addMouseListener(new GateClickListener(gate));
                    // board.addMouseMotionListener(new DragListener(gate));
                }
            }
        });
        sidePanel.add(gateButton);
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

        addLogicGateWidget("And Gate");

        addLogicGateWidget("Or Gate");

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
        JMenuItem clearWires = new JMenuItem("Clear");
        clearWires.addActionListener(e -> board.Clear());
        editMenu.add(clearWires);

        JMenu mode = new JMenu("Mode");
        JMenuItem enterWire = new JMenuItem("Enter Wire Drawing Mode");
        enterWire.addActionListener(e -> board.addMouseListener(wireListener));

        JMenuItem exitWire = new JMenuItem("Exit Wire Edit Mode");
        exitWire.addActionListener(e -> board.removeMouseListener(wireListener));

        mode.add(enterWire);
        mode.add(exitWire);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        // aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        menuBar.add(mode);
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