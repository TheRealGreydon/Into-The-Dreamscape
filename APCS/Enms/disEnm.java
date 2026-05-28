package APCS.Enms;

import java.awt.*;
import javax.swing.*;

public class disEnm 
{
    public enm Enm;
    private ImageIcon sprite;
    public JButton enmImg;
    public boolean selected;
    
    public disEnm(enm Enm) {this.Enm = Enm;enmInit();}
    
    //Makes the enm images
    private void enmInit()
    {
        spriteInit();
        enmImg = new JButton(sprite);
        enmImg.setSize(new Dimension(250,250));
        enmImg.setContentAreaFilled(false);
        enmImg.setBorderPainted(false);
        enmImg.setFocusPainted(false);
        enmImg.setOpaque(false);
        enmImg.setFocusable(false);
        enmImg.setDisabledIcon(enmImg.getIcon());
        enmImg.setEnabled(false);
    }

    //Handles selection
    public void select(boolean x) 
    {
        if(x) {sprite = new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Enemies/" + Enm.getName() + "Sel.gif").getImage()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));}

        else {spriteInit();}
        
        selected = x;enmImg.setDisabledIcon(sprite);
    }

    //Initalizes the sprite
    private void spriteInit() 
    {
        sprite = new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Enemies/" + Enm.getName() + ".gif").getImage()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    }
}