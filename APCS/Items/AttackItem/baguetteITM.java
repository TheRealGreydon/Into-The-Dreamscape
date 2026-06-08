package APCS.Items.AttackItem;

import APCS.Actions.Attacks.*;

public class baguetteITM implements atkItm
{
    public String getName() {return "Baguette";}

    public Atk getAtk() {return new baguetteATK();}

    public String getId() {return "BAGUETTEITM";}

    public String getDis() 
    {
        return 
        """
                                                            Baguette
                                     ------------------------------------------------
                                Deals some damage and then breaks. 
                                I mean, it is just a big bread stick""";
    }

}