package APCS.Uses.Items.SkillItem;

import APCS.Uses.Actions.Skills.*;

public class cookieITM implements sklItm
{
    public String getName() {return "Cookie";}

    public Skl getSkill() {return new cookieSKL();}

    public String getId() {return "COOKIEITM";}

    public String getDis() {return "";}
}