package APCS.Uses.Items.SkillItem;

import APCS.Uses.Actions.Skills.*;

public class hotCocoITM implements sklItm
{
    public String getName() {return "Hot Coco";}

    public Skl getSkill() {return new hotCocoSKL();}

    public String getId() {return "HOTCOCOITM";}

    public String getDis() {return "";}
}