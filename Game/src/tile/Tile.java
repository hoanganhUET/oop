package tile;

import object.OBJ_Coin;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public BufferedImage image;
    private OBJ_Coin coin;
    public Tile(int index) {
        if (index == 2) {
            this.coin = new OBJ_Coin();
        }
    }
    public Rectangle getBounds(int x, int y){
        return new Rectangle(x, y, 16, 16);
    }
    public boolean hasCoin() {
        return coin!=null;
    }
    public void removeCoin() {
        this.coin = null;
    }
}
