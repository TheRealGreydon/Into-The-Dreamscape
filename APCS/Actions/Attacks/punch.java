package APCS.Actions.Attacks;

public class punch implements Atk
{

    public String getName() {return "Punch";}

    public int getDmg() {return -3;}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getDis() {return "PUNCH";}
}