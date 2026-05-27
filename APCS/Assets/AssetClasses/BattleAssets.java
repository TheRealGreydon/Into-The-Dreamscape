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
            x.enmButton.setDisabledIcon(x.enmButton.getIcon());
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

                else {temp[count] = bbNext();}count++;
            }
        }

        
        return temp;
    }

    public JLabel bbExit()
    {
        JLabel bbExit = new JLabel();bbExit.setBackground(new Color(179, 9, 9));
        bbExit.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        bbExit.setForeground(Color.black);
        bbExit.setFont(new Font(bbExit.getFont().getName(), Font.BOLD, 20));
        bbExit.setText("Back");
        bbExit.setSize(new Dimension(150,75));
        bbExit.setHorizontalAlignment(SwingConstants.CENTER);
        bbExit.setVerticalAlignment(SwingConstants.CENTER);
        bbExit.setOpaque(true);
        bbExit.setLocation(0,540);
        return bbExit;
    }

    private JLabel bbNext()
    {
        JLabel bbNext = new JLabel();
        bbNext.setBackground(new Color(179, 9, 9));
        bbNext.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        bbNext.setForeground(Color.black);
        bbNext.setFont(new Font(bbNext.getFont().getName(), Font.BOLD, 40));
        bbNext.setText("Next");
        bbNext.setSize(new Dimension(300,150));
        bbNext.setLocation(800,615);
        bbNext.setHorizontalAlignment(SwingConstants.CENTER);
        bbNext.setVerticalAlignment(SwingConstants.CENTER);
        bbNext.setOpaque(true);
        return bbNext;
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
