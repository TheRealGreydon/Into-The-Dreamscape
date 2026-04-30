/*Name:	Mr. Klus
 *Date: 05/01/2019
 *Description: A graphical user interface for the drop game.
 */
package APCS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;


public class GUI extends JFrame implements ActionListener
{
    /** The Drop Game engine. */
    private Main game;
    /** The main panel containing the game components. */
    private JPanel mPanel;
    private JPanel sPanel;
    private JPanel cPanel;

    private JLabel mBack;
    /** Buttons for moving the player. */
    private JButton[] buttons;
    /** Displays the points and turns left. */
    private JLabel info;
    /** The title or "game over". */
    private JLabel title;
        
    /**
     * Initialize the GUI.
     * @param d the DropGame engine
     */
    public GUI(Main g)
    {
        game=g;
        initialize();           
    }
    
    /**
     * Run the game.
     */
    public void displayGame() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
    }

    /**
     * Initialize the display.
     */
    private void initialize()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panels();

        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setTitle("Into the Dreamscape");
        this.add(mPanel);
        this.setVisible(true);
        this.setResizable(true);
    }

    public void panels()
    {
        GridBagConstraints gbc = new GridBagConstraints();
        //Main
        mPanel = new JPanel();
        mPanel.setLayout(new GridBagLayout());
        mPanel.setBackground(Color.black);
        
        title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;       
        title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Into the Dreamscape");
        mPanel.add(title,gbc);

        buttons = new JButton[100];
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = -1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;       
        mPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(Color.RED);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("Credits");
        buttons[1].addActionListener(this);
        
        buttons[2]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_END; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;       
        mPanel.add(buttons[2],gbc);
        buttons[2].setPreferredSize(new Dimension(100,100));
        buttons[2].setBackground(Color.RED);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setText("Exit");
        buttons[2].addActionListener(this);

        //Credits
        cPanel = new JPanel();
        cPanel.setLayout(null);
        cPanel.setBackground(Color.black);

        buttons[3]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;      
        cPanel.add(buttons[3],gbc);
        buttons[3].setPreferredSize(new Dimension(100,100));
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("Exit");
        buttons[3].addActionListener(this);
        JLabel credits = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;    
        cPanel.add(credits, gbc);
        credits.setBounds(20,20,600,80);
        credits.setOpaque(true);
        credits.setBackground(Color.WHITE);
        credits.setHorizontalAlignment(info.CENTER);
        credits.setVerticalAlignment(info.CENTER);
        credits.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        credits.setText("Into the Dreamscape"); 

        JLabel creditsInfo = new JLabel();
        cPanel.add(creditsInfo);
        creditsInfo.setBounds(100,20,600,80);
        creditsInfo.setOpaque(true);
        creditsInfo.setBackground(Color.white);
        creditsInfo.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        creditsInfo.setText("<html><font color='black'>Made by Nicholas Munier and Kai Wilbur\n for the Mr.Klus AP Comp Sci final</font></html>"); 


        //Start
        sPanel = new JPanel();
        sPanel.setLayout(null);
        sPanel.setBackground(Color.PINK);
    }

    public void swapPanel(JPanel x, JPanel y)
    {
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.remove(x);
        this.add(y);
        this.setVisible(true);
        this.setResizable(true);
    }

    /**
     * Respond to a button click (on either the "Left" button,
     * "Stay" button, or the "Right" button).
     * @param e the button click action event
     */
    public void actionPerformed(ActionEvent e)
    {
        //the source of the button click
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0]))
        {
            swapPanel(mPanel, sPanel); 
        }
        else if(j.equals(buttons[1]))
        {
            swapPanel(mPanel, cPanel);
        }
        else if(j.equals(buttons[2]))
        {
            this.dispose();
        }
        else if(j.equals(buttons[3]))
        {
            swapPanel(cPanel, mPanel);
        }
    }
}
