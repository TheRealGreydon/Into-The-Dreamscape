package APCS.GUIs;

import APCS.*;
import APCS.Uses.Actions.*;
import APCS.Uses.Actions.Attacks.*;
import APCS.Uses.Actions.Skills.*;
import APCS.Assets.AssetClasses.*;
import APCS.Enms.*;
import APCS.Uses.Items.*;
import APCS.Uses.Items.AttackItem.*;
import APCS.Uses.Items.SkillItem.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class KBossGUI extends JFrame implements ActionListener
{
    private Player character;
    private JPanel bPanel, cut;
    private BattleAssets bGUI = new BattleAssets();
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private JLabel charSprite, hp = new JLabel();

    //bb 0-2 (Atk, Skl, Itm), bb 3-8 (Atk 1-4), bb 9-14 (Skl 1-4), bb 15-20 (Itm 1-4)
    private JLabel [] bb;
    private JLabel bbExit;
    private disEnm bossMan;
    private Atk selAtk;

    //Pause items
    private JButton pau = new JButton(), set = new JButton(), exit = new JButton(), settings = new JButton(), vole = new JButton();
    private boolean paused = false;

    //Win/Lose buttons
    private JButton bbWL = new JButton();

    //Control the scrolling text
    private JLabel tText = new JLabel();
    private int f,count = 0,looped = 0, wideLoop = 0;
    private String [] b, y,z;

    //Control the selected button/enm
    private boolean down = true;
    private int selBut = 0, minSel = 0, maxSel = 2;

    //Timertasks for enm atk
    private TimerTask taskN, taskB, taskF;

    public KBossGUI(Player character, JFrame kronk) {this.kronk = kronk;this.character = character;start();}

    //Initilizes the battle items
    private void battleInit()
    {
        bbExit = bGUI.bbEN(false);
        bossMan = new disEnm(new Karel(1));
        bossMan.enmImg.setLocation(1200, 250);
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
        tText.setBorder(BorderFactory.createLineBorder(Color.blue, 5));

        actBattleButtons();

        bPanel.add(bossMan.enmImg);
        bPanel.add(hp);bPanel.add(charSprite);
        bPanel.setComponentZOrder(hp,0);bPanel.setComponentZOrder(charSprite,1);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        bPanel.revalidate();
        bPanel.repaint();
        kronk.pack();
        bPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.add(bPanel);
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
        bPanel.revalidate();
        bPanel.repaint();
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
    private void Up() {if(!paused && !has(tText)) {UpDown();}}
    private void Down() {if(!paused && !has(tText)) {UpDown();}}
    private void Left() {if(!has(tText)) {if(selBut-1 >= minSel && !paused) {selBut--;highlight(selBut);}}}
    private void Right() {if(!has(tText)) {if(selBut+1 <= maxSel && !paused) {selBut++;highlight(selBut);}}}

    //Handles the button selection
    private void Select(int x)
    {
        if(!has(tText))
        {
            if(!paused)
            {
                if(selingEnmAtk) 
                {
                    int z = -1;
                    for(int i=0; i<3;i++) 
                    {
                        if(bbE[i].selected) {z = i;}
                    }
                    if(z != -1) 
                    {
                        if(!(selAtk.getId().equals("TWINSTRIKE"))) {bbE[z].select(false); turnPhaze(bbE[z], selAtk);selingEnmAtk = false;}

                        else {bbE[z].select(false); turnPhaze(bbE[z], selAtk, "");selingEnmAtk = false;}
                    }
                    highlight(selBut);
                }

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
                                    bPanel.remove(bb[3]);bPanel.remove(bb[4]);bPanel.remove(bb[5]);
                                    bPanel.add(bb[6]);bPanel.add(bb[7]);bPanel.add(bb[8]);
                                    minSel = 6; maxSel = 8;selBut = 8;highlight(8);
                                }

                                else
                                {
                                    bPanel.remove(bb[6]);bPanel.remove(bb[7]);bPanel.remove(bb[8]);
                                    bPanel.add(bb[3]);bPanel.add(bb[4]);bPanel.add(bb[5]);
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
                                    bPanel.remove(bb[9]);bPanel.remove(bb[10]);bPanel.remove(bb[11]);
                                    bPanel.add(bb[12]);bPanel.add(bb[13]);bPanel.add(bb[14]);
                                    minSel = 12; maxSel = 14;selBut = 14;highlight(14);
                                }

                                else
                                {
                                    bPanel.remove(bb[12]);bPanel.remove(bb[13]);bPanel.remove(bb[14]);
                                    bPanel.add(bb[9]);bPanel.add(bb[10]);bPanel.add(bb[11]);
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
                                    bPanel.remove(bb[15]);bPanel.remove(bb[16]);bPanel.remove(bb[17]);
                                    bPanel.add(bb[18]);bPanel.add(bb[19]);bPanel.add(bb[20]);
                                    minSel = 18; maxSel = 20;selBut = 20;highlight(20);
                                }

                                else
                                {
                                    bPanel.remove(bb[18]);bPanel.remove(bb[19]);bPanel.remove(bb[20]);
                                    bPanel.add(bb[15]);bPanel.add(bb[16]);bPanel.add(bb[17]);
                                    minSel = 15; maxSel = 17;selBut = 17;highlight(17);
                                }
                            }
                        }
                    }

                    else {for(int i = 3; i<21; i++) {if(has(bb[i])) {bPanel.remove(bb[i]);}} for(int i=0;i<3;i++) {bPanel.add(bb[i]);}minSel = 0;maxSel = 2;selBut = 0;highlight(0);down = true;bPanel.remove(bbExit);bPanel.repaint();}
                }
            }
        }
    }
    private void atkSel(Atk z) 
    {
        selAtk = z;selingEnmAtk = true;
        highlight(-2);
        if(bbE[0].Enm.isAlive()) {bbE[0].select(true);selEnm = 0;}
        else if(bbE[1].Enm.isAlive()) {bbE[1].select(true);selEnm = 1;}
        else if(bbE[2].Enm.isAlive()) {bbE[2].select(true);selEnm = 2;}
    }

    //Player turn
    private void turnPhaze(int y)
    {
        tText.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        switch (y)
        {
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
            if(y instanceof atkItm itm) {turnPhaze((Actions)(itm.getAtk()));}

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
                        bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());
                        tText.setSize(tText.getWidth()+10, tText.getHeight());
                        bPanel.add(tText);bPanel.repaint();
                    }

                    else
                    {
                        if(count==b.length+10) 
                        {
                            if(!x.Enm.isAlive()) {bPanel.remove(x.enmImg);}

                            bPanel.remove(tText);
                            bPanel.repaint();
                            timer.cancel();
                            enmBattlePhaze();
                        }
                    }
                    count++;
                }
            }
        };

        bPanel.add(tText);
        timer.scheduleAtFixedRate(task, 0, 100);
    }
    private void turnPhaze(disEnm x, Atk y, String z)
    {
        //Specificaly ONLY for twin strike atk
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
                        bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());
                        tText.setSize(tText.getWidth()+10, tText.getHeight());
                        bPanel.add(tText);bPanel.repaint();
                    }

                    else
                    {
                        if(count==b.length+10) 
                        {
                            if(!x.Enm.isAlive()) {bPanel.remove(x.enmImg);looped++;}

                            if(looped==0)
                            {
                                looped++;
                                count = 0;
                                tText.setText("");
                                bPanel.remove(tText);
                                bPanel.repaint();
                            }

                            else
                            {
                                bPanel.remove(tText);
                                bPanel.repaint();
                                timer.cancel();
                                enmBattlePhaze();  
                            }
                        }
                    }
                    count++;
                }
            }
        };

        bPanel.add(tText);
        timer.scheduleAtFixedRate(task, 0, 100);
    }
    private void turnPhaze(Atk y)
    {
        //Incase of a swing atk
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
                {
                    public void run() 
                    {
                        if(!paused)
                        {
                            if(count<b.length) {bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                            tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                            bPanel.add(tText);bPanel.repaint();}
                            else
                            {
                                if(count==b.length+10) 
                                {
                                    if(!bbE[wideLoop].Enm.isAlive()) 
                                    {
                                        bPanel.remove(bbE[wideLoop].enmImg);
                                    }
                                    bPanel.remove(tText);
                                    bPanel.repaint();
                                    timer.cancel();
                                    if(wideLoop<2)
                                    {  
                                        wideLoop++;
                                        turnPhaze(y);
                                    }
                                    else {looped = 0; enmBattlePhaze();}
                                }
                            }
                            count++;
                        }
                    }
                };

                bPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
            }
        }
        
        else {atkSel(y);}
    }
    private void turnPhaze(Skl y)
    {
        if(!y.getId().equals("RAGE"))
        {
            if(y instanceof atkSkl skl) 
            {
                if(skl.getAtk().swing()) {turnPhaze(skl.getAtk());}

                else {atkSel(skl.getAtk());}
            }

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
                            if(count<b.length) {bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                            tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                            bPanel.add(tText);bPanel.repaint();}
                            else{if(count==b.length+10) {bPanel.remove(tText);bPanel.repaint();timer.cancel();enmBattlePhaze();}}count++;}}};

                bPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
            }
        }

        //Runs the rage skl
        else
        {
            //Checks if player can rage
            if(!character.actRage())
            {
                count = 0; looped = 0;
                Timer timer = new Timer();
                b = "Can't Rage".split("");
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
                            if(count<b.length) {bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                            tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                            bPanel.add(tText);bPanel.repaint();}
                            else{if(count==b.length+10) {bPanel.remove(tText);bPanel.repaint();timer.cancel();}}count++;}}};

                bPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
            }

            else
            {
                count = 0; looped = 0;
                Timer timer = new Timer();
                b = "Rageing".split("");
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
                            if(count<b.length) {bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                            tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                            bPanel.add(tText);bPanel.repaint();}
                            else{if(count==b.length+10) {bPanel.remove(tText);bPanel.repaint();timer.cancel();}}count++;}}};

                bPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
            }
        }
    }
    private void turnPhaze(Actions z)
    {
        //Main turnPhaze to check for special atk/skls
        //Regens player hp
        if(character.regenT>0) {character.doHp(character.regenAmt);}

        //If not charging
        if(character.chargeT==0)
        {
            if(z.stat() != 0)
            {
                switch (z.stat()) {
                    case 1 -> 
                    {
                        character.chargeT = z.statT()+1;
                        chargePhaze();
                    }
                    case 2 -> 
                    {
                        character.regenT += z.statT();
                        character.regenAmt = z.regenAmt();
                    }
                    case 3 -> character.regenT = z.statT();
                    case 4 -> character.atkupT = z.statT();
                }
            }

            else
            {
                if(z instanceof Atk atk) {turnPhaze(atk);}

                else {turnPhaze((Skl)z);}
            }
        }
        
        //Handles the charge atk
        else
        {
            if(character.chargeT>0)
            {
                if(character.chargeT==1)
                {
                    if(z instanceof Atk atk) {turnPhaze(atk);}

                    else {turnPhaze((Skl)z);}
                }

                else {chargePhaze();}
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
            if(count<b.length) {bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
            tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());bPanel.add(tText);bPanel.repaint();}
            else {if(count==b.length+10) {bPanel.remove(tText);bPanel.repaint();bPanel.remove(tText);timer.cancel();enmBattlePhaze();}}count++;
        }}};

        bPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
    }
    
    //Enm turn, also handles winning/losing
    private void enmBattlePhaze()
    {        
        count = 0;
        tText.setBorder(BorderFactory.createLineBorder(Color.red, 5));
        tText.setText("");
        tText.setLocation(500, 220);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        Timer timer = new Timer();
        if(bossMan.Enm.isAlive()) 
        {
            //Special atk
            if((int)(Math.random()*5)==0) {b = new BattleAssets().attack(character, bossMan.Enm.getAtk(1)).split("");}
            
            else {b = new BattleAssets().attack(character, bossMan.Enm.getAtk(0)).split("");}

            taskN = new TimerTask()
            {
                public void run() 
                {
                    if(!paused)
                    {
                        if(count<b.length) {bPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                        bPanel.add(tText);bPanel.repaint();}
                        else
                        {
                            if(count==b.length+10) 
                            {
                                if(character.isAlive()) {bPanel.remove(tText);bPanel.repaint();timer.cancel();looped++;enmBattlePhaze();}

                                else {gEnd(false);timer.cancel();}
                            }
                        }count++;
                    }
                }
            };

            z = (" " + bossMan.Enm.getName() + " is bleeding").split("");
            taskB = new TimerTask()
            {
                public void run() 
                {
                    if(!paused)
                    {
                        if(count<z.length) {bPanel.remove(tText);tText.setText(tText.getText() + z[count]);
                        tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                        bPanel.add(tText);bPanel.repaint();}
                        else
                        {
                            if(count==z.length+10) 
                            {
                                if(character.isAlive()) 
                                {
                                    count = 0;tText.setText("");bPanel.remove(tText);bPanel.repaint();
                                    hpReset();bPanel.add(tText);taskB.cancel();timer.scheduleAtFixedRate(taskN, 0, 100);
                                }
                                
                                else {gEnd(false);timer.cancel();}
                            }
                        }count++;
                    }
                }
            };
            
            y = (" " + bossMan.Enm.getName() + " was burnt").split("");
            taskF = new TimerTask()
            {
                public void run() 
                {
                    if(!paused)
                    {
                        if(count<y.length) {bPanel.remove(tText);tText.setText(tText.getText() + y[count]);
                        tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                        bPanel.add(tText);bPanel.repaint();}
                        else
                        {
                            if(count==y.length+10) 
                            {
                                if(character.isAlive()) 
                                {
                                    bPanel.remove(tText);bPanel.repaint();
                                    if(bossMan.Enm.bleed())
                                    {
                                        count = 0;tText.setText("");bossMan.Enm.bleedTurn();bossMan.Enm.doHp(-2);
                                        hpReset();bPanel.add(tText);taskF.cancel();timer.scheduleAtFixedRate(taskB, 0, 100);   
                                    }

                                    else {count = 0;tText.setText("");hpReset();bPanel.add(tText);taskF.cancel();timer.scheduleAtFixedRate(taskN, 0, 100);}
                                }

                                else {gEnd(false);timer.cancel();}
                            }
                        }count++;
                    }
                }
            };

            if(!bossMan.Enm.burnt() && !bossMan.Enm.bleed()) {hpReset();bPanel.add(tText);timer.scheduleAtFixedRate(taskN, 0, 100);}

            else if(bossMan.Enm.burnt())
            {
                bossMan.Enm.burnTurn();bossMan.Enm.doHp(-2);
                hpReset();bPanel.add(tText);timer.scheduleAtFixedRate(taskF, 0, 100);
            }

            else
            {
                bossMan.Enm.bleedTurn();bossMan.Enm.doHp(-2);
                hpReset();bPanel.add(tText);timer.scheduleAtFixedRate(taskB, 0, 100);
            }
        }

        else if(looped<2) {looped++;enmBattlePhaze();hpReset();} 
        
        else if(!bossMan.Enm.isAlive()) {gEnd(true);}

        else if(looped>=2) {character.nextTurn();}
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
            kronk.remove(bPanel);
            if(j.getText().equals("Next")) {new RewGUI(character, kronk).initialize();}

            else {new LevelGUI(character, kronk).initialize();}
        }
    }
    private void keyActions()
    {
        bPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        bPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        bPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Select");
        bPanel.getActionMap().put("Select", new AbstractAction() {public void actionPerformed(ActionEvent e) {Select(selBut);}});
        bPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");
        bPanel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {if(down){Left();}}});
        bPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Right");
        bPanel.getActionMap().put("Right", new AbstractAction() {public void actionPerformed(ActionEvent e) {if(down){Right();}}});
        bPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        bPanel.getActionMap().put("Down", new AbstractAction() {public void actionPerformed(ActionEvent e) {Down();}});
        bPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        bPanel.getActionMap().put("Up", new AbstractAction() {public void actionPerformed(ActionEvent e) {Up();}});
        bPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('i'), "Info");
        bPanel.getActionMap().put("Info", new AbstractAction() {public void actionPerformed(ActionEvent e) {kronk.remove(bPanel);new InfoGUI(bPanel, kronk).initialize();}});
    }

    //Various helpers, hell if I know
    private boolean has(Component x) {for (Component c : bPanel.getComponents()) {if (c == x) {return true;}} return false;}
    private void exit() {if(set.isVisible()) {pause();} else {character.resetHP();kronk.remove(bPanel);new LevelGUI(character, kronk).initialize();}}
    private void atk() {for(int i=0; i<3; i++) {bPanel.remove(bb[i]);}bPanel.add(bbExit);bPanel.add(bb[3]);bPanel.add(bb[4]);bPanel.add(bb[5]);bPanel.repaint();}
    private void skl() {for(int i=0; i<3; i++) {bPanel.remove(bb[i]);}bPanel.add(bbExit);bPanel.add(bb[9]);bPanel.add(bb[10]);bPanel.add(bb[11]);bPanel.repaint();}
    private void itm() {for(int i=0; i<3; i++) {bPanel.remove(bb[i]);}bPanel.add(bbExit);bPanel.add(bb[15]);bPanel.add(bb[16]);bPanel.add(bb[17]);bPanel.repaint();}
    private void actBattleButtons() {bb = bGUI.actBattleButtons();highlight(0);for(int i=0; i<3; i++) {bb[i].setVisible(true);bPanel.add(bb[i]);}pauButtons();atkBattleButtons();sklBattleButtons();itmBattleButtons();}
    private void start() {bPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Battle Level/BB" + ((int)(Math.random()*4 + 1)) +".png").getImage(), 0);kronk.setLocationRelativeTo(null);battleInit();}  
    private void highlight(int x)
    {
        for (Component c : bPanel.getComponents()) {if (c instanceof JLabel && !c.equals(charSprite) && !c.equals(tText) && !c.equals(bossMan.enmImg))
            
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
            pau.setLocation(bPanel.getWidth()/2-2500, bPanel.getHeight()/2-2650);
            settings.setText("Settings");
            bPanel.add(settings);bPanel.add(exit);bPanel.add(pau);
            exit.setLocation((bPanel.getWidth()/2)-100, (bPanel.getHeight()/2));
            settings.setLocation((bPanel.getWidth()/2-100), (bPanel.getHeight()/2-100));settings.setVisible(true);
            settings.setEnabled(true);
            exit.setVisible(true);
            exit.setEnabled(true);
            exit.setText("Exit");
            pau.setVisible(true);
        }
        
        bPanel.add(charSprite);bPanel.add(hp);
        for(int i=0;i<3;i++) {if(has(bbE[i].enmImg)) {bPanel.add(bbE[i].enmImg);bbE[i].enmImg.setVisible(true);}}
        if(has(bbExit)) {bPanel.add(bbExit);bbExit.setVisible(true);}
        if(has(tText)) {tText.setVisible(true);bPanel.add(tText);tText.setVisible(true);}
        for(int i = 0; i<21; i++) {if(has(bb[i])) {bPanel.add(bb[i]);bb[i].setVisible(true);}}

        paused ^= true;
    }    
    private void settings()
    {
        bPanel.remove(charSprite);
        for(int i=0;i<3;i++) {if(has(bbE[i].enmImg)) {bbE[i].enmImg.setVisible(false);}}
        if(has(bbExit)) {bbExit.setVisible(false);}if(has(tText)) {tText.setVisible(false);}bPanel.remove(hp);
        for(int i = 0; i<21; i++) {if(has(bb[i])) {bb[i].setVisible(false);}}

        pau.setVisible(false);set.setEnabled(false);set.setForeground(Color.white);set.setVisible(true);set.setBackground(Color.BLACK);
        set.setFont(new Font(set.getFont().getName(), Font.BOLD, 40));set.setText("Settings"); 
        exit.setText("Back");settings.setText("Volume");settings.setEnabled(false);
        vole.setFont(new Font(vole.getFont().getName(), Font.BOLD, 40));vole.addActionListener(this);vole.setSize(new Dimension(125,100));vole.setBackground(Color.black);
        vole.setForeground(Color.white);vole.setLocation((bPanel.getWidth()/2-50)+150, (bPanel.getHeight()/2-100));
        vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(character.getVol()));
        bPanel.add(vole);set.setLocation(bPanel.getWidth()/2-2500, bPanel.getHeight()/2-2650);bPanel.add(set);
        vole.setFocusable(false);
    }
    private void gEnd(boolean x)
    {
        if(((int)(Math.random())*20) == 19 && !x) {SQUONKINIT();}

        else
        {
            bPanel.remove(tText);bPanel.repaint();
            bPanel.getActionMap().clear();highlight(-2);
            bPanel.remove(pau);
            
            pau.setForeground(Color.white);
            pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));
            pau.setBackground(new Color(0,0,0,200));
            pau.setLocation(bPanel.getWidth()/2-2500, bPanel.getHeight()/2-2650);
            bPanel.add(pau);
            bPanel.add(charSprite);
            for(int i=0;i<3;i++) {if(has(bbE[i].enmImg)) {bPanel.add(bbE[i].enmImg);}}
            if(has(bbExit)) {bPanel.add(bbExit);}
            if(has(tText)) {tText.setVisible(true);bPanel.add(tText);}
            for(int i = 0; i<21; i++) {if(has(bb[i])) {bPanel.add(bb[i]);}}
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
                bPanel.setComponentZOrder(pau, 0);
                bPanel.repaint();
                character.resetHP();
                bbWL.setText("Next");

                character.nextLvl();
            }

            else
            {
                character.reset();
                pau.setText("You Lose");
                bPanel.setComponentZOrder(pau, 0);
                bPanel.repaint();
                character.resetHP();
                bbWL.setText("Retry?");
                bPanel.repaint();
                character.reset();
            }

            character.resetTurn();bPanel.add(bbWL);bPanel.setComponentZOrder(bbWL, 0);character.saveGame();
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
        f = 0;
        Timer timer = new Timer();
        count = 0;
        String [] b = " Squonk has saved you.".split("");
        tText.setText("");
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        tText.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));

        cut = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Cut/CircleOfSquonk.gif").getImage(), 2);
        bPanel.removeAll();kronk.remove(bPanel);kronk.add(cut);kronk.revalidate();kronk.repaint();
        TimerTask task = new TimerTask() 
        {
            public void run() 
            {
                if(f==40)
                {
                    kronk.remove(cut);
                    bPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Sprites/SquonkingIt.png").getImage(), 0);
                    kronk.add(bPanel);
                    kronk.revalidate();
                    kronk.repaint();
                    bPanel.add(tText);
                }

                if(f>=40) 
                {
                    if(!(count<5) && count<b.length+5) 
                    {
                        tText.setText(tText.getText() + b[count-5]);
                        tText.setSize(tText.getPreferredSize());
                        tText.setLocation(500 - tText.getWidth()/2, 450-tText.getHeight()/2);
                        bPanel.add(tText);
                        //bPanel.setComponentZOrder(tText, 0);
                    }

                    else if(count>b.length+27) {timer.cancel();character.resetHP();kronk.remove(bPanel);new LevelGUI(character, kronk).initialize();}
                    count++;
                }
                f++;
            }
        };
        
        timer.scheduleAtFixedRate(task, 0, 100);
    }
}