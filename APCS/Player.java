package APCS;

import java.util.ArrayList;


public class Player
{
    private String name;
    private int gend;
    private int outfit;
    private int[] stats = {0,0,0,0,0,0};
    private int hp = 9;
    private int lvl = 1;
    private ArrayList<Skills> skills;
    private int fav;
    private int curLev = 1;

    public Player(String name, int gend, int outfit, int fav)
    {
        this.name = name;
        this.gend = gend;
        this.outfit = outfit;
        this.fav = fav;
        this.skills = new ArrayList<Skills>();
        switch (fav) {
            case 1:
            addAbil(new MatchBox());
            break;
            case 2:
            addAbil(new WetTowel());
            break;
            case 3:
            addAbil(new RockThrow());
            break;
            case 4:
            addAbil(new PartyPopper());
            break;
            default:
                throw new AssertionError();
        }
    }
    
    public void setName(String x) {name = x;}

    public void setFav(int x) {fav = x;}

    public void setOut(int x) {outfit = x;}

    public void setGend(int x) {gend = x;}

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

    public void swapAbil(Skills x, int y) {skills.set(y, x);}

    public String getAbil(int x) {return skills.get(x).getName();}

    public int getCurLev() {return curLev;}

    public void setCurLev(int x) {curLev = x;}

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
        int temp=(int)(Math.random()*6);
        statMod(temp, 1);
    }

    public String toString()
    {
        String temp="";
        temp+="Player name: " + name + "\n";
        temp+="Player Lvl: " + lvl + "\n";
        return temp;
    }
}
