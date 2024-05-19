package entity;

import main.Collision;
import main.GamePanel;
import main.KeyHandler;
import main.UI;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Player extends Entity{
    GamePanel gp;
    AffineTransform tx = new AffineTransform();
    KeyHandler keyH;
    private Collision collision;
    public UI ui;
    public Player (GamePanel gp, KeyHandler keyH, TileManager tileManager){
        this.gp = gp;
        this.keyH = keyH;
        this.ui = new UI(gp);
        this.up = new BufferedImage[8];
        this.down = new BufferedImage[8];
        this.left = new BufferedImage[8];
        this.right = new BufferedImage[8];
        setDefaultValues();
        getPlayerImage();
        setPoweredUp(isPoweredUp);
        this.collision = new Collision(tileManager, this);
    }

    private void setDefaultValues(){
        x = 34*16;
        y = 14*16;
        speed = 1;
        direction = "up";
    }
    public void getPlayerImage() {
        try {
            InputStream in = getClass().getResourceAsStream("/PacMan.png");
            BufferedImage playerImage = ImageIO.read(in);
            for (int i = 0; i < 8; i++) {
                 right[i] = playerImage.getSubimage(i * 16, 0, 16, 16);
                 tx = AffineTransform.getScaleInstance(-1, 1);
                 tx.translate(-right[i].getWidth(null), 0);
                 AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                 left[i] = op.filter(right[i], null);
                 tx = AffineTransform.getRotateInstance(Math.toRadians(90), right[i].getWidth() / 2.0, right[i].getHeight() / 2.0);
                 op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                 down[i] = op.filter(right[i], null);
                 tx = AffineTransform.getRotateInstance(Math.toRadians(-90), right[i].getWidth() / 2.0, right[i].getHeight() / 2.0);
                 op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                 up[i] = op.filter(right[i], null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }public void update() {
        int newX = x;
        int newY = y;
        int offset = 1;
        if (this.isPoweredUp&&System.currentTimeMillis() >= this.powerUpTimeEndTime){
            this.isPoweredUp = false;
        }
        if (keyH.upPressed) {
            direction = "up";
            newY -= speed;
        }
        if (keyH.downPressed) {
            direction = "down";
            newY += speed;
        }
        if (keyH.leftPressed) {
            direction = "left";
            newX -= speed;
        }
        if (keyH.rightPressed) {
            direction = "right";
            newX += speed;
        }

        switch (direction) {
            case "up":
                if (!collision.isCollidingWithTile((x + offset) / 16, (newY / 16)) && !collision.isCollidingWithTile((x + 16 - offset - 1) / 16, (newY / 16))) {
                    y = newY;
                }
                break;
            case "down":
                if (!collision.isCollidingWithTile((x + offset) / 16, (newY + 16 - 1) / 16) && !collision.isCollidingWithTile((x + 16 - offset - 1) / 16, (newY + 16 - 1) / 16)) {
                    y = newY;
                }
                break;
            case "left":
                if (!collision.isCollidingWithTile((newX / 16), (y + offset) / 16) && !collision.isCollidingWithTile((newX / 16), (y + 16 - offset - 1) / 16)) {
                    x = newX;
                }
                break;
            case "right":
                if (!collision.isCollidingWithTile((newX + 16 - 1) / 16, (y + offset) / 16) && !collision.isCollidingWithTile((newX + 16 - 1) / 16, (y + 16 - offset - 1) / 16)) {
                    x = newX;
                }
                break;
        }

        spriteCounter++;
        if (spriteCounter == 10) {
            spriteCounter = 0;
            spriteNum++;
            if (spriteNum == 9) {
                spriteNum = 1;
            }
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if (spriteNum == 1) {
                    image = up[0];
                }
                if (spriteNum == 2) {
                    image = up[1];
                }
                if (spriteNum == 3) {
                    image = up[2];
                }
                if (spriteNum == 4) {
                    image = up[3];
                }
                if (spriteNum == 5) {
                    image = up[4];
                }
                if (spriteNum == 6) {
                    image = up[5];
                }
                if (spriteNum == 7) {
                    image = up[6];
                }
                if (spriteNum == 8) {
                    image = up[7];
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down[0];
                }
                if (spriteNum == 2) {
                    image = down[1];
                }
                if (spriteNum == 3) {
                    image = down[2];
                }
                if (spriteNum == 4) {
                    image = down[3];
                }
                if (spriteNum == 5) {
                    image = down[4];
                }
                if (spriteNum == 6) {
                    image = down[5];
                }
                if (spriteNum == 7) {
                    image = down[6];
                }
                if (spriteNum == 8) {
                    image = down[7];
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left[0];
                }
                if (spriteNum == 2) {
                    image = left[1];
                }
                if (spriteNum == 3) {
                    image = left[2];
                }
                if (spriteNum == 4) {
                    image = left[3];
                }
                if (spriteNum == 5) {
                    image = left[4];
                }
                if (spriteNum == 6) {
                    image = left[5];
                }
                if (spriteNum == 7) {
                    image = left[6];
                }
                if (spriteNum == 8) {
                    image = left[7];
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right[0];
                }
                if (spriteNum == 2) {
                    image = right[1];
                }
                if (spriteNum == 3) {
                    image = right[2];
                }
                if (spriteNum == 4) {
                    image = right[3];
                }
                if (spriteNum == 5) {
                    image = right[4];
                }
                if (spriteNum == 6) {
                    image = right[5];
                }
                if (spriteNum == 7) {
                    image = right[6];
                }
                if (spriteNum == 8) {
                    image = right[7];
                }
                break;
        }
        g2.drawImage(image, x, y, 16,16, null);

    }
    public boolean isPoweredUp;
    public boolean isPoweredUp() {
        return this.isPoweredUp;
    }
    private long powerUpTimeEndTime;
    public void setPoweredUp(boolean isPoweredUp) {
        this.isPoweredUp = isPoweredUp;
        if(isPoweredUp){
            this.powerUpTimeEndTime = System.currentTimeMillis() + 10000;
        }
    }
}

