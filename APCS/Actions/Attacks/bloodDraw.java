package APCS.Actions.Attacks;

public class bloodDraw implements Atk
{

    public String getName() {return "Blood Draw";}

    public int getDmg() {return -4;}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getDis() {return "BLOODDRAW";}
}