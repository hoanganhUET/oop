package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Coin extends SuperObject {
    public OBJ_Coin() {
        this.name = "Coin";
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}