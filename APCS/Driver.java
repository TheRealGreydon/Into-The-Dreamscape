/**
 *Name: Mr. Klus	
 *Date: 05/01/2019    
 *Description:  Creates an instance DropGame, and calls
 *              methods to set up and play the game.
 */
package APCS;

public class Driver
{
    public static void main(String[] args)
    {
        Main m = new Main();

        String name="Default";
        int gend=0;
        int outfit=0;
        int fav=0;

        Player p = new Player(name, gend, outfit, fav);

        p.setName("NDefault");
    }
}
