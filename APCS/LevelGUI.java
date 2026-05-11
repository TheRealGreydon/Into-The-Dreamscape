package APCS;

import java.awt.*;
import javax.swing.*;

public class LevelGUI extends JFrame
{
    private Player character;
    private int x;

    public LevelGUI(Player character, int x) {this.character = character;this.x=x;}

    private BackgroundPanel a = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage(), 1);

    public void imgSwap(int x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage());}

    public void imgSwap(String x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + x + ".jpg").getImage());}

    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}

    public void select(int x) {String temp=String.valueOf(x) + "0";imgSwap(temp);}

    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
}
