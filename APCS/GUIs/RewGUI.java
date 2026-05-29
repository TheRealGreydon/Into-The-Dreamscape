package APCS.GUIs;

import APCS.Assets.AssetClasses.*;
import APCS.Items.Itm;
import APCS.Items.AttackItem.swordITM;
import APCS.Items.SkillItem.grilledCheeseITM;
import APCS.Player;
import APCS.Actions.Attacks.Atk;
import APCS.Actions.Attacks.punch;
import APCS.Actions.Attacks.smack;
import APCS.Actions.Attacks.widePunch;
import APCS.Actions.Skills.Skl;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class RewGUI extends JFrame implements ActionListener
{
    

    private Player character;
    private BattleAssets bGUI = new BattleAssets();
    private JPanel rPanel;
    private JButton pau = new JButton(), set = new JButton(), exit = new JButton(), settings = new JButton(), vole = new JButton();
    private boolean paused = false;
    private JLabel tText = new JLabel(), charImg, chestImg;
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private int count = 0;
    private BackgroundPanel a;

    public RewGUI(Player character, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;
        a = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
    }

    public void initialize() 
    {
        rPanel = a;
        paint(5);
        kronk.pack();
        rPanel.setLayout(null);
        kronk.setSize(1500, 800);

        imgInit();

        kronk.add(rPanel);
        pauButtons();
        keyActions();
    }

    

    private void imgInit()
    {
        charInit();
        chestInit();
    }

    private void charInit()
    {
        charImg = bGUI.spriteImg(character);
        charImg.setLocation(210,150);
        rPanel.add(charImg);
    }
    private void chestInit()
    {
        chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Enemies/Jar.gif").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        chestImg.setSize(300,300);
        chestImg.setLocation(600,300);
        rPanel.add(chestImg);
    }

    private void select() 
    {
        if(charImg.getX()>=350 && charImg.getX()<=660)
        {
            //Implement animation later
            randRew();   
        }
    }

    private void randRew()
    {
        int x = (int)(Math.random()*3);
        if(x==0)
        {
            //If the player has all Atks add an item
            if(randAtk()!=null)
            {
                //If player doesnt have 4 atks
                if(!character.AtkF()) {character.AtkA(randAtk());}

                else 
                {

                }
            }

            else
            {
                randItm();
            }
        }
        else if(x==1)
        {
            //If the player has all Skls add an item
            if(randSkl()!=null)
            {
                //If player doesnt have 4 skls
                if(!character.SklF()) {character.SklA(randSkl());}

                else 
                {

                }
            }
            else
            {
                randItm();
            }
        }
        else
        {
            //If player doesnt have 4 itms
            if(!character.ItmF()) {character.ItmA(randItm());}

            else 
            {

            }
        }
    }

    private Atk randAtk()
    {
        Atk rands[] = {};
        int x = ((int)(Math.random()*rands.length));

        for(int i=0; i<rands.length; i++)
        {
            boolean has = false;

            for(int j=0; j<4; j++) {has = character.atks[i].equals(rands[x]);}

            if(!has) {return rands[x];}

            if(x+1>=rands.length) {x = 0;}
            
            else{x++;}
        }
        return null;
    }
    private Skl randSkl()
    {
        Skl rands[] = {};
        int x = ((int)(Math.random()*rands.length));

        for(int i=0; i<rands.length; i++)
        {
            boolean has = false;

            for(int j=0; j<4; j++) {has = character.skls[i].equals(rands[x]);}

            if(!has) {return rands[x];}

            if(x+1>=rands.length) {x = 0;}

            else{x++;}
        }
        return null;
    }
    private Itm randItm() {Itm rands[] = {new grilledCheeseITM(), new swordITM()};return rands[((int)(Math.random()*rands.length))];}

    private void full(Atk x)
    {
        JPanel fPanel = new JPanel();
        JLabel atksB[] = new JLabel[4];

        for(int i=5;i<9; i++)
        {
            atksB[i] = new JLabel();
            atksB[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 20));
            atksB[i].setBackground(new Color(43,18,204));
            fPanel.add(atksB[i]);
        }

        atksB[0].setLocation(550,150);
        atksB[1].setLocation(750,150);
        atksB[2].setLocation(550,350);
        atksB[3].setLocation(750,350);

        JLabel title = new JLabel(); 
        title.setText("Select an attack to replace");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);
        
        fPanel.add(title);
        
        paint(5);

        kronk.remove(rPanel);   
    }

    //Loading screen
    private void loadScreen()
    {
        kronk.remove(rPanel);
        rPanel.removeAll();
        rPanel = new JPanel();
        rPanel.setBackground(Color.black);
        kronk.pack();
        rPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.add(rPanel);
        timedText("Level " + (character.getLevel()+1) + " - " + (character.getStage()+1));
    }

    //Scrolling text
    private void timedText(String a)
    {
        count = 0;
        Timer timer = new Timer();
        String [] b = a.split("");
        tText.setText("");
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        TimerTask task = new TimerTask()
        {public void run() 
            {
                for (Component c : rPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                if(!paused)
                {
                    if(count<b.length) {tText.setText(tText.getText() + b[count]);tText.setSize(tText.getPreferredSize());
                    tText.setLocation(rPanel.getWidth()/2 - tText.getWidth()/2, rPanel.getHeight()/2-tText.getHeight()/2);
                    rPanel.add(tText);}
                    else{
                        if(count>b.length+15) 
                        {
                            rPanel.remove(tText);rPanel.repaint();
                            for (Component c : rPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}
                            timer.cancel();kronk.remove(rPanel);new GameGUI(character,kronk);
                        }
                    }count++;  
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 50);        
    }

    //Pause and settings
    private void pauButtons()
    {   
        set.setVisible(false);
        set.setSize(new Dimension(5000,5000));
        pau.setSize(new Dimension(5000,5000));
        pau.setEnabled(false);pau.setVisible(false);pau.setForeground(Color.white);pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 30));pau.setText("Paused"); 
        exit.setBackground(new Color(0,0,0));settings.setBackground(new Color(0,0,0));pau.setBackground(new Color(0,0,0,200));
        exit.setSize(new Dimension(200,100));settings.setSize(new Dimension(200,100));exit.setEnabled(false);exit.setVisible(false);exit.setForeground(Color.white);
        exit.setFont(new Font(exit.getFont().getName(), Font.BOLD, 40));exit.setText("Exit");settings.setEnabled(false);settings.setVisible(false);settings.setForeground(Color.white);
        settings.setFont(new Font(settings.getFont().getName(), Font.BOLD, 40));settings.setText("Settings");settings.addActionListener(this);exit.addActionListener(this);this.setLocationRelativeTo(null);
        settings.setFocusable(false);
        exit.setFocusable(false);
    }
    private void pause()
    {  
        if(paused) 
        {
            paused=false;
            settings.setVisible(false);settings.setEnabled(false);
            pau.setVisible(false);
            exit.setEnabled(false);exit.setVisible(false);
            set.setVisible(false);
            vole.setVisible(false);vole.setEnabled(false);
        }
        else 
        {
            paused=true;
            pau.setForeground(Color.white);
            pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));pau.setText("Paused");pau.setBackground(new Color(0,0,0,200));
            pau.setLocation(rPanel.getWidth()/2-2500, rPanel.getHeight()/2-2650);
            settings.setText("Settings");
            rPanel.add(settings);rPanel.add(exit);rPanel.add(pau);
            exit.setLocation((rPanel.getWidth()/2)-100, (rPanel.getHeight()/2));
            settings.setLocation((rPanel.getWidth()/2-100), (rPanel.getHeight()/2-100));settings.setVisible(true);settings.setEnabled(true);
            exit.setVisible(true);exit.setEnabled(true);exit.setText("Exit");
            pau.setVisible(true);
        }
    }    
    private void settings()
    {
        pau.setVisible(false);set.setEnabled(false);set.setForeground(Color.white);set.setVisible(true);set.setBackground(Color.BLACK);
        set.setFont(new Font(set.getFont().getName(), Font.BOLD, 40));set.setText("Settings"); 
        exit.setText("Back");settings.setText("Volume");settings.setEnabled(false);
        vole.setFont(new Font(vole.getFont().getName(), Font.BOLD, 40));vole.addActionListener(this);vole.setSize(new Dimension(125,100));vole.setBackground(Color.black);
        vole.setForeground(Color.white);vole.setLocation((rPanel.getWidth()/2-50)+150, (rPanel.getHeight()/2-100));vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(character.getVol()));
        rPanel.add(vole);set.setLocation(rPanel.getWidth()/2-2500, rPanel.getHeight()/2-2650);rPanel.add(set);
        vole.setFocusable(false);
    }
    
    //Keybinds and buttons
    private void keyActions() 
    {
        rPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        rPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        rPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");
        rPanel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {{if(charImg.getX()>=220) {charImg.setLocation(charImg.getX()-10, charImg.getY());}};}});
        rPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Right");
        rPanel.getActionMap().put("Right", new AbstractAction() {public void actionPerformed(ActionEvent e) {{if(charImg.getX()<=800){charImg.setLocation(charImg.getX()+10, charImg.getY());}};}});
        rPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        rPanel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {select();}});
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(character.getVol()+25>100) {character.setVol(0);} else {character.setVol(character.getVol() + 25);} vole.setText(String.valueOf(character.getVol()));}
    }
    
    //Paints a JPanel with a percentage of stars randomly
    private void paint(int percent)
    {
        for (Component c : rPanel.getComponents()) {rPanel.setComponentZOrder(c, 0);}
        for(int i=0; i<40; i++)
        {
            for(int j=0; j<18; j++)
            {
                JLabel star = new JLabel();
                star.setOpaque(true);
                star.setSize(new Dimension(20, 20));
                star.setLocation(j*20, i*20);
                
                if((int)(Math.random()*100) <= percent-1) {if((int)(Math.random()*2)==0) {star.setBackground(Color.YELLOW);}else {star.setBackground(new Color(189,185,38));}}

                else {star.setBackground(Color.BLACK);}

                rPanel.add(star);
            }

            for(int j=57; j<75; j++)
            {
                JLabel star = new JLabel();
                star.setOpaque(true);
                star.setSize(new Dimension(20, 20));
                star.setLocation(j*20, i*20);
                
                if((int)(Math.random()*100) <= percent-1) {if((int)(Math.random()*2)==0) {star.setBackground(Color.YELLOW);}else {star.setBackground(new Color(189,185,38));}}

                else {star.setBackground(Color.BLACK);}

                rPanel.add(star);
            }
        }
    }

    private void exit() {if(!settings.getText().equals("Volume")){kronk.dispose();} else{pause();}}
}
