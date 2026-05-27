package APCS.Actions.Skills;

public class juiceBox implements healSkl
{
    public String getName() {return "Juice Box";}

    public int getHeal() {return ((int)(Math.random()*3 + 2));}

    public String getId() {return "JUICEBOX";}
}
