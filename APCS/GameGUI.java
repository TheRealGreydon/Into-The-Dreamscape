package APCS;

import java.awt.*;
import javax.swing.*;


public class GameGUI extends JFrame
{
    private JPanel gPanel;

    private Player character;

    String path="APCS/Assets/Default Img/defaultLevelMenuA.jpg";        
    private BackgroundPanel b = new BackgroundPanel(new ImageIcon(path).getImage(), 1);

    private int levSel = 0;

    public GameGUI(Player character)
    {
        this.character = character;
        gPanel = new JPanel();
        gPanel = b;
        gPanel.setLayout(new GridBagLayout());
    }

    public JPanel gamePan()
    {
        return gPanel;
    }
}
