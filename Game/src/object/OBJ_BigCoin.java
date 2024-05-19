package object;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class OBJ_BigCoin extends SuperObject {
    public OBJ_BigCoin() {
        this.name = "BigCoin";
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Big_coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(worldX, worldY, 16, 16);
    }
}
