package APCS.GUIs;

import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Assets.AssetClasses.*;
import APCS.Items.AttackItem.*;
import APCS.Items.Itm;
import APCS.Items.SkillItem.*;
import APCS.Player;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class infoGUI extends JFrame implements ActionListener
{
    private Player character;
    private BattleAssets bGUI = new BattleAssets();
    private JPanel iPanel, lPanel;
    private JPanel [] aPanels, sPanels,iPanels;
    private JButton buttons [];
    private JLabel charImg, chestImg, title;
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private boolean down = false;
    private int KBH = 0,mode, curPan = 0;

    private Atk atkL[] = {new smack(), new punch(), new widePunch(), new bigHit(), new twinStrike(), new woundingStrike(), new gamble()};
    private Skl sklL[] = {new juiceBox(), new vampSwordSKL(), new rage(), new fireballSKL()};
    private Itm itmL[] = {new grilledCheeseITM(), new swordITM(), new milkITM(), new cookieITM(), new baguetteITM()};
    

    public infoGUI(Player character, JPanel lPanel, JFrame kronk) 
    {
        this.character = character;this.kronk = kronk;this.lPanel = lPanel;
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
    }

    public void initialize() 
    {
        kronk.pack();
        iPanel.setLayout(null);
        kronk.setSize(1500, 800);
        pansInit();
        highlight(0);
        keyActions(iPanel);
    }

    private void pansInit()
    {
        iPanInit();
        kronk.add(iPanel);
        atkPansInit();
        sklPansInit();
        itmPansInit();
    }

    private void iPanInit()
    {
        iPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
        iPanel.setLayout(null);

        buttons = new JButton[7];
        for(int i=0;i<4; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            buttons[i].setFocusable(false);
            buttons[i].setBackground(Color.red);
            buttons[i].setEnabled(false);
            buttons[i].addActionListener(this);
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 45));
            iPanel.add(buttons[i]);
        }
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 30));

        buttons[0].setText("Atks");
        buttons[1].setText("Skls");
        buttons[2].setText("Itms");
        buttons[3].setText("Back");
        buttons[3].setBounds(650,650,200,100);
        
        buttons[0].setBounds(450,325,200,150);
        buttons[1].setBounds(650,325,200,150);
        buttons[2].setBounds(850,325,200,150);


        title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Select an option to learn more about");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 125);
      
        iPanel.add(title);
      
        paint(iPanel,5);      
    }

    private void atkPansInit()
    {
        aPanels = new JPanel[atkL.length];
        for(int i=0;i<atkL.length;i++)
        {
            aPanels[i] = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
            aPanels[i].setLayout(null);

            title = new JLabel();
            title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
            title.setOpaque(true);
            title.setBackground(Color.WHITE);
            title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
            title.setText(atkL[i].getName());
            title.setSize(title.getPreferredSize());
            title.setSize(title.getWidth()+10, title.getHeight());
            title.setLocation(750 - title.getWidth()/2, 90);

            for(int j=4;j<7; j++)
            {
                buttons[j] = new JButton();
                buttons[j].setBorder(BorderFactory.createLineBorder(Color.blue, 5));
                buttons[j].setFocusable(false);
                buttons[j].setBackground(Color.red);
                buttons[j].setFont(new Font(buttons[j].getFont().getName(), Font.BOLD, 45));
                aPanels[i].add(buttons[j]);
            }

            JTextPane disc = new JTextPane();
            disc.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
            disc.setText(atkL[i].getDis());
            disc.setBackground(Color.white);
            disc.setFont(new Font(disc.getFont().getName(), Font.BOLD, 40));
            disc.setForeground(Color.BLACK);      
            disc.setSize(disc.getPreferredSize());
            disc.setSize(disc.getWidth()+10, disc.getHeight());
            disc.setLocation(750-disc.getWidth()/2,380-disc.getHeight()/2);
            disc.setEditable(false);
            aPanels[i].add(disc);

            buttons[4].setText("Back");
            buttons[4].setBounds(650,650,200,100);
            buttons[5].setText("<-");
            buttons[5].setBounds(550,675,100,50);
            buttons[6].setText("->");
            buttons[6].setBounds(850,675,100,50);
        
            aPanels[i].add(title);
            keyActions(aPanels[i]);
        
            paint(aPanels[i],5);      
        }
    }
    private void sklPansInit()
    {
        sPanels = new JPanel[sklL.length];
        for(int i=0;i<sklL.length;i++)
        {
            sPanels[i] = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
            sPanels[i].setLayout(null);

            title = new JLabel();
            title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
            title.setOpaque(true);
            title.setBackground(Color.WHITE);
            title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
            title.setText(sklL[i].getName());
            title.setSize(title.getPreferredSize());
            title.setSize(title.getWidth()+10, title.getHeight());
            title.setLocation(750 - title.getWidth()/2, 90);

            for(int j=4;j<7; j++)
            {
                buttons[j] = new JButton();
                buttons[j].setBorder(BorderFactory.createLineBorder(Color.blue, 5));
                buttons[j].setFocusable(false);
                buttons[j].setBackground(Color.red);
                buttons[j].setFont(new Font(buttons[j].getFont().getName(), Font.BOLD, 45));
                sPanels[i].add(buttons[j]);
            }

            JTextPane disc = new JTextPane();
            disc.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
            disc.setText(sklL[i].getDis());
            disc.setBackground(Color.white);
            disc.setFont(new Font(disc.getFont().getName(), Font.BOLD, 40));
            disc.setForeground(Color.BLACK);      
            disc.setSize(disc.getPreferredSize());
            disc.setSize(disc.getWidth()+10, disc.getHeight());
            disc.setLocation(750-disc.getWidth()/2,380-disc.getHeight()/2);
            disc.setEditable(false);
            sPanels[i].add(disc);

            buttons[4].setText("Back");
            buttons[4].setBounds(650,650,200,100);
            buttons[5].setText("<-");
            buttons[5].setBounds(550,675,100,50);
            buttons[6].setText("->");
            buttons[6].setBounds(850,675,100,50);
        
            sPanels[i].add(title);
            keyActions(sPanels[i]);
        
            paint(sPanels[i],5);      
        }
    }
    private void itmPansInit()
    {
        iPanels = new JPanel[itmL.length];
        for(int i=0;i<itmL.length;i++)
        {
            iPanels[i] = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);
            iPanels[i].setLayout(null);

            title = new JLabel();
            title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
            title.setOpaque(true);
            title.setBackground(Color.WHITE);
            title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
            title.setText(itmL[i].getName());
            title.setSize(title.getPreferredSize());
            title.setSize(title.getWidth()+10, title.getHeight());
            title.setLocation(750 - title.getWidth()/2, 90);

            for(int j=4;j<7; j++)
            {
                buttons[j] = new JButton();
                buttons[j].setBorder(BorderFactory.createLineBorder(Color.blue, 5));
                buttons[j].setFocusable(false);
                buttons[j].setBackground(Color.red);
                buttons[j].setFont(new Font(buttons[j].getFont().getName(), Font.BOLD, 45));
                iPanels[i].add(buttons[j]);
            }

            JTextPane disc = new JTextPane();
            disc.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
            disc.setText(itmL[i].getDis());
            disc.setBackground(Color.white);
            disc.setFont(new Font(disc.getFont().getName(), Font.BOLD, 40));
            disc.setForeground(Color.BLACK);      
            disc.setSize(disc.getPreferredSize());
            disc.setSize(disc.getWidth()+10, disc.getHeight());
            disc.setLocation(750-disc.getWidth()/2,380-disc.getHeight()/2);
            disc.setEditable(false);
            iPanels[i].add(disc);

            buttons[4].setText("Back");
            buttons[4].setBounds(650,650,200,100);
            buttons[5].setText("<-");
            buttons[5].setBounds(550,675,100,50);
            buttons[6].setText("->");
            buttons[6].setBounds(850,675,100,50);
        
            iPanels[i].add(title);
            keyActions(iPanels[i]);
        
            paint(iPanels[i],5);      
        }
    }

    //Keybinds and buttons
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(buttons[0])) {panSet(iPanel, aPanels[0]);mode = 0;}

        else if(j.equals(buttons[1])) {panSet(iPanel, sPanels[0]);mode = 1;}

        else if(j.equals(buttons[2])) {panSet(iPanel, iPanels[0]);mode = 2;}

        else if(j.equals(buttons[3])) {panSet(iPanel,lPanel);}
    }
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
        if(hasPanel(iPanel))
        {
            if(!down)
            {
                buttons[KBH].setEnabled(true);
                buttons[KBH].doClick();
                buttons[KBH].setEnabled(false);
            }

            else 
            {
                buttons[3].setEnabled(true);
                buttons[3].doClick();
                buttons[3].setEnabled(false);
            }
        }

        else {panSet(getPan(), iPanel);}
    }
    private void left() 
    {
        if(hasPanel(iPanel) && !down) {if(KBH-1>=0) {KBH--;}else {KBH=2;}}

        else 
        {
            switch (mode) 
            {
                case 0 -> {if(curPan-1>=0) {curPan--;}else {curPan = aPanels.length-1;}panSet(getPan(), aPanels[curPan]);}

                case 1 -> {if(curPan-1>=0) {curPan--;}else {curPan = sPanels.length-1;}panSet(getPan(), sPanels[curPan]);}

                case 2 -> {if(curPan-1>=0) {curPan--;} else {curPan = iPanels.length-1;}panSet(getPan(), iPanels[curPan]);}
            }
        }
    }
    private void right() 
    {
        if(hasPanel(iPanel) && !down) {if(KBH+1<=2) {KBH++;}else {KBH=0;}}

        else 
        {
            switch (mode) 
            {
                case 0 -> {if(curPan+1<=aPanels.length-1) {curPan++;}else {curPan = 0;}panSet(getPan(), aPanels[curPan]);}

                case 1 -> {if(curPan+1<=sPanels.length-1) {curPan++;}else {curPan = 0;}panSet(getPan(), sPanels[curPan]);}

                case 2 -> {if(curPan+1<=iPanels.length-1) {curPan++;}else {curPan = 0;}panSet(getPan(), iPanels[curPan]);}
            }
        }
    }
    private void upDown() {down^=true;}

    private void keys(int x)
    {
        switch (x) 
        {
            case 0 -> {left();}

            case 1 -> {right();}

            case 2 -> {upDown();}

            case 3 -> {select();}
        }

        if(!down) {highlight(KBH);}
        else {highlight(3);}
    }

    private void highlight(int x)
    {
        for (Component c : iPanel.getComponents()) 
        {
            if (c instanceof JButton)
            {((JButton)c).setBorder(BorderFactory.createLineBorder(Color.black, 5));}}   

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
                    if((int)(Math.random()*100)<=percent-1)
                    {
                        if((int)(Math.random()*2)==0)
                        {
                            JLabel star = new JLabel();
                            star.setOpaque(true);
                            star.setBackground(Color.YELLOW);
                            star.setSize(new Dimension(20, 20));
                            star.setLocation(j*20, i*20);
                            x.setComponentZOrder(star, 1);
                            x.add(star);
                        }
                        else
                        {
                            JLabel star = new JLabel();
                            star.setOpaque(true);
                            star.setBackground(new Color(189,185,38));
                            star.setSize(new Dimension(20, 20));
                            star.setLocation(j*20, i*20);
                            x.setComponentZOrder(star, 1);
                            x.add(star);
                        }                    
                    }
                    else
                    {
                        JLabel star = new JLabel();
                        star.setOpaque(true);
                        star.setBackground(Color.BLACK);
                        star.setSize(new Dimension(20, 20));
                        star.setLocation(j*20, i*20);
                        x.setComponentZOrder(star, 1);
                        x.add(star);
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
