package APCS.GUIs;

import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Assets.AssetClasses.*;
import APCS.Items.AttackItem.swordITM;
import APCS.Items.Itm;
import APCS.Items.SkillItem.cookieITM;
import APCS.Items.SkillItem.grilledCheeseITM;
import APCS.Items.SkillItem.milkITM;
import APCS.Player;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.Border;

public class RewGUI extends JFrame implements ActionListener
{
    

    private Player character;
    private BattleAssets bGUI = new BattleAssets();
    private JPanel rPanel,fPanel;
    private JButton pau = new JButton(), set = new JButton(), exit = new JButton(), settings = new JButton(), vole = new JButton();
    private boolean paused = false;
    private JButton buttons [];
    private JLabel tText = new JLabel(), charImg, chestImg, title;
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private int mode,count = 0, KBV = 0, KBH = 0;
    private int KB [][] = {{0,1},{2,3},{4,5}};
    private Atk selAtk;
    private Skl selSkl;
    private Itm selItm;
    private String y [];

    public RewGUI(Player character, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;
        rPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
    }

    public void initialize() 
    {
        kronk.pack();
        rPanel.setLayout(null);
        kronk.setSize(1500, 800);
        imgInit();
        paint(rPanel,5);
        kronk.add(rPanel);
        pauButtons();
        keyActions();
    }

    //Makes character and chest imgs
    private void imgInit()
    {
        charImg = bGUI.walkingImg(character);
        charImg.setLocation(210,150);
        rPanel.add(charImg);
        chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.png").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
        chestImg.setSize(300,300);
        chestImg.setLocation(600,300);
        rPanel.add(chestImg);
        rPanel.setComponentZOrder(chestImg,0);
        openAnimation();
    }

    //Opening the chest animation
    private void openAnimation()
    {
        count = 0;
        Timer timer = new Timer();
        TimerTask task = new TimerTask()
        {public void run() 
            {
                for (Component c : rPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                if(!paused)
                {
                    if(count >2 && count<18)
                    {
                         charImg.setLocation(charImg.getX()+10, charImg.getY());
                    }

                    else if(count==18)
                    {
                        Point x = new Point(charImg.getLocation());
                        rPanel.remove(charImg);
                        charImg = bGUI.battleImg(character);
                        charImg.setLocation(x);
                        rPanel.add(charImg);

                        rPanel.remove(chestImg);
                        chestImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Sprites/chest.gif").getImage()).getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT)));
                        chestImg.setSize(300,300);
                        chestImg.setLocation(600,300);
                        rPanel.add(chestImg);
                        rPanel.setComponentZOrder(chestImg,0);
                        rPanel.repaint();
                    }
                    
                    else if(count==38)
                    {
                        randRew((int)(Math.random()*3)); 
                        timer.cancel();
                    }

                    count++;  
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 75);     
    }
    
    //Randomly gives player a reward
    private void randRew(int x)
    {
        switch (x) 
        {
            case 0 -> 
            {
                //If the player has all Atks add an item
                if(randAtk()!=null) 
                {
                    mode = 0;
                    selAtk = randAtk();
                    //If player doesnt have 4 atks
                    if(!character.AtkF()) 
                    {
                        character.AtkA(selAtk);
                        loadScreen();
                    }
                    
                    else
                    {
                        full(selAtk);
                    }
                }
                
                else 
                {
                    randRew(2);
                }
            }

            case 1 -> 
            {
                //If the player has all Atks add an item
                if(randSkl()!=null) 
                {
                    mode = 1;
                    selSkl = randSkl();
                    //If player doesnt have 4 atks
                    if(!character.SklF()) 
                    {
                        character.SklA(selSkl);
                        loadScreen();
                    }
                    
                    else
                    {
                        full(selSkl);
                    }
                }
                
                else 
                {
                    randRew(2);
                }
            }
            
            default -> 
            {
                mode = 2;
                selItm = randItm();
                //If player doesnt have 4 atks
                if(!character.ItmF()) 
                {
                    character.ItmA(selItm);
                    loadScreen();
                }
                
                else
                {
                    full(selItm);
                }
            }
        }
    }

    //Gets a rand atk/skl/itm
    private Atk randAtk()
    {
        Atk rands[] = {new smack(), new punch(), new widePunch(), new bigHit()};
        int x = ((int)(Math.random()*rands.length));

        for(int i=0; i<rands.length; i++)
        {
            if(!character.has(rands[x])) {return rands[x];}
            
            if(x+1>=rands.length) {x = 0;}
            
            else{x++;}
        }
        return null;
    }
    private Skl randSkl()
    {
        Skl rands[] = {new juiceBox(), new rockThrowSKL(), new vampSwordSKL(), new rage()};
        int x = ((int)(Math.random()*rands.length));

        for(int i=0; i<rands.length; i++)
        {
            if(!character.has(rands[x])) {return rands[x];}
            
            if(x+1>=rands.length) {x = 0;}
            
            else{x++;}
        }
        return null;
    }
    private Itm randItm() 
    {
        Itm rands[] = {new grilledCheeseITM(), new swordITM(), new milkITM(), new cookieITM()};
        return rands[((int)(Math.random()*rands.length))];
    }

    //Loading screen
    private void loadScreen()
    {
        character.saveGame();
        if(hasPanel(fPanel)) {kronk.remove(fPanel);}
        
        else {kronk.remove(rPanel);}

        rPanel.removeAll();
        rPanel = new JPanel();
        rPanel.setBackground(Color.black);
        kronk.pack();
        rPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.add(rPanel);
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
            {public void run() 
                {
                    for (Component c : rPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                    if(!paused)
                    {
                        if(count<y.length) 
                        {
                            tText.setText(tText.getText() + y[count]);tText.setSize(tText.getPreferredSize());
                            tText.setLocation(rPanel.getWidth()/2 - tText.getWidth()/2, rPanel.getHeight()/2-tText.getHeight()/2);
                            rPanel.add(tText);
                        }

                        else if(count==y.length+5)
                        {
                            tText.setText("");
                        }

                        else if(count>y.length+5 && count<y.length+5+b.length)
                        {
                            tText.setText(tText.getText() + b[count-5-y.length]);tText.setSize(tText.getPreferredSize());
                            tText.setLocation(rPanel.getWidth()/2 - tText.getWidth()/2, rPanel.getHeight()/2-tText.getHeight()/2);
                            rPanel.add(tText);
                        }

                        else if(count>y.length + b.length + 20)
                        {
                            rPanel.remove(tText);rPanel.repaint();
                            for (Component c : rPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}
                            timer.cancel();kronk.remove(rPanel);new GameGUI(character,kronk);
                        }
                        count++;  
                    }
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
            {public void run() 
                {
                    for (Component c : rPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                    if(!paused)
                    {
                        if(count<b.length) 
                        {
                            tText.setText(tText.getText() + b[count]);tText.setSize(tText.getPreferredSize());
                            tText.setLocation(rPanel.getWidth()/2 - tText.getWidth()/2, rPanel.getHeight()/2-tText.getHeight()/2);
                            rPanel.add(tText);
                        }
                    
                        else if(count>b.length + 15)
                        {
                            rPanel.remove(tText);rPanel.repaint();
                            for (Component c : rPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}
                            timer.cancel();kronk.remove(rPanel);new GameGUI(character,kronk);
                        }
                        count++;  
                    }
                }
            };
            timer.scheduleAtFixedRate(task, 0, 50);     
        }
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
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(buttons[0])) 
        {
            switch (mode) 
            {
                case 0 -> {character.atks[0] = selAtk;}
                case 1 -> {character.skls[0] = selSkl;}
                case 2 -> {character.itms[0] = selItm;}
            }            
            loadScreen();
        }

        else if(j.equals(buttons[1])) 
        {
            switch (mode) 
            {
                case 0 -> {character.atks[1] = selAtk;}
                case 1 -> {character.skls[1] = selSkl;}
                case 2 -> {character.itms[1] = selItm;}
            }   
            loadScreen();
        }

        else if(j.equals(buttons[2])) 
        {
            switch (mode) 
            {
                case 0 -> {character.atks[2] = selAtk;}
                case 1 -> {character.skls[2] = selSkl;}
                case 2 -> {character.itms[2] = selItm;}
            }   
            loadScreen();
        }

        else if(j.equals(buttons[3])) 
        {
            switch (mode) 
            {
                case 0 -> {character.atks[3] = selAtk;}
                case 1 -> {character.skls[3] = selSkl;}
                case 2 -> {character.itms[3] = selItm;}
            }   
            loadScreen();
        }

        else if(j.equals(buttons[5])) {mode = -1;loadScreen();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(character.getVol()+25>100) {character.setVol(0);} else {character.setVol(character.getVol() + 25);} vole.setText(String.valueOf(character.getVol()));}
    }
    private void fullKeyActions()
    {
        fPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        fPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        fPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");
        fPanel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {keyPad(2);}});
        fPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Right");
        fPanel.getActionMap().put("Right", new AbstractAction() {public void actionPerformed(ActionEvent e) {keyPad(3);}});
        fPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        fPanel.getActionMap().put("Up", new AbstractAction() {public void actionPerformed(ActionEvent e) {keyPad(0);}});
        fPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        fPanel.getActionMap().put("Down", new AbstractAction() {public void actionPerformed(ActionEvent e) {keyPad(1);}});
        fPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        fPanel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {fullSel();}});
    }
    
    //Handles up down left right for the select buttons
    private void keyPad(int z)
    {
        switch (z) 
        {
            case 0 -> {fullUp();}

            case 1 -> {fullDown();}

            case 2 -> {fullLeft();}

            case 3 -> {fullRight();}
        }
        highlight(KB[KBV][KBH]);
    }
    private void fullUp() {if(KBV-1 >=0) {KBV--;}}
    private void fullDown() {if(KBV+1 <=2) {KBV++;}}
    private void fullLeft() {if(KBH-1 >=0) {KBH--;}}
    private void fullRight() {if(KBH+1 <=1) {KBH++;}}
    private void fullSel() 
    {
        buttons[KB[KBV][KBH]].setEnabled(true);
        buttons[KB[KBV][KBH]].doClick();
        buttons[KB[KBV][KBH]].setEnabled(false);
    }

    //handles the selection of what to replace
    private void full(Atk x)
    {
        selAtk = x;
        fullPan(x);
        highlight(KB[KBV][KBH]);
        fullKeyActions();
        panSet(rPanel, fPanel);
    }
    private void fullPan(Atk x)
    {
        fPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        fPanel.setLayout(null);

        buttons = new JButton[6];
        for(int i=0;i<6; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.red);
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 30));
            fPanel.add(buttons[i]);
        }

        for(int i=0;i<4;i++) {buttons[i].setText(character.atks[i].getName());}

        buttons[4].setText(x.getName());
        buttons[5].setText("Skip");
        buttons[4].setBounds(550,650,200,100);
        buttons[5].setBounds(750,650,200,100);

        buttons[1].setBounds(750,150,200,200);
        buttons[0].setBounds(550,150,200,200);
        buttons[2].setBounds(550,350,200,200);
        buttons[3].setBounds(750,350,200,200);


        title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Pick an attack to replace");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 5);
      
        fPanel.add(title);
      
        paint(fPanel,5);
    }
    private void full(Skl x)
    {
        selSkl = x;
        fullPan(x);
        highlight(KB[KBV][KBH]);
        fullKeyActions();
        panSet(rPanel, fPanel);
    }
    private void fullPan(Skl x)
    {
        fPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        fPanel.setLayout(null);

        buttons = new JButton[6];
        for(int i=0;i<6; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.red);
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 30));
            fPanel.add(buttons[i]);
        }

        for(int i=0;i<4;i++) {buttons[i].setText(character.skls[i].getName());}

        buttons[4].setText(x.getName());
        buttons[5].setText("Skip");
        buttons[4].setBounds(550,650,200,100);
        buttons[5].setBounds(750,650,200,100);

        buttons[1].setBounds(750,150,200,200);
        buttons[0].setBounds(550,150,200,200);
        buttons[2].setBounds(550,350,200,200);
        buttons[3].setBounds(750,350,200,200);


        title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Pick an skill to replace");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 5);
      
        fPanel.add(title);
      
        paint(fPanel,5);
    }
    private void full(Itm x)
    {
        selItm = x;
        fullPan(x);
        highlight(KB[KBV][KBH]);
        fullKeyActions();
        panSet(rPanel, fPanel);
    }
    private void fullPan(Itm x)
    {
        fPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        fPanel.setLayout(null);

        buttons = new JButton[6];
        for(int i=0;i<6; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.red);
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 30));
            fPanel.add(buttons[i]);
        }

        for(int i=0;i<4;i++) {buttons[i].setText(character.itms[i].getName());}

        buttons[4].setText(x.getName());
        buttons[5].setText("Skip");
        buttons[4].setBounds(550,650,200,100);
        buttons[5].setBounds(750,650,200,100);

        buttons[1].setBounds(750,150,200,200);
        buttons[0].setBounds(550,150,200,200);
        buttons[2].setBounds(550,350,200,200);
        buttons[3].setBounds(750,350,200,200);


        title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Pick an item to replace");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 5);
      
        fPanel.add(title);
      
        paint(fPanel,5);
    }

    //Extra helpers
    private void highlight(int x)
    {
        for (Component c : fPanel.getComponents()) {if (c instanceof JButton && !c.equals(pau) && !c.equals(set)
        && !c.equals(exit) && !c.equals(settings) && !c.equals(vole))
            
            {((JButton)c).setBorder(BorderFactory.createLineBorder(Color.black, 5));}}   

        if(x>=0) {buttons[x].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));}
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
    private boolean has(JPanel y, Component x) {for (Component c : y.getComponents()) {if (c == x) {return true;}} return false;}
    private void exit() {if(!settings.getText().equals("Volume")){kronk.dispose();} else{pause();}}
    private boolean hasPanel(JPanel targetPanel) 
    {
        Component[] components = kronk.getContentPane().getComponents();
        
        for (Component comp : components) {if (comp == targetPanel) {return true;}}
        return false;
    }
}
