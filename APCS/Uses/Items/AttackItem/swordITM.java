package APCS.Uses.Items.AttackItem;

import APCS.Uses.Actions.Attacks.*;

public class swordITM implements atkItm
{
    public String getName() {return "Sword";}

    public Atk getAtk() {return new swordATK();}

    public String getId() {return "SWORDITM";}

    public String getDis() {return "";}

}