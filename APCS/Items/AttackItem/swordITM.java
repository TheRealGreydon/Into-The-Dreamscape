package APCS.Items.AttackItem;

import APCS.Actions.Attacks.*;

public class swordITM implements atkItm
{
    public String getName() {return "Sword";}

    public Atk getAtk() {return new swordATK();}

    public String getId() {return "SWORDITM";}

    public String getDis() 
    {
        return 
        """
                                                     Old Sword
                                -------------------------------------------------
                                Doesn't hurt much, but it's at
                                least something if you throw it""";
    }

}