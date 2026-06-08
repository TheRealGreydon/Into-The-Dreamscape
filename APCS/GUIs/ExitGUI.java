package APCS.GUIs;

import APCS.Assets.AssetClasses.*;
import APCS.Player;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class ExitGUI extends JFrame
{
    private Player character;
    private JPanel iPanel;
    private JLabel blinky;
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;

    public ExitGUI(Player character, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        initialize();
    }
    private void initialize() 
    {
        kronk.pack();
        kronk.setSize(1500, 800);
        instInit();
        kronk.add(iPanel);
        keyActions();
    }
    private void instInit()
    {
        iPanel.setLayout(null);

        JLabel title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Welcome to Into the Dreamscape");
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750-title.getWidth()/2, 50);

        JTextPane cred = new JTextPane();
        cred.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
       
        cred.setText("""
                        Arrow keys for Up, Down, Left, Right
                        Enter to select, I to show info

                        Enter the Dreamscape and defeat 
                            your nighmares
                            
                        Please give us a 100% Mr. Klus :)""");

        cred.setBackground(Color.white);
        cred.setFont(new Font(cred.getFont().getName(), Font.BOLD, 40));
        cred.setForeground(Color.BLACK);      
        cred.setSize(cred.getPreferredSize());
        cred.setSize(cred.getWidth()+10, cred.getHeight());
        cred.setLocation(750-cred.getWidth()/2, 200);
        cred.setEditable(false);
        
        blinky = new JLabel();
        blinky.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        blinky.setForeground(Color.black);
        blinky.setOpaque(true);
        blinky.setBackground(Color.red);
        blinky.setFont(new Font(blinky.getFont().getName(), Font.BOLD, 30));
        blinky.setText("Press Enter");
        blinky.setSize(200,100);
        blinky.setLocation(650,650);
        blinky.setHorizontalAlignment(SwingConstants.CENTER);
        blinky.setVerticalAlignment(SwingConstants.CENTER);
        
        iPanel.add(blinky);
        iPanel.add(title);
        iPanel.add(cred);

        paint(iPanel, 5);

        new Timer().scheduleAtFixedRate(new TimerTask() {public void run() {blinky.setVisible(!blinky.isVisible());}}, 500, 500);
    }

    private void keyActions()
    {
        iPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        iPanel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {sel();}});
    }
    private void sel() 
    {
        kronk.remove(iPanel);
        new AniGUI(character, kronk).introAnimation();
    }

    private void paint(JPanel x, int percent)
    {
        for (Component c : x.getComponents()) {x.setComponentZOrder(c, 0);}
        for(int i=0; i<40; i++)
        {
            for(int j=0; j<75; j++)
            {
                if(j<18 || j>56)
                {
                    JLabel star = new JLabel();
                    star.setOpaque(true);
                    star.setBackground(Color.BLACK);
                    star.setSize(new Dimension(20, 20));
                    star.setLocation(j*20, i*20);
                    x.setComponentZOrder(star, 1);
                    x.add(star);
                    
                    if((int)(Math.random()*100)<=percent-1)
                    {
                        if((int)(Math.random()*2)==0) {star.setBackground(Color.YELLOW);}

                        else {star.setBackground(new Color(189,185,38));}                    
                    }
                }
            }
        }
    }
}
