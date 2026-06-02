package APCS.Actions.Attacks;

public class vampSword implements Atk
{

    public String getName() {return "Vamp Blade";}

    public int getDmg() {return -10;}

    public boolean swing() {return false;}

    public int acur() {return 90;}

    public String getId() {return "VAMPBLADE";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}
}