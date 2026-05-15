package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import APCS.*;
import APCS.Assets.*;
import APCS.GUIs.*;

public class GUI extends JFrame implements ActionListener
{
    public Player character = new Player("Default", 1, 1, 1);
    
    private Panels p;
    private StartGUI sGUI;
    private GameGUI gGUI;
    private LevelGUI lGUI;
    
    private JPanel mPanel = new JPanel();
    private JPanel sPanel = new JPanel();
    private JPanel cPanel = new JPanel();
    private JPanel gPanel = new JPanel();

    private boolean paused = false;
    private boolean selected = false;
    private int levNum = 0;
    private int vol = 50;

    private GridBagConstraints gbc = new GridBagConstraints();

    private JButton[]buttons = new JButton[100];
    private JButton pau = new JButton();
    private JButton set = new JButton();
    private JButton exit = new JButton();
    private JButton settings = new JButton();
    private JButton vole = new JButton();
           
    public GUI(String x) {p = new Panels();sGUI = new StartGUI();initialize();}
    public GUI() {gGUI = new GameGUI(character);}
    
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

    //Sets the character
    public void cha(String name, int gend, int out, int fav) {character.setName(name);character.setGend(gend);character.setOut(out);character.setFav(fav);}
    public void cha(Player x) {character = x;vol = character.getVol();gGUI = new GameGUI(character);}

    //Runs the main game
    public void game() {initialize2();gPanel=gGUI.gamePan();swapPanel(sPanel, gPanel);keyActions();}

    public void level() {lGUI.setChar(character);initialize2();swapPanel(gPanel, lGUI.lPanel);}

    private void pauButtons()
    {
        set.setVisible(false);
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
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        gPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "Select");

        gPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
        gPanel.getActionMap().put("Select", new AbstractAction() {public void actionPerformed(ActionEvent e) {select();}});
    }

    //Displays the pause screen
    private void pause() 
    {  
        if(paused) {this.settings.setVisible(false);this.settings.setEnabled(false);this.pau.setVisible(false);this.exit.setEnabled(false);this.exit.setVisible(false);this.set.setVisible(false);this.vole.setVisible(false);this.vole.setEnabled(false);paused=false;}
        else 
        {
            this.pau.setForeground(Color.white);
            this.pau.setFont(new Font(pau.getFont().getName(), Font.BOLD, 40));this.pau.setText("Paused");this.pau.setBackground(new Color(0,0,0,200));
            this.exit.setText("Exit");
            this.settings.setText("Settings");
            gPanel.add(settings);
            gPanel.add(exit);
            gPanel.add(pau);
            exit.setLocation((gPanel.getWidth()/2)-100, (gPanel.getHeight()/2)+150);
            settings.setLocation((gPanel.getWidth()/2-100), (gPanel.getHeight()/2+50));
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
        this.pau.setVisible(false);set.setEnabled(false);set.setForeground(Color.white);set.setVisible(true);set.setBackground(Color.BLACK);
        set.setFont(new Font(set.getFont().getName(), Font.BOLD, 40));set.setText("Settings"); 
        exit.setText("Back");settings.setText("Volume");settings.setEnabled(false);
        vole.setFont(new Font(vole.getFont().getName(), Font.BOLD, 40));vole.addActionListener(this);vole.setSize(new Dimension(125,100));vole.setBackground(Color.black);
        vole.setForeground(Color.white);vole.setLocation((gPanel.getWidth()/2-50)+150, (gPanel.getHeight()/2+50));vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(vol));
        gPanel.add(vole);gPanel.add(set);
    }

    private void select() 
    {
        if(paused==false && selected==false){gGUI.select(character.getCurLev());selected=true;}

        else if(paused==false && selected==true){gGUI.start(character);}
    }
    
    //Buttons
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0])) {swapPanel(mPanel,sPanel);}

        else if(j.equals(buttons[1])) {swapPanel(mPanel,cPanel);}

        else if(j.equals(buttons[2])) {this.dispose();}

        else if(j.equals(buttons[3])) {swapPanel(cPanel,mPanel);}

        else if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(vol+25>100) {vol=0;}else{vol+=25;}character.setVol(vol);vole.setText(String.valueOf(vol));}
    }

    private void close() {while(Window.getWindows().length>1) {Window.getWindows()[0].dispose();}}
    //Handles swaping the panels
    private void swapPanel(JPanel x, JPanel y) {this.pack();this.setExtendedState(JFrame.MAXIMIZED_BOTH);this.remove(x);this.add(y);this.repaint();}
    private void exit() {if(set.isVisible()) {pause();} else {this.dispose();}}
}