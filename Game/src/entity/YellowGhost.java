package entity;

import main.Collision;
import main.GamePanel;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Random;

public class YellowGhost extends Entity {
    GamePanel gp;
    Player player;
    private int moveCount = 0;
    private boolean initialMoveDone = false;
    private int currentDirection = -1;
    TileManager tileManager;
    private Collision collision;

    public YellowGhost(TileManager tileManager, GamePanel gp, Player player) {
        this.tileManager = tileManager;
        this.gp = gp;
        this.player = player;
        this.sprite = new BufferedImage[9];
        this.x = 33 * 16;
        this.y = 6 * 16;
        this.speed = 1;
        this.direction = "up";
        getYellowGhostImage();
        this.collision = new Collision(tileManager, this);
    }

    public void defeat() {
        this.x = 33 * 16;
        this.y = 4 * 16;
    }

    public void getYellowGhostImage() {
        try {
            InputStream in = getClass().getResourceAsStream("/YellowGhost.png");
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

    public void move() {
        if (!initialMoveDone) {
            if (moveCount < 16) {
                x -= speed;
            } else if (moveCount < 16 + 32) {
                y -= speed; // Move up
            } else {
                initialMoveDone = true;
            }
            moveCount += speed;
        } else {
            Random random = new Random();
            int direction;
            int newX = x;
            int newY = y;

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
                currentDirection = direction;
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
            return;
        }
        else {
            g2.drawImage(sprite[spriteCounter], x, y, null);
        }
    }
}
