package APCS.Uses.Items.SkillItem;

import APCS.Uses.Actions.Skills.*;

public class milkITM implements sklItm
{
    public String getName() {return "Milk";}

    public Skl getSkill() {return new milkSKL();}

    public String getId() {return "MILK";}

    public String getDis() {return "";}
}