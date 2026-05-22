package APCS.Actions.Skills;

public class juiceBox implements healSkl
{
    public String getName() {return "Juice Box";}

    public int getHeal() {return ((int)(Math.random()*3 + 2));} public int status() {return 0;}

    public boolean isHeal() {return true;} public boolean isDmg() {return false;}

    public String getDis() {return "JUICEBOX";}
}
