package APCS.Actions.Attacks;

public class woundingStrike implements Atk
{

    public String getName() {return "Wound Strike";}

    public int getDmg() {return -5;}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getId() {return "WOUNDINGSTRIKE";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() 
    {
        return 
        """
                                            Wounding Strike
                             -----------------------------------------------
                               Does small damage to enemie,
                               and makes the enemy bleed 
                                        for 3 turns""";
    }
}