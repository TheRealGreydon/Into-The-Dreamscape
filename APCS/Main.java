/**
 *Name:	Mr. Klus
 *Date: 05/01/2019
 *Description:  Contains methods to create and play a drop game.
 *              Players move left/right using the keyboard to catch
 *              points and earn additional turns.  The game is over
 *              when the player has no more turns.        
 */
package APCS;

import java.util.Scanner;


public class Main
{
    /** reads input from the keyboard*/
    Scanner in = new Scanner(System.in);
    
    /** stores the number of rows*/
    int rows=3;
    
    /** stores the number of columns*/
    int cols=5;
    
    /** stores the point values for the game*/
    int[][] board = new int[rows][cols];
  
    /** the column under which the player is positioned */
    String playName;
  
    /** the number of points earned by the player */
    boolean gend;
  
    /** the number of turns remaining before the game ends */
    int out;
  
    
    /** Initialize the board with random values of 0, 100, 200, 300, 400
     *  or 500.  0 should occur no 10% of the time, while all
     *  other values appear with equal probability.  The player should 
     *  start in column 2 and have 10 turns remaining.
     */
    public void setUpGame()
    {
        	      
    }
    
    
    /** Prints the contents of board in a grid format.  Prints a '^' 
     *  representing the column location of the player.  Prints out
     *  the current player points, and turns left.  
     *  
     *  Postcondition:  The values of all variables are unchanged.
     */
    public void printGame()
    {
     
   
    }
    
    
    /** Continue to print the game board, ask the player for a move value, 
     *  move player, and update the game if the player has more turns. 
     *  Otherwise end the game. 
     */
    public void playGame()
    {
        

    }
    
    

    /** Updates playerCol given a move value. Make sure playerCol
     *  doesn't go out of bounds. Updates playerPoints and turnsLeft
     *  based on the player catching the item. Prints an appropriate message.	
     * 
     *  Postcondition: 0<=playerCol<board[0].length
     *  
     *  @param move 1 for left, 2 for stay, 3 for right 
     */
    public void moveAndCatch(int move)
    {
  

    }
    
    
    /** Moves each item on the board 1 row below its previous location.
     *  Items on the bottom row are removed from the board. Fills the top
     *  row with new random point values.
     *  
     *  Postcondition: The value of playerCol is unchanged.
     */
    public void updateBoard()
    { 

        
    }


    //The methods below are needed to interact withe the Graphical User Interface
    
    /**
     * @return the rows
     */
    public int getRows()
    {
        return rows;
    }


    /**
     * @return the cols
     */
    public int getCols()
    {
        return cols;
    }
    
    /**
     * @param r the row to be accessed
     * @param c the column to be accessed
     * @return the board value at board[r][c]
     */
    public int getBoardValue(int r, int c)
    {
        return board[r][c];
    }
    
    /**
     * @return the playerCol
     */
    public String getPlayerCol()
    {
        return playerCol;
    }


    /**
     * @return the playerPoints
     */
    public int getPlayerPoints()
    {
        return playerPoints;
    }


    /**
     * @return the turnsLeft
     */
    public int getTurnsLeft()
    {
        return turnsLeft;
    }
    
    /**
     * Reduce turnsLeft by one
     */
    public void takeTurn()
    {
        turnsLeft--;
    }
     
}
