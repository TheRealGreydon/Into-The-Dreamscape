package APCS;

import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Items.AttackItem.swordITM;
import APCS.Items.Itm;
import APCS.Items.SkillItem.grilledCheeseITM;
import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;


public class Player
{
    private String name = "DEFAULT";
    private int gend = 1, lvl = 1, outfit = 1, fav = 1;
    private int[] stats = {0,0,0,0,0,0};
    private int hp = 100;
    public Skl[] skls = {new juiceBox(), null, null, null};
    public Itm[] itms = {null, null, null, null};
    public Atk[] atks = {new punch(), null, null, null};
    private int curLev = 0,curStage = 0;
    private int vol = 50;
    private Image sprite, bSprite;
    private boolean alive = true;
    private File save = new File("APCS/Save.txt");

    //To add an atk/skl/itm to the player, add discription in ID, and the matching obj to IDS
    private String [] atkId = {"PUNCH", "WIDEPUNCH"};
    private Atk [] atkIdS = {new punch(), new widePunch()};
    
    private String [] sklId = {"JUICEBOX"};
    private Skl [] sklIdS = {new juiceBox()};
    
    private String [] itmId = {"SWORD", "GRILLEDCHEESE"};
    private Itm [] itmIdS = {new swordITM(), new grilledCheeseITM()};

    public Player(String name, int gend, int outfit, int fav) 
    {
        this.name = name;
        this.gend = gend;
        this.outfit = outfit;
        this.fav = fav;
        spriteInit();
    }

    public Player() {spriteInit();}
    
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

    public void resetHP() {hp=9+lvl+(getHp()*2);}

    public void reset() {resetHP();curLev = 0; curStage = 0;}

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

    public void loadSave()
    {
        String temp;try (Scanner sc = new Scanner(save)) 
        {while(sc.hasNext())
        {
            temp = sc.next();

            if(temp.equals("NAME")) {name = sc.next();}

            else if(temp.equals("GEND")) {gend = Integer.parseInt(sc.next());}

            else if(temp.equals("OUT")) {outfit = Integer.parseInt(sc.next());}

            else if(temp.equals("FAV")) {fav = Integer.parseInt(sc.next());}

            else if(temp.equals("LVL")) {lvl = Integer.parseInt(sc.next());}

            else if(temp.equals("STATS")) {for(int i=0; i<6; i++) {stats[i] = Integer.parseInt(sc.next());}}

            else if(temp.equals("ATK")) 
            {
                for(int i=0; i<4; i++)
                {
                    temp = sc.next();

                    if(!temp.equals("NULL")) {for(int j=0; j<atkId.length; j++) {if(temp.equals(atkId[j])) {atks[i] = atkIdS[j];}}}

                    else {atks[i] = null;}
                }
            }

            else if(temp.equals("SKL")) 
            {
                for(int i=0; i<4; i++)
                {
                    temp = sc.next();

                    if(!temp.equals("NULL")) {for(int j=0; j<sklId.length; j++) {if(temp.equals(sklId[j])) {skls[i] = sklIdS[j];}}}

                    else {skls[i] = null;}
                }
            }

            else if(temp.equals("ITM")) 
            {
                for(int i=0; i<4; i++)
                {
                    temp = sc.next();

                    if(!temp.equals("NULL")) {for(int j=0; j<itmId.length; j++) {if(temp.equals(itmId[j])) {itms[i] = itmIdS[j];}}}

                    else {itms[i] = null;}
                }
            }

            else if(temp.equals("LEVEL")) {curLev = Integer.parseInt(sc.next());}

            else if(temp.equals("STAGE")) {curStage = Integer.parseInt(sc.next());}

            else if(temp.equals("VOL")) {vol = Integer.parseInt(sc.next());}}
        }
        
        catch (FileNotFoundException e){}
    }

    public void saveGame()
    {
        String load = "NAME " + name +"\n" + 
                "GEND "+ gend +"\n" + 
                "OUT "+ outfit +"\n" + 
                "FAV "+ fav +"\n" +
                "LVL "+ lvl +"\n" +
                "STATS "+ stats[0] +" " + stats[1] +" " + stats[2] +" " + stats[3] +" " + stats[4] +" " + stats[5] + "\n";
                
                load += "ATK "; for(int i=0; i<4; i++) {if(atks[i]!=null) {load += atks[i].getDis();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "SKL "; for(int i=0; i<4; i++) {if(skls[i]!=null) {load += skls[i].getDis();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "ITM "; for(int i=0; i<4; i++) {if(itms[i]!=null) {load += itms[i].getDis();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "LEVEL " + curLev +"\n" + 
                "STAGE " + curStage +"\n" + 
                "VOL " + vol;

        try {FileWriter w = new FileWriter("APCS/Save.txt");w.write(load);w.close();} catch (IOException e) {}
    }
}