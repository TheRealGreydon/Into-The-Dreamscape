/**
 *Name:	Mr. Klus
 *Date: 05/01/2019
 *Description:  Contains methods to create and play a drop game.
 *              Players move left/right using the keyboard to catch
 *              points and earn additional turns.  The game is over
 *              when the player has no more turns.        
 */
package APCS;

import java.util.ArrayList;


public class Player
{
    private String name;
    private int gend;
    private int outfit;
    private int[] stats = {0,0,0,0,0,0};
    private int hp=9;
    private int lvl=1;
    private ArrayList<Skills> skills;
    private int fav;

    public Player(String name, int gend, int outfit, int fav)
    {
        this.name = name;
        this.gend = gend;
        this.outfit = outfit;
        this.fav = fav;
        switch (fav) {
            case 1:
           
            break;
            case 2:
           
            break;
            case 3:
           
            break;
            case 4:
           
            break;
            default:
                throw new AssertionError();
        }
    }
    
    public String getName()
    {
        return name;
    }    

    public int getGend()
    {
        return gend;
    }

    public int getOutfit()
    {
        return outfit;
    }

    public int getStr()
    {
        return stats[0];
    }

    public int getCha()
    {
        return stats[1];
    }

    public int getHp()
    {
        return stats[2];
    }

    public int getInt()
    {
        return stats[3];
    }

    public int getSpe()
    {
        return stats[4];
    }

    public int getSpd()
    {
        return stats[5];
    }

    public void statMod(int x, int y)
    {
        stats[x]+= y;
    }

    public void doHp(int x)
    {
        hp+=x;
    }

    public int getHealth()
    {
        return hp;
    }

    public void resetHp()
    {
        hp=9+lvl+(getHp()*2);
    }

    public void addAbil(Skills x)
    {
        skills.add(x);
    }

    public void swapAbil(Skills x, int y)
    {
        skills.set(y, x);
    }

    public String getAbil(int x)
    {
        return skills.get(x).getName();
    }

    public String listAbil()
    {
        String temp="";
        for(int i=0; i<skills.size(); i++)
        {
            temp+=getAbil(i)+"\n";
        }
        return temp;
    }

    public void lvlUp(int x)
    {
        lvl++;
        statMod(x, 1);
        statMod(2,1);
        statMod(0, 1);
        statMod(5, 1);
    }
}
