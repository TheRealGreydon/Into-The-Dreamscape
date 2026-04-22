/*Name:	Mr. Klus
 *Date: 05/01/2019
 *Description: Creates a new Drop Game and GUI to play the game.
 */
package APCS;

public class GUIDriver
{

    public static void main(String[] args)
    {
        Main d = new Main();
        GUI gui = new GUI(d);
        gui.displayGame();
    }

}
