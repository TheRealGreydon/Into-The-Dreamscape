/*Name:	Mr. Klus
 *Date: 05/01/2019
 *Description: A graphical user interface for the drop game.
 */
package APCS;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
        
        this.setSize(700,700);
        
        mPanel = new JPanel();
        mPanel.setLayout(null);
        mPanel.setBackground(Color.BLACK);
        
        title = new JLabel();

        //create the panel for the game title
        mPanel.add(title);
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

        mPanel.add(info);
        info.setBounds(150,500,400,50);
        info.setOpaque(true);
        info.setBackground(Color.WHITE);
        info.setHorizontalAlignment(info.CENTER);
        info.setVerticalAlignment(info.CENTER);
        info.setFont(new Font(info.getFont().getName(), Font.BOLD, 20));
        info.setText("Wario");
        
        this.setTitle("Into the Dreamscape");
        this.add(mPanel);
        this.setVisible(true);
        this.setResizable(true);
    }

    public void start()
    {
        sPanel = new JPanel();
        sPanel.setLayout(null);
        sPanel.setBackground(Color.PINK);
        
        this.setVisible(false);
        this.remove(mPanel);
        this.add(sPanel);
        this.setVisible(true);
    }

    public void credit()
    {
        cPanel = new JPanel();
        cPanel.setLayout(null);
        cPanel.setBackground(Color.PINK);

        buttons[3]=new JButton();
        cPanel.add(buttons[3]);
        buttons[3].setBounds(125,450,150,50);
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("Exit");
        buttons[3].addActionListener(this);
        
        this.setVisible(false);
        this.remove(mPanel);
        this.add(cPanel);
        this.setVisible(true);
    }

    public void exit()
    {
        this.setVisible(false);
        this.getContentPane().removeAll();
        this.add(mPanel);
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
            start();    
        }
        else if(j.equals(buttons[1]))
        {
            credit();
        }
        else if(j.equals(buttons[2]))
        {
            this.dispose();
        }
        else if(j.equals(buttons[3]))
        {
            exit();
        }
    }
}
