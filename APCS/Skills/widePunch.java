package APCS.Skills;

public class widePunch implements Atk
{

    public String getName() {return "Wide Punch";}

    public String getDes() {return "Punch ALL the guy.";}

    public int getDmg() {return -3;}

    public int getDmgType() {return 1;}

    public int status() {return 0;}

    public boolean swing() {return true;}

    public int acur() {return 100;}
}