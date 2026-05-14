package APCS;

import java.awt.*;
import java.awt.event.*;
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
    private JLabel charSprite = null;

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

        lPanel = lGUI.lPanel;close();displayGame();battleItm();vol = character.getVol();
    }

    private void battleItm()
    {
        battleImg();
        //battleLbl();
        //battleButtons();
        keyActions();
    }

    private void battleImg()
    {
        charSprite = new JLabel(new ImageIcon(character.getSprite()));charSprite.setPreferredSize(new Dimension(300,450));charSprite.setLocation(1000+lPanel.WIDTH, lPanel.HEIGHT);
        lPanel.add(charSprite);
    }

    private void battleLbl()
    {

    }

    private void battleButtons()
    {

    }

    private void pauButtons()
    {
        set.setVisible(false);
        pau.setSize(new Dimension(5000,5000));
        pau.setEnabled(false);pau.setVisible(false);pau.setForeground(Color.white);pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));pau.setText("Paused"); 
        exit.setBackground(new Color(0,0,0));settings.setBackground(new Color(0,0,0));pau.setBackground(new Color(0,0,0,200));
        exit.setSize(new Dimension(200,100));settings.setSize(new Dimension(200,100));exit.setEnabled(false);exit.setVisible(false);exit.setForeground(Color.white);
        exit.setFont(new Font(exit.getFont().getName(), Font.BOLD, 40));exit.setText("Exit");settings.setEnabled(false);settings.setVisible(false);settings.setForeground(Color.white);
        settings.setFont(new Font(settings.getFont().getName(), Font.BOLD, 40));settings.setText("Settings");settings.addActionListener(this);exit.addActionListener(this);this.setLocationRelativeTo(null);
    }

    //Keybinds
    private void keyActions()
    {
        pauButtons();
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        lPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
    }

    //Displays the pause screen
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
            lPanel.add(charSprite);
            paused=false;
        }
        else 
        {
            pau.setForeground(Color.white);
            pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));pau.setText("Paused");pau.setBackground(new Color(0,0,0,200));
            pau.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2500);
            exit.setText("Exit");
            settings.setText("Settings");
            lPanel.remove(charSprite);
            lPanel.add(settings);
            lPanel.add(exit);
            lPanel.add(pau);
            lPanel.add(charSprite);
            exit.setLocation((lPanel.getWidth()/2)-100, (lPanel.getHeight()/2)+150);
            settings.setLocation((lPanel.getWidth()/2-100), (lPanel.getHeight()/2+50));
            settings.setVisible(true);
            settings.setEnabled(true);
            exit.setVisible(true);
            exit.setEnabled(true);
            pau.setVisible(true);
            paused=true;
        }
    }    

    private void settings()
    {
        lPanel.remove(charSprite);
        pau.setVisible(false);set.setEnabled(false);set.setForeground(Color.white);set.setVisible(true);set.setBackground(Color.BLACK);
        set.setFont(new Font(set.getFont().getName(), Font.BOLD, 40));set.setText("Settings"); 
        exit.setText("Back");settings.setText("Volume");settings.setEnabled(false);
        vole.setFont(new Font(vole.getFont().getName(), Font.BOLD, 40));vole.addActionListener(this);vole.setSize(new Dimension(125,100));vole.setBackground(Color.black);
        vole.setForeground(Color.white);vole.setLocation((lPanel.getWidth()/2-50)+150, (lPanel.getHeight()/2+50));vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(vol));
        lPanel.add(vole);lPanel.add(set);
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(vol+25>100) {vol=0;}else{vol+=25;}character.setVol(vol);vole.setText(String.valueOf(vol));}
    }

    private void displayGame() {initialize();java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}
    private void initialize() {setDefaultCloseOperation(EXIT_ON_CLOSE);this.pack();this.setExtendedState(JFrame.MAXIMIZED_BOTH);this.setTitle("Into the Dreamscape");this.setVisible(true);this.setResizable(true);this.add(lPanel);}
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
    private void levExit() {GUI gui = new GUI();close();gui.cha(character);gui.game();}
    private void imgSwap(String x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + x + ".jpg").getImage());}
    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}
    public void select(int x) {String temp=String.valueOf(x) + String.valueOf(character.getStage());imgSwap(temp);}
    private void exit() {if(set.isVisible()) {pause();} else {levExit();}}
}
