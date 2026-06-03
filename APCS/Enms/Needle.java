package APCS.Enms;

import APCS.Actions.Attacks.*;

public class Needle implements enm
{
    private int lvl, hp, burn = 0, bleed = 0;
    private boolean alive = true;
    public Atk[] atks = {new smack(), new bloodDraw()};

    public Needle(int lvl) {this.lvl = lvl;hp = 100;}//10 + (2*lvl);}

    public String getName() {return "Needle";}

    public int getHp() {return hp;}

    public void doHp(int x) {if(hp+x>0 && alive){hp+=x;} else{hp = 0; alive = false;}}

    public int getLvl() {return lvl;}

    public boolean isAlive() {return alive;}

    public Atk getAtk(int x) {return atks[x];}

    public void setLvl(int x) {lvl = x;}

    public void burn() {burn = 3;}

    public boolean burnt() {return !(burn == 0);}

    public void burnTurn() {if(burn-1>=0) {burn--;}}

    public int getBurn() {return burn;}

    public void blood() {bleed = 3;}

    public boolean bleed() {return !(bleed == 0);}

    public void bleedTurn() {if(bleed-1>=0) {bleed--;}}

    public int getBleed() {return bleed;}
}
