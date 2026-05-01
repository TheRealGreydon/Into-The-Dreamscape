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
        Main m = new Main();
        String path="APCS/CelesteBackTEMP.jpg";

        Image image = new ImageIcon(path).getImage();
        
        BackgroundPanel b = new BackgroundPanel(image, 1);

        GUI gui = new GUI(m,b);
        gui.displayGame();
    }

}
