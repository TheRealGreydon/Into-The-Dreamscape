package APCS.Actions.Attacks;

public class bEAtk implements Atk
{
    public String getName() {return "Basic Attack ";}

    public String getDes() {return "Basic Attack";}

    public int getDmg() {return -2;}

    public int getDmgType() {return 1;}

    public int status() {return 0;}

    public boolean spc() {return false;}   

    public boolean swing() {return false;}

    public int acur() {return 100;}
}