package APCS.Uses.Actions.Skills;

public class block implements Skl
{
    public String getName() {return "Block";}

    public String getId() {return "BLOCK";}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}

    public String getDis() 
    {
        return 
        """
                                                                Rage
                                    -----------------------------------------------
                                 Take less and deal more damage
                                 Cannot activate again until enough 
                                 damage has been taken """;
    }
}
