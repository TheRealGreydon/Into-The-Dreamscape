package APCS.GUIs;

import APCS.Assets.AssetClasses.*;
import APCS.Player;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;

public class LevelGUI extends JFrame implements ActionListener
{
    

    private Player character;
    private JPanel lPanel;
    private JButton pau = new JButton(), set = new JButton(), exit = new JButton(), settings = new JButton(), vole = new JButton();
    private boolean paused = false;
    private JLabel tText = new JLabel();
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private int count = 0;
    private BackgroundPanel a;

    public LevelGUI(Player character, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;
        a = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Game Level Menu/MapScreens-" + character.getLevel() + ".png").getImage(), 2);
    }

    public void initialize() 
    {
        lPanel = a;
        lPanel.setBackground(Color.BLACK);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        kronk.pack();
        lPanel.setLayout(null);
        kronk.setSize(1500, 800);
        kronk.setTitle("Into the Dreamscape");
        kronk.setVisible(true);
        kronk.setResizable(true);
        kronk.add(lPanel);
        pauButtons();keyActions();
    }

    //Select
    private void select() 
    {
        if(lPanel.equals(a) && !paused)
        {
            lPanel.removeAll();
            kronk.remove(lPanel);
            lPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Game Level Menu/Lvl Sel/MapScreens-" + character.getStage() + ".png").getImage(), 2);
            kronk.add(lPanel);
            kronk.revalidate();
            kronk.repaint();
            pauButtons();
            keyActions();
        }

        else if(!paused) {loadScreen();}
    }

    //Loading screen
    private void loadScreen()
    {
        kronk.remove(lPanel);
        lPanel = new JPanel();
        lPanel.setBackground(Color.black);
        lPanel.setLayout(null);
        kronk.add(lPanel);kronk.revalidate();kronk.repaint();
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
                for (Component c : lPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                if(!paused)
                {
                    if(count<b.length) {tText.setText(tText.getText() + b[count]);tText.setSize(tText.getPreferredSize());
                    tText.setLocation(lPanel.getWidth()/2 - tText.getWidth()/2, lPanel.getHeight()/2-tText.getHeight()/2);
                    lPanel.add(tText);}
                    else{
                        if(count>b.length+15) 
                        {
                            lPanel.remove(tText);lPanel.repaint();
                            for (Component c : lPanel.getComponents()) 
                            {
                                if (c instanceof JButton) 
                                {
                                    c.setEnabled(true);
                                }
                            }
                            timer.cancel();kronk.remove(lPanel);
                            //Impl boss GUI
                            //if(character.getStage()==2){new BossGUI(character,kronk).start();} else 
                            {new GameGUI(character,kronk);}

                            //{new RewGUI(character,kronk).initialize();}
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
            pau.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2650);
            settings.setText("Settings");
            lPanel.add(settings);lPanel.add(exit);lPanel.add(pau);
            exit.setLocation((lPanel.getWidth()/2)-100, (lPanel.getHeight()/2));
            settings.setLocation((lPanel.getWidth()/2-100), (lPanel.getHeight()/2-100));settings.setVisible(true);settings.setEnabled(true);
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
        vole.setForeground(Color.white);vole.setLocation((lPanel.getWidth()/2-50)+150, (lPanel.getHeight()/2-100));vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(character.getVol()));
        lPanel.add(vole);set.setLocation(lPanel.getWidth()/2-2500, lPanel.getHeight()/2-2650);lPanel.add(set);
        vole.setFocusable(false);
    }
    
    //Keybinds and buttons
    private void keyActions() 
    {
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        lPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        lPanel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {select();}});
    }
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(character.getVol()+25>100) {character.setVol(0);} else {character.setVol(character.getVol() + 25);} vole.setText(String.valueOf(character.getVol()));}
    }
    
    private void exit() {if(!settings.getText().equals("Volume")){kronk.dispose();} else{pause();}}
}
