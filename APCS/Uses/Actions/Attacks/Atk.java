package APCS.Uses.Actions.Attacks;

import APCS.Uses.Actions.Actions;

//DO NOT MAKE AN ATTACK NAMED X OR NEXT, THIS WILL SCREW SO MUCH UP
public interface Atk extends Actions 
{
    public int getDmg();
    public boolean swing();
    public int acur();
}
