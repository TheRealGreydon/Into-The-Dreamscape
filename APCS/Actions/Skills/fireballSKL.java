package APCS.Actions.Skills;

import APCS.Actions.Attacks.*;

public class fireballSKL implements atkSkl
{
    public String getName() {return "Fireball";}
    
    public String getId() {return "FIREBALLSKL";}

    public Atk getAtk() {return new fireballATK();}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}
}
