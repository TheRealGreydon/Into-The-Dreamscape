package APCS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GameGUI extends JFrame implements ActionListener
{
    private JPanel gPanel;
    private Player character;
    String path="APCS/Assets/Default Img/defaultBack.jpg";        
    private BackgroundPanel b = new BackgroundPanel(new ImageIcon(path).getImage(), 1);

    public GameGUI()
    {
        gPanel = new JPanel();
        gPanel = b;
        gPanel.setLayout(new GridBagLayout());
    }

    public JPanel gamePan()
    {
        return gPanel;
    }

    public void actionPerformed(ActionEvent e)
    {
        //the source of the button click
        JButton j = (JButton)(e.getSource());
    }
}
