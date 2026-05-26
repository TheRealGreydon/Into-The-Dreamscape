package APCS.Actions.Skills;

public class juiceBox implements healSkl
{
    public String getName() {return "Juice Box";}

    public int getHeal() {return ((int)(Math.random()*3 + 2));}

    public boolean isHeal() {return true;}

    public String getDis() {return "JUICEBOX";}
}
