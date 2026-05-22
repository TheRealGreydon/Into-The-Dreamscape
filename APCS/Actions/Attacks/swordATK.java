package APCS.Actions.Attacks;

public class swordATK implements Atk
{

    public String getName() {return "Sword";}

    public int getDmg() {return -5;}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getDis() {return "SWORDATK";}
}