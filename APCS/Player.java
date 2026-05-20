package APCS;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import APCS.Skills.*;

public class Player
{
    private String name;
    private int gend;
    private int outfit;
    private int[] stats = {0,0,0,0,0,0};
    private int hp = 15;
    private int lvl = 1;
    public Skills[] skills = {new juiceBox(), null, null, null};;
    public Atk[] atks = {new punch(), null, null, null};
    private int fav;
    private int curLev = 0;
    private int curStage = 0;
    private int vol = 50;
    private Image sprite;
    private Image bSprite;
    private boolean alive = true;

    public Player(String name, int gend, int outfit, int fav)
    {
        this.name = name;
        this.gend = gend;
        this.outfit = outfit;
        this.fav = fav;
        spriteInit();
    }
    
    public boolean isAlive() {return alive;}

    public void setName(String x) {name = x;}

    public void setStage(int x) {curStage = x;}

    public int getStage() {return curStage;}

    public void setFav(int x) {fav = x;}

    public void setOut(int x) {outfit = x;}

    public void setGend(int x) {gend = x;}

    public String getName() {return name;}    

    public void setVol(int x) {vol = x;}

    public int getVol() {return vol;}

    public int getGend() {return gend;}

    public int getOutfit() {return outfit;}

    public int getStr() {return stats[0];}

    public int getCha() {return stats[1];}

    public int getHp() {return stats[2];}

    public int getInt() {return stats[3];}

    public int getSpe() {return stats[4];}

    public int getSpd() {return stats[5];}

    public void statMod(int x, int y) {stats[x]+= y;}

    public void doHp(int x) {if(hp+x>0 && alive){hp+=x;} else{hp = 0; alive = false;}}

    public int getHealth() {return hp;}

    public void resetHp() {hp=9+lvl+(getHp()*2);}

    public int getCurLev() {return curLev;}

    public void setCurLev(int x) {curLev = x;}

    public void setSprite(Image x) {sprite = x;}

    public Image getSprite() {return sprite;}
    
    public void setBSprite(Image x) {bSprite = x;}

    public Image getBSprite() {return bSprite;}

    public void lvlUp(int x)
    {
        lvl++;
        statMod(x, 1);
        int temp=(int)(Math.random()*6);
        statMod(temp, 1);
    }

    public String toString()
    {
        String temp="";
        temp+="Player name: " + name + "\n";
        temp+="Player Lvl: " + lvl + "\n";
        return temp;
    }

    private void spriteInit() 
    {
        try {sprite = ImageIO.read(new File("APCS/Assets/Img/Character Img/Char" + outfit + ".png")).getScaledInstance(650, 650, Image.SCALE_SMOOTH);}
        catch (IOException e) {e.printStackTrace();}
        
        try {bSprite = ImageIO.read(new File("APCS/Assets/Img/Character Img/HeroCombat-" + outfit + ".png")).getScaledInstance(650, 650, Image.SCALE_SMOOTH);}
        catch (IOException e) {e.printStackTrace();}
    }
}
