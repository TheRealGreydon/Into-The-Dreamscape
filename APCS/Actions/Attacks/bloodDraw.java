package APCS.Actions.Attacks;

public class bloodDraw implements Atk
{
    public String getName() {return "Blood Draw";}

    public int getDmg() {return -4;}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getId() {return "BLOODDRAW";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}
}