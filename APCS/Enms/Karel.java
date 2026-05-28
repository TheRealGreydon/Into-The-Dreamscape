package APCS.Enms;

import APCS.Actions.Attacks.*;

public class Karel implements enm
{
    private int lvl;
    private int hp;
    private boolean alive = true;
    public Atk[] atks = {new smack(), new bloodDraw()};

    public Karel(int lvl) {this.lvl = lvl;hp = 10 + (2*lvl);}

    public String getName() {return "Karel";}

    public int getHp() {return hp;}

    public void doHp(int x) {if(hp+x>0 && alive){hp+=x;} else{hp = 0; alive = false;}}

    public int getLvl() {return lvl;}

    public boolean isAlive() {return alive;}

    public Atk getAtk(int x) {return atks[x];}

    public void setLvl(int x) {lvl = x;}
}
