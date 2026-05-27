package APCS.Actions.Attacks;

public class winPunch implements Atk
{
    public String getName() {return "Win";}

    public int getDmg() {return -100;}

    public boolean swing() {return true;}

    public int acur() {return 100;}

    public String getId() {return "WINPUNCH";}
}