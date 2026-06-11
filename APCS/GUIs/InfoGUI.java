package APCS.GUIs;

import APCS.*;
import APCS.Assets.AssetClasses.*;
import APCS.Uses.Actions.Attacks.*;
import APCS.Uses.Actions.Skills.*;
import APCS.Uses.Items.AttackItem.*;
import APCS.Uses.Items.SkillItem.*;
import APCS.Uses.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InfoGUI extends JFrame 
{
    private JPanel iPanel, cPanel, lPanel;
    private JPanel [][] actPanels = new JPanel[3][];
    private Player character;
    private JLabel [] backs = new JLabel[5];
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private int level = 1;
    private int KBH = 0,mode, curPan = 0;

    private Uses [][] uses = 
    {{new smack(), new punch(), new bigHit(), new twinStrike(), new woundingStrike(), new gamble(), new stunStrike()},
    {new juiceBox(), new vampSwordSKL(), new rage(), new fireballSKL(), new block()},
    {new swordITM(), new milkITM(), new cookieITM(), new baguetteITM(), new iocPowderITM()}};
    
    public InfoGUI(Player character, JPanel lPanel, JFrame kronk) 
    {
        this.kronk = kronk;this.lPanel = lPanel;this.character = character;
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        initialize();
    }
    
    private void initialize() 
    {
        kronk.pack();
        iPanel.setLayout(null);
        kronk.setSize(1500, 800);
        allPansInit();
        iPanInit();
        charPanel();
        kronk.add(iPanel);
        highlight(0);
        keyActions(iPanel);
    }
    private void iPanInit()
    {
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        iPanel.setLayout(null);
        
        //Makes the backs
        for(int i=0;i<5; i++)
        {
            backs[i] = new JLabel();
            backs[i].setOpaque(true);
            backs[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            backs[i].setFocusable(false);
            backs[i].setBackground(Color.red);
            backs[i].setHorizontalAlignment(SwingConstants.CENTER);
            backs[i].setVerticalAlignment(SwingConstants.CENTER);
            backs[i].setFont(new Font(backs[i].getFont().getName(), Font.BOLD, 45));

            iPanel.add(backs[i]);
        }

        backs[3].setFont(new Font(backs[3].getFont().getName(), Font.BOLD, 30));
        backs[0].setText("Atks");
        backs[1].setText("Skls");
        backs[2].setText("Itms");
        backs[3].setText("Back");
        backs[4].setText(character.getName());
        
        backs[0].setBounds(450,375,200,150);
        backs[1].setBounds(650,375,200,150);
        backs[2].setBounds(850,375,200,150);
        backs[3].setBounds(650,650,200,100);
        backs[4].setSize(backs[4].getPreferredSize());
        if(backs[4].getWidth()<200) {backs[4].setSize(200, 100);}
        backs[4].setSize(backs[4].getWidth(), 100);
        backs[4].setLocation(750-backs[4].getWidth()/2,225);

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

        for(int i=0; i<3;i++) {arrbacks(actPanels[i]);}
      
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
                charInfos(actPanels[i][j]);
                keyActions(actPanels[i][j]);
            
                paint(actPanels[i][j],5);      
            }
        }
    }
    private void charPanel()
    {
        cPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        cPanel.setLayout(null);

        JLabel charImg = new BattleAssets().spriteImg(character,650,650);
        charImg.setLocation(1000-charImg.getWidth()/2,500-charImg.getHeight()/2);

        JLabel title = new JLabel();
        title.setText(character.getName());
        title.setSize(title.getPreferredSize());
        if(title.getWidth()<200) {title.setSize(200,80);}
        title.setSize(title.getWidth(),80);
        title.setBackground(Color.white);
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setForeground(Color.BLACK);      
        title.setLocation(650-title.getWidth()/2,title.getHeight()/2);
        title.setOpaque(true);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);

        JLabel back = new JLabel();
        back.setOpaque(true);
        back.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        back.setFocusable(false);
        back.setBackground(Color.red);
        back.setHorizontalAlignment(SwingConstants.CENTER);
        back.setVerticalAlignment(SwingConstants.CENTER);
        back.setFont(new Font(back.getFont().getName(), Font.BOLD, 30));
        back.setBounds(650,650,200,100);
        back.setText("Back");

        JTextPane charInfo = new JTextPane();
        charInfo.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        charInfo.setBackground(Color.white);
        charInfo.setFont(new Font(charInfo.getFont().getName(), Font.BOLD, 45));
        charInfo.setForeground(Color.BLACK);      
        charInfo.setEditable(false);
        charInfo.setText("""
                Level:\t""" + character.getLvl() + """
                
                Stage:\t""" + (character.getLevel()+1) + " - " + (character.getStage()+1) + """

                Attacks Unlocked:\t""" + character.atkUnlocked() + "/" + character.atkId.length + """

                Skills Unlocked:\t""" + character.sklUnlocked() + "/" + character.sklId.length + """

                Times Died:\t""" + character.died() + """
                """);        
        charInfo.setSize(charInfo.getPreferredSize());
        charInfo.setSize(charInfo.getWidth()+10, charInfo.getHeight());
        charInfo.setLocation(650-charInfo.getWidth()/2,360-charInfo.getHeight()/2);

        cPanel.add(charImg);
        cPanel.add(charInfo);
        cPanel.add(title);
        cPanel.add(back);
        cPanel.setComponentZOrder(charInfo,0);
        paint(cPanel,5);
        keyActions(cPanel);
    }

    //Makes the arrow backs/titles/charInfos for the individual panels
    private void arrbacks(JPanel[] x)
    {
        for(int i=0; i<x.length; i++)
        {
            JLabel arrowbacks [] = new JLabel[3];            

            for(int j=0;j<3;j++)
            {
                arrowbacks[j] = new JLabel();
                arrowbacks[j].setOpaque(true);
                arrowbacks[j].setFocusable(false);
                arrowbacks[j].setBackground(Color.red);
                arrowbacks[j].setHorizontalAlignment(SwingConstants.CENTER);
                arrowbacks[j].setVerticalAlignment(SwingConstants.CENTER);
                arrowbacks[j].setFont(new Font(arrowbacks[j].getFont().getName(), Font.BOLD, 45));
                arrowbacks[j].setBorder(BorderFactory.createLineBorder(Color.black, 5));

                x[i].add(arrowbacks[j]);
            }

            arrowbacks[0].setText("Back");
            arrowbacks[0].setBounds(650,650,200,100);
            arrowbacks[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            arrowbacks[1].setText("<-");
            arrowbacks[1].setBounds(550,675,100,50);
            arrowbacks[2].setText("->");
            arrowbacks[2].setBounds(850,675,100,50);
        }
    }
    private void titles(JPanel x)
    {
        JLabel title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));

        for(int j=0; j<3;j++) {if(actPanels[j]!=null) {for(int i=0;i<actPanels[j].length;i++) {if(actPanels[j][i]==x) {title.setText(uses[j][i].getName());actPanels[j][i].add(title);}}}}
      
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 90);
    }
    private void charInfos(JPanel x)
    {
        JTextPane charInfo = new JTextPane();
        charInfo.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        charInfo.setBackground(Color.white);
        charInfo.setFont(new Font(charInfo.getFont().getName(), Font.BOLD, 40));
        charInfo.setForeground(Color.BLACK);      
        charInfo.setEditable(false);
        
        for(int j=0; j<3;j++) {if(actPanels[j]!=null) {for(int i=0;i<actPanels[j].length;i++) {if(actPanels[j][i]==x) {charInfo.setText(uses[j][i].getDis());actPanels[j][i].add(charInfo);}}}}

        charInfo.setSize(charInfo.getPreferredSize());
        charInfo.setSize(charInfo.getWidth()+10, charInfo.getHeight());
        charInfo.setLocation(750-charInfo.getWidth()/2,380-charInfo.getHeight()/2);
    }

    //Keybinds
    private void keyActions(JPanel panel)
    {
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");
        panel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(0);}});
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "Right");
        panel.getActionMap().put("Right", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(1);}});
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        panel.getActionMap().put("Up", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(2);}});
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        panel.getActionMap().put("Down", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(3);}});
        panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Sel");
        panel.getActionMap().put("Sel", new AbstractAction() {public void actionPerformed(ActionEvent e) {keys(4);}});
    }
    private void select()
    {
        if(hasPanel(iPanel)) 
        {
            if(level==1) {panSet(iPanel, actPanels[KBH][0]);mode = KBH;} 
            
            else if(level==0) {panSet(iPanel,lPanel);} 

            else {panSet(iPanel, cPanel);}
        }

        else {panSet(getPan(), iPanel);}
    }
    private void left() 
    {
        if(hasPanel(iPanel) && level==1) {if(KBH-1>=0) {KBH--;}else {KBH=2;}}

        else if(!hasPanel(iPanel)) {if(curPan-1>=0) {curPan--;}else {curPan = actPanels[mode].length-1;}panSet(getPan(), actPanels[mode][curPan]);}
    }
    private void right() 
    {
        if(hasPanel(iPanel) && level==1) {if(KBH+1<=2) {KBH++;}else {KBH=0;}}

        else if(!hasPanel(iPanel)) {if(curPan+1<=actPanels[mode].length-1) {curPan++;}else {curPan = 0;}panSet(getPan(), actPanels[mode][curPan]);}
    }
    private void up() {if(level+1<=2) {level++;}}
    private void down() {if(level-1>=0) {level--;}}
    private void keys(int x)
    {
        switch (x) 
        {
            case 0 -> {left();}

            case 1 -> {right();}

            case 2 -> {up();}

            case 3 -> {down();}

            case 4 -> {select();}
        }

        if(level==1) {highlight(KBH);}
        else if(level==0){highlight(3);}
        else {highlight(4);}
    }
    private void highlight(int x)
    {
        for (Component c : iPanel.getComponents()) 
        {
            if (c instanceof JLabel)
            {((JLabel)c).setBorder(BorderFactory.createLineBorder(Color.black, 5));}}   

        if(x>=0) {backs[x].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 10));}
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