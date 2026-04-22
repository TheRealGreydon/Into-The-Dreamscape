/**
 *Name: Mr. Klus	
 *Date: 05/01/2019    
 *Description:  Creates an instance DropGame, and calls
 *              methods to set up and play the game.
 */
package APCS;

public interface Atk extends Skills
{
    public int getDmg();

    public int getDmgType();

    public int status();

    public boolean ranged();
}
