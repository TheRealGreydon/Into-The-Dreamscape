package APCS.Items.SkillItem;

import APCS.Actions.Skills.*;

public class grilledCheeseITM implements sklItm
{
    public String getName() {return "Grilled Cheese";}

    public boolean isAtk() {return false;}

    public Skl getSkill() {return new grilledCheeseSKL();}

    public String getDis() {return "GRILLEDCHEESEITM";}
}