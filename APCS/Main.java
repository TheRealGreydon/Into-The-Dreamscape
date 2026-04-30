/**
 *Name: Mr. Klus	
 *Date: 05/01/2019    
 *Description:  Creates an instance DropGame, and calls
 *              methods to set up and play the game.
 */
package APCS;

public class Main
{
    public static void main(String[] args)
    {
        String name="Default";
        int gend=0;
        int outfit=0;
        int fav=1;

        Player p = new Player(name, gend, outfit, fav);

        p.setName("NDefault");
        System.out.println(p.toString());
    }
}
