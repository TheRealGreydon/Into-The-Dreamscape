package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import APCS.Assets.AssetClasses.*;
import APCS.Player;

public class CutGUI extends JFrame implements ActionListener
{
    public CutGUI(Player character) 
    {
        this.character = character;
        a = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Game Level Menu/defaultLevelMenu" + character.getCurLev() + ".jpg").getImage(), 1);
    }

    private Player character;
    private JPanel cPanel;
    private JLabel tText = new JLabel();
    private int count = 0;

    private BackgroundPanel a;

    public void displayGame() {initialize();java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}

    private void initialize() 
    {
        cPanel = a;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        cPanel.setLayout(null);
        this.setSize(1500, 800);
        this.setTitle("Into the Dreamscape");
        this.setVisible(true);
        this.setResizable(true);
        this.add(cPanel);
        keyActions();
    }

    private void select() 
    {
        if(cPanel.equals(a))
        {
            cPanel.removeAll();
            this.remove(cPanel);
            cPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + 
            character.getCurLev() + "" + character.getStage() + ".jpg").getImage(), 1);
            this.add(cPanel);
            this.revalidate();
            this.repaint();
            keyActions();
        }
    }

    private void loadScreen()
    {
        this.remove(cPanel);
        cPanel = new JPanel();
        cPanel.setBackground(Color.black);
        cPanel.setLayout(null);
        this.add(cPanel);this.revalidate();this.repaint();
        timedText("Level " + (character.getCurLev()+1) + " - " + (character.getStage()+1));
    }

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
                //for (Component c : cPanel.getComponents()) {if (c instanceof JButton && !c.equals(exit) && !c.equals(settings) && !c.equals(vole)) {c.setEnabled(false);}}
                    if(count<b.length) {tText.setText(tText.getText() + b[count]);tText.setSize(tText.getPreferredSize());
                    tText.setLocation(cPanel.getWidth()/2 - tText.getWidth()/2, cPanel.getHeight()/2-tText.getHeight()/2);
                    cPanel.add(tText);}
                    else{if(count>b.length+15) {cPanel.remove(tText);cPanel.repaint();
                        for (Component c : cPanel.getComponents()) {if (c instanceof JButton) {c.setEnabled(true);}}timer.cancel();close();new GameGUI(character).start();}}
                        count++;
            }
        };
        timer.scheduleAtFixedRate(task, 0, 50);        
    }

    private void keyActions() 
    {
        cPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        cPanel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {select();}});
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
    }
    
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
}
