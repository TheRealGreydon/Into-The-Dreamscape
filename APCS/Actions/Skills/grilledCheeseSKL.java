package APCS.Actions.Skills;

public class grilledCheeseSKL implements healSkl
{
    public String getName() {return "Grilled Cheese";}

    public int getHeal() {return ((int)(Math.random()*3 + 2));}

    public String getId() {return "GRILLEDCHEESESKL";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}
}
