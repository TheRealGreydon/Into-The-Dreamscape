package APCS;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Player
{
    private String name;
    private int gend;
    private int outfit;
    private int[] stats = {0,0,0,0,0,0};
    private int hp = 9;
    private int lvl = 1;
    private ArrayList<Skills> skills;
    private int fav;
    private int curLev;
    private int curStage = 0;
    private int vol = 50;
    private Image sprite;

    public Player(String name, int gend, int outfit, int fav)
    {
        this.name = name;
        this.gend = gend;
        this.outfit = outfit;
        this.fav = fav;
        this.skills = new ArrayList<Skills>();
        //switch (fav) {
        //    case 1:
        //    addAbil(new MatchBox());
        //    break;
        //    case 2:
        //    addAbil(new WetTowel());
        //    break;
        //    case 3:
        //    addAbil(new RockThrow());
        //    break;
        //    case 4:
        //    addAbil(new PartyPopper());
        //    break;
        //    default:
        //        throw new AssertionError();
        //}
        spriteInit();
    }
    
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

    public void doHp(int x) {hp+=x;}

    public int getHealth() {return hp;}

    public void resetHp() {hp=9+lvl+(getHp()*2);}

    public void addAbil(Skills x) {skills.add(x);}

    public void swapAbil(Skills x, int y) {skills.set(y, x);}

    public String getAbil(int x) {return skills.get(x).getName();}

    public int getCurLev() {return curLev;}

    public void setCurLev(int x) {curLev = x;}

    public void setSprite(Image x) {sprite = x;}

    public Image getSprite() {return sprite;}

    public String listAbil()
    {
        String temp="";
        for(int i=0; i<skills.size(); i++)
        {
            temp+=getAbil(i)+"\n";
        }
        return temp;
    }

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

    private void spriteInit() {try {sprite = ImageIO.read(new File("APCS/Assets/Character Img/Char" + outfit + ".jpg")).getScaledInstance(650, 650, Image.SCALE_SMOOTH);;} catch (IOException e) {e.printStackTrace();}}
}
