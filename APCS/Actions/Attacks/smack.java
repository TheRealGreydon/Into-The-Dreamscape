package APCS.Actions.Attacks;

public class smack implements Atk
{

    public String getName() {return "Smack";}

    public int getDmg() {return (int)(Math.random()*-3 - 1);}

    public boolean swing() {return false;}

    public int acur() {return 75;}

    public String getId() {return "SMACK";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() 
    {
        return 
        """
                                            Smack
                             ---------------------------------------
                                What do you think it does?""";
    }
}