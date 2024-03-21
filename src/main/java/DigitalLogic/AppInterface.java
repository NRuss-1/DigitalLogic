package DigitalLogic;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppInterface extends JFrame {

    private JPanel sidePanel;

    private Board board = Board.getInstance();
    private WireClickListener wireListener = new WireClickListener();

    public AppInterface() {
        initInterface();
        this.add(board, BorderLayout.CENTER);
        setupMenuBar();
        setVisible(true);
    }

    private void addLogicGateWidget(String gateName) {
        JButton gateButton = new JButton(gateName);
        gateButton.setPreferredSize(new Dimension(150, 60));
        gateButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        gateButton.setBackground(Color.LIGHT_GRAY);
        gateButton.setForeground(Color.BLACK);
        gateButton.setFont(new Font("Arial", Font.BOLD, 14)); // Change font
        gateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (gateName) {
                    case "And Gate":
                        board.addGate(new AndGate());
                        break;
                    case "Or Gate":
                        board.addGate(new OrGate());
                        break;
                    case "XOr Gate":
                        board.addGate(new XOrGate());
                        break;
                    case "NAnd Gate":
                        board.addGate(new NAndGate());
                        break;
                    case "XNor Gate":
                        board.addGate(new xNorGate());
                        break;
                    case "Input Device":
                        board.addGate(new InputComponent());
                        break;
                    default:
                        break;
                }
            }
        });
        sidePanel.add(Box.createVerticalStrut(10));
        sidePanel.add(gateButton);
    }

    private void initInterface() {
        setTitle("Digital Logic");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating side panel for logic gates
        sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setSize(new Dimension(200, getHeight()));
        sidePanel.setBackground(Color.WHITE);

        this.add(sidePanel, BorderLayout.WEST);

        board.addMouseListener(wireListener);

        addLogicGateWidget("And Gate");

        addLogicGateWidget("Or Gate");

        addLogicGateWidget("XOr Gate");

        addLogicGateWidget("NAnd Gate");

        addLogicGateWidget("XNor Gate");

        addLogicGateWidget("Input Device");

    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this, "Simple Digital Logic Application\nVersion 1.0\nCreated by Nicholas Russ",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(e -> BoardIO.saveBoard(board, "board.ser"));
        fileMenu.add(saveItem);

        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener(e -> BoardIO.loadBoard("board.ser"));
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

        JMenu mode = new JMenu("Wiring Tool");

        JMenuItem removeWire = new JMenuItem("Remove last wire");
        removeWire.addActionListener(e -> board.removeWire());

        mode.add(removeWire);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(mode);
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