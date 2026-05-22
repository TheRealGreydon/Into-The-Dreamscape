package APCS.Actions.Attacks;

public class bAtk implements Atk
{
    public int getDmg() {return -5;}

    public boolean swing() {return false;} 
    
    public int acur() {return 100;}

    public String getName() {return "bAtk";}

    public String getDis() {return "BATK";}
}