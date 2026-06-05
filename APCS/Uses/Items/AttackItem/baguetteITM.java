package APCS.Uses.Items.AttackItem;

import APCS.Uses.Actions.Attacks.*;

public class baguetteITM implements atkItm
{
    public String getName() {return "Baguette";}

    public Atk getAtk() {return new baguetteATK();}

    public String getId() {return "BAGUETTEITM";}

    public String getDis() {return "";}

}