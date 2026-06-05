package APCS.GUIs;

import APCS.Assets.AssetClasses.*;
import APCS.Uses.Uses;
import APCS.Uses.Actions.Attacks.*;
import APCS.Uses.Actions.Skills.*;
import APCS.Uses.Items.Itm;
import APCS.Uses.Items.AttackItem.*;
import APCS.Uses.Items.SkillItem.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.swing.*;

public class InfoGUI extends JFrame 
{
    private JPanel iPanel, lPanel;
    private JPanel [][] actPanels = new JPanel[3][];

    private JLabel [] buttons = new JLabel[4];
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private boolean down = false;
    private int KBH = 0,mode, curPan = 0;

    private Atk atkL[] = {new smack(), new punch(), new widePunch(), new bigHit(), new twinStrike(), new woundingStrike(), new gamble()};
    private Skl sklL[] = {new juiceBox(), new vampSwordSKL(), new rage(), new fireballSKL()};
    private Itm itmL[] = {new grilledCheeseITM(), new swordITM(), new milkITM(), new cookieITM(), new baguetteITM()};
    private Uses [][] uses = {atkL, sklL, itmL};
    
    public InfoGUI(JPanel lPanel, JFrame kronk) 
    {
        this.kronk = kronk;this.lPanel = lPanel;
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
    }
    
    public void initialize() 
    {
        kronk.pack();
        iPanel.setLayout(null);
        kronk.setSize(1500, 800);
        allPansInit();
        iPanInit();
        kronk.add(iPanel);
        highlight(0);
        keyActions(iPanel);
    }
    private void iPanInit()
    {
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        iPanel.setLayout(null);
        
        //Makes the buttons
        for(int i=0;i<4; i++)
        {
            buttons[i] = new JLabel();
            buttons[i].setOpaque(true);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.red);
            buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
            buttons[i].setVerticalAlignment(SwingConstants.CENTER);
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 45));

            iPanel.add(buttons[i]);
        }

        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 30));
        buttons[0].setText("Atks");
        buttons[1].setText("Skls");
        buttons[2].setText("Itms");
        buttons[3].setText("Back");
        
        buttons[0].setBounds(450,325,200,150);
        buttons[1].setBounds(650,325,200,150);
        buttons[2].setBounds(850,325,200,150);
        buttons[3].setBounds(650,650,200,100);

        JLabel title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Select an option to learn more about");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 125);
        iPanel.add(title);

        for(int i=0; i<3;i++) {arrButtons(actPanels[i]);}
      
        paint(iPanel,5);
    }
    private void allPansInit()
    {
        for(int i=0;i<3;i++)
        {
            actPanels[i] = new JPanel[uses[i].length];
            for(int j=0;j<uses[i].length;j++)
            {
                actPanels[i][j] = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
                actPanels[i][j].setLayout(null);

                titles(actPanels[i][j]);
                discs(actPanels[i][j]);
                keyActions(actPanels[i][j]);
            
                paint(actPanels[i][j],5);      
            }
        }
    }

    //Makes the arrow buttons/titles/discs for the individual panels
    private void arrButtons(JPanel[] x)
    {
        for(int i=0; i<x.length; i++)
        {
            JLabel arrowButtons [] = new JLabel[3];            

            for(int j=0;j<3;j++)
            {
                arrowButtons[j] = new JLabel();
                arrowButtons[j].setOpaque(true);
                arrowButtons[j].setFocusable(false);
                arrowButtons[j].setBackground(Color.red);
                arrowButtons[j].setHorizontalAlignment(SwingConstants.CENTER);
                arrowButtons[j].setVerticalAlignment(SwingConstants.CENTER);
                arrowButtons[j].setFont(new Font(arrowButtons[j].getFont().getName(), Font.BOLD, 45));
                arrowButtons[j].setBorder(BorderFactory.createLineBorder(Color.black, 5));

                x[i].add(arrowButtons[j]);
            }

            arrowButtons[0].setText("Back");
            arrowButtons[0].setBounds(650,650,200,100);
            arrowButtons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            arrowButtons[1].setText("<-");
            arrowButtons[1].setBounds(550,675,100,50);
            arrowButtons[2].setText("->");
            arrowButtons[2].setBounds(850,675,100,50);
        }
    }
    private void titles(JPanel x)
    {
        JLabel title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));

        for(int j=0; j<3;j++)
        {
            if(Arrays.stream(actPanels[j]).anyMatch(x::equals)) 
            {
                for (int i = 0; i < actPanels[j].length; i++) 
                {
                    if (x.equals(actPanels[j][i])) 
                    {
                        title.setText(uses[j][i].getName());actPanels[j][i].add(title);
                    }
                }   
            }
        }
        
        //else if(Arrays.stream(actPanels[1]).anyMatch(x::equals)) {for (int i = 0; i < actPanels[1].length; i++) {if (x.equals(actPanels[1][i])) {title.setText(uses[1][i].getName());actPanels[1][i].add(title);}}}
        //
        //else {for (int i = 0; i < actPanels[2].length; i++) {if (x.equals(actPanels[2][i])) {title.setText(uses[2][i].getName());actPanels[2][i].add(title);}}}
        
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 90);
    }
    private void discs(JPanel x)
    {
        JTextPane disc = new JTextPane();
        disc.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        disc.setBackground(Color.white);
        disc.setFont(new Font(disc.getFont().getName(), Font.BOLD, 40));
        disc.setForeground(Color.BLACK);      
        disc.setEditable(false);
        
        if(Arrays.stream(actPanels[0]).anyMatch(x::equals)) {for (int i = 0; i < actPanels[0].length; i++) {if (x.equals(actPanels[0][i])) {disc.setText(atkL[i].getDis());actPanels[0][i].add(disc);}}}
        
        else if(Arrays.stream(actPanels[1]).anyMatch(x::equals)) {for (int i = 0; i < actPanels[1].length; i++) {if (x.equals(actPanels[1][i])) {disc.setText(sklL[i].getDis());actPanels[1][i].add(disc);}}}
        
        else {for (int i = 0; i < actPanels[2].length; i++) {if (x.equals(actPanels[2][i])) {disc.setText(itmL[i].getDis());actPanels[2][i].add(disc);}}}

        disc.setSize(disc.getPreferredSize());
        disc.setSize(disc.getWidth()+10, disc.getHeight());
        disc.setLocation(750-disc.getWidth()/2,380-disc.getHeight()/2);
    }

    //Keybinds
    private void keyActions(JPanel panel)
    {
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");
        panel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(0);}});
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Right");
        panel.getActionMap().put("Right", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(1);}});
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "upDown");
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "upDown");
        panel.getActionMap().put("upDown", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(2);}});
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        panel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(3);}});
    }
    private void select()
    {
        if(hasPanel(iPanel)) {if(!down) {panSet(iPanel, actPanels[KBH][0]);mode = KBH;} else {panSet(iPanel,lPanel);}}

        else {panSet(getPan(), iPanel);}
    }
    private void left() 
    {
        if(hasPanel(iPanel) && !down) {if(KBH-1>=0) {KBH--;}else {KBH=2;}}

        else {if(curPan-1>=0) {curPan--;}else {curPan = actPanels[mode].length-1;}panSet(getPan(), actPanels[mode][curPan]);}
    }
    private void right() 
    {
        if(hasPanel(iPanel) && !down) {if(KBH+1<=2) {KBH++;}else {KBH=0;}}

        else {if(curPan+1<=actPanels[mode].length-1) {curPan++;}else {curPan = 0;}panSet(getPan(), actPanels[mode][curPan]);}
    }
    private void keys(int x)
    {
        switch (x) 
        {
            case 0 -> {left();}

            case 1 -> {right();}

            case 2 -> {down^=true;}

            case 3 -> {select();}
        }

        if(!down) {highlight(KBH);}
        else {highlight(3);}
    }
    private void highlight(int x)
    {
        for (Component c : iPanel.getComponents()) 
        {
            if (c instanceof JLabel)
            {((JLabel)c).setBorder(BorderFactory.createLineBorder(Color.black, 5));}}   

        if(x>=0) {buttons[x].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));}
    }
    private void paint(JPanel x, int percent)
    {
        for (Component c : x.getComponents()) {x.setComponentZOrder(c, 0);}
        for(int i=0; i<40; i++)
        {
            for(int j=0; j<75; j++)
            {
                if(j<18 || j>56)
                {
                    JLabel star = new JLabel();
                    star.setOpaque(true);
                    star.setBackground(Color.BLACK);
                    star.setSize(new Dimension(20, 20));
                    star.setLocation(j*20, i*20);
                    x.setComponentZOrder(star, 1);
                    x.add(star);
                    
                    if((int)(Math.random()*100)<=percent-1)
                    {
                        if((int)(Math.random()*2)==0) {star.setBackground(Color.YELLOW);}
                    
                        else {star.setBackground(new Color(189,185,38));}                    
                    }
                }
            }
        }
    }
    private void panSet(JPanel x, JPanel y) {kronk.remove(x);kronk.add(y);y.revalidate();y.repaint();}
    private boolean hasPanel(JPanel targetPanel) 
    {
        Component[] components = kronk.getContentPane().getComponents();
        
        for (Component comp : components) {if (comp == targetPanel) {return true;}}
        return false;
    }
    private JPanel getPan()
    {
        Component[] components = kronk.getContentPane().getComponents();
        
        for (Component comp : components) {if (comp instanceof JPanel) {return (JPanel)comp;}}
        return null;
    }
}
