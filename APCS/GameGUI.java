package APCS;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GameGUI extends JFrame implements ActionListener
{
    private int x;
    private Player character;
    public GameGUI(Player character) {this.character = character; x = character.getCurLev();
        a = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage(), 1);
    }
    private LevelGUI lGUI = new LevelGUI();
    private JPanel lPanel;
    private JButton pau = new JButton();
    private JButton set = new JButton();
    private JButton exit = new JButton();
    private JButton settings = new JButton();
    private JButton vole = new JButton();
    private int vol;
    private JLabel charSprite;
    private JLabel enm1 = null;
    private JLabel enm2 = null;
    private JLabel enm3 = null;
    private JButton [] bb = new JButton[3];
    private JButton [] bbE = new JButton[3];
    private JButton bbExit;

    private boolean paused = false;

    private BackgroundPanel a;

    public void start(Player x)
    {
        lGUI.setChar(character);
        if(character.getStage()==2) 
        {
            character.setStage(0);

            if(character.getCurLev()==4) {character.setCurLev(0);}

            else {character.setCurLev(character.getCurLev()+1);}
        }
        else {character.setStage(character.getStage()+1);}

        lPanel = lGUI.lPanel;close();this.setLocationRelativeTo(null);displayGame();battleInit();vol = character.getVol();
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
        charSprite = new JLabel(new ImageIcon(character.getSprite()));charSprite.setPreferredSize(new Dimension(300,450));
        charSprite.setOpaque(false);lPanel.add(charSprite);
        charSprite.setBounds(100,200,300,450);
        try {enm1 = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Enemies/Char" + ((int)(Math.random()*3 + 1)) + ".png")).getScaledInstance(420, 420, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}
        enm1.setOpaque(false);//lPanel.add(enm1);
        enm1.setBounds(1600,0,200,300);
        try {enm2 = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Enemies/Char" + ((int)(Math.random()*3 + 1)) + ".png")).getScaledInstance(420, 420, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}
        enm2.setOpaque(false);//lPanel.add(enm2);
        enm2.setBounds(1600,300,200,300);
        try {enm3 = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Enemies/Char" + ((int)(Math.random()*3 + 1)) + ".png")).getScaledInstance(420, 420, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}
        enm3.setOpaque(false);//lPanel.add(enm3);
        enm3.setBounds(1600,600,200,300);
    }

    private void enmBattleButtons()
    {
        for(int i=0; i<3; i++)
        {
            int z = ((int)(Math.random()*3 + 1));
            try {bbE[i] = new JButton(new ImageIcon(ImageIO.read(new File("APCS/Assets/Enemies/Char" + z + ".png")).getScaledInstance(420, 420, Image.SCALE_SMOOTH)));} 
            catch (IOException e) {e.printStackTrace();}
            bbE[i].setText("APCS/Assets/Enemies/Char" + z);
            //bbE[i].setVisible(true);//bbE[i].setBackground(new Color(0,0,0,0));
            bbE[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            bbE[i].setSize(new Dimension(200,300));
            bbE[i].setLocation(1600, (i*300));   
            bbE[i].setContentAreaFilled(false);
            bbE[i].setBorderPainted(false);
            bbE[i].setFocusPainted(false);
            bbE[i].setOpaque(false);
            bbE[i].addActionListener(this);
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
        bbExit = new JButton();
        bbExit.setBackground(new Color(179, 9, 9));
        bbExit.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbExit.setForeground(Color.black);
        bbExit.setFont(new Font(bbExit.getFont().getName(), Font.BOLD, 40));
        bbExit.setText("Back");bbExit.addActionListener(this);bbExit.setSize(new Dimension(400,200));
        bbExit.setLocation(1200,800);bbExit.setVisible(false);bbExit.setEnabled(false);
        bbExit.setFocusable(false);lPanel.add(bbExit);
        buttonShow();pauButtons();
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
        lPanel.add(enm1);
        lPanel.add(enm2);
        lPanel.add(enm3);
        buttonShow();
        if(paused) {bb[0].setEnabled(false);bb[1].setEnabled(false);bb[2].setEnabled(false);}

        else {bb[0].setEnabled(true);bb[1].setEnabled(true);bb[2].setEnabled(true);}
    }

    private void battleHide()
    {
        lPanel.remove(charSprite);
        lPanel.remove(enm1);
        lPanel.remove(enm2);
        lPanel.remove(enm3);
        buttonHide();
    }

    private void buttonHide()
    {
        for(int i=0; i<3; i++) {lPanel.remove(bb[i]);}
    }

    private void buttonShow()
    {
        for(int i=0; i<3; i++) {lPanel.add(bb[i]);}
    }

    private void atk()
    {
        buttonHide();
        bbExit.setVisible(true);bbExit.setEnabled(true);
        lPanel.repaint();
    }

    private void buttonSel(int x)
    {
        for(int i=0; i<3; i++)
        {
            try {bbE[i].setIcon((new ImageIcon(ImageIO.read(new File(bbE[i].getText() + ".png")).getScaledInstance(420, 420, Image.SCALE_SMOOTH))));} 
            catch (IOException e) {e.printStackTrace();}
        }
        try {bbE[x].setIcon((new ImageIcon(ImageIO.read(new File(bbE[x].getText() + "Sel.png")).getScaledInstance(420, 420, Image.SCALE_SMOOTH))));} 
        catch (IOException e) {e.printStackTrace();}
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(vol+25>100) {vol=0;}else{vol+=25;}character.setVol(vol);vole.setText(String.valueOf(vol));}

        else if(j.equals(bb[0])) {atk();}
        
        else if(j.equals(bb[1])) {atk();}
        
        else if(j.equals(bb[2])) {atk();}

        else if(j.equals(bbExit)) {bbExit.setVisible(false);bbExit.setEnabled(false);buttonShow();lPanel.repaint();}

        else if(j.equals(bbE[0])) {buttonSel(0);}

        else if(j.equals(bbE[1])) {buttonSel(1);}

        else if(j.equals(bbE[2])) {buttonSel(2);}
    }

    private void battleButtons() {enmBattleButtons();actBattleButtons();}
    private void displayGame() {initialize();java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}
    private void initialize() {setDefaultCloseOperation(EXIT_ON_CLOSE);this.pack();this.setExtendedState(JFrame.MAXIMIZED_BOTH);this.setTitle("Into the Dreamscape");this.setVisible(true);this.setResizable(true);this.pack();this.add(lPanel);}
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
    private void levExit() {GUI gui = new GUI();close();gui.cha(character);gui.game();}
    private void imgSwap(String x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + x + ".jpg").getImage());}
    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}
    public void select(int x) {String temp=String.valueOf(x) + String.valueOf(character.getStage());imgSwap(temp);}
    private void exit() {if(set.isVisible()) {pause();} else {levExit();}}
}
