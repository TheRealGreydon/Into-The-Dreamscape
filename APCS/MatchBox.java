/**
 *Name: Mr. Klus	
 *Date: 05/01/2019    
 *Description:  Creates an instance DropGame, and calls
 *              methods to set up and play the game.
 */
package APCS;

public class MatchBox implements  RangAtk
{
    public String getName()
    {
        return "Match Box";
    }

    public String getDes()
    {
        return "Who gives a kid matches?? 10% to burn.";
    }

    public double accuracy()
    {
        return .5;
    }

    public int getDmg()
    {
        return 3;
    }

    public int getDmgType()
    {
        return 1;
    }

    public int status()
    {
        if((int)((Math.random()*10))==9)
        {
            return 2;
        }  
        return 0;
    }

    public boolean spc()
    {
        return true;
    }
}
