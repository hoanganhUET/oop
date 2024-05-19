package entity;

import main.Collision;
import main.GamePanel;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Random;

public class BlueGhost extends Entity {
    GamePanel gp;
    private int moveCount = 0;
    Player player;
    private boolean initialMoveDone = false;
    TileManager tileManager;
    private Collision collision;
    public BlueGhost(TileManager tileManager, GamePanel gp,Player player) {
        this.player = player;
        this.tileManager = tileManager;
        this.gp = gp;
        this.sprite = new BufferedImage[9];
        this.x = 33 * 16;
        this.y = 6 * 16;
        this.speed = 1;
        this.direction = "up";
        getBlueGhostImage();
        this.collision = new Collision(tileManager, this);
    }

    public void defeat() {
        this.x = 33 * 16;
        this.y = 4 * 16;
    }

    public void getBlueGhostImage() {
        try {
            InputStream in = getClass().getResourceAsStream("/blueGhost.png");
            BufferedImage ghostImage = ImageIO.read(in);
            for (int i = 0; i < 8; i++) {
                sprite[i] = ghostImage.getSubimage(i * 16, 0, 16, 16);
            }
            InputStream in2 = getClass().getResourceAsStream("/orangeGhost.png");
            BufferedImage orangeGhostImage = ImageIO.read(in2);
            sprite[8] = orangeGhostImage.getSubimage(0, 0, 16, 16);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int currentDirection = -1;
    public void move() {
        if (!initialMoveDone) {
            if (moveCount < 32) {
                y -= speed; // Move up
            } else if (moveCount < 32 + 16) { // Assuming the ghost should move right 16 pixels
                x += speed; // Move right
            } else {
                initialMoveDone = true;
            }
            moveCount += speed;
        } else {
            Random random = new Random();
            int direction;
            int newX = x;
            int newY = y;
            int offset = 1;

            if (currentDirection == -1 || isColliding(newX, newY, currentDirection)) {
                do {
                    direction = random.nextInt(4);
                    switch (direction) {
                        case 0:
                            newY = y - speed;
                            break;
                        case 1:
                            newY = y + speed;
                            break;
                        case 2:
                            newX = x - speed;
                            break;
                        case 3:
                            newX = x + speed;
                            break;
                    }
                } while (isColliding(newX, newY, direction));
                currentDirection = direction; // Add this line
            } else {
                direction = currentDirection;
                switch (direction) {
                    case 0:
                        newY = y - speed;
                        break;
                    case 1:
                        newY = y + speed;
                        break;
                    case 2:
                        newX = x - speed;
                        break;
                    case 3:
                        newX = x + speed;
                        break;
                }
            }
        switch (direction) {
            case 0:
                y = newY;
                break;
            case 1:
                y = newY;
                break;
            case 2:
                x = newX;
                break;
            case 3:
                x = newX;
                break;
            }
        }
    }

    private boolean isColliding(int newX, int newY, int direction) {
        int offset = 1;
        switch (direction) {
            case 0:
                return collision.isCollidingWithTile((x + offset) / 16, (newY / 16)) || collision.isCollidingWithTile((x + 16 - offset - 1) / 16, (newY / 16));
            case 1:
                return collision.isCollidingWithTile((x + offset) / 16, (newY + 16 - 1) / 16) || collision.isCollidingWithTile((x + 16 - offset - 1) / 16, (newY + 16 - 1) / 16);
            case 2:
                return collision.isCollidingWithTile((newX / 16), (y + offset) / 16) || collision.isCollidingWithTile((newX / 16), (y + 16 - offset - 1) / 16);
            case 3:
                return collision.isCollidingWithTile((newX + 16 - 1) / 16, (y + offset) / 16) || collision.isCollidingWithTile((newX + 16 - 1) / 16, (y + 16 - offset - 1) / 16);
        }

        return false;
    }

    public void draw(Graphics2D g2) {

        if (player.isPoweredUp()) {
            g2.drawImage(sprite[8], x, y, null);
        }
        else{
            g2.drawImage(sprite[spriteCounter], x, y, null);
        }
    }
}
