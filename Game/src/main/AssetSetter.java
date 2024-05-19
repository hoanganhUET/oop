package main;

import object.OBJ_BigCoin;

public class AssetSetter {
    GamePanel gp;
    public  AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    public void setObject(){
        gp.obj[0] = new OBJ_BigCoin();
        gp.obj[0].worldX = 16;
        gp.obj[0].worldY = 16*3;
        gp.obj[1] = new OBJ_BigCoin();
        gp.obj[1].worldX = 16;
        gp.obj[1].worldY = 16*14;
        gp.obj[2] = new OBJ_BigCoin();
        gp.obj[2].worldX = 16*12;
        gp.obj[2].worldY = 16*7;
        gp.obj[3] = new OBJ_BigCoin();
        gp.obj[3].worldX = 16*58;
        gp.obj[3].worldY = 16*3;
        gp.obj[4] = new OBJ_BigCoin();
        gp.obj[4].worldX = 16*56;
        gp.obj[4].worldY = 16*14;

    }
}
