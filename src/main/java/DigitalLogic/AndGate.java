package DigitalLogic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
import java.awt.*;

public class AndGate implements Gate {
    private BufferedImage png;
    private String gateName;
    private Point position;
    private final GateClickListener listener;

    // Should get its own instance of a click listener
    public AndGate() {
        this.gateName = "And Gate";
        this.position = new Point(10, 10);
        File file = new File("C:/Users/newby/Programming/ECS160/DigitalLogic/src/Images/andGate.png");
        try {
            this.png = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.listener = new GateClickListener(this);
    }

    @Override
    public GateClickListener getListener() {
        return listener;
    }

    @Override
    public ImageIcon getPNG() {
        Image newImg = png.getScaledInstance(100, 40, Image.SCALE_SMOOTH);
        ImageIcon resizedPNG = new ImageIcon(newImg);
        return resizedPNG;
    }

    @Override
    public String getName() {
        return gateName;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(Point newPosition) {
        this.position = newPosition;
    }

    @Override
    public Boolean getLogic() {
        return true;
    }

}
