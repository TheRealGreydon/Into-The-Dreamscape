package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import APCS.*;
import APCS.Assets.AssetClasses.*;
import APCS.Skills.bAtk;

public class MainGUI extends JFrame implements ActionListener
{
    public Player character = new Player("Default", 1, 1, 1);
    
    private Panels p;
    private StartGUI sGUI;
    
    private JPanel mPanel = new JPanel();
    private JPanel sPanel = new JPanel();
    private JPanel cPanel = new JPanel();

    private GridBagConstraints gbc = new GridBagConstraints();

    private JButton[]buttons = new JButton[4];
           
    public MainGUI() {p = new Panels();sGUI = new StartGUI();initialize();}
    
    //Runs the game
    public void displayGame() {java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}

    //Main initalize
    private void initialize()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panels();

        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Into the Dreamscape");
        //this.add(mPanel);
        this.add(sPanel);
        this.setVisible(true);
        this.setResizable(true);
    }

    public void panels() {menu();credits();start();}

    //Adds to menu
    public void menu()
    {
        mPanel = p.menu();

        buttons[0]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;       
        mPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(Color.RED);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("Start");
        buttons[0].addActionListener(this);
        
        buttons[1]=new JButton();
        gbc.gridx = -1;       
        mPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(Color.RED);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("Credits");
        buttons[1].addActionListener(this);
        
        buttons[2]=new JButton();
        gbc.anchor = GridBagConstraints.PAGE_END; 
        gbc.gridx = 1;       
        mPanel.add(buttons[2],gbc);
        buttons[2].setPreferredSize(new Dimension(100,100));
        buttons[2].setBackground(Color.RED);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setText("Exit");
        buttons[2].addActionListener(this);
    }

    //Adds button to credits
    public void credits()
    {
        cPanel = p.credits();

        buttons[3]=new JButton();
        cPanel.add(buttons[3],gbc);
        buttons[3].setPreferredSize(new Dimension(100,100));
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("Exit");
        buttons[3].addActionListener(this);
    }

    //Runs start panel
    private void start() {sPanel=sGUI.start();}

    //Buttons
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0])) {swapPanel(mPanel,sPanel);}

        else if(j.equals(buttons[1])) {swapPanel(mPanel,cPanel);}

        else if(j.equals(buttons[2])) {this.dispose();}

        else if(j.equals(buttons[3])) {swapPanel(cPanel,mPanel);}
    }
    //Handles swaping the panels
    private void swapPanel(JPanel x, JPanel y) {this.pack();this.setExtendedState(JFrame.MAXIMIZED_BOTH);this.remove(x);this.add(y);this.repaint();}
}