package APCS;

public class WetTowel  implements Atk
{
    public String getName()
    {
        return "Wet Towel";
    }

    public String getDes()
    {
        return "Party Pooper.";
    }

    public int getDmg()
    {
        return 2;
    }

    public int getDmgType()
    {
        return 2;
    }

    public int status()
    {
        return 0;
    }

    public boolean spc()
    {
        return false;
    }
}
