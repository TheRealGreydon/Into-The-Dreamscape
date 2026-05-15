package APCS;

import java.awt.*;
import javax.swing.*;
import APCS.Assets.*;

public class Panels
{
    private String path="APCS/Assets/Background Img/CelesteBackTEMP.jpg";
    private BackgroundPanel b = new BackgroundPanel(new ImageIcon(path).getImage(), 1);

    private GridBagConstraints gbc = new GridBagConstraints();

    //Menu panel
    public JPanel menu()
    {
        JPanel mPanel = b;
        mPanel.setLayout(new GridBagLayout());

        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;       
        title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Into the Dreamscape");
        mPanel.add(title,gbc);

        return mPanel;
    }

    //Credits panel
    public JPanel credits()
    {
        JPanel cPanel = new JPanel();
        cPanel.setLayout(new GridBagLayout());
        cPanel.setBackground(Color.black);

        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 1;       
        gbc.gridwidth = 2;   
        gbc.gridy = 2;       
        title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Into the Dreamscape");
        cPanel.add(title,gbc);

        JLabel creditsInfo = new JLabel();
        gbc.anchor = GridBagConstraints.CENTER; 
        cPanel.add(creditsInfo, gbc);
        creditsInfo.setBounds(100,20,600,80);
        creditsInfo.setOpaque(true);
        creditsInfo.setBackground(Color.white);
        creditsInfo.setFont(new Font(creditsInfo.getFont().getName(), Font.BOLD, 40));
        creditsInfo.setText("Made by Nicholas Munier and Kai Wilbur\n for the Mr.Klus AP Comp Sci final"); 

        return cPanel;
    }
}
