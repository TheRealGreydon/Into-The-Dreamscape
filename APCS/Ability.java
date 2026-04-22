/**
 *Name:	Mr. Klus
 *Date: 05/01/2019
 *Description:  Contains methods to create and play a drop game.
 *              Players move left/right using the keyboard to catch
 *              points and earn additional turns.  The game is over
 *              when the player has no more turns.        
 */
package APCS;


public class Ability
{
    private String name;
    private String des;
    private int hpMod;
    private boolean spe;

    public Ability(String name, String des, int hpMod, boolean spe)
    {
        this.name = name;
        this.des = des;
        this.hpMod = hpMod;
        this.spe = spe;
    }
    
    public String getName()
    {
        return name;
    }    

    public String getDes()
    {
        return des;
    }

    public boolean isSpe()
    {
        return spe;
    }

    public String getHpMod()
    {
        if(hpMod>0)
        {
            return hpMod + " Healing";
        }
        else
        {
            if(spe)
            {
                return hpMod + " Special Attack Dmg";
            }
            return hpMod + " Attack Dmg";
        }   
    }
}
