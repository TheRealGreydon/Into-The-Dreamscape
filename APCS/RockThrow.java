/**
 *Name: Mr. Klus	
 *Date: 05/01/2019    
 *Description:  Creates an instance DropGame, and calls
 *              methods to set up and play the game.
 */
package APCS;

public class RockThrow  implements RangAtk
{
    public String getName()
    {
        return "Rock Throw";
    }

    public String getDes()
    {
        return "Throws a rock. Hard. 20% chance to bludgen.";
    }

    public double accuracy()
    {
        return .75;
    }

    public int getDmg()
    {
        return 2;
    }

    public int getDmgType()
    {
        return 3;
    }

    public int status()
    {
        if((int)((Math.random()*5))==4)
        {
            return 1;
        }  
        return 0;
    }

    public boolean spc()
    {
        return false;
    }
}
