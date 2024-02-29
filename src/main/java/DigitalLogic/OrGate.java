package DigitalLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
import java.awt.*;

public class OrGate implements Gate {
    private BufferedImage png;
    private String gateName;
    private Point position;

    public OrGate() {
        this.gateName = "Or Gate";
        File file = new File("C:/Users/newby/Programming/ECS160/DigitalLogic/src/Images/orGate.png");
        try {
            this.png = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ImageIcon getPNG() {
        Image newImg = png.getScaledInstance(120, 60, Image.SCALE_SMOOTH);
        ImageIcon resizedPNG = new ImageIcon(newImg);
        return resizedPNG;
    }

    public String getName() {
        return gateName;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }

    public Boolean getLogic() {
        return true;
    }

}
