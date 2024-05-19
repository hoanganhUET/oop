package main;
import entity.*;
import object.OBJ_BigCoin;
import object.OBJ_Coin;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel implements Runnable {
    public final int orginalTileSize = 16;
    public final int maxScreenCol = 60;
    public final int maxScreenRow = 16;
    public final int ScreenWidth = orginalTileSize * maxScreenCol;
    final int ScreenHeight = orginalTileSize * maxScreenRow;
    UI ui = new UI(this);
    KeyHandler KeyH = new KeyHandler(this, ui);
    AssetSetter aSetter = new AssetSetter(this);
    Sound sound = new Sound();
    Thread gameThread;
    int coinCount = 0;
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    Player player = new Player(this, KeyH, tileM);
    RedGhost redGhost = new RedGhost(tileM, this);
    BlueGhost blueGhost = new BlueGhost(tileM, this);
    GreenGhost greenGhost = new GreenGhost(tileM, this);
    YellowGhost yellowGhost = new YellowGhost(tileM, this);
    public SuperObject[] obj = new SuperObject[10];
    public int gameState;
    public final int playState = 0;
    public final int pauseState = 1;
    public final int loseState = 2;

    public GamePanel() {
        this.setPreferredSize(new Dimension(ScreenWidth, ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(KeyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setupGame() {
        aSetter.setObject();
        playMusic(2);
        gameState = pauseState;
    }


    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int drawCount = 0;
        int timer = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] instanceof OBJ_BigCoin bigCoin) {
                    if (player.getBounds().intersects(bigCoin.getBounds())) {
                        obj[i] = null;
                        player.setPoweredUp(true);
                    }
                }
            }
            for (int row = 0; row < tileM.coinMap.length; row++) {
                for (int col = 0; col < tileM.coinMap[row].length; col++) {
                    OBJ_Coin coin = tileM.coinMap[row][col];
                    if (coin != null && player.getBounds().intersects(coin.getBounds())) {
                        playSE(1);
                        tileM.coinMap[row][col] = null;
                        ++coinCount;
                        if (coinCount == 328) {
                            ui.gameFinished = true;
                            Graphics2D g2 = (Graphics2D) getGraphics();
                            ui.draw(g2);
                        }
                    }
                }
            }
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                ++drawCount;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            redGhost.move();
            blueGhost.move();
            greenGhost.move();
            yellowGhost.move();
            player.update();
            checkCollisions();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2);
            }
        }
        redGhost.draw(g2);
        blueGhost.draw(g2);
        greenGhost.draw(g2);
        yellowGhost.draw(g2);
        player.draw(g2);
        if (ui.gameStart) {
            ui.draw(g2);
        }
        if (ui.gameLose) {
            ui.draw(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

    public void checkCollisions() {
        Graphics2D g2 = (Graphics2D) getGraphics();
        if (player.isColliding(yellowGhost)) {
            if (player.isPoweredUp()) {
                yellowGhost.defeat();
            } else {
                ui.gameLose = true;
            }
        }
        if (player.isColliding(redGhost)) {
            if (player.isPoweredUp()) {
                redGhost.defeat();
            } else {
                ui.gameLose = true;
            }
        }
        if (player.isColliding(blueGhost)) {
            if (player.isPoweredUp()) {
                blueGhost.defeat();
            } else {
                gameState = loseState;
                ui.gameLose = true;
            }
        }
        if (player.isColliding(greenGhost)) {
            if (player.isPoweredUp()) {
                greenGhost.defeat();
            } else {
                gameState = loseState;
                ui.gameLose = true;
            }
        }
    }
}
