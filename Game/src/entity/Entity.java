package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed;
    public BufferedImage up[], down[], left[], right[], sprite[];
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
    public boolean isColliding(Entity other) {
        return this.x < other.x + 16 &&
                this.x + 16 > other.x &&
                this.y < other.y + 16 &&
                this.y + 16 > other.y;
    }
}
