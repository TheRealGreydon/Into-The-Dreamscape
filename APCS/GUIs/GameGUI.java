package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import APCS.Assets.AssetClasses.*;
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
    private JToggleButton [] bbE = new JToggleButton[3];
    private JButton bbExit;
    private JButton next;

    private boolean paused = false;

    private BackgroundPanel a;

    public void start(Player x)
    {
        if(character.getStage()==2) 
        {
            character.setStage(0);

            if(character.getCurLev()==4) {character.setCurLev(0);}  else {character.setCurLev(character.getCurLev()+1);}
        }

        else {character.setStage(character.getStage()+1);}

        lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Battle Level/BB" + ((int)(Math.random()*4 + 1)) +".png").getImage(), 0);
        close();this.setLocationRelativeTo(null);displayGame();battleInit();vol = character.getVol();
    }

    private void battleInit()
    {
        this.pack();
        lPanel.setLayout(null);
        battleImg();
        battleLbl();
        battleButtons();
        keyActions();
    }

    private void battleImg()
    {
        charSprite = new JLabel(new ImageIcon(character.getBSprite()));charSprite.setPreferredSize(new Dimension(300,650));
        charSprite.setOpaque(false);lPanel.add(charSprite);
        charSprite.setBounds(100,200,300,550);
    }

    private void enmBattleButtons()
    {
        for(int i=0; i<3; i++)
        {
            int j = i;
            int z = ((int)(Math.random()*3 + 1));
            try {bbE[i] = new JToggleButton(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Enemies/HemoNeedle-1.png")).getScaledInstance(350,350, Image.SCALE_SMOOTH)));} 
            catch (IOException e) {e.printStackTrace();}
            bbE[i].setText("APCS/Assets/Img/Enemies/HemoNeedle-1");
            bbE[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            bbE[i].setSize(new Dimension(350,350));
            bbE[i].setLocation(1600, (i*300));   
            bbE[i].setContentAreaFilled(false);
            bbE[i].setBorderPainted(false);
            bbE[i].setFocusPainted(false);
            bbE[i].setOpaque(false);
            bbE[i].setFocusable(false);
            bbE[i].addItemListener(new ItemListener()
            {public void itemStateChanged(ItemEvent event) {if (event.getStateChange() == ItemEvent.SELECTED) {enmSel(j);} else {enmSel(-1);}}});
            lPanel.add(bbE[i]);
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
            bb[i].setSize(new Dimension(400,200));bb[i].setFocusable(false);bb[i].setLocation((i*500 + 100), 800);
        }
        bb[0].setText("Attack");bb[1].setText("Skills");bb[2].setText("Items");
        bbExit = new JButton();bbExit.setBackground(new Color(179, 9, 9));
        bbExit.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbExit.setForeground(Color.black);
        bbExit.setFont(new Font(bbExit.getFont().getName(), Font.BOLD, 20));
        bbExit.setText("Back");bbExit.addActionListener(this);bbExit.setSize(new Dimension(200,100));
        bbExit.setLocation(100,700);bbExit.setFocusable(false);
        buttonShow();pauButtons();atkBattleButtons();sklBattleButtons();
    }

    private void atkBattleButtons()
    {
        for(int i=0; i<4; i++)
        {
            bbA[i] = new JButton();
            bbA[i].setBackground(new Color(179, 9, 9));
            bbA[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbA[i].setForeground(Color.black);
            bbA[i].setFont(new Font(bbA[i].getFont().getName(), Font.BOLD, 40));bbA[i].addActionListener(this);
            bbA[i].setSize(new Dimension(400,200));bbA[i].setFocusable(false);
            if(i%2==0) {bbA[i].setLocation(100, 800);} else {bbA[i].setLocation(600, 800);}
            bbA[i].setText("BA" + i);
        }
    }

    private void itmBattleButtons()
    {
        for(int i=0; i<4; i++)
        {
            bbI[i] = new JButton();
            bbI[i].setBackground(new Color(179, 9, 9));
            bbI[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbI[i].setForeground(Color.black);
            bbI[i].setFont(new Font(bbI[i].getFont().getName(), Font.BOLD, 40));bbI[i].addActionListener(this);
            bbI[i].setSize(new Dimension(400,200));bbI[i].setFocusable(false);
            if(i%2==0) {bbI[i].setLocation(100, 800);} else {bbI[i].setLocation(600, 800);}
            bbI[i].setText("BI" + i);
        }
    }

    private void sklBattleButtons()
    {
        for(int i=0; i<4; i++)
        {
            bbS[i] = new JButton();
            bbS[i].setBackground(new Color(179, 9, 9));
            bbS[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbS[i].setForeground(Color.black);
            bbS[i].setFont(new Font(bbS[i].getFont().getName(), Font.BOLD, 40));bbS[i].addActionListener(this);
            bbS[i].setSize(new Dimension(400,200));bbS[i].setFocusable(false);
            if(i%2==0) {bbS[i].setLocation(100, 800);} else {bbS[i].setLocation(600, 800);}
            bbS[i].setText("BS" + i);
        }
    }

    private void battleButtons() 
    {
        next = new JButton();
        next.setBackground(new Color(179, 9, 9));
        next.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));next.setForeground(Color.black);
        next.setFont(new Font(next.getFont().getName(), Font.BOLD, 40));
        next.setText("Next");next.addActionListener(this);next.setSize(new Dimension(400,200));
        next.setLocation(1100,800);next.setFocusable(false);
        enmBattleButtons();actBattleButtons();itmBattleButtons();
    }

    private void pauButtons()
    {   
        set.setVisible(false);
        set.setSize(new Dimension(5000,5000));
        pau.setSize(new Dimension(5000,5000));
        pau.setEnabled(false);pau.setVisible(false);pau.setForeground(Color.white);pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));pau.setText("Paused"); 
        exit.setBackground(new Color(0,0,0));settings.setBackground(new Color(0,0,0));pau.setBackground(new Color(0,0,0,200));
        exit.setSize(new Dimension(200,100));settings.setSize(new Dimension(200,100));exit.setEnabled(false);exit.setVisible(false);exit.setForeground(Color.white);
        exit.setFont(new Font(exit.getFont().getName(), Font.BOLD, 40));exit.setText("Exit");settings.setEnabled(false);settings.setVisible(false);settings.setForeground(Color.white);
        settings.setFont(new Font(settings.getFont().getName(), Font.BOLD, 40));settings.setText("Settings");settings.addActionListener(this);exit.addActionListener(this);this.setLocationRelativeTo(null);
        settings.setFocusable(false);
        exit.setFocusable(false);
    }

    private void battleLbl()
    {

    }

    //Keybinds
    private void keyActions()
    {
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        lPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
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
            pau.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2500);
            settings.setText("Settings");
            lPanel.add(settings);lPanel.add(exit);lPanel.add(pau);
            battlePause();
            exit.setLocation((lPanel.getWidth()/2)-100, (lPanel.getHeight()/2)+150);
            settings.setLocation((lPanel.getWidth()/2-100), (lPanel.getHeight()/2+50));settings.setVisible(true);settings.setEnabled(true);
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
        vole.setForeground(Color.white);vole.setLocation((lPanel.getWidth()/2-50)+150, (lPanel.getHeight()/2+50));vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(vol));
        lPanel.add(vole);set.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2500);lPanel.add(set);
        vole.setFocusable(false);
    }

    private void battlePause()
    {
        lPanel.add(charSprite);
        lPanel.add(bbE[0]);
        lPanel.add(bbE[1]);
        lPanel.add(bbE[2]);
        if(has(next)) {next.setVisible(true);lPanel.add(next);}

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
        lPanel.remove(bbE[0]);
        lPanel.remove(bbE[1]);
        lPanel.remove(bbE[2]);
        if(has(next)) {next.setVisible(false);}

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
    
    private void next()
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

        lPanel.remove(next);lPanel.remove(bbExit);buttonShow();lPanel.repaint();
    }

    private void enmSel(int x)
    {
        for(int i=0; i<3; i++) {try {bbE[i].setIcon((new ImageIcon(ImageIO.read(new File(bbE[i].getText() + ".png")).getScaledInstance(350,350, Image.SCALE_SMOOTH))));}catch (IOException e) {e.printStackTrace();}}
        
        if(x!=-1) {try {bbE[x].setIcon((new ImageIcon(ImageIO.read(new File(bbE[x].getText() + "Sel.png")).getScaledInstance(350, 350, Image.SCALE_SMOOTH))));}catch (IOException e) {e.printStackTrace();}}
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

        else if(j.equals(bbExit)) {actBack();}

        else if(j.equals(next)) {next();}
    }

    private void displayGame() {initialize();java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}
    private void initialize() {setDefaultCloseOperation(EXIT_ON_CLOSE);this.pack();this.setExtendedState(JFrame.MAXIMIZED_BOTH);this.setTitle("Into the Dreamscape");this.setVisible(true);this.setResizable(true);this.pack();this.add(lPanel);}
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
    private void levExit() {GUI gui = new GUI();close();gui.cha(character);gui.game();}
    private void imgSwap(String x) {a.setImage(new ImageIcon("APCS/Assets/Img/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + x + ".jpg").getImage());}
    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}
    private boolean has(Component x) {for (Component c : lPanel.getComponents()) {if (c == x) {return true;}} return false;}
    public void select(int x) {String temp=String.valueOf(x) + String.valueOf(character.getStage());imgSwap(temp);}
    private void exit() {if(set.isVisible()) {pause();} else {levExit();}}
    private void atk() {buttonHide();lPanel.add(bbExit);lPanel.add(bbA[0]);lPanel.add(bbA[1]);lPanel.add(next);lPanel.repaint();}  
    private void skl() {buttonHide();lPanel.add(bbExit);lPanel.add(bbS[0]);lPanel.add(bbS[1]);lPanel.add(next);lPanel.repaint();}
    private void itm() {buttonHide();lPanel.add(bbExit);lPanel.add(bbI[0]);lPanel.add(bbI[1]);lPanel.add(next);lPanel.repaint();}
    private void buttonHide() {for(int i=0; i<3; i++) {lPanel.remove(bb[i]);}}
    private void buttonShow() {for(int i=0; i<3; i++) {bb[i].setVisible(true);lPanel.add(bb[i]);}}
}
