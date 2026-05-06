package APCS;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


public class StartGUI extends JFrame implements ActionListener
{
    /** The Drop Game engine. */
    private Main g;
    JTextField nameBox;
    
    private String name="";
    private int gend=-1;
    private int out=-1;
    private int fav=-1;
    
    private int page=0;
    /** The main panel containing the game components. */
    private JPanel sPanel;

    private BackgroundPanel b;

    private GridBagConstraints gbc = new GridBagConstraints();

    private JButton[]buttons = new JButton[100];
    
    public StartGUI(BackgroundPanel b)
    {
        this.b=b;
        sPanel = new JPanel();
        sPanel = b;
        sPanel.setLayout(new GridBagLayout());
    }
    
    public JPanel start()
    {
        name();
        return sPanel;
    }

    private void name()
    {
        nameBox = new JTextField(9);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.gridx = 3;       
        gbc.gridwidth = 1;   
        gbc.gridy = 1;    
        nameBox.setFont(new Font("SansSerif", Font.PLAIN, 48));
        sPanel.add(nameBox,gbc);

        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.gridx = 3;       
        gbc.gridwidth = 1;   
        gbc.gridy = 0;   
        //title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What is your name?");
        sPanel.add(title,gbc);

        buttons[0]=new JButton();
        gbc.gridx=12;
        gbc.gridy = 1;       
        gbc.gridwidth =1;
        gbc.anchor = GridBagConstraints.LINE_END; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setForeground(Color.black);
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");
        buttons[0].addActionListener(this);
    }

    public void gend()
    {
        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.gridx = 2;       
        gbc.gridwidth = 1;   
        gbc.gridy = -1;       
        title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What is your gender?");
        sPanel.add(title,gbc);

        gbc.gridx=10;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_END; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");

        buttons[1]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].setForeground(Color.black);
        buttons[1].addActionListener(this);

        buttons[2]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=1;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[2],gbc);
        buttons[2].setPreferredSize(new Dimension(200,100));
        buttons[2].setBackground(new Color(179, 9, 9));
        buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setForeground(Color.black);
        buttons[2].setText("Male");
        buttons[2].addActionListener(this);

        buttons[3]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[3],gbc);
        buttons[3].setPreferredSize(new Dimension(100,100));
        buttons[3].setBackground(new Color(179, 9, 9));
        buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setForeground(Color.black);
        buttons[3].setText("Female");
        buttons[3].addActionListener(this);

        buttons[4]=new JButton();
        gbc.gridx=3;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[4],gbc);
        buttons[4].setPreferredSize(new Dimension(200,100));
        buttons[4].setBackground(new Color(179, 9, 9));
        buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 15));
        buttons[4].setForeground(Color.black);
        buttons[4].setText("Non-Binary");
        buttons[4].addActionListener(this);

        BufferedImage out1=null;
        BufferedImage out2=null;
        BufferedImage out3=null;

        try {out1 = ImageIO.read(new File("APCS/Assets/Default Img/DefaultOutImg.jpg"));} catch (IOException e) {e.printStackTrace();}
        JLabel out1lab = new JLabel(new ImageIcon(out1));
        out1lab.setPreferredSize(new Dimension(300,300));
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 0;       
        gbc.weighty = .5;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.gridx = 1;       
        gbc.gridwidth = 1;   
        gbc.gridheight=1;
        gbc.gridy = 0;     
        sPanel.add(out1lab, gbc);

        try {out2 = ImageIO.read(new File("APCS/Assets/Default Img/DefaultOutImg.jpg"));} catch (IOException e) {e.printStackTrace();}
        JLabel out2lab = new JLabel(new ImageIcon(out2));
        out2lab.setPreferredSize(new Dimension(300,300));
        gbc.gridx = 2;       
        sPanel.add(out2lab, gbc);

        try {out3 = ImageIO.read(new File("APCS/Assets/Default Img/DefaultOutImg.jpg"));} catch (IOException e) {e.printStackTrace();}
        JLabel out3lab = new JLabel(new ImageIcon(out3));
        out3lab.setPreferredSize(new Dimension(300,300));
        gbc.gridx = 3;       
        sPanel.add(out3lab, gbc);
    }

    public void out()
    {
        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.gridx = 2;       
        gbc.gridwidth = 1;   
        gbc.gridy = -1;       
        title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Pick an outfit.");
        sPanel.add(title,gbc);

        gbc.gridx=10;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_END; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=1;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[2],gbc);
        buttons[2].setPreferredSize(new Dimension(200,100));
        buttons[2].setBackground(new Color(179, 9, 9));
        buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setText("1");
        buttons[2].addActionListener(this);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[3],gbc);
        buttons[3].setPreferredSize(new Dimension(100,100));
        buttons[3].setBackground(new Color(179, 9, 9));
        buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("2");
        buttons[3].addActionListener(this);

        gbc.gridx=3;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[4],gbc);
        buttons[4].setPreferredSize(new Dimension(200,100));
        buttons[4].setBackground(new Color(179, 9, 9));
        buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 15));
        buttons[4].setText("3");
        buttons[4].addActionListener(this);

        BufferedImage out1=null;
        BufferedImage out2=null;
        BufferedImage out3=null;

        try {out1 = ImageIO.read(new File("APCS/Assets/Default Img/DefaultOutImg.jpg"));} catch (IOException e) {e.printStackTrace();}
        JLabel out1lab = new JLabel(new ImageIcon(out1));
        out1lab.setPreferredSize(new Dimension(300,300));
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 0;       
        gbc.weighty = .5;   
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.gridx = 1;       
        gbc.gridwidth = 1;   
        gbc.gridheight=1;
        gbc.gridy = 0;     
        sPanel.add(out1lab, gbc);

        try {out2 = ImageIO.read(new File("APCS/Assets/Default Img/DefaultOutImg.jpg"));} catch (IOException e) {e.printStackTrace();}
        JLabel out2lab = new JLabel(new ImageIcon(out2));
        out2lab.setPreferredSize(new Dimension(300,300));
        gbc.gridx = 2;       
        sPanel.add(out2lab, gbc);

        try {out3 = ImageIO.read(new File("APCS/Assets/Default Img/DefaultOutImg.jpg"));} catch (IOException e) {e.printStackTrace();}
        JLabel out3lab = new JLabel(new ImageIcon(out3));
        out3lab.setPreferredSize(new Dimension(300,300));
        gbc.gridx = 3;       
        sPanel.add(out3lab, gbc);
    }

    public void fav()
    {
        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.gridx = 3;       
        gbc.gridwidth = 1;   
        gbc.gridy = -1;       
        //title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Whats your favorite color?");
        sPanel.add(title,gbc);

        gbc.gridx = 20;
        gbc.gridy = 1;       
        gbc.gridwidth = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("FINISH");

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = -10;
        gbc.gridy = 1;     
        gbc.gridwidth = 0;  
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");

        buttons[5] = new JButton();
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.gridx= 2;
        gbc.gridy = 0;       
        gbc.gridwidth = 1;
        gbc.ipadx = 0;
        gbc.ipady = 0;
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[5],gbc);
        buttons[5].setPreferredSize(new Dimension(200,200));
        buttons[5].setBackground(new Color(179, 9, 9));
        buttons[5].setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
        buttons[5].addActionListener(this);

        buttons[6]=new JButton();
        //gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=3;
        gbc.gridy = 0;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[6],gbc);
        buttons[6].setPreferredSize(new Dimension(200,200));
        buttons[6].setBackground(new Color(43,18,204));
        buttons[6].setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
        buttons[6].addActionListener(this);

        buttons[7]=new JButton();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx=2;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[7],gbc);
        buttons[7].setPreferredSize(new Dimension(200,200));
        buttons[7].setBackground(new Color(5,97,51));
        buttons[7].setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
        buttons[7].addActionListener(this);

        buttons[8]=new JButton();
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx=3;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[8],gbc);
        buttons[8].setPreferredSize(new Dimension(200,200));
        buttons[8].setBackground(Color.WHITE);
        buttons[8].setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
        buttons[8].addActionListener(this);
    }

    public void fin()
    {

    }

    public void next()
    {
        if(page==0)
        {
            name=nameBox.getText();
            if(name.length()>0)
            {
                sPanel.removeAll();
                gend();
                sPanel.repaint();
                page++;
            }
        }
        else if(page==1)
        {
            if(gend!=-1)
            {
                sPanel.removeAll();
                out();
                sPanel.repaint();
                page++;
            }
        }
        else if(page==2)
        {
            if(out!=-1)
            {
                sPanel.removeAll();
                fav();
                sPanel.repaint();
                page++;
            }
            
        }
        else if(page==3)
        {
            if(fav!=-1)
            {
                sPanel.removeAll();
                fin();
                sPanel.repaint();
                page++;
            }
            
        }
    }

    public void back()
    {
        if(page==1)
        {
            sPanel.removeAll();
            name();
            sPanel.repaint();
            page--;
        }
        else if(page==2)
        {
            sPanel.removeAll();
            gend();
            sPanel.repaint();
            page--;
        }
        else if(page==3)
        {
            sPanel.removeAll();
            out();
            sPanel.repaint();
            page--;
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        //the source of the button click
        JButton j = (JButton)(e.getSource());

        if(j.equals(buttons[0]))
        {
            next();
        }
        else if(j.equals(buttons[1]))
        {
            back();
        }
        else if(j.equals(buttons[2]))
        {
            if(page==1){gend=1;}else{out=1;}
            buttons[2].setBackground(new Color(43,18,204));
            buttons[2].setBorder(BorderFactory.createLineBorder(new Color(179, 9, 9), 5));
            buttons[3].setBackground(new Color(179, 9, 9));
            buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            buttons[4].setBackground(new Color(179, 9, 9));
            buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        }
        else if(j.equals(buttons[3]))
        {
            if(page==1){gend=2;}else{out=2;}
            buttons[2].setBackground(new Color(179, 9, 9));
            buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            buttons[3].setBackground(new Color(43,18,204));
            buttons[3].setBorder(BorderFactory.createLineBorder(new Color(179, 9, 9), 5));
            buttons[4].setBackground(new Color(179, 9, 9));
            buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        }
        else if(j.equals(buttons[4]))
        {
            if(page==1){gend=3;}else{out=3;}
            buttons[2].setBackground(new Color(179, 9, 9));
            buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            buttons[3].setBackground(new Color(179, 9, 9));
            buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            buttons[4].setBackground(new Color(43,18,204));
            buttons[4].setBorder(BorderFactory.createLineBorder(new Color(179, 9, 9), 5));
        }
        else if(j.equals(buttons[5]))
        {
            fav=1;
            buttons[5].setBorder(BorderFactory.createLineBorder(Color.WHITE,20));
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.BLACK,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.black,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.black,20));
        }
        else if(j.equals(buttons[6]))
        {
            fav=2;
            buttons[5].setBorder(BorderFactory.createLineBorder(Color.black,20));
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.white,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.black,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.black,20));
        }
        else if(j.equals(buttons[7]))
        {
            fav=3;
            buttons[5].setBorder(BorderFactory.createLineBorder(Color.black,20));
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.BLACK,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.white,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.black,20));
        }
        else if(j.equals(buttons[8]))
        {
            fav=4;
            buttons[5].setBorder(BorderFactory.createLineBorder(Color.black,20));
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.BLACK,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.black,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.white,20));
        }
    }
}
