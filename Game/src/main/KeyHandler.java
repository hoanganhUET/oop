package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    GamePanel gp;
    UI ui;
    public KeyHandler(GamePanel gp,UI ui) {
        this.gp = gp;
        this.ui = ui;
    }
    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                ui.gameStart = false;
            }
            upPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            if(gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                ui.gameStart = false;
            }
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                ui.gameStart = false;
            }
            downPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            if (gp.gameState == gp.pauseState) {
                gp.gameState = gp.playState;
                ui.gameStart = false;
            }
            rightPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else {
                gp.gameState = gp.playState;
            }
        }
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
