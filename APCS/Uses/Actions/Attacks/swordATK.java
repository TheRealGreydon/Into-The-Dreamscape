package APCS.Uses.Actions.Attacks;

public class swordATK implements Atk
{

    public String getName() {return "Sword";}

    public int getDmg() {return -5;}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getId() {return "SWORDATK";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() {return "";}
}