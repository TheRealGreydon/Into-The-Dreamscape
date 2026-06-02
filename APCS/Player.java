package APCS;

import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Items.AttackItem.*;
import APCS.Items.*;
import APCS.Items.SkillItem.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class Player
{
    private String name = "DEFAULT";
    private int gend = 1, lvl = 1, outfit = 1, fav = 1;
    private int hp, hpM = 100, rageHP;

    private int lev = 0,curStage = 0,curTurn = 0,vol = 50;
    private Image sprite, bSprite;
    private boolean alive = true, rage = false;
    private File save = new File("APCS/Save.txt");

    //Starting atk/skls
    public Skl[] skls = {new juiceBox(), null, null, null};
    public Itm[] itms = {null, null, null, null};
    public Atk[] atks = {new punch(), null, null, null};
    
    //To add an atk/skl/itm to the player, add discription in getId, and the matching obj to IDS
    private String [] atkId = {"PUNCH", "WIDEPUNCH", "WINPUNCH", "BIGHIT", "TWINSTRIKE",};
    private Atk [] atkIdS = {new punch(), new widePunch(), new winPunch(), new bigHit(), new twinStrike(),};
    
    private String [] sklId = {"JUICEBOX", "ROCKTHROW", "VAMPBLADESKL", "RAGE"};
    private Skl [] sklIdS = {new juiceBox(), new rockThrowSKL(), new vampSwordSKL(), new rage()};
    
    private String [] itmId = {"SWORDITM", "GRILLEDCHEESEITM", "MILKITM", "COOKIEITM"};
    private Itm [] itmIdS = {new swordITM(), new grilledCheeseITM(), new milkITM(), new cookieITM()};

    public int chargeT = 0, regenT = 0, atkupT = 0,regenAmt,atkUpAmt, rageT = 0;

    public Player(String name, int gend, int outfit, int fav) 
    {
        this.name = name;
        this.gend = gend;
        this.outfit = outfit;
        this.fav = fav;
        hp = hpM;
        rageHP = hpM-5;
        spriteInit();
    }

    public Player() {spriteInit();}

    public boolean actRage() 
    {
        if(hp<=rageHP && !rage)
        {
            rage = true;
            rageT = 3;
            rageHP = hp-5;
            return rage;
        }
        return false;
    }

    public int turn() {return curTurn;}

    public void nextTurn() 
    {
        if(rageT-1>=0) {rageT--;rage = rageT>0;}
        if(chargeT-1>=0) {chargeT--;}
        if(regenT-1>=0) {regenT--;}
        if(atkupT-1>=0) {atkupT--;}
    }

    public boolean rageing() {return rage;}

    public void resetTurn() {curTurn = 0;}

    public void nextLvl() {if(getStage()<2) {setStage(getStage()+1);} else {setStage(0);setLevel(getLevel()+1);}}

    public boolean isAlive() {return alive;}

    public void setStage(int x) {curStage = x;}

    public int getStage() {return curStage;}

    public String getName() {return name;}    

    public void setVol(int x) {vol = x;}

    public int getVol() {return vol;}

    public int getGend() {return gend;}

    public int getOutfit() {return outfit;}

    public void doHp(int x) {if(hp+x>0 && alive){if(hp+x<=hpM) {hp+=x;} else{hp = hpM;}} else{hp = 0; alive = false;}}

    public int getHealth() {return hp;}

    public void resetHP() {hp=hpM;}

    public void reset() {resetHP();lev = 0; curStage = 0;}

    public int getLevel() {return lev;}

    public void setLevel(int x) {lev = x;}

    public void setSprite(Image x) {sprite = x;}

    public Image getSprite() {return sprite;}
    
    public void setBSprite(Image x) {bSprite = x;}

    public Image getBSprite() {return bSprite;}

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

            else if(temp.equals("LEVEL")) {lev = Integer.parseInt(sc.next());}

            else if(temp.equals("STAGE")) {curStage = Integer.parseInt(sc.next());}

            else if(temp.equals("VOL")) {vol = Integer.parseInt(sc.next());}
            
            else if(temp.equals("HP")) {hp = Integer.parseInt(sc.next());}}
            
            rageHP = hpM - 5;
        }
        
        catch (FileNotFoundException e){}
    }

    public void saveGame()
    {
        String load = "NAME " + name +"\n" + 
                "GEND "+ gend +"\n" + 
                "OUT "+ outfit +"\n" + 
                "FAV "+ fav +"\n" +
                "LVL "+ lvl +"\n";
                
                load += "ATK "; for(int i=0; i<4; i++) {if(atks[i]!=null) {load += atks[i].getId();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "SKL "; for(int i=0; i<4; i++) {if(skls[i]!=null) {load += skls[i].getId();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "ITM "; for(int i=0; i<4; i++) {if(itms[i]!=null) {load += itms[i].getId();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "LEVEL " + lev +"\n" + 
                "STAGE " + curStage +"\n" + 
                "VOL " + vol+"\n" + 
                "HP " + hpM;

        try {FileWriter w = new FileWriter("APCS/Save.txt");w.write(load);w.close();} catch (IOException e) {}
    }

    //Check if the Atk/Skl/Itms are full
    public boolean AtkF() {int x=0;for(int i=0;i<4;i++) {if(atks[i]!=null) {x++;}}return x==4;}
    public boolean SklF() {int x=0;for(int i=0;i<4;i++) {if(skls[i]!=null) {x++;}}return x==4;}
    public boolean ItmF() {int x=0;for(int i=0;i<4;i++) {if(itms[i]!=null) {x++;}}return x==4;}

    //Adds to the Atk/Skl/Itms list
    public void AtkA(Atk x) {if(!AtkF()) {for(int i=0; i<4; i++) {if(atks[i]==null) {atks[i] = x;i=100;}}}}
    public void SklA(Skl x) {if(!SklF()) {for(int i=0; i<4; i++) {if(skls[i]==null) {skls[i] = x;i=100;}}}}
    public void ItmA(Itm x) {if(!ItmF()) {for(int i=0; i<4; i++) {if(itms[i]==null) {itms[i] = x;i=100;}}}}

    public boolean has(Atk x) {for(int i=0;i<4;i++) {if(atks[i]!=null && atks[i].equals(x)) {return true;}}return false;}
    public boolean has(Skl x) {for(int i=0;i<4;i++) {if(skls[i]!=null && skls[i].equals(x)) {return true;}}return false;}
    public boolean has(Itm x) {for(int i=0;i<4;i++) {if(itms[i]!=null && itms[i].equals(x)) {return true;}}return false;}

    public void printA() {for(int i=0; i<4;i++) {if(atks[i]!=null){System.out.println(atks[i].getName());}}}
    public void printS() {for(int i=0; i<4;i++) {if(skls[i]!=null){System.out.println(skls[i].getName());}}}
    public void printI() {for(int i=0; i<4;i++) {if(itms[i]!=null){System.out.println(itms[i].getName());}}}
}