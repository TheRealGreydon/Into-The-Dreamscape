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
    private JPanel panel;
    /** The game board displaying point values. */
    private JLabel[][] board;
    /** The display of the player column. */
    private JLabel[] player;
    /** Buttons for moving the player. */
    private JButton[] buttons;
    /** Displays the points and turns left. */
    private JLabel info;
    /** The title or "game over". */
    private JLabel title;
    /** Button to start over. */
    private JButton startOver;
    
    
    private int buttonLength=100;
    private int buttonWidth=100;
    
    private int rows;
    private int cols;
    
    
    /**
     * Initialize the GUI.
     * @param d the DropGame engine
     */
    public GUI(Main d)
    {
        game=d;
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
        
        panel = new JPanel();
        panel.setLayout(null);
        
        panel.setBackground(Color.WHITE);
        
        title = new JLabel();

        //create the panel for the game title
        panel.add(title);
        title.setBounds(200,20,300,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setHorizontalAlignment(info.CENTER);
        title.setVerticalAlignment(info.CENTER);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Into the Dreamscape"); 
        
        //create the 5 labels for showing the current player location
        player = new JLabel[cols];
        
        for(int i=0;i<player.length;i++)
        {
            player[i]=new JLabel();
            panel.add(player[i]);
            player[i].setBounds(buttonWidth*i+100,rows*buttonLength+100,buttonWidth,50);
            player[i].setOpaque(true);
            player[i].setBackground(Color.WHITE);
            player[i].setHorizontalAlignment(player[i].CENTER);
            player[i].setVerticalAlignment(player[i].CENTER);
            player[i].setFont(new Font(player[i].getFont().getName(), Font.PLAIN, 30));
            
        }
    
        player[cols/2].setText("^");
        
        //create the 3 buttons for moving the player
        buttons = new JButton[3];

        buttons[0]=new JButton();
        panel.add(buttons[0]);
        buttons[0].setBounds(125,450,150,50);
        buttons[0].setBackground(Color.RED);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("LEFT");
        buttons[0].addActionListener(this);
        
        buttons[1]=new JButton();
        panel.add(buttons[1]);
        buttons[1].setBounds(275,450,150,50);
        buttons[1].setBackground(Color.RED);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("STAY");
        buttons[1].addActionListener(this);
        
        buttons[2]=new JButton();
        panel.add(buttons[2]);
        buttons[2].setBounds(425,450,150,50);
        buttons[2].setBackground(Color.RED);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setText("RIGHT");
        buttons[2].addActionListener(this);

        //create the label to display score and turns left
        info = new JLabel();

        panel.add(info);
        info.setBounds(150,500,400,50);
        info.setOpaque(true);
        info.setBackground(Color.WHITE);
        info.setHorizontalAlignment(info.CENTER);
        info.setVerticalAlignment(info.CENTER);
        info.setFont(new Font(info.getFont().getName(), Font.BOLD, 20));
        info.setText("POINTS: "+game.getPlayerPoints()+"       TURNS LEFT: "+ game.getTurnsLeft());
        
        //create the button to restart the game
        startOver=new JButton();
        panel.add(startOver);
        startOver.setBounds(275,575,150,50);
        startOver.setBackground(Color.RED);
        startOver.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        startOver.setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        startOver.setText("START OVER");
        startOver.addActionListener(this);
        
        
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
  
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
        
        if(j.equals(startOver))
        {
            //reset for new game
            buttons[0].setEnabled(true);
            buttons[1].setEnabled(true);
            buttons[2].setEnabled(true);
            title.setText("DROP GAME");
        }
        else
        {
            
            
            //call game engine method with appropriate number based
            //on which button was clicked
            if(j.equals(buttons[0]))
            {
                game.moveAndCatch(1);       
            }
            else if(j.equals(buttons[1]))
            {
                game.moveAndCatch(2);
            }
            else
            {
                game.moveAndCatch(3);
            }
            
            
            
            //call game engine method to update board
            game.updateBoard();
        }
        
        //clear all player locations
        for(int i=0;i<player.length;i++)
        {
            player[i].setText("");
        }
        //put player "^" in correct location
        player[game.getPlayerCol()].setText("^"); 

        //redraw board labels with new values
        for(int r=0;r<board.length;r++)
        {
            for(int c=0;c<board[0].length;c++)
            {
                int val=game.getBoardValue(r, c);
                if(val==0)
                    board[r][c].setText("***");
                else
                    board[r][c].setText(""+val);
            }
        }
        
        //update the score and turns left
        info.setText("POINTS: "+game.getPlayerPoints()+"       TURNS LEFT: "+ game.getTurnsLeft());
        
        //If out of turns, the game is over.  Disable all buttons
        //and display message.
        if(game.getTurnsLeft()==0)
        {
            buttons[0].setEnabled(false);
            buttons[1].setEnabled(false);
            buttons[2].setEnabled(false);
            title.setText("GAME OVER");
        }
        
        //Print to the console just for checking.
        game.printGame();

    }


}
