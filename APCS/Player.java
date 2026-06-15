package APCS;

import APCS.Achievements.*;
import APCS.Uses.Actions.Attacks.*;
import APCS.Uses.Actions.Skills.*;
import APCS.Uses.Items.*;
import APCS.Uses.Items.AttackItem.*;
import APCS.Uses.Items.SkillItem.*;
import java.io.*;
import java.util.*;

public class Player
{
    private String name = "DEFAULT";
    private int lvl = 1, outfit = 1, fav = 1;
    private int hp, hpM = 20, rageHP, sklMana, sklM = 6;

    private int lev = 0,curStage = 0,curTurn = 0,vol = 80, died = 0;
    private boolean alive = true, rage = false, block = false;
    private File save = new File("APCS/Save.txt");

    public achievement [] ach = {new intoTheDreamscape(), new firstSteps(this), new firstSteps(this), new firstSteps(this), new firstSteps(this), new firstSteps(this), new firstSteps(this), new firstSteps(this), new firstSteps(this), new firstSteps(this)};

    //Starting atk/skls
    public Atk[] atks = {new punch(), null, null, null};
    public Skl[] skls = {new block(), null, null, null};
    public Itm[] itms = {null, null, null, null};

    private ArrayList <Atk> atkUn = new ArrayList<Atk>();
    private ArrayList <Skl> sklUn = new ArrayList<Skl>();
    
    //To add an atk/skl/itm to the player, add discription in getId, and the matching obj to IDS
    public String [] atkId = {"PUNCH", "WINPUNCH", "BIGHIT", "TWINSTRIKE","WOUNDINGSTRIKE", "GAMBLE", "STUNSTRIKE"};
    private Atk [] atkIdS = {new punch(), new winPunch(), new bigHit(), new twinStrike(), new woundingStrike(), new gamble(), new stunStrike()};
    
    public String [] sklId = {"JUICEBOX", "VAMPBLADESKL", "RAGE", "SKIP", "FIREBALLSKL", "BLOCK"};
    private Skl [] sklIdS = {new juiceBox(), new vampSwordSKL(), new rage(), new skip(), new fireballSKL(), new block()};
    
    private String [] itmId = {"SWORDITM", "MILKITM", "COOKIEITM", "BAGUETTEITM", "IOCPOWDERITM", "HOTCOCOITM"};
    private Itm [] itmIdS = {new swordITM(), new milkITM(), new cookieITM(), new baguetteITM(), new iocPowderITM(), new hotCocoITM()};

    public int chargeT = 0, regenT = 0, atkupT = 0,regenAmt,atkUpAmt, rageT = 0;

    public Player(String name, int outfit, int fav) 
    {
        this.name = name;
        this.outfit = outfit;
        this.fav = fav;
        hp = hpM;
        sklMana = sklM;
        rageHP = hpM-5;

        for(int i=0;i<4;i++)
        {
            if(atks[i]!=null){atkUn.add(atks[i]);}
            if(skls[i]!=null){sklUn.add(skls[i]);}
        }
    }

    public Player() {}

    //Skills
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

    public boolean rageing() {return rage;}

    public boolean block() {if(block==true){return false;}block = true;return true;}

    public boolean blocking() {if(block) {block = false;return true;}return block;}

    public boolean useSkl() {if(sklMana-1>=0) {sklMana--;return true;}return false;}

    //Mana
    public void resetMANA() {sklMana=sklM;}
    
    public boolean incrMana() {if(sklMana+1<=sklM){sklMana++;return true;}return false;}

    public int incrMana(int x) {int count = 0;for(int i=0;i<x;i++) {if(incrMana()){count++;}}return count;}

    public int getMana() {return sklMana;}

    //Hp
    public void doHp(int x) {if(hp+x>0 && alive){if(hp+x<=hpM) {hp+=x;} else{hp = hpM;}} else{hp = 0; alive = false;}}

    public void resetHP() {hp=hpM;}

    public int getHealth() {return hp;}

    public boolean isAlive() {return alive;}

    //Turn
    public int turn() {return curTurn;}

    public void nextTurn() 
    {
        if(rageT-1>=0) {rageT--;rage = rageT>0;}
        if(chargeT-1>=0) {chargeT--;}
        if(regenT-1>=0) {regenT--;}
        if(atkupT-1>=0) {atkupT--;}
    }

    public void resetTurn() {curTurn = 0;}

    public void nextLvl() 
    {
        if(getStage()<2) {setStage(getStage()+1);} else {setStage(0);setLevel(getLevel()+1);}
        
        lvl++;hpM = 18 + (lvl*2);hp = hpM; sklM = 5 + lvl;

        if(sklM>12) {sklM = 12;} sklMana = sklM;
    }

    public void setStage(int x) {curStage = x;}

    public int getStage() {return curStage;}

    public void reset() {resetHP();resetMANA();lev = 0; curStage = 0;died++;for(int i=0; i<itms.length; i++) {itms[i] = null;}}

    public int getLevel() {return lev;}

    public void setLevel(int x) {lev = x;}

    //Misc
    public int atkUnlocked() {return atkUn.size();}

    public int sklUnlocked() {return sklUn.size();}

    public int died() {return died;}

    public String getName() {return name;}    

    public void setVol(int x) {vol = x;}

    public int getVol() {return vol;}

    public int getOutfit() {return outfit;}

    public int getLvl() {return lvl;}

    public int curAtk() {int counter = 0;for(int i=0;i<4;i++) {if(atks[i]!=null){counter++;}}return counter;}

    public int curSkl() {int counter = 0;for(int i=0;i<4;i++) {if(skls[i]!=null){counter++;}}return counter;}

    public int curItm() {int counter = 0;for(int i=0;i<4;i++) {if(itms[i]!=null){counter++;}}return counter;}

    private boolean unAtkHas(Atk x)
    {
        boolean temp = true;
        for(int i=0;i<atkUn.size();i++) {if(atkUn.get(i).equals(x)) {temp = false;}}
        return temp;
    }

    private boolean unSklHas(Skl x)
    {
        boolean temp = true;
        for(int i=0;i<sklUn.size();i++) {if(sklUn.get(i).equals(x)) {temp = false;}}
        return temp;
    }

    public void achCheck() {for(int i=0;i<ach.length;i++) {ach[i].getComplete();}}

    public void loadSave()
    {
        String temp;try (Scanner sc = new Scanner(save)) 
        {
            while(sc.hasNext())
            {
                temp = sc.next();

                switch (temp) 
                {
                    case "NAME" -> name = sc.next();
                    case "OUT" -> outfit = Integer.parseInt(sc.next());
                    case "FAV" -> fav = Integer.parseInt(sc.next());
                    case "LVL" -> lvl = Integer.parseInt(sc.next());

                    case "ATK" -> 
                    {
                        for(int i=0; i<4; i++)
                        {
                            temp = sc.next();

                            if(!temp.equals("NULL")) {for(int j=0; j<atkId.length; j++) {if(temp.equals(atkId[j])) {atks[i] = atkIdS[j];}}}

                            else {atks[i] = null;}
                        }
                    }

                    case "SKL" -> 
                    {
                        for(int i=0; i<4; i++)
                        {
                            temp = sc.next();

                            if(!temp.equals("NULL")) {for(int j=0; j<sklId.length; j++) {if(temp.equals(sklId[j])) {skls[i] = sklIdS[j];}}}

                            else {skls[i] = null;}
                        }
                    }

                    case "ITM" -> 
                    {
                        for(int i=0; i<4; i++)
                        {
                            temp = sc.next();

                            if(!temp.equals("NULL")) {for(int j=0; j<itmId.length; j++) {if(temp.equals(itmId[j])) {itms[i] = itmIdS[j];}}}

                            else {itms[i] = null;}
                        }
                    }

                    case "LEVEL" -> lev = Integer.parseInt(sc.next());
                    case "STAGE" -> curStage = Integer.parseInt(sc.next());
                    case "VOL" -> vol = Integer.parseInt(sc.next());
                    case "HP" -> hp = Integer.parseInt(sc.next());
                    case "MANA" -> sklMana = Integer.parseInt(sc.next());
                    case "UNATK" -> {while(!(temp.equals("END"))){temp = sc.next();for(int j=0; j<atkId.length; j++) {if(temp.equals(atkId[j])) {atkUn.add(atkIdS[j]);}}}}
                    case "UNSKL" -> {while(!(temp.equals("END"))) {temp = sc.next();for(int j=0; j<sklId.length; j++) {if(temp.equals(sklId[j])) {sklUn.add(sklIdS[j]);}}}}
                    case "DIED" -> died = Integer.parseInt(sc.next());
                    case "ACH" -> 
                    {
                        temp = sc.next();
                        for(int i=0; i<ach.length;i++) 
                        {
                            if(temp.equals("TRUE")) {ach[i].setComp();}
                            if(sc.hasNext()) {temp = sc.next();}
                        }
                    }
                }
            }

            rageHP = hpM - 5;
            hpM = hp;
            sklM = sklMana;
        }
        
        catch (FileNotFoundException e){}
    }
    public void saveGame()
    {
        String load = "NAME " + name +"\n" + 
                "OUT "+ outfit +"\n" + 
                "FAV "+ fav +"\n" +
                "LVL "+ lvl +"\n";
                
                load += "ATK "; for(int i=0; i<4; i++) {if(atks[i]!=null) {load += atks[i].getId();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "SKL "; for(int i=0; i<4; i++) {if(skls[i]!=null) {load += skls[i].getId();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "ITM "; for(int i=0; i<4; i++) {if(itms[i]!=null) {load += itms[i].getId();} else {load += "NULL";} load+= " ";} load += "\n";

                load += "LEVEL " + lev + "\n" + 
                "STAGE " + curStage + "\n" + 
                "VOL " + vol + "\n" + 
                "HP " + hpM + "\n" + 
                "MANA " + sklM + "\n" + 
                
                "UNATK ";
                for(int i=0;i<atkUn.size();i++) {load += (atkUn.get(i).getId() + " ");}load += "END\n" +
                
                "UNSKL ";
                for(int i=0;i<sklUn.size();i++) {load += (sklUn.get(i).getId() + " ");}load += "END\n" +
                
                "DIED " + died + "\n" +
                "ACH "; for(int i=0;i<ach.length;i++) {if(ach[i].getComplete()) {load += "TRUE ";}else {load += "X ";}}

        try {FileWriter w = new FileWriter("APCS/Save.txt");w.write(load);w.close();} catch (IOException e) {}
    }

    public String getAch()
    {
        String ret = "ACH ";

        for(int i=0; i<ach.length; i++)
        {
            if(ach[i].getComplete()) {ret += "TRUE ";}
            else {ret += "X ";}
        }

        return ret;
    }

    public void loadAch(String achs)
    {
        String [] temp = achs.split(" ");

        for(int i=1;i<temp.length;i++) {if(temp[i].equals("TRUE")) {ach[i-1].setComp();}}
    }

    //Check if the Atk/Skl/Itms are full
    public boolean AtkF() {int x=0;for(int i=0;i<4;i++) {if(atks[i]!=null) {x++;}}return x==4;}
    public boolean SklF() {int x=0;for(int i=0;i<4;i++) {if(skls[i]!=null) {x++;}}return x==4;}
    public boolean ItmF() {int x=0;for(int i=0;i<4;i++) {if(itms[i]!=null) {x++;}}return x==4;}

    //Adds to the Atk/Skl/Itms list
    public void AtkA(Atk x) {if(!AtkF()) {for(int i=0; i<4; i++) {if(atks[i]==null) {atks[i] = x;i=100;}}}if(unAtkHas(x)){atkUn.add(x);}}
    public void SklA(Skl x) {if(!SklF()) {for(int i=0; i<4; i++) {if(skls[i]==null) {skls[i] = x;i=100;}}}if(unSklHas(x)){sklUn.add(x);}}
    public void ItmA(Itm x) {if(!ItmF()) {for(int i=0; i<4; i++) {if(itms[i]==null) {itms[i] = x;i=100;}}}}

    public boolean has(Atk x) {for(int i=0;i<4;i++) {if(atks[i]!=null && atks[i].equals(x)) {return true;}}return false;}
    public boolean has(Skl x) {for(int i=0;i<4;i++) {if(skls[i]!=null && skls[i].equals(x)) {return true;}}return false;}
    public boolean has(Itm x) {for(int i=0;i<4;i++) {if(itms[i]!=null && itms[i].equals(x)) {return true;}}return false;}

    public void printA() {for(int i=0; i<4;i++) {if(atks[i]!=null){System.out.println(atks[i].getName());}}}
    public void printS() {for(int i=0; i<4;i++) {if(skls[i]!=null){System.out.println(skls[i].getName());}}}
    public void printI() {for(int i=0; i<4;i++) {if(itms[i]!=null){System.out.println(itms[i].getName());}}}
}