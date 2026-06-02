package APCS.Actions.Attacks;

public class twinStrike implements Atk
{
    public String getName() {return "Twin Strike";}

    public int getDmg() {return -5;}

    public boolean swing() {return false;}

    public int acur() {return 75;}

    public String getId() {return "TWINSTRIKE";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}
}