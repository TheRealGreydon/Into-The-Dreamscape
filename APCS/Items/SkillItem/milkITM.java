package APCS.Items.SkillItem;

import APCS.Actions.Skills.*;

public class milkITM implements sklItm
{
    public String getName() {return "Milk";}

    public Skl getSkill() {return new milkSKL();}

    public String getId() {return "MILK";}
}