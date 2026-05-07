package APCS;

import javax.swing.*;


public class GameGUI extends JFrame
{
    private Player character;

    private BackgroundPanel a = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu0.jpg").getImage(), 1);

    public GameGUI(Player character) {this.character = character;}

    public void imgSwap(int x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage());}

    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}
}
