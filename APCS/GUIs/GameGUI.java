package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import APCS.Assets.AssetClasses.*;
import APCS.Enms.*;
import APCS.*;
import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Items.Itm;
import APCS.Items.AttackItem.atkItm;
import APCS.Items.SkillItem.sklItm;

public class GameGUI extends JFrame implements ActionListener
{
    private Player character;
    private JPanel lPanel;
    private BattleAssets bGUI;
    private LevelGUI lGUI;

    private JLabel charSprite, hp = new JLabel();

    //bb 0-2 (Atk, Skl, Itm), bb 3-8 (Atk 1-4), bb 9-14 (Skl 1-4), bb 15-20 (Itm 1-4)
    //Im sorry, this is the worst way to do it, and I fucking love it
    private JLabel [] bb;
    private JButton bbExit;
    private disEnm [] bbE;
    private Atk selAtk;

    //Pause items
    private JButton pau = new JButton(), set = new JButton(), exit = new JButton(), settings = new JButton(), vole = new JButton();
    private boolean paused = false;

    //Win/Lose buttons
    private JButton bbWL = new JButton();

    //Control the scrolling text
    private JLabel tText = new JLabel();
    private int count = 0,looped = 0, wideLoop = 0;
    private String [] b;

    //Control the selected button/enm
    private boolean selingEnmAtk = false;
    private int selEnm = 0, selBut = 0, minSel = 0;
    private int maxSel = 2;
    private boolean down = true;

    //Cutscene
    private String [] scenes = {"sce"};
    private int [] frameCount = {5};
    private int frames,f = 0;

    public GameGUI(Player character) {this.character = character;}

    //Initilizes the battle items
    private void battleInit()
    {
        this.pack();
        this.add(lPanel);
        bGUI = new BattleAssets();
        bbExit = bGUI.bbExit();
        lPanel.setLayout(null);
        this.setSize(1500, 800);
        battleImg();enmBattleButtons();actBattleButtons();

        hp.setBackground(new Color(179, 9, 9));
        hp.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        hp.setForeground(Color.black);
        hp.setFont(new Font(hp.getFont().getName(), Font.BOLD, 40));
        hp.setText(String.valueOf("HP: " + character.getHealth()));
        hp.setSize(hp.getPreferredSize());
        hp.setSize((hp.getWidth()+10),(hp.getHeight()+10));
        hp.setLocation(0,40);
        hp.setOpaque(true);
        lPanel.add(hp);
        lPanel.setComponentZOrder(hp,0);

        lPanel.revalidate();
        lPanel.repaint();
        keyActions();
    }

    //Repaints the hp icon
    private void hpReset()
    {
        hp.setText(String.valueOf("HP: " + character.getHealth()));
        hp.setSize(hp.getPreferredSize());
        hp.setSize((hp.getWidth()+10),(hp.getHeight()+10));
        hp.setLocation(0,40);
        lPanel.revalidate();
        lPanel.repaint();
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
        if(!has(tText))
        {
            if(!selingEnmAtk && !paused)
            {
                if(down && !has(bb[0])) {highlight(-1);down = false;}

                else if(!down && !has(bb[0]))
                {
                    if(has(bb[3])) {highlight(3);}

                    else if(has(bb[6])) {highlight(6);}

                    else if(has(bb[9])) {highlight(9);}

                    else if(has(bb[12])) {highlight(12);}

                    else if(has(bb[15])) {highlight(15);}

                    else if(has(bb[18])) {highlight(18);}

                    down = true;
                }
            }

            else if(!paused) 
            {
                if(selEnm - 1>=0 && bbE[selEnm - 1].Enm.isAlive() || selEnm - 2>=0 && bbE[selEnm - 2].Enm.isAlive()) 
                {
                    if(selEnm - 1>=0 && bbE[selEnm - 1].Enm.isAlive()){selEnm--;}

                    else {selEnm -= 2;}

                    for(int i=0; i<3; i++) 
                    {
                        bbE[i].select(false);
                    } 
                    bbE[selEnm].select(true);
                }
            }
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
                            if(x==0) {atk();minSel = 3; maxSel = 5;selBut = 3;highlight(3);}

                            else if(x==1) {skl();minSel = 9; maxSel = 11;selBut = 9;highlight(9);}

                            else if(x==2) {itm();minSel = 15; maxSel = 17;selBut = 15;highlight(15);}
                        }
                        

                        //Atk buttons
                        else if(x>=3 && x<=8)
                        {
                            if(!character.froze() && !bb[x].getText().equals("Next") &&!bb[x].getText().equals("X")) {turnPhaze(x);}

                            else if(!bb[x].getText().equals("Next") && !bb[x].getText().equals("X")) {frozPhaze();}

                            else if(bb[x].getText().equals("Next"))
                            {
                                if(x==5)
                                {
                                    lPanel.remove(bb[3]);lPanel.remove(bb[4]);lPanel.remove(bb[5]);
                                    lPanel.add(bb[6]);lPanel.add(bb[7]);lPanel.add(bb[8]);
                                    minSel = 6; maxSel = 8;selBut = 8;highlight(8);
                                }

                                else
                                {
                                    lPanel.remove(bb[6]);lPanel.remove(bb[7]);lPanel.remove(bb[8]);
                                    lPanel.add(bb[3]);lPanel.add(bb[4]);lPanel.add(bb[5]);
                                    minSel = 3; maxSel = 5;selBut = 5;highlight(5);
                                }
                            }
                        }

                        //Skl buttons
                        else if(x>=9 && x<=14)
                        {
                            if(!character.froze() && !bb[x].getText().equals("Next") &&!bb[x].getText().equals("X")) {turnPhaze(x);}

                            else if(!bb[x].getText().equals("Next") && !bb[x].getText().equals("X")) {frozPhaze();}

                            else if(bb[x].getText().equals("Next"))
                            {
                                if(x==11)
                                {
                                    lPanel.remove(bb[9]);lPanel.remove(bb[10]);lPanel.remove(bb[11]);
                                    lPanel.add(bb[12]);lPanel.add(bb[13]);lPanel.add(bb[14]);
                                    minSel = 12; maxSel = 14;selBut = 14;highlight(14);
                                }

                                else
                                {
                                    lPanel.remove(bb[12]);lPanel.remove(bb[13]);lPanel.remove(bb[14]);
                                    lPanel.add(bb[9]);lPanel.add(bb[10]);lPanel.add(bb[11]);
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
                                    lPanel.remove(bb[15]);lPanel.remove(bb[16]);lPanel.remove(bb[17]);
                                    lPanel.add(bb[18]);lPanel.add(bb[19]);lPanel.add(bb[20]);
                                    minSel = 18; maxSel = 20;selBut = 20;highlight(20);
                                }

                                else
                                {
                                    lPanel.remove(bb[18]);lPanel.remove(bb[19]);lPanel.remove(bb[20]);
                                    lPanel.add(bb[15]);lPanel.add(bb[16]);lPanel.add(bb[17]);
                                    minSel = 15; maxSel = 17;selBut = 17;highlight(17);
                                }
                            }
                        }
                    }

                    else {for(int i = 3; i<21; i++) {if(has(bb[i])) {lPanel.remove(bb[i]);}} for(int i=0;i<3;i++) {lPanel.add(bb[i]);}minSel = 0;maxSel = 2;selBut = 0;highlight(0);down = true;lPanel.remove(bbExit);lPanel.repaint();}
                }
            }
        }
    }

    //Player turn
    private void turnPhaze(int y)
    {
        switch (y) {
            case 3:turnPhaze(character.atks[0]);break;
            case 4:turnPhaze(character.atks[1]);break;
            case 6:turnPhaze(character.atks[2]);break;
            case 7:turnPhaze(character.atks[3]);break;
            case 9:turnPhaze(character.skls[0]);break;
            case 10:turnPhaze(character.skls[1]);break;
            case 12:turnPhaze(character.skls[2]);break;
            case 13:turnPhaze(character.skls[3]);break;
            case 15:turnPhaze(character.itms[0], 0);break;
            case 16:turnPhaze(character.itms[1], 1);break;
            case 18:turnPhaze(character.itms[2], 2);break;
            case 19:turnPhaze(character.itms[3], 3);break;
            default:break;
        }
        hpReset();
    }
    private void turnPhaze(Itm y, int z)
    {
        if(y != null)
        {
            if(y instanceof atkItm) {if(!((atkItm)y).getAtk().swing()) {atkSel(((atkItm)y).getAtk());} else {turnPhaze(((atkItm)(y)).getAtk());}}
            
            else {turnPhaze((Skl)((sklItm)y).getSkill());}itmUsed(z);
        }
        
    }
    private void turnPhaze(disEnm x, Atk y)
    {
        count = 0;
        looped = 0;

        Timer timer = new Timer();
        b = new BattleAssets().attack(x.Enm, y).split("");
        tText.setText("");
        tText.setLocation(350, 120);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        TimerTask task = new TimerTask()
        {public void run() 
            {
                if(!paused)
                {
                    if(count<b.length) {lPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                    tText.setSize(tText.getPreferredSize());
                    tText.setSize(tText.getWidth()+10, tText.getHeight());
                    lPanel.add(tText);lPanel.repaint();}
                    else{if(count==b.length+10) 
                    {
                        if(!x.Enm.isAlive()) {lPanel.remove(x.enmButton);}
                        lPanel.remove(tText);lPanel.repaint();lPanel.remove(tText);timer.cancel();enmBattlePhaze();
                    }
                    }count++;}}};

        lPanel.add(tText);
        timer.scheduleAtFixedRate(task, 0, 100);
    }
    private void turnPhaze(Atk y)
    {
        if(y.swing())
        {
            count = 0;
            tText.setText("");
            tText.setLocation(350, 120);
            tText.setOpaque(true);
            tText.setBackground(Color.WHITE);
            tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
            Timer timer = new Timer();
            if(wideLoop <=2 && bbE[wideLoop].Enm.isAlive()) 
            {
                b = new BattleAssets().attack(bbE[wideLoop].Enm, y).split("");
                TimerTask task = new TimerTask()
                {public void run() 
                    {if(!paused)
                    {
                        if(count<b.length) {lPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                        lPanel.add(tText);lPanel.repaint();}
                        else{if(count==b.length+10) 
                        {
                            if(!bbE[wideLoop].Enm.isAlive()) {lPanel.remove(bbE[wideLoop].enmButton);}
                            lPanel.remove(tText);lPanel.repaint();timer.cancel();if(wideLoop<2){turnPhaze(y);}
                            else {looped = 0; enmBattlePhaze();}
                        }}count++;
                    }}};

                lPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
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
            tText.setLocation(350, 120);
            tText.setOpaque(true);
            tText.setBackground(Color.WHITE);
            tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
            TimerTask task = new TimerTask()
            {public void run() 
                {
                    if(!paused)
                    {
                        if(count<b.length) {lPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                        tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                        lPanel.add(tText);lPanel.repaint();}
                        else{if(count==b.length+10) {lPanel.remove(tText);lPanel.repaint();timer.cancel();enmBattlePhaze();}}count++;}}};

            lPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);
        }
    }
    
    //Runs the turn when the player is constricted
    private void frozPhaze()
    {
        count = 0; looped = 0;

        Timer timer = new Timer();
        b = (" " + character.getName() + " is constricted").split("");
        tText.setText("");
        tText.setLocation(350, 120);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        TimerTask task = new TimerTask()
        {public void run() {if(!paused)
        {
            if(count<b.length) {lPanel.remove(tText);tText.setText(tText.getText() + b[count]);
            tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());lPanel.add(tText);lPanel.repaint();}
            else {if(count==b.length+10) {lPanel.remove(tText);lPanel.repaint();lPanel.remove(tText);timer.cancel();enmBattlePhaze();}}count++;
        }}};

        lPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);character.freeze(false);
    }
    
    //Enm turn, also handles winning/losing
    private void enmBattlePhaze()
    {
        count = 0;
        tText.setText("");
        tText.setLocation(350, 120);
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
                    if(count<b.length) {lPanel.remove(tText);tText.setText(tText.getText() + b[count]);
                    tText.setSize(tText.getPreferredSize());tText.setSize(tText.getWidth()+10, tText.getHeight());
                    lPanel.add(tText);lPanel.repaint();}
                    else{if(count==b.length+10) 
                    {
                        if(character.isAlive()) {lPanel.remove(tText);lPanel.repaint();timer.cancel();enmBattlePhaze();}

                        else {gEnd(false);timer.cancel();}
                    }}count++;
                }}};

            hpReset();
            lPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);looped++;
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
            if(bbWL.getText().equals("Next")) {cutScene("sce");}

            else {close();lGUI = new LevelGUI(character);lGUI.displayGame();}
        }
    }
    private void keyActions()
    {
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        lPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Select");
        lPanel.getActionMap().put("Select", new AbstractAction() {public void actionPerformed(ActionEvent e) {Select(selBut);}});
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");
        lPanel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {if(down){Left();}}});
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Right");
        lPanel.getActionMap().put("Right", new AbstractAction() {public void actionPerformed(ActionEvent e) {if(down){Right();}}});
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "UpDown");
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "UpDown");
        lPanel.getActionMap().put("UpDown", new AbstractAction() {public void actionPerformed(ActionEvent e) {UpDown();}});
    }

    //Various helpers
    private void displayGame() {setDefaultCloseOperation(EXIT_ON_CLOSE);this.pack();this.setTitle("Into the Dreamscape");this.setVisible(true);this.setResizable(true);this.pack();java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
    private boolean has(Component x) {for (Component c : lPanel.getComponents()) {if (c == x) {return true;}} return false;}
    private void exit() {if(set.isVisible()) {pause();} else {}}
    private void battleImg() {charSprite = bGUI.battleImg(character);lPanel.add(charSprite);}
    private void enmBattleButtons() {bbE = bGUI.enmBattleButtons(); for(int i=0; i<3; i++) {bbE[i].enmButton.setVisible(true);bbE[i].enmButton.addActionListener(this);lPanel.add(bbE[i].enmButton);}}
    private void atk() {for(int i=0; i<3; i++) {lPanel.remove(bb[i]);}lPanel.add(bbExit);lPanel.add(bb[3]);lPanel.add(bb[4]);lPanel.add(bb[5]);lPanel.repaint();}
    private void skl() {for(int i=0; i<3; i++) {lPanel.remove(bb[i]);}lPanel.add(bbExit);lPanel.add(bb[9]);lPanel.add(bb[10]);lPanel.add(bb[11]);lPanel.repaint();}
    private void itm() {for(int i=0; i<3; i++) {lPanel.remove(bb[i]);}lPanel.add(bbExit);lPanel.add(bb[15]);lPanel.add(bb[16]);lPanel.add(bb[17]);lPanel.repaint();}
    private void actBattleButtons() {bb = bGUI.actBattleButtons();highlight(0);for(int i=0; i<3; i++) {bb[i].setVisible(true);lPanel.add(bb[i]);}pauButtons();atkBattleButtons();sklBattleButtons();itmBattleButtons();}
    private void atkSel(Atk z) {selAtk = z;selingEnmAtk = true;highlight(-2);for(int i=0;i<3;i++) {if(bbE[i].Enm.isAlive()) {bbE[i].select(true);selEnm = i;}}}
    private void highlight(int x)
    {
        for (Component c : lPanel.getComponents()) {if (c instanceof JLabel && !c.equals(charSprite) && !c.equals(tText))
            {((JLabel)c).setBorder(BorderFactory.createLineBorder(Color.black, 5));}}   

        if(x>=0) {bb[x].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));}

        else if(x==-1) {bbExit.setBorder(BorderFactory.createLineBorder(Color.black, 5));}
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
        this.setLocationRelativeTo(null);
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
            lPanel.add(charSprite);
            
            for(int i=0;i<3;i++) {if(has(bbE[i].enmButton)) {lPanel.add(bbE[i].enmButton);}}
            if(has(bbExit)) {lPanel.add(bbExit);}
            if(has(tText)) {tText.setVisible(true);lPanel.add(tText);}
            for(int i = 0; i<21; i++) {if(has(bb[i])) {lPanel.add(bb[i]);}}lPanel.add(hp);
        }

        else 
        {
            paused=true;
            pau.setForeground(Color.white);
            pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));pau.setText("Paused");pau.setBackground(new Color(0,0,0,200));
            pau.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2650);
            settings.setText("Settings");
            lPanel.add(settings);lPanel.add(exit);lPanel.add(pau);
            lPanel.add(charSprite);
            for(int i=0;i<3;i++) {if(has(bbE[i].enmButton)) {lPanel.add(bbE[i].enmButton);}}
            if(has(bbExit)) {lPanel.add(bbExit);}lPanel.add(hp);
            if(has(tText)) {tText.setVisible(true);lPanel.add(tText);}
            for(int i = 0; i<21; i++) {if(has(bb[i])) {lPanel.add(bb[i]);}}
            exit.setLocation((lPanel.getWidth()/2)-100, (lPanel.getHeight()/2));
            settings.setLocation((lPanel.getWidth()/2-100), (lPanel.getHeight()/2-100));settings.setVisible(true);settings.setEnabled(true);
            exit.setVisible(true);exit.setEnabled(true);exit.setText("Exit");pau.setVisible(true);
        }
    }    
    private void settings()
    {
        lPanel.remove(charSprite);
        for(int i=0;i<3;i++) {if(has(bbE[i].enmButton)) {lPanel.remove(bbE[i].enmButton);} lPanel.remove(bbE[i].enmButton);}
        if(has(bbExit)) {lPanel.remove(bbExit);}if(has(tText)) {tText.setVisible(true);lPanel.remove(tText);}
        for(int i = 0; i<21; i++) {if(has(bb[i])) {lPanel.remove(bb[i]);}}
        pau.setVisible(false);set.setEnabled(false);set.setForeground(Color.white);set.setVisible(true);set.setBackground(Color.BLACK);
        set.setFont(new Font(set.getFont().getName(), Font.BOLD, 40));set.setText("Settings"); 
        exit.setText("Back");settings.setText("Volume");settings.setEnabled(false);
        vole.setFont(new Font(vole.getFont().getName(), Font.BOLD, 40));vole.addActionListener(this);vole.setSize(new Dimension(125,100));vole.setBackground(Color.black);
        vole.setForeground(Color.white);vole.setLocation((lPanel.getWidth()/2-50)+150, (lPanel.getHeight()/2-100));
        vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(character.getVol()));
        lPanel.add(vole);set.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2650);lPanel.add(set);
        vole.setFocusable(false);
    }
    private void gEnd(boolean x)
    {
        lPanel.remove(tText);lPanel.repaint();
        lPanel.getActionMap().clear();highlight(-2);
        lPanel.remove(pau);
        
        pau.setForeground(Color.white);
        pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));
        pau.setBackground(new Color(0,0,0,200));
        pau.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2650);
        lPanel.add(pau);
        lPanel.add(charSprite);
        for(int i=0;i<3;i++) {if(has(bbE[i].enmButton)) {lPanel.add(bbE[i].enmButton);}}
        if(has(bbExit)) {lPanel.add(bbExit);}
        if(has(tText)) {tText.setVisible(true);lPanel.add(tText);}
        for(int i = 0; i<21; i++) {if(has(bb[i])) {lPanel.add(bb[i]);}}
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
            lPanel.setComponentZOrder(pau, 0);
            lPanel.repaint();
            character.resetHP();
            bbWL.setText("Next");

            if(character.getStage()<2) {character.setStage(character.getStage()+1);} else {character.setStage(0);character.setLevel(character.getLevel()+1);}
        }

        else
        {
            character.reset();
            pau.setText("You Lose");
            lPanel.setComponentZOrder(pau, 0);
            lPanel.repaint();
            character.resetHP();
            bbWL.setText("Retry?");
            lPanel.repaint();
            character.reset();
        }
        
        lPanel.add(bbWL);lPanel.setComponentZOrder(bbWL, 0);character.saveGame();
    }
    public void start()
    {
        lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Battle Level/BB" + ((int)(Math.random()*4 + 1)) +".png").getImage(), 0);
        close();this.setLocationRelativeTo(null);displayGame();battleInit();
    }
    private void itmUsed(int x) 
    {
        for(int i=x; i<3; i++)
        {
            character.itms[i] = character.itms[i+1];
        }

        character.itms[3] = null;itmBattleButtons();
    }
    private void cutScene(String scene)
    {
        this.setSize(900,900);
        f = 0;Timer timer = new Timer();

        for(int i = 0; i<frameCount.length; i++) {if(scenes[i].equals(scene)){frames = frameCount[i];}}

        TimerTask task = new TimerTask() {public void run() {if(f<frames) {nextFrame(f, scene);} else if(f==frames+5) {timer.cancel();close(); new LevelGUI(character).displayGame();}f++;}};
        
        timer.scheduleAtFixedRate(task, 0, 200);
    }
    private void nextFrame(int x, String scene)
    {
        lPanel.removeAll();this.remove(lPanel);
        lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Cut/" + scene + "/" + x + ".png").getImage(), 2);
        this.add(lPanel);this.revalidate();this.repaint();
    }
}