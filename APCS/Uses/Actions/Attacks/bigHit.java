package APCS.Uses.Actions.Attacks;

public class bigHit implements Atk
{

    public String getName() {return "Big Hit";}

    public int getDmg() {return -25;}

    public boolean swing() {return true;}

    public int acur() {return 100;}

    public String getId() {return "BIGHIT";}

    public int stat() {return 1;}

    public int statT() {return 1;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() 
    {
        return 
        """
                                                       Big Hit
                            -----------------------------------------------------
                               Does 25 damage to all enemies,
                                and takes a turn to charge up""";
    }
}