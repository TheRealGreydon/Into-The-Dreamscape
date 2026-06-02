package APCS.GUIs;

import APCS.*;
import APCS.Actions.*;
import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Assets.AssetClasses.*;
import APCS.Enms.*;
import APCS.Items.AttackItem.atkItm;
import APCS.Items.Itm;
import APCS.Items.SkillItem.sklItm;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class GameGUI extends JFrame implements ActionListener
{
    private Player character;
    private JPanel gPanel, cut;
    private BattleAssets bGUI = new BattleAssets();
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;

    private JLabel charSprite, hp = new JLabel();

    //bb 0-2 (Atk, Skl, Itm), bb 3-8 (Atk 1-4), bb 9-14 (Skl 1-4), bb 15-20 (Itm 1-4)
    //Im sorry, this is the worst way to do it, and I fucking love it
    private JLabel [] bb;
    private JLabel bbExit;
    private disEnm [] bbE;
    private Atk selAtk;

    //Pause items
    private JButton pau = new JButton(), set = new JButton(), exit = new JButton(), settings = new JButton(), vole = new JButton();
    private boolean paused = false;

    //Win/Lose buttons
    private JButton bbWL = new JButton();

    //Control the scrolling text
    private JLabel tText = new JLabel();
    private int f,count = 0,looped = 0, wideLoop = 0;
    private String [] b;

    //Control the selected button/enm
    private boolean selingEnmAtk = false, down = true;
    private int selEnm = 0, selBut = 0, minSel = 0, maxSel = 2;

    public GameGUI(Player character, JFrame kronk) {this.kronk = kronk;this.character = character;start();}

    //Initilizes the battle items
    private void battleInit()
    {
        bbExit = bGUI.bbEN(false);
        bbE = bGUI.enmBattleButtons(1);
        charSprite = bGUI.battleImg(character);
        charSprite.setLocation(0, 125);

        //Sets the hp counter
        hp.setBackground(new Color(179, 9, 9));
        hp.setBorder(BorderFactory.createLineBorder(Color.black,5));
        hp.setForeground(Color.black);
        hp.setFont(new Font(hp.getFont().getName(), Font.BOLD, 40));
        hp.setText(String.valueOf("HP: " + character.getHealth()));
        hp.setSize(hp.getPreferredSize());
        hp.setSize((hp.getWidth()+10),(hp.getHeight()+10));
        hp.setLocation(175,100);
        hp.setOpaque(true);
        
        actBattleButtons();

        //Does the enm and character imgs
        for(int i=0; i<3; i++) {gPanel.add(bbE[i].enmImg);}
        gPanel.add(hp);gPanel.add(charSprite);
        gPanel.setComponentZOrder(hp,0);gPanel.setComponentZOrder(charSprite,1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gPanel.revalidate();
        gPanel.repaint();
        kronk.pack();
        gPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.add(gPanel);
        kronk.setVisible(true);

        keyActions();
    }

    //Repaints the hp icon
    private void hpReset()
    {
        hp.setText(String.valueOf("HP: " + character.getHealth()));
        hp.setSize(hp.getPreferredSize());
        hp.setSize((hp.getWidth()+10),(hp.getHeight()+10));
        hp.setLocation(175,100);
        gPanel.revalidate();
        gPanel.repaint();
    }

    //Attack, skill, and item buttons
    private void atkBattleButtons()
    {
        int x=0;
        for(int i=3; i<8; i++) 
        {
            if(i!=5 && i!=8)
            {
                if(character.atks[x] != null) {bb[i].setText(character.atks[x].getName());}
                
                else{bb[i].setText("X");} x++;
            }
        }
    }
    private void sklBattleButtons() 
    {
        int x=0;
        for(int i=9; i<14; i++) 
        {
            if(i!=11 && i!=14)
            {
                if(character.skls[x] != null) {bb[i].setText(character.skls[x].getName());}
                
                else{bb[i].setText("X");} x++;
            }
        }
    }
    private void itmBattleButtons() 
    {
        int x=0;
        for(int i=15; i<20; i++) 
        {
            if(i!=17 && i!=20)
            {
                if(character.itms[x] != null) {bb[i].setText(character.itms[x].getName());}

                else{bb[i].setText("X");} x++;
            }
        }
    }

    //Left, Right, Up and Down keybind actions
    private void UpDown()
    {
        if(down && !has(bb[0])) {highlight(-1);}

        else if(!down && !has(bb[0]))
        {
            if(has(bb[3])) {highlight(3);} else if(has(bb[6])) {highlight(6);}

            else if(has(bb[9])) {highlight(9);} else if(has(bb[12])) {highlight(12);}

            else if(has(bb[15])) {highlight(15);} else if(has(bb[18])) {highlight(18);}
        }
        down ^= true;
    }
    private void Up()
    {
        if(!paused && !has(tText))
        {
            if(selingEnmAtk)
            {
                if(selEnm - 1>=0 && bbE[selEnm-1].Enm.isAlive()) {selEnm--;}

                else if(selEnm-2>=0 && bbE[selEnm-2].Enm.isAlive()) {selEnm-=2;}
                
                for(int i=0; i<3; i++) {bbE[i].select(false);}bbE[selEnm].select(true);
            }

            else {UpDown();}
        }
    }
    private void Down()
    {
        if(!paused && !has(tText))
        {
            if(selingEnmAtk)
            {
                if(selEnm+1<=2 && bbE[selEnm+1].Enm.isAlive()) {selEnm++;}

                else if(selEnm+2<=2 && bbE[selEnm+2].Enm.isAlive()) {selEnm+=2;}
                
                for(int i=0; i<3; i++) {bbE[i].select(false);}bbE[selEnm].select(true);
            }

            else {UpDown();}
        }
    }
    private void Left()
    {
        if(!has(tText))
        {
            if(selBut-1 >= minSel && !paused && !selingEnmAtk) 
            {
                selBut--;highlight(selBut);
            }
        }
    }
    private void Right()
    {
        if(!has(tText))
        {
            if(selBut+1 <= maxSel && !paused && !selingEnmAtk)
            {
                selBut++;highlight(selBut);
            }
        }
    }

    //Handles the button selection
    private void Select(int x)
    {
        if(!has(tText))
        {
            if(!paused)
            {
                if(selingEnmAtk) {int z = -1;for(int i=0; i<3;i++) {if(bbE[i].selected) {z = i;}}if(z != -1) {bbE[z].select(false); turnPhaze(bbE[z], selAtk);selingEnmAtk = false;}highlight(selBut);}

                else
                {
                    //Atk, Skl, Itm
                    if(down)
                    {
                        if(x<=2)
                        {
                            switch (x) 
                            {
                                case 0 -> 
                                {
                                    atk();
                                    minSel = 3;
                                    maxSel = 5;
                                    selBut = 3;
                                    highlight(3);
                                }

                                case 1 -> 
                                {
                                    skl();
                                    minSel = 9;
                                    maxSel = 11;
                                    selBut = 9;
                                    highlight(9);
                                }

                                case 2 -> 
                                {
                                    itm();
                                    minSel = 15;
                                    maxSel = 17;
                                    selBut = 15;
                                    highlight(15);
                                }
                            }
                        }
                        

                        //Atk buttons
                        else if(x>=3 && x<=8)
                        {
                            if(!bb[x].getText().equals("Next") &&!bb[x].getText().equals("X")) {turnPhaze(x);}

                            else if(bb[x].getText().equals("Next"))
                            {
                                if(x==5)
                                {
                                    gPanel.remove(bb[3]);gPanel.remove(bb[4]);gPanel.remove(bb[5]);
                                    gPanel.add(bb[6]);gPanel.add(bb[7]);gPanel.add(bb[8]);
                                    minSel = 6; maxSel = 8;selBut = 8;highlight(8);
                                }

                                else
                                {
                                    gPanel.remove(bb[6]);gPanel.remove(bb[7]);gPanel.remove(bb[8]);
                                    gPanel.add(bb[3]);gPanel.add(bb[4]);gPanel.add(bb[5]);
                                    minSel = 3; maxSel = 5;selBut = 5;highlight(5);
                                }
                            }
                        }

                        //Skl buttons
                        else if(x>=9 && x<=14)
                        {
                            if(!bb[x].getText().equals("Next") &&!bb[x].getText().equals("X")) {turnPhaze(x);}

                            else if(bb[x].getText().equals("Next"))
                            {
                                if(x==11)
                                {
                                    gPanel.remove(bb[9]);gPanel.remove(bb[10]);gPanel.remove(bb[11]);
                                    gPanel.add(bb[12]);gPanel.add(bb[13]);gPanel.add(bb[14]);
                                    minSel = 12; maxSel = 14;selBut = 14;highlight(14);
                                }

                                else
                                {
                                    gPanel.remove(bb[12]);gPanel.remove(bb[13]);gPanel.remove(bb[14]);
                                    gPanel.add(bb[9]);gPanel.add(bb[10]);gPanel.add(bb[11]);
                                    minSel = 9; maxSel = 11;selBut = 11;highlight(11);
                                }
                            }
                        }

                        //Itm buttons
                        else if(x>=15 && x<=20)
                        {
                            if(!bb[x].getText().equals("Next") && !bb[x].getText().equals("X")) {turnPhaze(x);}

                            else if(bb[x].getText().equals("Next"))
                            {
                                if(x==17)
                                {
                                    gPanel.remove(bb[15]);gPanel.remove(bb[16]);gPanel.remove(bb[17]);
                                    gPanel.add(bb[18]);gPanel.add(bb[19]);gPanel.add(bb[20]);
                                    minSel = 18; maxSel = 20;selBut = 20;highlight(20);
                                }

                                else
                                {
                                    gPanel.remove(bb[18]);gPanel.remove(bb[19]);gPanel.remove(bb[20]);
                                    gPanel.add(bb[15]);gPanel.add(bb[16]);gPanel.add(bb[17]);
                                    minSel = 15; maxSel = 17;selBut = 17;highlight(17);
                                }
                            }
                        }
                    }

                    else {for(int i = 3; i<21; i++) {if(has(bb[i])) {gPanel.remove(bb[i]);}} for(int i=0;i<3;i++) {gPanel.add(bb[i]);}minSel = 0;maxSel = 2;selBut = 0;highlight(0);down = true;gPanel.remove(bbExit);gPanel.repaint();}
                }
            }
        }
    }
    private void atkSel(Atk z) 
    {
        selAtk = z;selingEnmAtk = true;
        highlight(-2);
        if(bbE[0].Enm.isAlive()) 
        {
            bbE[0].select(true);selEnm = 0;
        }
        else if(bbE[1].Enm.isAlive()) 
        {
            bbE[1].select(true);selEnm = 1;
        }
        else if(bbE[2].Enm.isAlive()) 
        {
            bbE[2].select(true);selEnm = 2;
        }
    }

    //Player turn
    private void turnPhaze(int y)
    {
        switch (y) {
            case 3 -> turnPhaze((Actions)character.atks[0]);
            case 4 -> turnPhaze((Actions)character.atks[1]);
            case 6 -> turnPhaze((Actions)character.atks[2]);
            case 7 -> turnPhaze((Actions)character.atks[3]);
            case 9 -> turnPhaze((Actions)character.skls[0]);
            case 10 -> turnPhaze((Actions)character.skls[1]);
            case 12 -> turnPhaze((Actions)character.skls[2]);
            case 13 -> turnPhaze((Actions)character.skls[3]);
            case 15 -> turnPhaze(character.itms[0], 0);
            case 16 -> turnPhaze(character.itms[1], 1);
            case 18 -> turnPhaze(character.itms[2], 2);
            case 19 -> turnPhaze(character.itms[3], 3);
        }
        hpReset();
    }
    private void turnPhaze(Itm y, int z)
    {
        if(y != null)
        {
            if(y instanceof atkItm) {turnPhaze((Actions)(((atkItm)y).getAtk()));}

            else {turnPhaze((Actions)(((sklItm)y).getSkill()));}
            itmUsed(z);
        }
        
    }
    private void turnPhaze(disEnm x, Atk y)
    {
        count = 0;
        looped = 0;

        Timer timer = new Timer();
        b = new BattleAssets().attack(x.Enm, y, character).split("");
        tText.setText("");
        tText.setLocation(500, 220);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        TimerTask task = new TimerTask()
        {public void run() 
            {
                if(!paused)
                {
                    if(count<b.length) 
                    {
                        gPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());
                        tText.setSize(tText.getWidth()+10, tText.getHeight());
                        gPanel.add(tText);gPanel.repaint();
                    }

                    else
                    {
                        if(count==b.length+10) 
                        {
                            if(!x.Enm.isAlive()) {gPanel.remove(x.enmImg);}

                            gPanel.remove(tText);
                            gPanel.repaint();
                            timer.cancel();
                            enmBattlePhaze();
                        }
                    }
                    count++;
                }
            }
        };

        gPanel.add(tText);
        timer.scheduleAtFixedRate(task, 0, 100);
    }
    private void turnPhaze(Atk y)
    {
        if(y.swing())
        {
            count = 0;
            tText.setText("");
            tText.setLocation(500, 220);
            tText.setOpaque(true);
            tText.setBackground(Color.WHITE);
            tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
            Timer timer = new Timer();
            if(wideLoop <=2 && bbE[wideLoop].Enm.isAlive()) 
            {
                b = new BattleAssets().attack(bbE[wideLoop].Enm, y, character).split("");
                TimerTask task = new TimerTask()
                {public void run() 
                    {if(!paused)
                    {
                        if(count<b.length) {gPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                        gPanel.add(tText);gPanel.repaint();}
                        else{if(count==b.length+10) 
                        {
                            if(!bbE[wideLoop].Enm.isAlive()) {gPanel.remove(bbE[wideLoop].enmImg);}
                            gPanel.remove(tText);gPanel.repaint();timer.cancel();if(wideLoop<2){turnPhaze(y);}
                            else {looped = 0; enmBattlePhaze();}
                        }}count++;
                    }}};

                gPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
            }

            else if(wideLoop <2) {wideLoop++;turnPhaze(y);}
        }
        
        else {atkSel(y);}
    }
    private void turnPhaze(Skl y)
    {
        if(!(y instanceof healSkl)) {if(((atkSkl)y).getAtk().swing()) {turnPhaze(((atkSkl)y).getAtk());} else{atkSel(((atkSkl)y).getAtk());}}

        else
        {
            count = 0; looped = 0;

            int tmp = ((healSkl)y).getHeal();
            Timer timer = new Timer();
            if(y instanceof healSkl) {b = ("Heal " + String.valueOf(tmp)).split("");character.doHp(tmp);}
            tText.setText("");
            tText.setLocation(500, 220);
            tText.setOpaque(true);
            tText.setBackground(Color.WHITE);
            tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
            TimerTask task = new TimerTask()
            {public void run() 
                {
                    if(!paused)
                    {
                        if(count<b.length) {gPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                        gPanel.add(tText);gPanel.repaint();}
                        else{if(count==b.length+10) {gPanel.remove(tText);gPanel.repaint();timer.cancel();enmBattlePhaze();}}count++;}}};

            gPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
        }
    }

    private void turnPhaze(Actions z)
    {
        if(character.regenT>0) {character.regenT--;character.doHp(character.regenAmt);}

        if(character.normal())
        {
            if(z.stat() != 0)
            {
                if(z.stat() == 1)
                {
                    character.chargeT = z.statT();
                    chargePhaze();
                }

                else if(z.stat() == 2)
                {
                    character.regenT += z.statT();
                    character.regenAmt = z.regenAmt();
                }

                else if(z.stat() == 3)
                {
                    character.regenT = z.statT();
                }

                else if(z.stat() == 4)
                {
                    character.atkupT = z.statT();
                }
            }

            else
            {
                if(z instanceof Atk) {turnPhaze((Atk)z);}

                else {turnPhaze((Skl)z);}
            }
        }
        
        else
        {
            if(character.chargeT>0)
            {
                if(character.chargeT==1)
                {
                    if(z instanceof Atk) {turnPhaze((Atk)z);}

                    else {turnPhaze((Skl)z);}
                }

                else {chargePhaze();}
                character.chargeT--;
            }
        }
    }

    //When player charges an atk/skl
    private void chargePhaze()
    {
        count = 0; looped = 0;

        Timer timer = new Timer();
        b = (" " + character.getName() + " is charging a move").split("");
        tText.setText("");
        tText.setLocation(500, 220);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        TimerTask task = new TimerTask()
        {public void run() {if(!paused)
        {
            if(count<b.length) {gPanel.remove(tText);tText.setText(tText.getText() + b[count]);
            tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());gPanel.add(tText);gPanel.repaint();}
            else {if(count==b.length+10) {gPanel.remove(tText);gPanel.repaint();gPanel.remove(tText);timer.cancel();enmBattlePhaze();}}count++;
        }}};

        gPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
    }
    
    //Enm turn, also handles winning/losing
    private void enmBattlePhaze()
    {
        count = 0;
        tText.setText("");
        tText.setLocation(500, 220);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        Timer timer = new Timer();
        if(looped <=2 && bbE[looped].Enm.isAlive()) 
        {
            if((int)(Math.random()*10)==0) {b = new BattleAssets().attack(character, bbE[looped].Enm.getAtk(1)).split("");}
            
            else {b = new BattleAssets().attack(character, bbE[looped].Enm.getAtk(0)).split("");}
            TimerTask task = new TimerTask()
            {public void run() 
                {if(!paused)
                {
                    if(count<b.length) {gPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                    tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                    gPanel.add(tText);gPanel.repaint();}
                    else{if(count==b.length+10) 
                    {
                        if(character.isAlive()) {gPanel.remove(tText);gPanel.repaint();timer.cancel();enmBattlePhaze();}

                        else {gEnd(false);timer.cancel();}
                    }}count++;
                }}};

            hpReset();
            gPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);looped++;
        }

        else if(looped<2) {looped++;enmBattlePhaze();hpReset();} 
        
        else if(!bbE[0].Enm.isAlive() && !bbE[1].Enm.isAlive() && !bbE[2].Enm.isAlive()) {gEnd(true);}
    }    

    //Button actions and keybinds
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(character.getVol()+25>100) {character.setVol(0);}else{character.setVol(character.getVol()+25);}vole.setText(String.valueOf(character.getVol()));}

        else if(j.equals(bbWL)) 
        {
            kronk.remove(gPanel);
            if(j.getText().equals("Next")) {new RewGUI(character, kronk).initialize();}

            else {new LevelGUI(character, kronk).initialize();}
        }
    }
    private void keyActions()
    {
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        gPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Select");
        gPanel.getActionMap().put("Select", new AbstractAction() {public void actionPerformed(ActionEvent e) {Select(selBut);}});
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");
        gPanel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {if(down){Left();}}});
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Right");
        gPanel.getActionMap().put("Right", new AbstractAction() {public void actionPerformed(ActionEvent e) {if(down){Right();}}});
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        gPanel.getActionMap().put("Down", new AbstractAction() {public void actionPerformed(ActionEvent e) {Down();}});
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        gPanel.getActionMap().put("Up", new AbstractAction() {public void actionPerformed(ActionEvent e) {Up();}});
    }

    //Various helpers, hell if I know
    private boolean has(Component x) {for (Component c : gPanel.getComponents()) {if (c == x) {return true;}} return false;}
    private void exit() {if(set.isVisible()) {pause();} else {character.resetHP();kronk.remove(gPanel);new LevelGUI(character, kronk).initialize();}}
    private void atk() {for(int i=0; i<3; i++) {gPanel.remove(bb[i]);}gPanel.add(bbExit);gPanel.add(bb[3]);gPanel.add(bb[4]);gPanel.add(bb[5]);gPanel.repaint();}
    private void skl() {for(int i=0; i<3; i++) {gPanel.remove(bb[i]);}gPanel.add(bbExit);gPanel.add(bb[9]);gPanel.add(bb[10]);gPanel.add(bb[11]);gPanel.repaint();}
    private void itm() {for(int i=0; i<3; i++) {gPanel.remove(bb[i]);}gPanel.add(bbExit);gPanel.add(bb[15]);gPanel.add(bb[16]);gPanel.add(bb[17]);gPanel.repaint();}
    private void actBattleButtons() {bb = bGUI.actBattleButtons();highlight(0);for(int i=0; i<3; i++) {bb[i].setVisible(true);gPanel.add(bb[i]);}pauButtons();atkBattleButtons();sklBattleButtons();itmBattleButtons();}
    private void start() {gPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Battle Level/BB" + ((int)(Math.random()*4 + 1)) +".png").getImage(), 0);kronk.setLocationRelativeTo(null);battleInit();}  
    private void highlight(int x)
    {
        for (Component c : gPanel.getComponents()) {if (c instanceof JLabel && !c.equals(charSprite) && !c.equals(tText)
        && !c.equals(bbE[0].enmImg) && !c.equals(bbE[1].enmImg) && !c.equals(bbE[2].enmImg))
            
            {((JLabel)c).setBorder(BorderFactory.createLineBorder(Color.black, 5));}}   

        if(x>=0) {bb[x].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));}

        else if(x==-1) {bbExit.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));}
    }
    private void pauButtons()
    {   
        set.setVisible(false);
        set.setSize(new Dimension(5000,5000));
        pau.setSize(new Dimension(5000,5000));
        pau.setEnabled(false);
        pau.setVisible(false);
        pau.setForeground(Color.white);
        pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 30));
        pau.setText("Paused"); 
        exit.setBackground(new Color(0,0,0));
        settings.setBackground(new Color(0,0,0));
        pau.setBackground(new Color(0,0,0,200));
        exit.setSize(new Dimension(200,100));
        settings.setSize(new Dimension(200,100));
        exit.setEnabled(false);
        exit.setVisible(false);
        exit.setForeground(Color.white);
        exit.setFont(new Font(exit.getFont().getName(), Font.BOLD, 40));
        exit.setText("Exit");
        settings.setEnabled(false);
        settings.setVisible(false);
        settings.setForeground(Color.white);
        settings.setFont(new Font(settings.getFont().getName(), Font.BOLD, 40));
        settings.setText("Settings");
        settings.addActionListener(this);
        exit.addActionListener(this);
        kronk.setLocationRelativeTo(null);
        settings.setFocusable(false);
        exit.setFocusable(false);
    }
    private void pause()
    {  
        if(paused) 
        {
            settings.setVisible(false);
            settings.setEnabled(false);
            pau.setVisible(false);
            exit.setEnabled(false);
            exit.setVisible(false);
            set.setVisible(false);
            vole.setVisible(false);
            vole.setEnabled(false);
        }

        else 
        {
            pau.setForeground(Color.white);
            pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));
            pau.setText("Paused");
            pau.setBackground(new Color(0,0,0,200));
            pau.setLocation(gPanel.getWidth()/2-2500, gPanel.getHeight()/2-2650);
            settings.setText("Settings");
            gPanel.add(settings);gPanel.add(exit);gPanel.add(pau);
            exit.setLocation((gPanel.getWidth()/2)-100, (gPanel.getHeight()/2));
            settings.setLocation((gPanel.getWidth()/2-100), (gPanel.getHeight()/2-100));settings.setVisible(true);
            settings.setEnabled(true);
            exit.setVisible(true);
            exit.setEnabled(true);
            exit.setText("Exit");
            pau.setVisible(true);
        }
        
        gPanel.add(charSprite);gPanel.add(hp);
        for(int i=0;i<3;i++) {if(has(bbE[i].enmImg)) {gPanel.add(bbE[i].enmImg);bbE[i].enmImg.setVisible(true);}}
        if(has(bbExit)) {gPanel.add(bbExit);bbExit.setVisible(true);}
        if(has(tText)) {tText.setVisible(true);gPanel.add(tText);tText.setVisible(true);}
        for(int i = 0; i<21; i++) {if(has(bb[i])) {gPanel.add(bb[i]);bb[i].setVisible(true);}}

        paused ^= true;
    }    
    private void settings()
    {
        gPanel.remove(charSprite);
        for(int i=0;i<3;i++) {if(has(bbE[i].enmImg)) {bbE[i].enmImg.setVisible(false);}}
        if(has(bbExit)) {bbExit.setVisible(false);}if(has(tText)) {tText.setVisible(false);}gPanel.remove(hp);
        for(int i = 0; i<21; i++) {if(has(bb[i])) {bb[i].setVisible(false);}}

        pau.setVisible(false);set.setEnabled(false);set.setForeground(Color.white);set.setVisible(true);set.setBackground(Color.BLACK);
        set.setFont(new Font(set.getFont().getName(), Font.BOLD, 40));set.setText("Settings"); 
        exit.setText("Back");settings.setText("Volume");settings.setEnabled(false);
        vole.setFont(new Font(vole.getFont().getName(), Font.BOLD, 40));vole.addActionListener(this);vole.setSize(new Dimension(125,100));vole.setBackground(Color.black);
        vole.setForeground(Color.white);vole.setLocation((gPanel.getWidth()/2-50)+150, (gPanel.getHeight()/2-100));
        vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(character.getVol()));
        gPanel.add(vole);set.setLocation(gPanel.getWidth()/2-2500, gPanel.getHeight()/2-2650);gPanel.add(set);
        vole.setFocusable(false);
    }
    private void gEnd(boolean x)
    {
        if(((int)(Math.random())*20) == 19 && !x) {SQUONKINIT();}

        else
        {
            gPanel.remove(tText);gPanel.repaint();
            gPanel.getActionMap().clear();highlight(-2);
            gPanel.remove(pau);
            
            pau.setForeground(Color.white);
            pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));
            pau.setBackground(new Color(0,0,0,200));
            pau.setLocation(gPanel.getWidth()/2-2500, gPanel.getHeight()/2-2650);
            gPanel.add(pau);
            gPanel.add(charSprite);
            for(int i=0;i<3;i++) {if(has(bbE[i].enmImg)) {gPanel.add(bbE[i].enmImg);}}
            if(has(bbExit)) {gPanel.add(bbExit);}
            if(has(tText)) {tText.setVisible(true);gPanel.add(tText);}
            for(int i = 0; i<21; i++) {if(has(bb[i])) {gPanel.add(bb[i]);}}
            pau.setVisible(true);

            bbWL = new JButton();
            bbWL.setBackground(new Color(0,0,0));
            bbWL.setSize(new Dimension(200,100));
            bbWL.setEnabled(true);
            bbWL.setVisible(true);
            bbWL.setForeground(Color.white);
            bbWL.setFont(new Font(bbWL.getFont().getName(), Font.BOLD, 40));
            bbWL.setFocusable(false);
            bbWL.addActionListener(this);
            bbWL.setLocation(650,350);

            if(x)
            {
                character.resetHP();
                pau.setText("You Win!");
                gPanel.setComponentZOrder(pau, 0);
                gPanel.repaint();
                character.resetHP();
                bbWL.setText("Next");

                character.nextLvl();
            }

            else
            {
                character.reset();
                pau.setText("You Lose");
                gPanel.setComponentZOrder(pau, 0);
                gPanel.repaint();
                character.resetHP();
                bbWL.setText("Retry?");
                gPanel.repaint();
                character.reset();
            }

            character.resetTurn();gPanel.add(bbWL);gPanel.setComponentZOrder(bbWL, 0);character.saveGame();
        }
    }   
    private void itmUsed(int x) 
    {
        for(int i=x; i<3; i++)
        {
            character.itms[i] = character.itms[i+1];
        }

        character.itms[3] = null;itmBattleButtons();
    }
    private void SQUONKINIT()
    {
        kronk.setSize(900,900);f = 0;
        
        Timer timer = new Timer();
        count = 0;
        String [] b = " Squonk has saved you.".split("");
        tText.setText("");
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        tText.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));

        cut = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Cut/CircleOfSquonk.gif").getImage(), 2);
        gPanel.removeAll();kronk.remove(gPanel);kronk.add(cut);kronk.revalidate();kronk.repaint();
        TimerTask task = new TimerTask() 
        {
            public void run() 
            {
                if(f==40)
                {
                    kronk.remove(cut);
                    gPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Sprites/SquonkingIt.png").getImage(), 0);
                    kronk.add(gPanel);
                    kronk.revalidate();
                    kronk.repaint();
                    gPanel.add(tText);
                }

                if(f>=40) 
                {
                    if(!(count<5) && count<b.length+5) 
                    {
                        tText.setText(tText.getText() + b[count-5]);
                        tText.setSize(tText.getPreferredSize());
                        tText.setLocation(500 - tText.getWidth()/2, 450-tText.getHeight()/2);
                        gPanel.add(tText);
                        //gPanel.setComponentZOrder(tText, 0);
                    }

                    else if(count>b.length+27) {timer.cancel();character.resetHP();kronk.remove(gPanel);new LevelGUI(character, kronk).initialize();}
                    count++;
                }
                f++;
            }
        };
        
        timer.scheduleAtFixedRate(task, 0, 100);
    }
}