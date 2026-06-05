package APCS.GUIs;

import APCS.Assets.AssetClasses.*;
import APCS.Player;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class IntroGUI extends JFrame
{
    private Player character;
    private BattleAssets bGUI = new BattleAssets();
    private JPanel iPanel,aPanel;
    private JLabel tText = new JLabel(), charImg, chestImg, blinky;
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private int count = 0, loop = 0;
    private String [] b;

    public IntroGUI(Player character, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        aPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
    }
    public void initialize() 
    {
        kronk.pack();
        kronk.setSize(1500, 800);
        instInit();
        kronk.add(iPanel);
        keyActions();
    }
    private void anPanInit()
    {
        aPanel.setLayout(null);
        imgInit();
        paint(aPanel,5);
    }
    private void imgInit()
    {
        charImg = bGUI.walkingImg(character);
        charImg.setLocation(210,150);
        aPanel.add(charImg);
        chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.png").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        chestImg.setSize(300,300);
        chestImg.setLocation(600,300);
        aPanel.add(chestImg);
        aPanel.setComponentZOrder(chestImg,0);
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
    
    //Runsafter intro animation
    private void animation()
    {
        count = 0;
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run() 
            {
                if(count >2 && count<18)
                {
                        charImg.setLocation(charImg.getX()+10, charImg.getY());
                }

                else if(count==18)
                {
                    Point x = new Point(charImg.getLocation());
                    aPanel.remove(charImg);
                    charImg = bGUI.battleImg(character);
                    charImg.setLocation(x);
                    aPanel.add(charImg);

                    aPanel.remove(chestImg);
                    chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.gif").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    chestImg.setSize(300,300);
                    chestImg.setLocation(600,300);
                    aPanel.add(chestImg);
                    aPanel.setComponentZOrder(chestImg,0);
                    aPanel.repaint();
                }
                
                else if(count==39) {timer.cancel();loadScreen();}

                count++;  
            }
        };
        timer.scheduleAtFixedRate(task, 0, 75);     
    }
    private void loadScreen()
    {
        kronk.remove(aPanel);

        aPanel.removeAll();
        aPanel = new JPanel();
        aPanel.setBackground(Color.black);
        kronk.pack();
        aPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.add(aPanel);
        timedText();
    }
    private void timedText()
    {
        count = 0;
        Timer timer = new Timer();
        b = (" Welcome to the Dreamscape").split("");
        tText.setText("");
        tText.setLocation(0,0);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));

        TimerTask task = new TimerTask()
        {   
            public void run() 
            {
                if(count<b.length) 
                {
                    aPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                    tText.setSize(tText.getPreferredSize());
                    tText.setSize(tText.getWidth()+10, tText.getHeight());
                    tText.setLocation(750-tText.getWidth()/2, 400-tText.getHeight()/2);
                    aPanel.add(tText);aPanel.repaint();count++;
                }

                else if(count >= b.length+10)
                {
                    if(loop!=2)
                    {
                        aPanel.remove(tText);tText.setText("");
                        tText.setSize(tText.getPreferredSize());
                        tText.setSize(tText.getWidth()+10, tText.getHeight());
                        aPanel.add(tText);aPanel.repaint();
                        count = 0;loop++;
                        if(loop==1) {b = (" Face your fears").split("");}

                        else {b = (" Level " + (character.getLevel()+1) + " - " + (character.getStage()+1)).split("");}
                    }

                    else {timer.cancel();kronk.remove(aPanel);new GameGUI(character, kronk);}
                }

                else {count++;}
            }
        };

        aPanel.add(tText);
        timer.scheduleAtFixedRate(task, 0, 100);
    }

    private void keyActions()
    {
        iPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        iPanel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {sel();}});
    }
    private void sel() 
    {
        anPanInit();
        panSet(iPanel, aPanel);
        animation();
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
                    if((int)(Math.random()*100)<=percent-1)
                    {
                        if((int)(Math.random()*2)==0)
                        {
                            JLabel star = new JLabel();
                            star.setOpaque(true);
                            star.setBackground(Color.YELLOW);
                            star.setSize(new Dimension(20, 20));
                            star.setLocation(j*20, i*20);
                            x.setComponentZOrder(star, 1);
                            x.add(star);
                        }
                        else
                        {
                            JLabel star = new JLabel();
                            star.setOpaque(true);
                            star.setBackground(new Color(189,185,38));
                            star.setSize(new Dimension(20, 20));
                            star.setLocation(j*20, i*20);
                            x.setComponentZOrder(star, 1);
                            x.add(star);
                        }                    
                    }
                    else
                    {
                        JLabel star = new JLabel();
                        star.setOpaque(true);
                        star.setBackground(Color.BLACK);
                        star.setSize(new Dimension(20, 20));
                        star.setLocation(j*20, i*20);
                        x.setComponentZOrder(star, 1);
                        x.add(star);
                    }
                }
            }
        }
    }
    private void panSet(JPanel x, JPanel y) {kronk.remove(x);kronk.add(y);y.revalidate();y.repaint();}
}
