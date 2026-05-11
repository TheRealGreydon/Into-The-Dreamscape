package APCS;

import java.awt.*;
import javax.swing.*;

public class LevelGUI extends JFrame
{
    private int x;
    private Player character;
    public LevelGUI(Player y, int x) {character = y; this.x = x;}

    public void setChar(Player z)
    {
        character = z;
        x = z.getCurLev();
        System.out.println(z.getCurLev() + " " + x);
    }

    public JPanel lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage(), 1);

    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
}
