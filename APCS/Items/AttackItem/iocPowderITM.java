package APCS.Items.AttackItem;

import APCS.Actions.Attacks.*;

public class iocPowderITM implements atkItm
{
    public String getName() {return "Iocaine";}

    public Atk getAtk() {return new iocPowderATK();}

    public String getId() {return "IOCPOWDERITM";}

    public String getDis() 
    {
        return 
        """
                                                    Iocaine Powder
                             -------------------------------------------------------
                                Inconcievable. When used, has a small
                                chance to instantly kill the target""";
    }

}