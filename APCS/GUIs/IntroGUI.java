package APCS.GUIs;

import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Assets.AssetClasses.*;
import APCS.Items.Itm;
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
    private JPanel iPanel,fPanel;
    private JLabel tText = new JLabel(), charImg, chestImg, title, inst, blinky;
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private int mode,count = 0, KBV = 0, KBH = 0;
    private Atk selAtk;
    private Skl selSkl;
    private Itm selItm;
    private String y [];

    public IntroGUI(Player character, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
    }

    public void initialize() 
    {
        kronk.pack();
        iPanel.setLayout(null);
        kronk.setSize(1500, 800);
        instInit();
        kronk.add(iPanel);
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
                                  Into the Dreamscape
                        ------------------------------------------------
                        Main Story: Kai
                        Main Code: Nicholas
                        Literaly all the Code: Nicholas
                        4 all nighters: Nicholas
                        Art: Kai
                        Please give us a 100% Mr. Klus :)""");

        cred.setBackground(Color.white);
        cred.setFont(new Font(cred.getFont().getName(), Font.BOLD, 40));
        cred.setForeground(Color.BLACK);      
        cred.setSize(cred.getPreferredSize());
        cred.setSize(cred.getWidth()+10, cred.getHeight());
        cred.setLocation(750-cred.getWidth()/2, 150);
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
                    iPanel.remove(charImg);
                    charImg = bGUI.battleImg(character);
                    charImg.setLocation(x);
                    iPanel.add(charImg);

                    iPanel.remove(chestImg);
                    chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.gif").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    chestImg.setSize(300,300);
                    chestImg.setLocation(600,300);
                    iPanel.add(chestImg);
                    iPanel.setComponentZOrder(chestImg,0);
                    iPanel.repaint();
                }
                count++;  
            }
        };
        timer.scheduleAtFixedRate(task, 0, 75);
    }

    //Opening the chest animation
    private void openAnimation()
    {
        charImg = bGUI.walkingImg(character);
        charImg.setLocation(210,150);
        iPanel.add(charImg);
        chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.png").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        chestImg.setSize(300,300);
        chestImg.setLocation(600,300);
        iPanel.add(chestImg);
        iPanel.setComponentZOrder(chestImg,0);
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
                    iPanel.remove(charImg);
                    charImg = bGUI.battleImg(character);
                    charImg.setLocation(x);
                    iPanel.add(charImg);

                    iPanel.remove(chestImg);
                    chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.gif").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                    chestImg.setSize(300,300);
                    chestImg.setLocation(600,300);
                    iPanel.add(chestImg);
                    iPanel.setComponentZOrder(chestImg,0);
                    iPanel.repaint();
                }
                
                else if(count==38) {timer.cancel();}

                count++;  
            }
        };
        timer.scheduleAtFixedRate(task, 0, 75);     
    }

    //Loading screen
    private void loadScreen()
    {
        character.saveGame();
        if(hasPanel(fPanel)) {kronk.remove(fPanel);}
        
        else {kronk.remove(iPanel);}

        iPanel.removeAll();
        iPanel = new JPanel();
        iPanel.setBackground(Color.black);
        kronk.pack();
        iPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.add(iPanel);
        timedText(" Level " + (character.getLevel()+1) + " - " + (character.getStage()+1));
    }
    private void timedText(String a)
    {
        count = 0;
        Timer timer = new Timer();
        String [] b = a.split("");
        if(mode !=-1)
        {
            switch (mode) 
            {
                case 0 -> {y = (" You got " + selAtk.getName()).split("");}
                case 1 -> {y = (" You got " + selSkl.getName()).split("");}
                default -> {y = (" You got " + selItm.getName()).split("");}
            } 
            tText.setText("");
            tText.setOpaque(true);
            tText.setBackground(Color.WHITE);
            tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
            TimerTask task = new TimerTask()
            {
                public void run() 
                {
                    if(count<y.length) 
                    {
                        tText.setText(tText.getText() + y[count]);tText.setSize(tText.getPreferredSize());
                        tText.setLocation(iPanel.getWidth()/2 - tText.getWidth()/2, iPanel.getHeight()/2-tText.getHeight()/2);
                        iPanel.add(tText);
                    }

                    else if(count==y.length+5)
                    {
                        tText.setText("");
                    }

                    else if(count>y.length+5 && count<y.length+5+b.length)
                    {
                        tText.setText(tText.getText() + b[count-5-y.length]);tText.setSize(tText.getPreferredSize());
                        tText.setLocation(iPanel.getWidth()/2 - tText.getWidth()/2, iPanel.getHeight()/2-tText.getHeight()/2);
                        iPanel.add(tText);
                    }

                    else if(count>y.length + b.length + 20)
                    {
                        iPanel.remove(tText);iPanel.repaint();
                        for (Component c : iPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}
                        timer.cancel();kronk.remove(iPanel);new GameGUI(character,kronk);
                    }
                    count++;  
                }
            };
            timer.scheduleAtFixedRate(task, 0, 50);     
        }   
        
        else
        {
            tText.setText("");
            tText.setOpaque(true);
            tText.setBackground(Color.WHITE);
            tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
            TimerTask task = new TimerTask()
            {
                public void run() 
                {
                    if(count<b.length) 
                    {
                        tText.setText(tText.getText() + b[count]);tText.setSize(tText.getPreferredSize());
                        tText.setLocation(iPanel.getWidth()/2 - tText.getWidth()/2, iPanel.getHeight()/2-tText.getHeight()/2);
                        iPanel.add(tText);
                    }
                
                    else if(count>b.length + 15)
                    {
                        iPanel.remove(tText);iPanel.repaint();
                        for (Component c : iPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}
                        timer.cancel();kronk.remove(iPanel);new GameGUI(character,kronk);
                    }
                    count++;  
                }
            };
            timer.scheduleAtFixedRate(task, 0, 50);     
        }
    }

    private void fullKeyActions()
    {
        fPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        fPanel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {sel();}});
    }

    private void sel()
    {

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
    private boolean hasPanel(JPanel targetPanel) 
    {
        Component[] components = kronk.getContentPane().getComponents();
        
        for (Component comp : components) {if (comp == targetPanel) {return true;}}
        return false;
    }
}
