package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Font arial_40;
    public boolean gameStart = true;
    public boolean gameFinished = false;
    public boolean gameLose = false;
    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void draw (Graphics2D g2) {
        if (gameStart) {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            String text= "PacMan";
            int textLength;
            int x;
            int y;
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.maxScreenCol * gp.orginalTileSize / 2 - textLength / 2;
            y = gp.maxScreenRow * gp.orginalTileSize / 2 - (gp.orginalTileSize*3);
            g2.drawString(text, x, y);
        }
        if (gameFinished) {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            String text= "You win";
            int textLength;
            int x;
            int y;
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.maxScreenCol * gp.orginalTileSize / 2 - textLength / 2;
            y = gp.maxScreenRow * gp.orginalTileSize / 2 - (gp.orginalTileSize*3);
            g2.drawString(text, x, y);
            gp.gameThread = null;
        }
        if (gameLose) {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);
            String text= "You lose";
            int textLength;
            int x;
            int y;
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.maxScreenCol * gp.orginalTileSize / 2 - textLength / 2;
            y = gp.maxScreenRow * gp.orginalTileSize / 2 - (gp.orginalTileSize*3);
            g2.drawString(text, x, y);
            gp.gameThread = null;
        }
    }
}

