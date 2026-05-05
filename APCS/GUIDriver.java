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
        String pathMB="APCS/Assets/Background Img/CelesteBackTEMP.jpg";
        String pathSB="APCS/Assets/Default Img/defaultBack.jpg";        
        
        BackgroundPanel a = new BackgroundPanel(new ImageIcon(pathMB).getImage(), 1);
        BackgroundPanel b = new BackgroundPanel(new ImageIcon(pathSB).getImage(), 1);

        GUI gui = new GUI(m,a,b);
        gui.displayGame();
    }

}
