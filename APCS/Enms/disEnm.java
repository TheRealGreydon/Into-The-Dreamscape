package APCS.Enms;

import java.awt.*;
import javax.swing.*;

public class disEnm 
{
    public enm Enm;
    private ImageIcon sprite;
    public JButton enmButton;
    public boolean selected;
    
    public disEnm(enm Enm) {this.Enm = Enm;buttonInit();}
    
    private void buttonInit()
    {
        spriteInit();
        enmButton = new JButton(sprite);
        //enmButton.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        enmButton.setSize(new Dimension(250,250));
        enmButton.setContentAreaFilled(false);
        enmButton.setBorderPainted(false);
        enmButton.setFocusPainted(false);
        enmButton.setOpaque(false);
        enmButton.setFocusable(false);
        enmButton.setDisabledIcon(enmButton.getIcon());
        enmButton.setEnabled(false);
    }

    public JButton getButton() {return enmButton;}

    public Image getSprite() {return sprite.getImage();}

    public void select(boolean x) 
    {
        if(x)
        {
            sprite = new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Enemies/" + Enm.getName() + "Sel.gif").getImage()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
        }

        else {spriteInit();}
        
        selected = x;enmButton.setDisabledIcon(sprite);
    }

    private void spriteInit() 
    {
        sprite = new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Enemies/" + Enm.getName() + ".gif").getImage()).getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
    }
}