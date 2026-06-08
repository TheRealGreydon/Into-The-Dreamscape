package APCS.Uses.Actions.Attacks;

public class gamble implements Atk
{
    public String getName() {return "Gamble Slash";}

    public int getDmg() {return (((int)(Math.random()*31)-5)*-1);}

    public boolean swing() {return false;}

    public int acur() {return 100;}

    public String getId() {return "GAMBLE";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() 
    {
        return 
        """
                                                    Gambler's Slash
                             -------------------------------------------------------
                                Deals a random amount of damage
                                 to selected enemy, but may 
                                 also have a chance to heal them""";
    }
}