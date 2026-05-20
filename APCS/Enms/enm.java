package APCS.Enms;

import APCS.Skills.*;

public interface  enm 
{
    public String name = "";
    public int type = 0;
    public int lvl = 0;
    public int hp = 0;
    public boolean alive = true;
    public Atk[] atks = {new smack(), null};

    public String getName();

    public int getHp();

    public void doHp(int x);

    public int getLvl();

    public boolean isAlive();
    
    public int getType();
}
