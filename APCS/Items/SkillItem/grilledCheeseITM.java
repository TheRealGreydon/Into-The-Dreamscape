package APCS.Items.SkillItem;

import APCS.Actions.Skills.*;

public class grilledCheeseITM implements sklItm
{
    public String getName() {return "Grilled Cheese";}

    public Skl getSkill() {return new grilledCheeseSKL();}

    public String getId() {return "GRILLEDCHEESETM";}
}