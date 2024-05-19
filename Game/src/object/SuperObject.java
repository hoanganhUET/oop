package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SuperObject {
    public BufferedImage image;
    public String name;
    public int worldX;
    public int worldY;
    public  void draw(Graphics2D g2){
        g2.drawImage(image, worldX, worldY, null);
    }

    public Rectangle getBounds(){
        return new Rectangle(worldX, worldY, 16, 16);
    }
}
