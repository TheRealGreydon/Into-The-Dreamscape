package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import APCS.Assets.AssetClasses.*;
import APCS.Enms.*;
import APCS.Skills.Atk;
import APCS.Skills.bEAtk;
import APCS.Battle;
import APCS.Player;

public class GameGUI extends JFrame implements ActionListener
{
    private Player character;

    public GameGUI(Player character) 
    {
        this.character = character;
        a = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Game Level Menu/defaultLevelMenu" + character.getCurLev() + ".jpg").getImage(), 1);
    
    }

    private JPanel lPanel;
    private JButton pau = new JButton();
    private JButton set = new JButton();
    private JButton exit = new JButton();
    private JButton settings = new JButton();
    private JButton vole = new JButton();
    private int vol;
    private JLabel charSprite;
    private JButton [] bb = new JButton[3];
    private JButton [] bbA = new JButton[4];    
    private JButton [] bbS = new JButton[4];    
    private JButton [] bbI = new JButton[4];    
    private disEnm [] bbE = new disEnm[3];
    private JButton bbExit;
    private JButton actNext;
    private boolean paused = false;
    private JLabel tText = new JLabel();
    private int count = 0;
    private boolean turn = false;
    private int enm = 3;
    private int alv;

    private BackgroundPanel a;

    public void start(Player x)
    {
        if(character.getStage()==2) {character.setStage(0);if(character.getCurLev()==4) {character.setCurLev(0);}  else {character.setCurLev(character.getCurLev()+1);}}

        else {character.setStage(character.getStage()+1);}

        lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Battle Level/BB" + ((int)(Math.random()*4 + 1)) +".png").getImage(), 0);
        close();this.setLocationRelativeTo(null);displayGame();battleInit();vol = character.getVol();
    }

    private void battleInit()
    {
        this.pack();
        lPanel.setLayout(null);
        this.setSize(1500, 800);
        battleImg();
        battleButtons();
        keyActions();
        doBattle();
    }

    private void doBattle()
    {
        if(!turn)
        {
            enmTurn();
            turn = true;
            for (Component c : lPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}
        }
        else
        {
            
        }
    }

    private void enmTurn()
    {
        alv = 0;for(int i=0; i<3; i++) {if(bbE[i].Enm.isAlive()) {alv++;}}
        
        Timer timer = new Timer();TimerTask task = new TimerTask()
        {public void run() 
            {for (Component c : lPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                
            if(!paused) {if(alv>0) {timedText(new Battle().attack(character, new bEAtk()), 350, 120);alv--;}else {timer.cancel();}}}};
        timer.scheduleAtFixedRate(task, 0, 2000);
    }

    private void enmBattleButtons()
    {
        for(int i=0; i<3; i++)
        {
            bbE[i] = new disEnm(new HemoNeedle(1));
            bbE[i].enmButton.setLocation(1200, (i*250));   
            bbE[i].enmButton.addActionListener(this);
            bbE[i].enmButton.setDisabledIcon(bbE[i].enmButton.getIcon());
            lPanel.add(bbE[i].enmButton);
        }
    }

    private void actBattleButtons()
    {
        for(int i=0; i<3; i++)
        {
            bb[i] = new JButton();
            bb[i].setBackground(new Color(179, 9, 9));
            bb[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bb[i].setForeground(Color.black);
            bb[i].setFont(new Font(bb[i].getFont().getName(), Font.BOLD, 40));bb[i].addActionListener(this);
            bb[i].setSize(new Dimension(300,150));bb[i].setFocusable(false);bb[i].setLocation((i*400), 615);
        }
        bb[0].setText("Attack");bb[1].setText("Skills");bb[2].setText("Items");
        bbExit = new JButton();bbExit.setBackground(new Color(179, 9, 9));
        bbExit.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbExit.setForeground(Color.black);
        bbExit.setFont(new Font(bbExit.getFont().getName(), Font.BOLD, 20));
        bbExit.setText("Back");bbExit.addActionListener(this);bbExit.setSize(new Dimension(150,75));
        bbExit.setLocation(0,540);bbExit.setFocusable(false);
        buttonShow();pauButtons();atkBattleButtons();sklBattleButtons();
    }

    private void atkBattleButtons()
    {
        for(int i=0; i<4; i++)
        {
            bbA[i] = new JButton();
            bbA[i].setBackground(new Color(179, 9, 9));
            bbA[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbA[i].setForeground(Color.black);
            bbA[i].setFont(new Font(bbA[i].getFont().getName(), Font.BOLD, 40));
            bbA[i].setSize(new Dimension(300,150));bbA[i].setFocusable(false);
            
            if(i%2==0) {bbA[i].setLocation(0, 615);} else {bbA[i].setLocation(400, 615);}

            if(character.atks[i] != null) {bbA[i].addActionListener(this);bbA[i].setText(character.atks[i].getName());}
            
            else {bbA[i].setText("X");}
        }
    }

    private void atkSel(int y)
    {
        int z = -1;
        for(int i=0; i<3;i++) {if(bbE[i].selected) {z = i;}}
        if(z != -1)
        {
            timedText(new Battle().attack(bbE[z].Enm, character.atks[y]),350,120);
            if(!bbE[z].Enm.isAlive()) {lPanel.remove(bbE[z].enmButton);}
        }
    }

    private void timedText(String a, int x, int y)
    {
        count = 0;
        Timer timer = new Timer();
        String [] b = a.split("");
        tText.setText("");
        tText.setLocation(x, y);
        tText.setOpaque(true);
        tText.setBackground(Color.WHITE);
        tText.setFont(new Font(tText.getFont().getName(), Font.BOLD, 40));
        TimerTask task = new TimerTask()
        {public void run() 
            {
                for (Component c : lPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                if(!paused)
                {
                    if(count<b.length) {tText.setText(tText.getText() + b[count]);tText.setSize(tText.getPreferredSize()); lPanel.add(tText);}
                    else{if(count>b.length+10) {lPanel.remove(tText);lPanel.repaint();
                        for (Component c : lPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}
                        timer.cancel();}}count++;
                }
            }
        };
        timer.scheduleAtFixedRate(task, 0, 50);
    }

    private void itmBattleButtons()
    {
        for(int i=0; i<4; i++)
        {
            bbI[i] = new JButton();
            bbI[i].setBackground(new Color(179, 9, 9));
            bbI[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbI[i].setForeground(Color.black);
            bbI[i].setFont(new Font(bbI[i].getFont().getName(), Font.BOLD, 40));
            bbI[i].setSize(new Dimension(300,150));bbI[i].setFocusable(false);
            
            if(i%2==0) {bbI[i].setLocation(0, 615);} else {bbI[i].setLocation(400, 615);}
            
            bbI[i].setText("X");
        }
    }

    private void sklBattleButtons()
    {
        for(int i=0; i<4; i++)
        {
            bbS[i] = new JButton();
            bbS[i].setBackground(new Color(179, 9, 9));
            bbS[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbS[i].setForeground(Color.black);
            bbS[i].setFont(new Font(bbS[i].getFont().getName(), Font.BOLD, 40));
            bbS[i].setSize(new Dimension(300,150));bbS[i].setFocusable(false);
            
            if(i%2==0) {bbS[i].setLocation(0, 615);} else {bbS[i].setLocation(400, 615);}
            
            bbS[i].setText("X");
        }
    }

    private void battleButtons() 
    {
        actNext = new JButton();
        actNext.setBackground(new Color(179, 9, 9));
        actNext.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));actNext.setForeground(Color.black);
        actNext.setFont(new Font(actNext.getFont().getName(), Font.BOLD, 40));
        actNext.setText("Next");actNext.addActionListener(this);actNext.setSize(new Dimension(300,150));
        actNext.setLocation(800,615);actNext.setFocusable(false);
        enmBattleButtons();actBattleButtons();itmBattleButtons();
    }

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

    //Displays the pause screen
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
            battlePause();
        }
        else 
        {
            paused=true;
            pau.setForeground(Color.white);
            pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));pau.setText("Paused");pau.setBackground(new Color(0,0,0,200));
            pau.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2650);
            settings.setText("Settings");
            lPanel.add(settings);lPanel.add(exit);lPanel.add(pau);
            battlePause();
            exit.setLocation((lPanel.getWidth()/2)-100, (lPanel.getHeight()/2));
            settings.setLocation((lPanel.getWidth()/2-100), (lPanel.getHeight()/2-100));settings.setVisible(true);settings.setEnabled(true);
            exit.setVisible(true);exit.setEnabled(true);exit.setText("Exit");
            pau.setVisible(true);
        }
    }    

    private void settings()
    {
        battleHide();
        pau.setVisible(false);set.setEnabled(false);set.setForeground(Color.white);set.setVisible(true);set.setBackground(Color.BLACK);
        set.setFont(new Font(set.getFont().getName(), Font.BOLD, 40));set.setText("Settings"); 
        exit.setText("Back");settings.setText("Volume");settings.setEnabled(false);
        vole.setFont(new Font(vole.getFont().getName(), Font.BOLD, 40));vole.addActionListener(this);vole.setSize(new Dimension(125,100));vole.setBackground(Color.black);
        vole.setForeground(Color.white);vole.setLocation((lPanel.getWidth()/2-50)+150, (lPanel.getHeight()/2-100));vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(vol));
        lPanel.add(vole);set.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2650);lPanel.add(set);
        vole.setFocusable(false);
    }

    private void battlePause()
    {
        lPanel.add(charSprite);
        lPanel.add(bbE[0].enmButton);
        lPanel.add(bbE[1].enmButton);
        lPanel.add(bbE[2].enmButton);
        if(has(tText)) {tText.setVisible(true);lPanel.add(tText);}
        if(has(actNext)) {actNext.setVisible(true);lPanel.add(actNext);}

        if(has(bbA[0]) || has(bbA[2]))
        {
            if(has(bbA[0])) {lPanel.add(bbA[0]);lPanel.add(bbA[1]);bbA[0].setVisible(true);bbA[1].setVisible(true);}

            else {lPanel.add(bbA[2]);lPanel.add(bbA[3]);bbA[2].setVisible(true);bbA[3].setVisible(true);}
        }
        else if(has(bbS[0]) || has(bbS[2]))
        {
            if(has(bbS[0])) {lPanel.add(bbS[0]);lPanel.add(bbS[1]);bbS[0].setVisible(true);bbS[1].setVisible(true);}

            else {lPanel.add(bbS[2]);lPanel.add(bbS[3]);bbS[2].setVisible(true);bbS[3].setVisible(true);}
        }
        else if(has(bbI[0]) || has(bbI[2]))
        {
            if(has(bbI[0])) {lPanel.add(bbI[0]);lPanel.add(bbI[1]);bbI[0].setVisible(true);bbI[1].setVisible(true);}

            else {lPanel.add(bbI[2]);lPanel.add(bbI[3]);bbI[2].setVisible(true);bbI[3].setVisible(true);}
        }

        if(has(bbExit)) {lPanel.add(bbExit);bbExit.setVisible(true);} if(has(bb[0])) {buttonShow();}
    }

    private void battleHide()
    {
        lPanel.remove(charSprite);
        lPanel.remove(bbE[0].enmButton);
        lPanel.remove(bbE[1].enmButton);
        lPanel.remove(bbE[2].enmButton);
        if(has(tText)) {tText.setVisible(false);}
        if(has(actNext)) {actNext.setVisible(false);}

        if(has(bbA[0]) || has(bbA[2]))
        {
            if(has(bbA[0])) {bbA[0].setVisible(false);bbA[1].setVisible(false);}

            else {bbA[2].setVisible(false);bbA[3].setVisible(false);}
        }
        else if(has(bbS[0]) || has(bbS[2]))
        {
            if(has(bbS[0])) {bbS[0].setVisible(false);bbS[1].setVisible(false);}

            else {bbS[2].setVisible(false);bbS[3].setVisible(false);}
        }
        else if(has(bbI[0]) || has(bbI[2]))
        {
            if(has(bbI[0])) {bbI[0].setVisible(false);bbI[1].setVisible(false);}

            else {bbI[2].setVisible(false);bbI[3].setVisible(false);}
        }

        if(has(bbExit)) {bbExit.setVisible(false);} if(has(bb[0])) {for(int i=0; i<3; i++) {bb[i].setVisible(false);}}
    }
    
    private void actNext()
    {
        if(has(bbA[0]) || has(bbA[2]))
        {
            if(has(bbA[0])) {lPanel.remove(bbA[0]);lPanel.remove(bbA[1]);lPanel.add(bbA[2]);lPanel.add(bbA[3]);lPanel.repaint();}

            else {lPanel.add(bbA[0]);lPanel.add(bbA[1]);lPanel.remove(bbA[2]);lPanel.remove(bbA[3]);lPanel.repaint();}
        }
        
        else if(has(bbS[0]) || has(bbS[2]))
        {
            if(has(bbS[0])) {lPanel.remove(bbS[0]);lPanel.remove(bbS[1]);lPanel.add(bbS[2]);lPanel.add(bbS[3]);lPanel.repaint();}

            else {lPanel.add(bbS[0]);lPanel.add(bbS[1]);lPanel.remove(bbS[2]);lPanel.remove(bbS[3]);lPanel.repaint();}
        }

        else if(has(bbI[0]) || has(bbI[2]))
        {
            if(has(bbI[0])) {lPanel.remove(bbI[0]);lPanel.remove(bbI[1]);lPanel.add(bbI[2]);lPanel.add(bbI[3]);lPanel.repaint();}

            else {lPanel.add(bbI[0]);lPanel.add(bbI[1]);lPanel.remove(bbI[2]);lPanel.remove(bbI[3]);lPanel.repaint();}
        }
    }

    private void actBack()
    {
        if(has(bbA[0]) || has(bbA[2]))
        {
            if(has(bbA[0])) {lPanel.remove(bbA[0]);lPanel.remove(bbA[1]);}

            else {lPanel.remove(bbA[2]);lPanel.remove(bbA[3]);}
        }
        
        else if(has(bbS[0]) || has(bbS[2]))
        {
            if(has(bbS[0])) {lPanel.remove(bbS[0]);lPanel.remove(bbS[1]);}

            else {lPanel.remove(bbS[2]);lPanel.remove(bbS[3]);}
        }
        
        else if(has(bbI[0]) || has(bbI[2]))
        {
            if(has(bbI[0])) {lPanel.remove(bbI[0]);lPanel.remove(bbI[1]);}

            else {lPanel.remove(bbI[2]);lPanel.remove(bbI[3]);}
        }

        lPanel.remove(actNext);lPanel.remove(bbExit);buttonShow();lPanel.repaint();
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(vol+25>100) {vol=0;}else{vol+=25;}character.setVol(vol);vole.setText(String.valueOf(vol));}

        else if(j.equals(bb[0])) {atk();}
        
        else if(j.equals(bb[1])) {skl();}
        
        else if(j.equals(bb[2])) {itm();}

        else if(j.equals(bbE[0].enmButton)) {enmSel(0);}
        
        else if(j.equals(bbE[1].enmButton)) {enmSel(1);}
        
        else if(j.equals(bbE[2].enmButton)) {enmSel(2);}

        else if(j.equals(bbExit)) {actBack();}

        else if(j.equals(actNext)) {actNext();}

        else if(j.equals(bbA[0])) {atkSel(0);}
    }

    private void displayGame() {initialize();java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}
    private void initialize() {setDefaultCloseOperation(EXIT_ON_CLOSE);this.pack();this.setTitle("Into the Dreamscape");this.setVisible(true);this.setResizable(true);this.pack();this.add(lPanel);}
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
    private void levExit() {GUI gui = new GUI();close();gui.cha(character);gui.game();}
    private void imgSwap(String x) {a.setImage(new ImageIcon("APCS/Assets/Img/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + x + ".jpg").getImage());}
    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}
    private boolean has(Component x) {for (Component c : lPanel.getComponents()) {if (c == x) {return true;}} return false;}
    public void select(int x) {String temp=String.valueOf(x) + String.valueOf(character.getStage());imgSwap(temp);}
    private void exit() {if(set.isVisible()) {pause();} else {levExit();}}
    private void atk() {buttonHide();lPanel.add(bbExit);lPanel.add(bbA[0]);lPanel.add(bbA[1]);lPanel.add(actNext);lPanel.repaint();}  
    private void skl() {buttonHide();lPanel.add(bbExit);lPanel.add(bbS[0]);lPanel.add(bbS[1]);lPanel.add(actNext);lPanel.repaint();}
    private void itm() {buttonHide();lPanel.add(bbExit);lPanel.add(bbI[0]);lPanel.add(bbI[1]);lPanel.add(actNext);lPanel.repaint();}
    private void buttonHide() {for(int i=0; i<3; i++) {lPanel.remove(bb[i]);}}
    private void buttonShow() {for(int i=0; i<3; i++) {bb[i].setVisible(true);lPanel.add(bb[i]);}}
    private void keyActions() {lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");lPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});}
    private void enmSel(int x) {for(int i=0;i<3;i++) {bbE[i].select(false);} bbE[x].select(true);lPanel.repaint();}
    private void wait(int x) {long y = System.currentTimeMillis(); while((y+(1000*x) > System.currentTimeMillis())){}}
    private void battleImg() {charSprite = new Battle(character).battleImg();lPanel.add(charSprite);}
}
