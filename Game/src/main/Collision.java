package main;

import entity.Entity;
import tile.TileManager;

public class Collision {
    private TileManager tileManager;
    private Entity entity;
    public Collision(TileManager tileManager, Entity entity) {
        this.tileManager = tileManager;
        this.entity = entity;
    }
    public boolean isCollidingWithTile(int tileX, int tileY) {
        return tileManager.mapTileNum[tileY][tileX] == 0;
    }
}