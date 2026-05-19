package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import APCS.*;
import APCS.Assets.AssetClasses.*;
import APCS.Skills.bAtk;

public class MainGUI extends JFrame implements ActionListener
{
    private Player character = new Player("Default", 1, 1, 1);
        
    private JPanel mPanel = new JPanel();
    private JPanel sPanel = new StartGUI().start();
    private JPanel cPanel = new JPanel();

    private JButton[]buttons = new JButton[4];
           
    public MainGUI() {initialize();}
    
    //Runs the game
    public void displayGame() {java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}

    //Main initalize
    private void initialize()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        menu();credits();
        this.add(mPanel);
        this.setSize(1500, 800);
        this.setVisible(true);
        mPanel.revalidate();mPanel.repaint();
        panSet(mPanel,sPanel);  //TEMP
    }

    //Adds to menu
    private void menu()
    {
        mPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/CelesteBackTEMP.jpg").getImage(), 0);
        mPanel.setLayout(null);

        for(int i=0; i<3; i++)
        {
            buttons[i]=new JButton();
            buttons[i].setBackground(Color.RED);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            buttons[i].setFont(new Font(buttons[i].getFont().getName(), Font.BOLD, 40));
            buttons[i].addActionListener(this);
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

        buttons[0].setText("Start");buttons[0].setBounds(500, 325, 300, 150);
        buttons[1].setText("Credits");buttons[1].setBounds(800,325, 200, 150);
        buttons[2].setText("Exit");buttons[2].setBounds(500,600, 300, 150);
    }

    //Adds button to credits
    public void credits()
    {
        cPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/CelesteBackTEMP.jpg").getImage(), 0);
        cPanel.setLayout(null);

        buttons[3]=new JButton();
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 40));
        buttons[3].addActionListener(this);
        buttons[3].setText("Exit");buttons[3].setBounds(500,600, 300, 150);        
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
    }

    //Buttons
    public void actionPerformed(ActionEvent e)
    {
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0])) {panSet(mPanel,sPanel);}

        else if(j.equals(buttons[1])) {panSet(mPanel, cPanel);}

        else if(j.equals(buttons[2])) {this.dispose();}

        else if(j.equals(buttons[3])) {panSet(cPanel, mPanel);}
    }

    private void panSet(JPanel x, JPanel y) {this.remove(x);this.add(y);y.revalidate();y.repaint();}
}