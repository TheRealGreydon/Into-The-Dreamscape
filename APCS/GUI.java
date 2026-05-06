package APCS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener
{
    private Panels p;
    private StartGUI sGUI;
    private GameGUI gGUI;

    private Player character;
    
    private JPanel mPanel = new JPanel();
    private JPanel sPanel = new JPanel();
    private JPanel cPanel = new JPanel();
    private JPanel gPanel = new JPanel();
    private JPanel pPanel = new JPanel();

    private boolean paused = false;

    private GridBagConstraints gbc = new GridBagConstraints();

    private JButton[]buttons = new JButton[100];
           
    public GUI(String x)
    {p = new Panels();sGUI = new StartGUI();initialize();}

    public GUI()
    {gGUI = new GameGUI(character);}
    
    //Runs the game
    public void displayGame() 
    {java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}

    //Main initalize
    private void initialize()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panels();

        pPanel=p.pause();
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Into the Dreamscape");
        //this.add(mPanel);
        this.add(sPanel);
        this.setVisible(true);
        this.setResizable(true);
    }

    //Inintalizes when game is called
    private void initialize2()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Into the Dreamscape");
        this.setVisible(true);
        this.setResizable(true);
    }

    public void panels()
    {
        menu();
        credits();
        start();
    }

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
    private void start()
    {
        sPanel=sGUI.start();
    }

    //Sets the character
    public void cha(String name, int gend, int out, int fav)
    {
        character = new Player(name, gend, out, fav);
    }

    //Runs the main game
    public void game()
    {
        initialize2();
        gPanel=gGUI.gamePan();
        swapPanel(sPanel, gPanel);

        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        //gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "Left");

        gPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        //gPanel.getActionMap().put("Left", new AbstractAction() {public void actionPerformed(ActionEvent e) {System.out.println("left key pressed via Key Bindings");}});
    }

    public void pause()
    {
        if(paused)
        {
            System.out.println("UnPause");
            this.remove(pPanel);
            this.repaint();
            paused=false;
        }
        else
        {
            System.out.println("Pause");
            this.setLayout(new BorderLayout());
            this.add(pPanel, BorderLayout.NORTH);
            this.repaint();
            paused=true;
        }
    }    

    //Handles swaping the panels
    private void swapPanel(JPanel x, JPanel y)
    {
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.remove(x);
        this.add(y);
        this.repaint();
    }
    
    //Buttons
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0])) {swapPanel(mPanel,sPanel);}

        else if(j.equals(buttons[1])) {swapPanel(mPanel,cPanel);}

        else if(j.equals(buttons[2])) {this.dispose();}

        else if(j.equals(buttons[3])) {swapPanel(cPanel,mPanel);}
    }
}
