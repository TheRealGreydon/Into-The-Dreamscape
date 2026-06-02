package APCS.Actions.Skills;

import APCS.Actions.Attacks.*;

public class vampSwordSKL implements atkSkl
{
    public String getName() {return "Vamp Blade";}
    
    public String getId() {return "VAMPBLADESKL";}

    public Atk getAtk() {return new vampSwordATK();}

    public int stat() {return 0;}

    public int statT() {return 0;}

    public int regenAmt() {return 0;}

    public int atkUpAmt() {return 0;}
}
