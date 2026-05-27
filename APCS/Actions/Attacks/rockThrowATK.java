package APCS.Actions.Attacks;

public class rockThrowATK implements Atk
{
    public String getName() {return "Rock Throw";}

    public int getDmg() {return -8;}

    public boolean swing() {return false;}

    public int acur() {return 75;}

    public String getId() {return "ROCKTHROW";}
}