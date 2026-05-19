package APCS;

import java.awt.*;
import javax.swing.*;

import APCS.Enms.enm;
import APCS.Skills.Atk;

public class Battle 
{
    private JLabel charSprite;
    private Player character;

    public Battle(Player character) {this.character = character;}

    public Battle() {}

    public JLabel battleImg()
    {
        charSprite = new JLabel(new ImageIcon(character.getBSprite()));charSprite.setPreferredSize(new Dimension(300,650));
        charSprite.setOpaque(false);charSprite.setBounds(100,0,300,550);
        return charSprite;
    }

    public String attack(enm x, Atk y) {x.doHp(y.getDmg());return x.getName() + " took " + y.getDmg() + " from " + y.getName();}
}
