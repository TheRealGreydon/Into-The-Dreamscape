package APCS.Items.AttackItem;

import APCS.Actions.Attacks.Atk;
import APCS.Actions.Attacks.swordATK;

public class swordITM implements atkItm
{
    public String getName() {return "Sword";}

    public boolean isAtk() {return true;}

    public Atk getAtk() {return new swordATK();}

}