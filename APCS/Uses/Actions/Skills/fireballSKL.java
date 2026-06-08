package APCS.Uses.Actions.Skills;

import APCS.Uses.Actions.Attacks.*;

public class fireballSKL implements atkSkl
{
    public String getName() {return "Fireball";}
    
    public String getId() {return "FIREBALLSKL";}

    public Atk getAtk() {return new fireballATK();}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() 
    {
        return 
        """
                                                         Fireball
                             -------------------------------------------------------
                               Does medium damage to all enemies,
                               and makes the enemies burn 
                                            for 3 turns""";
    }
}
