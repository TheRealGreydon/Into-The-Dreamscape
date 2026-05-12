package APCS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameGUI extends JFrame implements ActionListener
{
    private int x;
    private Player character;
    public GameGUI(Player character, int x) {this.character = character; this.x = x;}
    private LevelGUI lGUI = new LevelGUI();
    private JPanel lPanel;

    private BackgroundPanel a = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage(), 1);

    public void imgSwap(int x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage());}

    public void imgSwap(String x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + x + ".jpg").getImage());}

    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}

    public void select(int x) {String temp=String.valueOf(x) + "0";imgSwap(temp);}

    public void start(Player x)
    {
        setChar(x);
        System.out.println(character.toString());
        lGUI.setChar(character);
        lPanel = lGUI.lPanel;
        close();
        initialize();
        displayGame();
        this.add(lPanel);
    }

    public void setChar(Player z) {character = z;}

    public void displayGame() {java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}

    private void initialize()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Into the Dreamscape");
        this.setVisible(true);
        this.setResizable(true);
    }
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}

    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
    }
}
