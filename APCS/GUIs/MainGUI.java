package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import APCS.*;

public class MainGUI extends JFrame implements ActionListener
{
    private File save = new File("APCS/Save.txt");
    private Player character = new Player();
    private boolean newSave;
        
    private JPanel mPanel = new JPanel();
    private JPanel cPanel = new JPanel();
    private JPanel dPanel = new JPanel();

    private JButton[]buttons = new JButton[7];
           
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
        menu();credits();saveDel();
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

        for(int i=0; i<4; i++)
        {
            buttons[i]=new JButton();
            buttons[i].setBackground(Color.RED);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 40));
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            if(i!=3) {mPanel.add(buttons[i]);} else if(!newSave) {mPanel.add(buttons[i]);}
        }

        JLabel title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Into the Dreamscape");
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        title.setLocation(500, 50);
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        mPanel.add(title);

        if(newSave) {buttons[0].setText("Start");}
        
        else {buttons[0].setText("Continue");}

        buttons[0].setBounds(500, 325, 300, 150);
        buttons[1].setText("Credits");
        buttons[1].setBounds(800,325, 200, 150);
        buttons[2].setText("Exit");
        buttons[2].setBounds(500,600, 300, 150);
        buttons[3].setText("Delete Save");
        buttons[3].setBounds(200,325, 300, 150);
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

        buttons[4]=new JButton();
        buttons[4].setBackground(Color.RED);
        buttons[4].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 40));
        buttons[4].addActionListener(this);
        buttons[4].setText("Exit");
        buttons[4].setBounds(600,600, 300, 150);        
        buttons[4].setFocusable(false);
        cPanel.add(buttons[4]);

        JLabel title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Credits");
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        title.setLocation(650, 50);
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());

        JTextPane cred = new JTextPane();
        cred.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        cred.setText("          Into the Dreamscape\n" +
                    "----------------------------------------------\n"+
                    "Main Code: Nicholas\n"+
                    "Main Story: Kai\n"+
                    "Main Art: Kai\n"+
                    "Literaly all the Code: Nicholas\n"+
                    "Please give us a 100% Mr. Klus :)");
        cred.setBackground(Color.white);
        cred.setFont(new Font(cred.getFont().getName(), Font.BOLD, 40));
        cred.setForeground(Color.BLACK);      
        cred.setSize(cred.getPreferredSize());
        cred.setSize(cred.getWidth()+10, cred.getHeight());
        cred.setLocation(450,200);
        cred.setEditable(false);
        
        cPanel.add(title);
        cPanel.add(cred);
        paint(cPanel, 5);
    }

    public void saveDel()
    {
        dPanel.setLayout(null);

        buttons[5]=new JButton();
        buttons[5].setBackground(Color.RED);
        buttons[5].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[5].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 40));
        buttons[5].addActionListener(this);
        buttons[5].setText("Confirm");
        buttons[5].setBounds(625,300, 300, 150);        
        buttons[5].setFocusable(false);
        dPanel.add(buttons[5]);

        buttons[6]=new JButton();
        buttons[6].setBackground(Color.RED);
        buttons[6].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[6].setFont(new Font(buttons[6].getFont().getName(), Font.BOLD, 40));
        buttons[6].addActionListener(this);
        buttons[6].setText("Exit");
        buttons[6].setBounds(625,600, 300, 150);        
        buttons[6].setFocusable(false);
        dPanel.add(buttons[6]);

        JLabel title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Delete Current Save?");
        title.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        title.setLocation(575, 50);
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        dPanel.add(title);
        paint(dPanel, 5);
    }

    //Buttons
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0])) {if(newSave) {this.dispose();new StartGUI().start();} else {this.dispose();new LevelGUI(character).displayGame();}}

        else if(j.equals(buttons[1])) {panSet(mPanel, cPanel);}

        else if(j.equals(buttons[2])) {this.dispose();}

        else if(j.equals(buttons[3])) {panSet(mPanel, dPanel);}

        else if(j.equals(buttons[4])) {panSet(cPanel, mPanel);}

        else if(j.equals(buttons[5])) 
        {
            try {FileWriter w = new FileWriter("APCS/Save.txt");w.write("NEW SAVE");w.close();}
            catch (IOException e1) {} newSave = true;menu();panSet(dPanel, mPanel);
        }
        
        else if(j.equals(buttons[6])) {panSet(dPanel, mPanel);}
    }

    //Sets panel to a new panel
    private void panSet(JPanel x, JPanel y) {this.remove(x);this.add(y);y.revalidate();y.repaint();}
}