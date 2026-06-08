package APCS.Assets.AssetClasses;

import APCS.Player;
import APCS.GUIs.*;
import java.awt.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class AniGUI extends JFrame
{
    private Player character;
    private BattleAssets bGUI = new BattleAssets();
    private JPanel aPanel;
    private JLabel tText = new JLabel(), charImg, chestImg;
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private int count = 0, loop = 0;
    private String [] b;

    public AniGUI(Player character, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;
        aPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        initialize();
    }

    public void exitAnimation()
    {
        charImg = bGUI.walkingImg(character);
        charImg.setLocation(210,150);
        aPanel.add(charImg);
        chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.png").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        chestImg.setSize(300,300);
        chestImg.setLocation(600,300);
        aPanel.add(chestImg);
        aPanel.setComponentZOrder(chestImg,0);
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
                
                else if(count==39) {timer.cancel();loadScreen(1);}

                count++;  
            }
        };
        timer.scheduleAtFixedRate(task, 0, 75);
    }

    public void introAnimation()
    {
        charImg = bGUI.walkingImg(character);
        charImg.setLocation(210,150);
        aPanel.add(charImg);
        chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.png").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        chestImg.setSize(300,300);
        chestImg.setLocation(600,300);
        aPanel.add(chestImg);
        aPanel.setComponentZOrder(chestImg,0);
        count = 0;
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {
            public void run() 
            {
                if(count >2 && count<18) {charImg.setLocation(charImg.getX()+10, charImg.getY());}

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
                
                else if(count==39) {timer.cancel();loadScreen(0);}

                count++;  
            }
        };
        timer.scheduleAtFixedRate(task, 0, 75);
    }

    private void initialize() 
    {
        aPanel.setLayout(null);
        paint(5);
        kronk.pack();
        kronk.setSize(1500, 800);
        kronk.add(aPanel);
    }
    
    private void loadScreen(int x)
    {
        kronk.remove(aPanel);

        aPanel.removeAll();
        aPanel = new JPanel();
        aPanel.setBackground(Color.black);
        kronk.pack();
        aPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.add(aPanel);
        timedText(x);
    }
    private void timedText(int x)
    {
        switch(x)
        {
            case 0 -> 
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

            case 1 ->
            {
                count = 0;
                Timer timer = new Timer();
                b = (" Thank you for playing").split("");
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
                            if(loop!=1)
                            {
                                aPanel.remove(tText);tText.setText("");
                                tText.setSize(tText.getPreferredSize());
                                tText.setSize(tText.getWidth()+10, tText.getHeight());
                                aPanel.add(tText);aPanel.repaint();
                                count = 0;loop++;
                                if(loop==1) {b = (" You have faced your fears").split("");}
                            }

                            else {timer.cancel();kronk.remove(aPanel);new MainGUI().end();}
                        }
                        else {count++;}
                    }
                };
                aPanel.add(tText);
                timer.scheduleAtFixedRate(task, 0, 100);
            }
        }
    }

    private void paint(int percent)
    {
        for (Component c : aPanel.getComponents()) {aPanel.setComponentZOrder(c, 0);}
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
                    aPanel.add(star);
                    
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
