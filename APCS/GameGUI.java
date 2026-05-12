package APCS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameGUI extends JFrame implements ActionListener
{
    private int x;
    private Player character;
    public GameGUI(Player character, int x) {this.character = character; this.x = x;}
    private LevelGUI lGUI = new LevelGUI();
    private JPanel lPanel;
    private JButton pau = new JButton();
    private JButton set = new JButton();
    private JButton exit = new JButton();
    private JButton settings = new JButton();
    private JButton vole = new JButton();
    private GridBagConstraints gbc = new GridBagConstraints();
    private int vol;

    private boolean paused = false;

    private BackgroundPanel a = new BackgroundPanel(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage(), 1);

    public void start(Player x)
    {
        setChar(x);
        lPanel = lGUI.lPanel;
        close();
        displayGame();
        battleButtons();
        vol = character.getVol();
    }

    private void battleButtons()
    {
        lPanel.setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;gbc.ipady = 0;gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.CENTER;gbc.gridx = 1;gbc.gridwidth = 2;gbc.gridy = 2;  
        keyActions();   
    }

    private void pauButtons()
    {
        lPanel.setLayout(null);
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
        lPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "Pause");
        lPanel.getActionMap().put("Pause", new AbstractAction() {public void actionPerformed(ActionEvent e) {pause();}});
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
            lPanel.add(settings);
            lPanel.add(exit);
            lPanel.add(pau);
            exit.setLocation((lPanel.getWidth()/2)-100, (lPanel.getHeight()/2)+150);
            settings.setLocation((lPanel.getWidth()/2-100), (lPanel.getHeight()/2+50));
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
        vole.setForeground(Color.white);vole.setLocation((lPanel.getWidth()/2-50)+150, (lPanel.getHeight()/2+50));vole.setVisible(true);vole.setEnabled(true);vole.setText(String.valueOf(vol));
        lPanel.add(vole);lPanel.add(set);
    }

    private void exit() {if(set.isVisible()) {pause();} else {levExit();}}

    public void setChar(Player z) {character = z;lGUI.setChar(character);}

    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());

        if(j.equals(exit)) {exit();}

        else if(j.equals(settings)) {settings();}
        
        else if(j.equals(vole)) {if(vol+25>100) {vol=0;}else{vol+=25;}character.setVol(vol);vole.setText(String.valueOf(vol));}
    }

    private void displayGame() {initialize();java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}
    private void initialize() {setDefaultCloseOperation(EXIT_ON_CLOSE);this.pack();this.setExtendedState(JFrame.MAXIMIZED_BOTH);this.setTitle("Into the Dreamscape");this.setVisible(true);this.setResizable(true);this.add(lPanel);}
    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
    private void levExit() {GUI gui = new GUI();close();gui.cha(character);gui.game();}
    public void imgSwap(int x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/defaultLevelMenu" + x + ".jpg").getImage());}
    public void imgSwap(String x) {a.setImage(new ImageIcon("APCS/Assets/Background Img/Game Level Menu/Lvl Sel/defaultLevelSel" + x + ".jpg").getImage());}
    public JPanel gamePan() {JPanel gPanel = a;return gPanel;}
    public void select(int x) {String temp=String.valueOf(x) + "0";imgSwap(temp);}
}
