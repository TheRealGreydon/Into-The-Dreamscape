package APCS.GUIs;

import java.awt.*;
import javax.swing.*;
import APCS.Assets.AssetClasses.*;
import APCS.*;

public class LevelGUI extends JFrame
{
    private int x;
    private Player character;
    public JPanel lPanel;

    public void setChar(Player z)
    {
        character = z;
        x = character.getCurLev();
        lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Battle Level/BattleBGLarge.png").getImage(), 0);
        //lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage(), 1);
    }
}
