package APCS.Actions.Attacks;

public class smack implements Atk
{

    public String getName() {return "Basic Attack";}

    public int getDmg() {return (int)(Math.random()*-3 - 1);}

    public boolean swing() {return false;}

    public int acur() {return 75;}

    public String getDis() {return "SMACK";}
}