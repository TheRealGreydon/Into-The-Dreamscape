package APCS.Uses.Actions.Attacks;

public class stunStrike implements Atk
{
    public String getName() {return "Stun Strike";}

    public int getDmg() {return -8;}

    public boolean swing() {return false;}

    public int acur() {return 75;}

    public String getId() {return "STUNSTRIKE";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() 
    {
        return 
        """
                                            Punch
                             -----------------------------------------
                                What do you think it does?""";
    }
}