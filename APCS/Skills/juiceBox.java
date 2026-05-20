package APCS.Skills;

public class juiceBox implements healSkl
{
    public int getHeal() {return ((int)(Math.random()*3 + 2));};

    public int status() {return 0;}

    public String getName() {return "Juice Box";}

    public String getDes() {return "Sippy Sip";}
}
