package APCS;

import javax.swing.*;


public class GameGUI extends JFrame
{
    private Player character;
    private int x;

    public GameGUI(Player character, int x) {this.character = character;this.x=x;}

    private BackgroundPanel a = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage(), 1);

    public void imgSwap(int x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage());}

    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}
}
