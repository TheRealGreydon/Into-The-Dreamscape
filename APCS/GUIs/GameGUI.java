package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import APCS.Assets.AssetClasses.*;
import APCS.Enms.*;
import APCS.*;
import APCS.Actions.Attacks.Atk;
import APCS.Actions.Skills.*;
import APCS.Items.AttackItem.atkItm;

public class GameGUI extends JFrame implements ActionListener
{
    private Player character;
    private JPanel lPanel;
    private BattleAssets bGUI;
    private LevelSelGUI lGUI;

    private JLabel charSprite;

    //bb 0-2 (Atk, Skl, Itm), bb 3-8 (Atk 1-4), bb 9-14 (Skl 1-4), bb 15-20 (Itm 1-4)
    //Im sorry, this is the worst way to do it, and I fucking love it
    private JButton [] bb;
    private JButton bbExit;
    private disEnm [] bbE;
    //Not entierly sure what this is but its important
    private int y;
    private Atk selAtk;

    //Pause items
    private JButton pau = new JButton(), set = new JButton(), exit = new JButton(), settings = new JButton(), vole = new JButton();
    private boolean paused = false;

    //Win/Lose buttons
    private JButton lose = new JButton();
    private JButton win = new JButton();

    //Control the scrolling text
    private JLabel tText = new JLabel();
    private int count = 0,looped = 0, wideLoop = 0;
    private String [] b;

    //Control the selected button/enm
    private boolean selingEnmAtk = false;
    private int selEnm = 0, selBut = 0, minSel =0;
    private int maxSel = 2;
    private boolean down = true;

    public GameGUI(Player character) {this.character = character;}

    //Makes the game
    public void start()
    {
        lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Battle Level/BB" + ((int)(Math.random()*4 + 1)) +".png").getImage(), 0);
        close();this.setLocationRelativeTo(null);displayGame();battleInit();
    }

    //Initilizes the battle items
    private void battleInit()
    {
        this.pack();
        this.add(lPanel);
        bGUI = new BattleAssets();
        bbExit = bGUI.bbExit();
        lPanel.setLayout(null);
        this.setSize(1500, 800);
        battleImg();
        enmBattleButtons();
        actBattleButtons();
        lPanel.revalidate();
        lPanel.repaint();
        keyActions();
    }

    //Runs the players turn
    private void playTurn(int type, int num)
    {
        if(type == 0) {if(!character.atks[num].swing()){atkSel();} else {wideLoop = 0;turnPhaze(character.atks[num]);}}
        else if(type == 1) {turnPhaze(character.skls[y]);}
        else if(type ==2) {if(character.itms[num].isAtk()) {if(!((atkItm)(character.itms[num])).getAtk().swing()) {atkSel();}}}
    }

    //Attack, skill, and item buttons
    private void atkBattleButtons() 
    {
        int x=0;
        for(int i=3; i<8; i++) 
        {
            if(i!=5 && i!=8)
            {
                if(character.atks[x] != null) 
                {
                    bb[i].setText(character.atks[x].getName());
                    bb[i].addActionListener(this);
                }
                
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
                if(character.itms[x] != null) 
                {
                    bb[i].setText(character.itms[x].getName());
                    bb[i].addActionListener(this);
                }

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
                if(character.skls[x] != null) 
                {
                    bb[i].setText(character.skls[x].getName());
                    bb[i].addActionListener(this);
                }
                
                else{bb[i].setText("X");} x++;
            }
        }
    }

    //Keybinds
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
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        lPanel.getActionMap().put("Up", new AbstractAction() {public void actionPerformed(ActionEvent e) {Up();}});
        lPanel.getActionMap().put("Down", new AbstractAction() {public void actionPerformed(ActionEvent e) {Down();}});
    }

    //Left, Right, Up and Down keybind actions
    private void Up()
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

    private void Down()
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
                if(selEnm + 1<=2 && bbE[selEnm + 1].Enm.isAlive() || selEnm + 2<=2 && bbE[selEnm + 2].Enm.isAlive()) 
                {
                    if(selEnm + 1<=2 && bbE[selEnm + 1].Enm.isAlive()){selEnm++;}

                    else {selEnm += 2;}

                    for(int i=0; i<3; i++) 
                    {
                        bbE[i].select(false);
                    } 
                    bbE[selEnm].select(true);
                }
            }
        }
    }

    private void Left() {if(!has(tText)){if(selBut-1 >= minSel && !paused && !selingEnmAtk) {selBut--;highlight(selBut);}}}

    private void Right() {if(!has(tText)){if(selBut+1 <= maxSel && !paused && !selingEnmAtk) {selBut++;highlight(selBut);}}}

    //Handles the button selection
    private void Select(int x)
    {
        if(!has(tText))
        {
            if(!paused && !selingEnmAtk)
            {
                if(down)
                {
                    if(x>=0 && x<=2)
                    {
                        if(x==0) {atk();minSel = 3; maxSel = 5;selBut = 3;highlight(3);}
                        else if(x==1) {skl();minSel = 9; maxSel = 11;selBut = 9;highlight(9);}
                        else if(x==2) {itm();minSel = 15; maxSel = 17;selBut = 15;highlight(15);}
                    }

                    else if(x>=3 && x<=8)
                    {
                        if(!bb[x].getText().equals("Next") && !bb[x].getText().equals("X")) {bb[x].doClick();}

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

                    else if(x>=9 && x<=14)
                    {
                        if(!bb[x].getText().equals("Next") && !bb[x].getText().equals("X")) {bb[x].doClick();}

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

                    else if(x>=15 && x<=20)
                    {
                        if(!bb[x].getText().equals("Next") && !bb[x].getText().equals("X")) {bb[x].doClick();}

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

                else
                {
                    for(int i = 3; i<21; i++)
                    {
                        if(has(bb[i])) {lPanel.remove(bb[i]);}

                        lPanel.add(bb[0]);lPanel.add(bb[1]);lPanel.add(bb[2]);
                        minSel = 0;maxSel = 2;selBut = 0;highlight(0);down = true;
                    }

                    lPanel.remove(bbExit);lPanel.repaint();
                }
            }
        
            else if(!paused && selingEnmAtk)
            {
                int z = -1;
                for(int i=0; i<3;i++) {if(bbE[i].selected) {z = i;}}

                if(z != -1) {bbE[z].select(false); turnPhaze(bbE[z], character.atks[y]);selingEnmAtk = false;}

                highlight(selBut);
            }
        }
    }

    //Selects the enm during a single enm attack
    private void atkSel() 
    {
        switch (selBut) {
            case 3:
                break;
            case 4:
                break;
            case 6:
                break;
            case 7:
                break;
            case 9:
                break;
            case 10:
                break;
            case 12:
                break;
            case 13:
                break;
            case 15:
                break;
            case 16:
                break;
            case 18:
                break;
            case 19:
                break;
            default:
                break;
        }
        selingEnmAtk = true;
        highlight(-2);
        if(bbE[0].Enm.isAlive())
        {
            bbE[0].select(true);
            selEnm = 0;
        }

        else if(bbE[1].Enm.isAlive())
        {
            bbE[1].select(true);
            selEnm = 1;
        }

        else if(bbE[2].Enm.isAlive())
        {
            bbE[2].select(true);
            selEnm = 2;
        }
    }

    //Player used attack
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

    //Player used attak that hits all enm
    private void turnPhaze(Atk y)
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

        else if(wideLoop <2)
        {
            wideLoop++;turnPhaze(y);
        }
    }

    //Player used skill
    private void turnPhaze(Skl y)
    {
        count = 0;
        looped = 0;

        Timer timer = new Timer();
        if(y.isHeal())
        {
            b = ("Heal " + ((healSkl)y).getHeal()).split("");
            character.doHp(((healSkl)y).getHeal());
        }
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
            if((int)(Math.random()*10)==0) {b = new BattleAssets().attack(character, bbE[looped].Enm.atks[1]).split("");}
            
            else {b = new BattleAssets().attack(character, bbE[looped].Enm.atks[0]).split("");}
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

            lPanel.add(tText);timer.scheduleAtFixedRate(task, 0, 100);looped++;
        }

        else if(looped<2) {looped++;enmBattlePhaze();}

        else if(!bbE[0].Enm.isAlive() && !bbE[1].Enm.isAlive() && !bbE[2].Enm.isAlive()) {gEnd(true);}
    }    

    //Handles end screen
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

        if(x)
        {
            character.resetHP();
            pau.setText("You Win!");
            win = new JButton();
            win.setBackground(new Color(0,0,0));
            win.setSize(new Dimension(200,100));
            win.setEnabled(true);
            win.setVisible(true);
            win.setForeground(Color.white);
            win.setFont(new Font(win.getFont().getName(), Font.BOLD, 40));
            win.setText("Next");
            win.setFocusable(false);
            win.addActionListener(this);
            win.setLocation(650,350);
            lPanel.setComponentZOrder(pau, 0);
            lPanel.add(win);
            lPanel.setComponentZOrder(win, 0);
            lPanel.repaint();
        }
        else
        {
            character.reset();
            pau.setText("You Win!");
            lose = new JButton();
            lose.setBackground(new Color(0,0,0));
            lose.setSize(new Dimension(200,100));
            lose.setEnabled(true);
            lose.setVisible(true);
            lose.setForeground(Color.white);
            lose.setFont(new Font(win.getFont().getName(), Font.BOLD, 40));
            lose.setText("Next");
            lose.setFocusable(false);
            lose.addActionListener(this);
            lose.setLocation(650,350);
            lPanel.setComponentZOrder(pau, 0);
            lPanel.add(lose);
            lPanel.setComponentZOrder(lose, 0);
            lPanel.repaint();
        }
    }

    //Button actions
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(character.getVol()+25>100) {character.setVol(0);}else{character.setVol(character.getVol()+25);}vole.setText(String.valueOf(character.getVol()));}

        else if(j.equals(bb[3])) {playTurn(0, 0);}

        else if(j.equals(bb[4])) {playTurn(0,1);}

        else if(j.equals(bb[6])) {playTurn(0,2);}

        else if(j.equals(bb[7])) {playTurn(0,3);}

        else if(j.equals(bb[9])) {playTurn(1,0);}
        
        else if(j.equals(bb[10])) {playTurn(1,1);}

        else if(j.equals(bb[12])) {playTurn(1,2);}

        else if(j.equals(bb[13])) {playTurn(1,3);}

        else if(j.equals(bb[15])) {playTurn(2,0);}

        else if(j.equals(bb[17])) {playTurn(2,1);}

        else if(j.equals(bb[19])) {playTurn(2,2);}

        else if(j.equals(bb[20])) {playTurn(2,3);}

        else if(j.equals(win)) {character.nextLvl();character.resetHP();close();lGUI = new LevelSelGUI(character);lGUI.displayGame();}

        else if(j.equals(lose)) {character.reset();close();lGUI = new LevelSelGUI(character);lGUI.displayGame();}
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
    private void highlight(int x)
    {
        for (Component c : lPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)
        && !c.equals(bbE[0].enmButton) && !c.equals(bbE[1].enmButton) && !c.equals(bbE[2].enmButton))
            {((JButton) c).setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));}}   

        if(x>=0) {bb[x].setBorder(BorderFactory.createLineBorder(Color.black, 5));}

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
            for(int i = 0; i<21; i++) {if(has(bb[i])) {lPanel.add(bb[i]);}}
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
            if(has(bbExit)) {lPanel.add(bbExit);}
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
        for(int i=0;i<3;i++) {if(has(bbE[i].enmButton)) {lPanel.remove(bbE[i].enmButton);}}
        lPanel.remove(bbE[0].enmButton);
        lPanel.remove(bbE[1].enmButton);
        lPanel.remove(bbE[2].enmButton);
        if(has(bbExit)) {lPanel.remove(bbExit);}
        if(has(tText)) {tText.setVisible(true);lPanel.remove(tText);}
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
}