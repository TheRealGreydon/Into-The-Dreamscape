package APCS.Actions.Skills;

public class grilledCheeseSKL implements healSkl
{
    public String getName() {return "Grilled Cheese";}

    public int getHeal() {return ((int)(Math.random()*3 + 2));}

    public boolean isHeal() {return true;} public boolean isDmg() {return false;}

    public String getDis() {return "GRILLEDCHEESESKL";}
}
