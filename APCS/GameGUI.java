package APCS;

import javax.swing.*;


public class GameGUI extends JFrame
{
    private Player character;

    String path="APCS/Assets/Default Img/defaultLevelMenuA.jpg";        
    private BackgroundPanel a = new BackgroundPanel(new ImageIcon(path).getImage(), 1);

    String path2="APCS/Assets/Default Img/defaultBack.jpg";        
    private BackgroundPanel b = new BackgroundPanel(new ImageIcon(path2).getImage(), 1);

    public GameGUI(Player character) {this.character = character;}

    public JPanel gamePan()
    {
        JPanel gPanel = a;
        return gPanel;
    }
}
