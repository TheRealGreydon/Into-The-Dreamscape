package APCS.Enms;

import APCS.Actions.Attacks.*;

public class Plane implements enm
{
    private int lvl,hp;
    public int burn = 0;
    private boolean alive = true;
    public Atk[] atks = {new smack(), new bloodDraw()};

    public Plane(int lvl) {this.lvl = lvl;hp = 100;}//1;}

    public String getName() {return "Plane";}

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
}
