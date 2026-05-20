package APCS.Enms;

import APCS.Skills.Atk;
import APCS.Skills.bEAtk;

public class HemoNeedle implements enm
{
    private String name = "HemoNeedle";
    private int lvl;
    private int type = 1;
    private int hp;
    private boolean alive = true;
    public Atk[] atks = {new bEAtk()};

    public HemoNeedle(int lvl) {this.lvl = lvl;hp = 8 + (2*lvl);}

    public String getName() {return name;}

    public int getHp() {return hp;}

    public void doHp(int x) {if(alive) {hp+=x;alive = hp>0;}}

    public int getLvl() {return lvl;}

    public boolean isAlive() {return alive;}

    public int getType() {return type;}
}
