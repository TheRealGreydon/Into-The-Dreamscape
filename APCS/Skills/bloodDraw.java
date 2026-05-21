package APCS.Skills;

public class bloodDraw implements Atk
{

    public String getName() {return "Blood Draw";}

    public String getDes() {return "Owwie";}

    public int getDmg() {return -4;}

    public int getDmgType() {return 1;}

    public int status() {return 0;}

    public boolean swing() {return false;}

    public int acur() {return 100;}
}