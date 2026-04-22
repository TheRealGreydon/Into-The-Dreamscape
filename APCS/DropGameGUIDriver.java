/*Name:	Mr. Klus
 *Date: 05/01/2019
 *Description: Creates a new Drop Game and GUI to play the game.
 */
package APCS;

public class DropGameGUIDriver
{

    public static void main(String[] args)
    {
        DropGame d = new DropGame();
        DropGameGUI gui = new DropGameGUI(d);
        gui.displayGame();
    }

}
