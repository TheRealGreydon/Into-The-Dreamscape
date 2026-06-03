package APCS.Items.SkillItem;

import APCS.Actions.Skills.*;

public class cookieITM implements sklItm
{
    public String getName() {return "Cookie";}

    public Skl getSkill() {return new cookieSKL();}

    public String getId() {return "COOKIEITM";}

    public String getDis() {return "";}
}