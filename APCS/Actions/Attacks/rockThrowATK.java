package APCS.Actions.Attacks;

public class rockThrowATK implements Atk
{
    public String getName() {return "Rock Throw";}

    public int getDmg() {return -8;}

    public boolean swing() {return false;}

    public int acur() {return 75;}

    public String getId() {return "ROCKTHROW";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}
}