package APCS.Skills;

public class punch implements Atk
{

    public String getName() {return "Punch";}

    public String getDes() {return "Punch the guy.";}

    public int getDmg() {return -3;}

    public int getDmgType() {return 1;}

    public int status() {return 0;}

    public boolean swing() {return false;}

    public int acur() {return 100;}
}