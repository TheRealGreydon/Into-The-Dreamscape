package APCS.Enms;

import java.awt.*;
import javax.swing.*;

public class disEnm 
{
    public enm Enm;
    private ImageIcon sprite;
    public JLabel enmImg;
    public boolean selected;
    
    public disEnm(enm Enm) {this.Enm = Enm;enmInit();}
    
    //Makes the enm images
    private void enmInit()
    {
        spriteInit();
        enmImg = new JLabel(sprite);
        enmImg.setSize(new Dimension(250,250));
        enmImg.setOpaque(false);
        enmImg.setFocusable(false);
    }

    //Handles selection
    public void select(boolean x) 
    {
        if(x) {sprite = new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Enemies/" + Enm.getName() + "Sel.gif").getImage()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));}

        else {spriteInit();}
        
        selected = x;enmImg.setIcon(sprite);
    }

    //Initalizes the sprite
    private void spriteInit() 
    {
        sprite = new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Enemies/" + Enm.getName() + ".gif").getImage()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    }
}