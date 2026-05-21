package APCS.Assets.AssetClasses;

import APCS.*;
import APCS.Enms.*;
import APCS.Skills.*;
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
            disEnm x = new disEnm(new HemoNeedle(1));
            x.enmButton.setLocation(1200, (i*250));   
            x.enmButton.setDisabledIcon(x.enmButton.getIcon());
            temp[i] = x;
        }
        return temp;
    }

    public JButton[] actBattleButtons()
    {
        JButton[] temp = new JButton[21];
        for(int i=0; i<3; i++)
        {
            temp[i] = new JButton();temp[i].setBackground(new Color(179, 9, 9));
            temp[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));temp[i].setForeground(Color.black);
            temp[i].setFont(new Font(temp[i].getFont().getName(), Font.BOLD, 40));
            temp[i].setSize(new Dimension(300,150));temp[i].setFocusable(false);temp[i].setLocation((i*400), 615);
        }
        temp[0].setText("Attack");temp[1].setText("Skills");temp[2].setText("Items");

        int count = 3;
        while(count<21)
        {
            for(int j=0; j<3; j++)
            {
                if(j==0 || j==1)
                {
                    temp[count] = new JButton();
                    temp[count].setBackground(new Color(179, 9, 9));
                    temp[count].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
                    temp[count].setForeground(Color.black);
                    temp[count].setFont(new Font(temp[count].getFont().getName(), Font.BOLD, 40));
                    temp[count].setSize(new Dimension(300,150));temp[count].setFocusable(false);

                    if(j==0) {temp[count].setLocation(0, 615);} else {temp[count].setLocation(400, 615);}
                }
                else {temp[count] = bbNext();}
                count++;
            }
        }
        return temp;
    }

    public JButton bbExit()
    {
        JButton bbExit = new JButton();bbExit.setBackground(new Color(179, 9, 9));
        bbExit.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbExit.setForeground(Color.black);
        bbExit.setFont(new Font(bbExit.getFont().getName(), Font.BOLD, 20));
        bbExit.setText("Back");bbExit.setSize(new Dimension(150,75));
        bbExit.setLocation(0,540);bbExit.setFocusable(false);return bbExit;
    }

    private JButton bbNext()
    {
        JButton bbNext = new JButton();
        bbNext.setBackground(new Color(179, 9, 9));
        bbNext.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));bbNext.setForeground(Color.black);
        bbNext.setFont(new Font(bbNext.getFont().getName(), Font.BOLD, 40));
        bbNext.setText("Next");bbNext.setSize(new Dimension(300,150));
        bbNext.setLocation(800,615);bbNext.setFocusable(false);return bbNext;
    }

    public JButton[] battleButtons()
    {
        JButton[] temp = new JButton[4];
        for(int i=0; i<4; i++)
        {
            temp[i] = new JButton();
            temp[i].setBackground(new Color(179, 9, 9));
            temp[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));temp[i].setForeground(Color.black);
            temp[i].setFont(new Font(temp[i].getFont().getName(), Font.BOLD, 40));
            temp[i].setSize(new Dimension(300,150));temp[i].setFocusable(false);
            
            if(i%2==0) {temp[i].setLocation(0, 615);} else {temp[i].setLocation(400, 615);}
        }
        return temp;
    }

    public String attack(enm x, Atk y) 
    {
        int z = y.getDmg();
        if(z!=0)
        {
            x.doHp(z);return x.getName() + " took " + z;
        }
        else
        {
            return " Miss";
        }
    }

    public String attack(Player x, Atk y) 
    {
        int z = y.getDmg();
        if(z!=0)
        {
            x.doHp(z);return x.getName() + " took " + z;
        }
        else
        {
            return " Miss";
        }
    }
}
