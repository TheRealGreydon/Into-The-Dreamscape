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


public class GUI extends JFrame implements ActionListener
{
    /** The Drop Game engine. */
    private Main game;
    /** The main panel containing the game components. */
    private JPanel mPanel;
    private JPanel sPanel;
    private JPanel cPanel;
    private GridBagConstraints gbc;

    private JLabel mBack;
    /** Buttons for moving the player. */
    private JButton[] buttons;
    /** Displays the points and turns left. */
    private JLabel info;
    /** The title or "game over". */
    private JLabel title;
    
    private int buttonLength=100;
    private int buttonWidth=100;    
    
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
        //Program will ende if frame is closed
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
        //Main
        gbc = new GridBagConstraints();

        mPanel = new JPanel();
        //mPanel.setLayout(null);
        mPanel.setBackground(Color.black);
        
        title = new JLabel();

        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row
        gbc.anchor = GridBagConstraints.WEST; // Align left
        gbc.insets = new Insets(10, 10, 10, 10); // 10px padding on all sides


        //create the panel for the game title
        mPanel.add(title, gbc);
        title.setBounds(20,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setHorizontalAlignment(info.CENTER);
        title.setVerticalAlignment(info.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Into the Dreamscape"); 
        
        //create the 3 buttons for moving the player
        buttons = new JButton[100];

        buttons[0]=new JButton();
        mPanel.add(buttons[0]);
        buttons[0].setBounds(125,450,150,50);
        buttons[0].setBackground(Color.RED);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("Start");
        buttons[0].addActionListener(this);
        
        buttons[1]=new JButton();
        mPanel.add(buttons[1]);
        buttons[1].setBounds(275,450,150,50);
        buttons[1].setBackground(Color.RED);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("Credits");
        buttons[1].addActionListener(this);
        
        buttons[2]=new JButton();
        mPanel.add(buttons[2]);
        buttons[2].setBounds(425,450,150,50);
        buttons[2].setBackground(Color.RED);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setText("Exit");
        buttons[2].addActionListener(this);

        //create the label to display score and turns left
        info = new JLabel();

        mPanel.setLayout(new GridBagLayout());
        mPanel.add(info);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setOpaque(true);
        info.setBackground(Color.WHITE);
        info.setHorizontalAlignment(info.CENTER);
        info.setVerticalAlignment(info.CENTER);
        info.setFont(new Font(info.getFont().getName(), Font.BOLD, 20));
        info.setText("Wario");

        //Credits
        cPanel = new JPanel();
        cPanel.setBackground(Color.PINK);

        buttons[3]=new JButton();
        cPanel.add(buttons[3]);
        buttons[3].setBounds(125,450,150,50);
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("Exit");
        buttons[3].addActionListener(this);

        //Start
        sPanel = new JPanel();
        sPanel.setLayout(null);
        sPanel.setBackground(Color.PINK);
    }

    public void swapPanel(JPanel x, JPanel y)
    {
        this.setVisible(false);
        this.remove(x);
        this.add(y);
        this.pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
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
