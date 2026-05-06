package APCS;

public class PartyPopper  implements RangAtk
{
    public String getName()
    {
        return "Party Popper";
    }

    public String getDes()
    {
        return "Festive! 25% to scare opponent.";
    }

    public double accuracy()
    {
        return .75;
    }

    public int getDmg()
    {
        return 1;
    }

    public int getDmgType()
    {
        return 4;
    }

    public int status()
    {
        if((int)((Math.random()*4))==3)
        {
            return 3;
        }  
        return 0;
    }

    public boolean spc()
    {
        return true;
    }
}
