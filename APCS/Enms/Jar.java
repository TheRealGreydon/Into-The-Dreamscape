package APCS.Enms;

import APCS.Actions.Attacks.*;

public class Jar implements enm
{
    private int lvl;
    private int hp;
    private boolean alive = true;
    public Atk[] atks = {new smack(), new bloodDraw()};

    public Jar(int lvl) {this.lvl = lvl;hp = 15 + (2*lvl); atks[1] = new jarCapt();}

    public String getName() {return "Jar";}

    public int getHp() {return hp;}

    public void doHp(int x) {if(hp+x>0 && alive){hp+=x;} else{hp = 0; alive = false;}}

    public int getLvl() {return lvl;}

    public boolean isAlive() {return alive;}

    public Atk getAtk(int x) {return atks[x];}
}
