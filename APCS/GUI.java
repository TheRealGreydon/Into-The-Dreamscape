/*Name:	Mr. Klus
 *Date: 05/01/2019
 *Description: A graphical user interface for the drop game.
 */
package APCS;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI extends JFrame implements ActionListener
{
    /** The Drop Game engine. */
    private Main g;
    private BackgroundPanel b;
    private Panels p;
    /** The main panel containing the game components. */
    private JPanel mPanel;
    private JPanel sPanel;
    private JPanel cPanel;
    private GridBagConstraints gbc = new GridBagConstraints();

    private JButton[]buttons = new JButton[100];
           
    /**
     * Initialize the GUI.
     * @param d the DropGame engine
     */
    public GUI(Main g, BackgroundPanel b)
    {
        this.g=g;
        this.b=b;
        p = new Panels(b);
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
        menu();
        credits();
        start();
    }

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
    }

    public void credits()
    {
        cPanel = p.credits();

        buttons[3]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_END; 
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
    }

    public void start()
    {
        sPanel = p.start();
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
    
    public void actionPerformed(ActionEvent e)
    {
        //the source of the button click
        JButton j = (JButton)(e.getSource());
        
        if(j.equals(buttons[0]))
        {
            swapPanel(mPanel,sPanel); 
        }
        else if(j.equals(buttons[1]))
        {
            swapPanel(mPanel,cPanel);
        }
        else if(j.equals(buttons[2]))
        {
            this.dispose();
        }
        else if(j.equals(buttons[3]))
        {
            swapPanel(cPanel,mPanel);
        }
    }
}
