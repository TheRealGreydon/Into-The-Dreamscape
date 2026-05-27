package APCS.Actions.Skills;

import APCS.Actions.Attacks.Atk;
import APCS.Actions.Attacks.rockThrowATK;

public class rockThrowSKL implements atkSkl
{
    public String getName() {return "Rock Throw";}
    
    public String getId() {return "ROCKTHROW";}

    public Atk getAtk() {return new rockThrowATK();}
}
