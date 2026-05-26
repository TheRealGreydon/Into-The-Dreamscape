package APCS.Enms;

import APCS.Actions.Attacks.Atk;
import APCS.Actions.Attacks.bloodDraw;

public class Needle implements enm
{
    private int lvl;
    private int hp;
    private boolean alive = true;
    private Atk[] atks = enm.atks;

    public Needle(int lvl) {this.lvl = lvl;hp = 10 + (2*lvl); atks[1] = new bloodDraw();}

    public String getName() {return "Needle";}

    public int getHp() {return hp;}

    public void doHp(int x) {if(hp+x>0 && alive){hp+=x;} else{hp = 0; alive = false;}}

    public int getLvl() {return lvl;}

    public boolean isAlive() {return alive;}
}
