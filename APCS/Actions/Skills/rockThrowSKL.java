package APCS.Actions.Skills;

import APCS.Actions.Attacks.Atk;
import APCS.Actions.Attacks.rockThrowATK;

public class rockThrowSKL implements atkSkl
{
    public String getName() {return "Rock Throw";}

    public boolean isHeal() {return false;} public boolean isDmg() {return true;}

    public String getDis() {return "ROCKTHROW";}

    public Atk getAtk() {return new rockThrowATK();}
}
