package APCS.Enms;

import APCS.Actions.Attacks.Atk;
import APCS.Actions.Attacks.smack;

public interface  enm 
{
    public Atk[] atks = {new smack(), null};

    public String getName();

    public int getHp();

    public void doHp(int x);

    public int getLvl();

    public boolean isAlive();
}
