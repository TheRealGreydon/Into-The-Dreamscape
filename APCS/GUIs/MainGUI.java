package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import APCS.*;
import APCS.Actions.Attacks.*;
import APCS.Actions.Skills.*;
import APCS.Items.*;
import APCS.Items.AttackItem.*;
import APCS.Items.SkillItem.*;

public class MainGUI extends JFrame implements ActionListener
{
    private File save = new File("APCS/Save.txt");
    private Player character = new Player();
    private String name;
    private int gend;
    private int out;
    private int[] stats = {0,0,0,0,0,0};
    private int hp = 15;
    private int lvl = 1;
    public Skl[] skls = {null, null, null, null};
    public Itm[] itms = {null, null, null, null};
    public Atk[] atks = {null, null, null, null};
    private int fav;
    private int curLev = 0;
    private int curStage = 0;
    private int vol = 50;
    private boolean newSave;
        
    private JPanel mPanel = new JPanel();
    private JPanel cPanel = new JPanel();

    private JButton[]buttons = new JButton[4];
           
    public MainGUI() {initialize();}
    
    //Runs the game
    public void displayGame() {java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}//this.dispose();new StartGUI().start();}

    //Main initalize
    private void initialize()
    {
        //Checks if it's a new game file
        try (Scanner sc = new Scanner(save)) {newSave = sc.nextLine().equals("NEW SAVE");}catch (FileNotFoundException e){}

        //Loads data from game file
        if(!newSave) {character.loadSave();}

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        menu();credits();
        this.add(mPanel);
        this.setSize(1500, 800);
        this.setVisible(true);
        mPanel.revalidate();
        mPanel.repaint();
    }

    //Adds to menu
    private void menu()
    {
        mPanel.setLayout(null);

        for(int i=0; i<3; i++)
        {
            buttons[i]=new JButton();
            buttons[i].setBackground(Color.RED);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 40));
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            mPanel.add(buttons[i]);
        }

        JLabel title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Into the Dreamscape");
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        title.setLocation(500, 50);
        title.setSize(title.getPreferredSize());
        mPanel.add(title);

        if(newSave) {buttons[0].setText("Start");}
        
        else {buttons[0].setText("Continue");}

        buttons[0].setBounds(500, 325, 300, 150);
        buttons[1].setText("Credits");buttons[1].setBounds(800,325, 200, 150);
        buttons[2].setText("Exit");buttons[2].setBounds(500,600, 300, 150);
        paint(mPanel,5);
    }

    //Paints a JPanel with a percentage of stars randomly
    private void paint(JPanel x, int percent)
    {
        for (Component c : x.getComponents()) {x.setComponentZOrder(c, 0);}
        for(int i=0; i<40; i++)
        {
            for(int j=0; j<75; j++)
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
    //Adds button to credits
    public void credits()
    {
        cPanel.setLayout(null);

        buttons[3]=new JButton();
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 40));
        buttons[3].addActionListener(this);
        buttons[3].setText("Exit");buttons[3].setBounds(500,600, 300, 150);        
        buttons[3].setFocusable(false);
        cPanel.add(buttons[3]);

        JLabel title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Credits");
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        title.setLocation(575, 50);
        title.setSize(title.getPreferredSize());
        cPanel.add(title);
        paint(cPanel, 5);
    }

    //Buttons
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0])) {if(newSave) {this.dispose();new StartGUI().start();} else {this.dispose();new LevelSelGUI(character).displayGame();}}

        else if(j.equals(buttons[1])) {panSet(mPanel, cPanel);}

        else if(j.equals(buttons[2])) {this.dispose();}

        else if(j.equals(buttons[3])) {panSet(cPanel, mPanel);}
    }

    //Sets panel to a new panel
    private void panSet(JPanel x, JPanel y) {this.remove(x);this.add(y);y.revalidate();y.repaint();}
}