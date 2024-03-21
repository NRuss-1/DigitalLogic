package DigitalLogic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class BoardIO {

    public static void saveBoard(Board board, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(board);
            JOptionPane.showMessageDialog(null, "Board saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving board: " + e.getMessage());
        }
    }

    public static Board loadBoard(String fileName) {
        Board board = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            board = (Board) in.readObject();
            JOptionPane.showMessageDialog(null, "Board loaded successfully!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading board: " + e.getMessage());
        }
        return board;
    }
}
