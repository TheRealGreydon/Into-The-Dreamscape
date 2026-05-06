package APCS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameGUI extends JFrame implements ActionListener
{
    private JPanel gPanel;
    private Player character;
    private BackgroundPanel b;

    public GameGUI(BackgroundPanel b)
    {
        this.b=b;
        gPanel = new JPanel();
        gPanel = b;
        gPanel.setLayout(new GridBagLayout());
    }

    public void actionPerformed(ActionEvent e)
    {
        //the source of the button click
        JButton j = (JButton)(e.getSource());
    }
}
