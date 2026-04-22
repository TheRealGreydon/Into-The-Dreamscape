/**
 *Name: Mr. Klus	
 *Date: 05/01/2019    
 *Description:  Creates an instance DropGame, and calls
 *              methods to set up and play the game.
 */
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
