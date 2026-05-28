package APCS.Assets.AssetClasses;

import APCS.*;
import APCS.Actions.Attacks.Atk;
import APCS.Enms.*;
import java.awt.*;
import javax.swing.*;

public class BattleAssets
{    
    public JLabel battleImg(Player character)
    {
        JLabel charSprite = new JLabel(new ImageIcon(character.getBSprite()));charSprite.setPreferredSize(new Dimension(450,650));
        charSprite.setOpaque(false);charSprite.setBounds(0,0,500,550);
        return charSprite;
    }

    public JLabel spriteImg(Player character)
    {
        JLabel charSprite = new JLabel(new ImageIcon(character.getSprite()));charSprite.setPreferredSize(new Dimension(450,650));
        charSprite.setOpaque(false);charSprite.setBounds(0,0,500,550);
        return charSprite;
    }

    public disEnm[] enmBattleButtons()
    {
        disEnm[] temp = new disEnm[3];
        for(int i=0; i<3; i++)
        {
            disEnm x;
            if((int)(Math.random()*2) == 1) {x = new disEnm(new Jar(1));}
            else {x = new disEnm(new Needle(1));}
            x.enmButton.setLocation(1200, (i*250));   
            temp[i] = x;
        }

        return temp;
    }

    public JLabel[] actBattleButtons()
    {
        JLabel[] temp = new JLabel[21];

        for(int i=0; i<21; i++)
        {
            temp[i] = new JLabel();
            temp[i].setBackground(new Color(179, 9, 9));
            temp[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            temp[i].setForeground(Color.black);
            temp[i].setFont(new Font(temp[i].getFont().getName(), Font.BOLD, 40));
            temp[i].setSize(new Dimension(300,150));temp[i].setFocusable(false);
            temp[i].setOpaque(true);
            temp[i].setHorizontalAlignment(SwingConstants.CENTER);
            temp[i].setVerticalAlignment(SwingConstants.CENTER);
        }
        
        for(int i=0; i<3; i++) {temp[i].setLocation((i*400), 615);}

        temp[0].setText("Attack");temp[1].setText("Skills");temp[2].setText("Items");int count = 3;

        while(count<21)
        {
            for(int j=0; j<3; j++)
            {
                if(j==0 || j==1) {if(j==0) {temp[count].setLocation(0, 615);} else {temp[count].setLocation(400, 615);}}

                else {temp[count] = bbEN(true);}count++;
            }
        }

        return temp;
    }

    public JLabel bbEN(boolean x)
    {
        JLabel bbNE = new JLabel();bbNE.setBackground(new Color(179, 9, 9));
        bbNE.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        bbNE.setForeground(Color.black);
        if(x)
        {
            bbNE.setFont(new Font(bbNE.getFont().getName(), Font.BOLD, 40));
            bbNE.setBounds(800,615,300,150);
            bbNE.setText("Next");
        }

        else
        {
            bbNE.setFont(new Font(bbNE.getFont().getName(), Font.BOLD, 20));
            bbNE.setBounds(0,540,150,75);
            bbNE.setText("Back");
        }
        
        bbNE.setHorizontalAlignment(SwingConstants.CENTER);
        bbNE.setVerticalAlignment(SwingConstants.CENTER);
        bbNE.setOpaque(true);
        
        return bbNE;
    }

    public String attack(enm x, Atk y) 
    {
        int z = (int)(Math.random()*100+1);
        int w = y.getDmg();
        if(z<=y.acur()) {x.doHp(w);return " " + x.getName() + " took " + w;}

        else {return " Miss";}
    }

    public String attack(Player x, Atk y) 
    {
        int z = (int)(Math.random()*100+1);
        int w = y.getDmg();
        if(z<=y.acur()) 
        {
            if(y.getId().equals("JARCAPTURE"))
            {
                x.freeze(true); return " " + x.getName() + " was constricted";
            }

            else
            {x.doHp(w);return " " + x.getName() + " took " + w;}
        }

        else {return " Miss";}
    }
}
