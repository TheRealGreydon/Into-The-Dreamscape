package APCS.Actions.Attacks;

public class punch implements Atk
{
    public String getName() {return "Punch";}

    public int getDmg() {return -100;}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getId() {return "PUNCH";}

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