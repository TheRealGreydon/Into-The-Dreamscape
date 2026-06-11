package APCS.Assets.AssetClasses;

import APCS.*;
import APCS.Enms.*;
import APCS.Uses.Actions.Attacks.Atk;

import java.awt.*;
import javax.swing.*;

public class BattleAssets
{    
    //Player sprites in label form
    public JLabel battleImg(Player character)
    {
        JLabel charSprite = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/HeroCombat-" + character.getOutfit() +".gif").getImage()).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
        charSprite.setSize(500,500);
        return charSprite;
    }
    public JLabel spriteImg(Player character)
    {
        JLabel charSprite = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/Char" + character.getOutfit() +".png").getImage()).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
        charSprite.setSize(500,500);
        return charSprite;
    }
    public JLabel walkingImg(Player character)
    {
        JLabel charSprite = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/HeroWalking-" + character.getOutfit() +".gif").getImage()).getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
        charSprite.setSize(500,500);
        return charSprite;
    }
    public JLabel battleImg(Player character, int x, int y)
    {
        JLabel charSprite = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/HeroCombat-" + character.getOutfit() +".gif").getImage()).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)));
        charSprite.setSize(x,y);
        return charSprite;
    }
    public JLabel spriteImg(Player character, int x, int y)
    {
        JLabel charSprite = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/Char" + character.getOutfit() +".png").getImage()).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)));
        charSprite.setSize(x,y);
        return charSprite;
    }
    public JLabel walkingImg(Player character, int x, int y)
    {
        JLabel charSprite = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/HeroWalking-" + character.getOutfit() +".gif").getImage()).getImage().getScaledInstance(x, y, Image.SCALE_DEFAULT)));
        charSprite.setSize(x,y);
        return charSprite;
    }

    //Makes the enms
    public disEnm[] enmBattleButtons(int lvl)
    {
        disEnm[] temp = new disEnm[3];
        for(int i=0; i<3; i++) {temp[i] = randEnm(lvl);temp[i].enmImg.setLocation(1200, (i*250));}

        return temp;
    }

    //Randomly picks one enm from the list
    private disEnm randEnm(int lvl)
    {
        disEnm enmArray [] = {new disEnm(new Needle(1)),new disEnm(new Jar(1)),new disEnm(new Plane(1))};   
        disEnm temp = enmArray[((int)(Math.random()*enmArray.length))];
        temp.Enm.setLvl(lvl);
        return temp;
    }

    //Action buttons
    public JLabel[] actBattleButtons()
    {
        JLabel[] temp = new JLabel[21];

        for(int i=0; i<21; i++)
        {
            temp[i] = new JLabel();
            temp[i].setBackground(new Color(179, 9, 9));
            temp[i].setBorder(BorderFactory.createLineBorder(Color.black, 5));
            temp[i].setForeground(Color.black);
            temp[i].setFont(new Font(temp[i].getFont().getName(), Font.BOLD, 39));
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

    //Next/Exit buttons
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

    //Attack method
    public String attack(enm x, Atk y, Player z) 
    {
        //Player attack
        int a = (int)(Math.random()*100+1);
        int w = y.getDmg();
        if(z.rageing()) {w = (w*6)/5;}

        if(x instanceof Jar && ((Jar)x).hidey()) {return " Miss";}
        
        if(a<=y.acur()) 
        {
            if(y.getId().equals("VAMPBLADEATK")) {z.doHp(5);}
            else if(y.getId().equals("FIREBALLATK")) {if((int)(Math.random()*2)==0) {x.burn();}}
            else if(y.getId().equals("WOUNDINGSTRIKE")) {if((int)(Math.random()*2)==0) {x.blood();}}
            else if(y.getId().equals("STUNSTRIKE")) 
            {
                x.stun();
                x.doHp(w);
                return " " + x.getName() + " took " + w + " and was stunned";
            }
            x.doHp(w);
            if(w<=0) {return " " + x.getName() + " took " + w;}
            return " " + x.getName() + " healed " + w;
        }

        else {return " Miss";}
    }
    public String attack(Player x, enm y, Atk z) 
    {
        int a = (int)(Math.random()*100+1);
        int w = z.getDmg();
        if(x.rageing()) {w = (w*4)/5;}
        if(x.blocking()) {w = w/2;}

        if(a<=z.acur() && w!=0) 
        {
            if(y.isStunned() && ((int)Math.random()*2)==0) {return " Miss";}
            if(z.getId().equals("JETATTACK"))
            {
                x.doHp(w + (-1*(y.getLvl()-1)));
                a = (int)(Math.random()*100+1);
                if(a<=z.acur())
                {
                    if(y.isStunned() && ((int)Math.random()*2)==0) {return " " + x.getName() + " took " + (w + (-1*(y.getLvl()-1)));}
                    x.doHp(w + (-1*(y.getLvl()-1)));
                    return " " + x.getName() + " was hit twice for " + (2*(w + (-1*(y.getLvl()-1))));
                }
                return " " + x.getName() + " took " + (w+ (-1*(y.getLvl()-1)));
            }
            x.doHp(w + (-1*(y.getLvl()-1)));return " " + x.getName() + " took " + (w+ (-1*(y.getLvl()-1)));
        }
        else {return " Miss";}
    }
    public String attack(Player x, enm y) 
    {
        //Only used for the needle special atk to get 15% of player hp
        int w = -1*((x.getHealth()*15)/100);
        if(x.rageing()) {w = (w*4)/5;}
        if(x.blocking()) {w = w/2;}
        
        if(y.isStunned() && ((int)Math.random()*2)==0) {return " Miss";}
        x.doHp(w);return " " + x.getName() + " took " + w;
    }
}
