package tile;

import main.GamePanel;
import object.OBJ_Coin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public OBJ_Coin[][] coinMap;
    public GamePanel gp;
    Tile[] tile;
    public int mapTileNum[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        coinMap = new OBJ_Coin[gp.maxScreenRow][gp.maxScreenCol];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap();
    }
    public void getTileImage(){
        try{
            tile[0] = new Tile(0);
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tile1.png"));
            tile[1] = new Tile(1);
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tile2.png"));
            tile[2] = new Tile(2);
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tile3.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap() {
        try {
            InputStream in = getClass().getResourceAsStream("/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            int row = 0;
            while (row < gp.maxScreenRow) {
                String line = br.readLine();
                String numbers[] = line.split(" ");
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                    if (num == 2) {
                        coinMap[row][col] = new OBJ_Coin();
                        coinMap[row][col].worldX = col * gp.orginalTileSize;
                        coinMap[row][col].worldY = row * gp.orginalTileSize;
                    }
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while (row < gp.maxScreenRow  && row < mapTileNum.length){
            int tileNum = mapTileNum[row][col];
            if (tileNum < 0 || tileNum >= tile.length || tile[tileNum] == null) {
                throw new IllegalArgumentException("Invalid tile number: " + tileNum);
            }

            g2.drawImage(tile[tileNum].image, x, y, gp.orginalTileSize, gp.orginalTileSize, null);
            if (tileNum == 2 && coinMap[row][col] != null) {
                g2.drawImage(coinMap[row][col].image, x, y, gp.orginalTileSize, gp.orginalTileSize, null);
            }
            col++;
            x += gp.orginalTileSize;
            if (col == gp.maxScreenCol){
                col = 0;
                row++;
                y += gp.orginalTileSize;
                x = 0;
            }
        }
    }
}
