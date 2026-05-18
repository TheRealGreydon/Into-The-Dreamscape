package APCS.Enms;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

public class disEnm 
{
    public enm Enm;
    private Image sprite;
    public JButton enmButton;
    public boolean selected;
    
    public disEnm(enm Enm) {this.Enm = Enm;spriteInit();buttonInit();}
    
    private void buttonInit()
    {
        enmButton = new JButton(new ImageIcon(sprite));
        enmButton.setText("APCS/Assets/Img/Enemies/HemoNeedle-1");
        enmButton.setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        enmButton.setSize(new Dimension(250,250));
        enmButton.setContentAreaFilled(false);
        enmButton.setBorderPainted(false);
        enmButton.setFocusPainted(false);
        enmButton.setOpaque(false);
        enmButton.setFocusable(false);
    }

    public JButton getButton() {return enmButton;}

    public Image getSprite() {return sprite;}

    public void select(boolean x) 
    {
        if(x)
        {
            try {sprite = ImageIO.read(new File("APCS/Assets/Img/Enemies/" + Enm.getName() + "Sel.png")).getScaledInstance(250, 250, Image.SCALE_SMOOTH);}
            catch (IOException e) {e.printStackTrace();}
        }
        else
        {
            try {sprite = ImageIO.read(new File("APCS/Assets/Img/Enemies/" + Enm.getName() + ".png")).getScaledInstance(250, 250, Image.SCALE_SMOOTH);}
            catch (IOException e) {e.printStackTrace();}
        }
        selected = x;
        enmButton.setIcon(new ImageIcon(sprite));
    }

    public void dead() 
    {
        try {sprite = ImageIO.read(new File("APCS/Assets/Img/Enemies/" + Enm.getName() + ".png")).getScaledInstance(250, 250, Image.SCALE_SMOOTH);}
        catch (IOException e) {e.printStackTrace();}

        enmButton.setIcon(new ImageIcon(sprite));
    }

    private void spriteInit() 
    {
        try {sprite = ImageIO.read(new File("APCS/Assets/Img/Enemies/" + Enm.getName() + ".png")).getScaledInstance(250, 250, Image.SCALE_SMOOTH);}
        catch (IOException e) {e.printStackTrace();}
    }
}