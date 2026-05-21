package APCS.Enms;

import APCS.Skills.*;

public class HemoNeedle implements enm
{
    private String name = "HemoNeedle";
    private int lvl;
    private int type = 1;
    private int hp;
    private boolean alive = true;
    public Atk[] atks = enm.atks;

    public HemoNeedle(int lvl) {this.lvl = lvl;hp = /*3*/0 + (2*lvl); atks[1] = new bloodDraw();}

    public String getName() {return name;}

    public int getHp() {return hp;}

    public void doHp(int x) {if(hp+x>0 && alive){hp+=x;} else{hp = 0; alive = false;}}

    public int getLvl() {return lvl;}

    public boolean isAlive() {return alive;}

    public int getType() {return type;}
}
