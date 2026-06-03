package APCS.Enms;

import APCS.Actions.Attacks.Atk;

public interface enm 
{
    public String getName();

    public int getHp();

    public void doHp(int x);

    public int getLvl();

    public Atk getAtk(int x);

    public boolean isAlive();

    public void setLvl(int x);

    public boolean burnt();

    public void burn();
    
    public void burnTurn();

    public int getBurn();

    public boolean bleed();

    public void blood();
    
    public void bleedTurn();

    public int getBleed();
}
