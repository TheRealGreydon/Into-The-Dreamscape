package APCS.Skills;

public class smack implements Atk
{

    public String getName() {return "Basic Attack";}

    public String getDes() {return "Basic Attack";}

    public int getDmg() {return (int)(Math.random()*-3 - 1);}

    public int getDmgType() {return 1;}

    public int status() {return 0;}

    public boolean spc() {return false;}   

    public boolean swing() {return false;}

    public int acur() {return 75;}
}