/*Name:	Mr. Klus
 *Date: 05/01/2019
 *Description: Creates a new Drop Game and GUI to play the game.
 */
package APCS;

import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;

public class GUIDriver
{

    public static void main(String[] args)
    {
        GUI gui = new GUI("x");
        gui.displayGame();
    }

}
