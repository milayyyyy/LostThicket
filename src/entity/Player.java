package entity;

import LostThicket.GamePanel;
import LostThicket.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2-(gp.tileSize/2);
        screenY = gp.screenHeight/2-(gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX=gp.tileSize*16;
        worldY=gp.tileSize*23;
        speed=4;
        direction = "right";
    }

    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
        } catch(IOException e){
            e.printStackTrace();
            System.out.println("getPlayerImage() is not working");

        }
    }

    public void update(){
        if(keyH.downPressed || keyH.leftPressed || keyH.upPressed || keyH.rightPressed){
            if(keyH.upPressed) {
                direction="up";
                worldY -= speed;
            } else if(keyH.downPressed) {
                direction="down";
                worldY += speed;
            } else if(keyH.leftPressed) {
                direction="left";
                worldX -= speed;
            } else if(keyH.rightPressed) {
                direction="right";
                worldX += speed;
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "up":
                if(spriteNum==1)
                    image = up1;
                if(spriteNum==2)
                    image = up2;
                break;
            case "down":
                if(spriteNum==1)
                    image = down1;
                if(spriteNum==2)
                    image = down2;
                break;
            case "left":
                if(spriteNum==1)
                    image = left1;
                if(spriteNum==2)
                    image = left2;
                break;
            case "right":
                if(spriteNum==1)
                    image = right1;
                if(spriteNum==2)
                    image = right2;
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }


}
