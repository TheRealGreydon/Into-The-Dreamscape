package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

import APCS.Player;
import APCS.Assets.AssetClasses.*;
import javax.swing.*;

//Character creation screen and starts the game
public class StartGUI extends JFrame implements ActionListener
{
    JTextField nameBox;
    
    private String name = " ";private int gend = 1;/*-1;*/private int out = 1;/*-1;*/private int fav = 1;//-1;    
    private int page = 0;
    //private int page = 4;

    private LevelSelGUI lGUI;

    private JPanel sPanel;

    private BackgroundPanel b = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Default Img/defaultBack.jpg").getImage(), 1);

    private JButton[]buttons = new JButton[100];
    
    public StartGUI() {sPanel = new JPanel();sPanel = b;sPanel.setLayout(null);}
    
    public JPanel start() {name();return sPanel;}

    //Name page
    private void name()
    {
        nameBox = new JTextField(9);
        nameBox.setFont(new Font("SansSerif", Font.PLAIN, 48));
        nameBox.setBounds(600, 400, 300,100);

        JLabel title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What is your name?");
        title.setSize(title.getPreferredSize());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-100);

        buttons[0]=new JButton();
        buttons[0].setPreferredSize(new Dimension(100,100));buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));buttons[0].setForeground(Color.black);
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));buttons[0].setText("NEXT");buttons[0].addActionListener(this);
        buttons[0].setBounds(900, 400, 100, 100);
        
        sPanel.add(buttons[0]);
        sPanel.add(title);
        sPanel.add(nameBox);

        paint(sPanel);
    }

    //Gender selection page
    private void gend()
    {
        JLabel title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What gender are you?");
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setSize(title.getPreferredSize());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);
        

        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");
        buttons[0].setBounds(1200, 450, 100, 100);

        buttons[1]=new JButton();
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].setForeground(Color.black);
        buttons[1].addActionListener(this);
        buttons[1].setBounds(200, 450, 100, 100);

        buttons[2]=new JButton();
        buttons[2].setBackground(new Color(179, 9, 9));
        buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setForeground(Color.black);
        buttons[2].setText("Male");
        buttons[2].addActionListener(this);
        buttons[2].setBounds(430,600,200,100);

        buttons[3]=new JButton();
        buttons[3].setBackground(new Color(179, 9, 9));
        buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setForeground(Color.black);
        buttons[3].setText("Female");
        buttons[3].addActionListener(this);
        buttons[3].setBounds(647,600,200,100);

        buttons[4]=new JButton();
        buttons[4].setBackground(new Color(179, 9, 9));
        buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 15));
        buttons[4].setForeground(Color.black);buttons[4].setText("Non-Binary");
        buttons[4].addActionListener(this);
        buttons[4].setBounds(856,600,200,100);

        JLabel out1lab = null;
        try {out1lab = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Character Img/Char1.png")).getScaledInstance(488, 488, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}out1lab.setOpaque(false);out1lab.setBounds(425,194,225,413);
        
        JLabel out2lab = null;
        try {out2lab = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Character Img/Char2.png")).getScaledInstance(488, 488, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}out2lab.setOpaque(false);out2lab.setBounds(637,194,225,413);
        
        JLabel out3lab = null;
        try {out3lab = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Character Img/Char3.png")).getScaledInstance(488, 488, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}out3lab.setOpaque(false);out3lab.setBounds(850,194,225,413);

        sPanel.add(title);
        sPanel.add(buttons[0]);
        sPanel.add(buttons[1]);
        sPanel.add(buttons[2]);
        sPanel.add(buttons[3]);
        sPanel.add(buttons[4]);
        sPanel.add(out1lab);
        sPanel.add(out2lab);
        sPanel.add(out3lab);

        paint(sPanel);
    }

    //Outfit selection page
    private void out()
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
        title.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What outfit would you like?");
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

        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");

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

        gbc.gridx=2;
        sPanel.add(buttons[3],gbc);
        buttons[3].setPreferredSize(new Dimension(100,100));
        buttons[3].setBackground(new Color(179, 9, 9));
        buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("2");
        buttons[3].addActionListener(this);

        gbc.gridx=3;sPanel.add(buttons[4],gbc);buttons[4].setPreferredSize(new Dimension(200,100));buttons[4].setBackground(new Color(179, 9, 9));
        buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 15));buttons[4].setText("3");
        buttons[4].addActionListener(this);BufferedImage out1=null;BufferedImage out2=null;BufferedImage out3=null;Image out1S;Image out2S;Image out3S;

        try {out1 = ImageIO.read(new File("APCS/Assets/Character Img/Char1.jpg"));} catch (IOException e) {e.printStackTrace();}
        out1S = out1.getScaledInstance(650, 650, Image.SCALE_SMOOTH);JLabel out1lab = new JLabel(new ImageIcon(out1S));out1lab.setPreferredSize(new Dimension(300,450));
        gbc.fill = GridBagConstraints.NONE;gbc.ipady = 0;gbc.weighty = .5;gbc.anchor = GridBagConstraints.CENTER;gbc.gridx = 1;gbc.gridwidth = 1;gbc.gridheight=1;gbc.gridy = 0;sPanel.add(out1lab, gbc);

        try {out2 = ImageIO.read(new File("APCS/Assets/Character Img/Char2.jpg"));} catch (IOException e) {e.printStackTrace();}
        out2S = out2.getScaledInstance(650, 650, Image.SCALE_SMOOTH);JLabel out2lab = new JLabel(new ImageIcon(out2S));out2lab.setPreferredSize(new Dimension(300,450));gbc.gridx = 2;sPanel.add(out2lab, gbc);

        try {out3 = ImageIO.read(new File("APCS/Assets/Character Img/Char3.jpg"));} catch (IOException e) {e.printStackTrace();}out3S = out3.getScaledInstance(650, 650, Image.SCALE_SMOOTH);
        JLabel out3lab = new JLabel(new ImageIcon(out3S));out3lab.setPreferredSize(new Dimension(300,450));gbc.gridx = 3;sPanel.add(out3lab, gbc);
    }

    //Favorite color page
    private void fav()
    {
        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.gridx = 3;       
        gbc.gridwidth = 1;   
        gbc.gridy = -1;       
        title.setBorder(BorderFactory.createLineBorder(Color.black, 5));
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
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");

        gbc.gridx = -10;
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");

        buttons[5] = new JButton();
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
        gbc.gridx=3;
        sPanel.add(buttons[6],gbc);
        buttons[6].setPreferredSize(new Dimension(200,200));
        buttons[6].setBackground(new Color(43,18,204));
        buttons[6].setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
        buttons[6].addActionListener(this);

        buttons[7]=new JButton();
        gbc.gridx=2;
        gbc.gridy = 1;       
        sPanel.add(buttons[7],gbc);
        buttons[7].setPreferredSize(new Dimension(200,200));
        buttons[7].setBackground(new Color(5,97,51));
        buttons[7].setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
        buttons[7].addActionListener(this);

        buttons[8]=new JButton();
        gbc.gridx=3;
        sPanel.add(buttons[8],gbc);
        buttons[8].setPreferredSize(new Dimension(200,200));
        buttons[8].setBackground(Color.WHITE);
        buttons[8].setBorder(BorderFactory.createLineBorder(Color.BLACK, 20));
        buttons[8].addActionListener(this);
    }

    private void fin()
    {
        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.NONE;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        gbc.gridx = 0;       
        gbc.gridwidth = 0;   
        gbc.gridy = 0;       
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        title.setText("Finalize your character?");
        sPanel.add(title,gbc);

        String g; String o; String f;
        if(gend==1){g="Male";}else if(gend==2){g="Female";}else{g="Non-Binary";}
        if(out==1){o="1";}else if(out==2){o="2";}else{o="3";}
        if(fav==1){f="red";}else if(fav==2){f="blue";}else if(fav==3){f="green";}else{f="white";}
        JTextPane charlist = new JTextPane();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER; 
        gbc.gridx = 1;       
        gbc.gridwidth = 1;   
        charlist.setBackground(Color.white);
        charlist.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        charlist.setForeground(Color.BLACK);
        charlist.setText("Name: " + name + "\nGender: " + g + "\nOutfit number: " + o + "\nFavorite color: " + f);
        charlist.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        sPanel.add(charlist);

        if(gend==1){g="M";}else if(gend==2){g="F";}else{g="N";} if(out==1){o="1";}else if (out==2){o="2";}else{o="3";}

        BufferedImage cha = null;
        try {cha = ImageIO.read(new File("APCS/Assets/Character Img/Char" + o + ".jpg"));} catch (IOException e) {e.printStackTrace();}
        JLabel chaimg = new JLabel(new ImageIcon(cha));
        chaimg.setPreferredSize(new Dimension(400,600));
        gbc.gridx = 0;       
        sPanel.add(chaimg);

        //For some reason, if I remove this one button, everything else disapears
        //Hell if I know why, I give up
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 10;
        gbc.gridy = 2;     
        gbc.anchor = GridBagConstraints.LINE_END; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(200,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("START");

        gbc.gridx = 0;
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(200,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
    }

    //Handles when the next button is called
    private void next()
    {
        if(page==0) {name=nameBox.getText();if(name.length()>-1){sPanel.removeAll();gend();sPanel.repaint();page++;}}

        else if(page==1) {if(gend!=-1){sPanel.removeAll();out();sPanel.repaint();page++;}}

        else if(page==2) {if(out!=-1){sPanel.removeAll();fav();sPanel.repaint();page++;}}

        else if(page==3) {if(fav!=-1){sPanel.removeAll();fin();sPanel.repaint();page++;}}

        else if(page==4) {close();lGUI = new LevelSelGUI(new Player(name, gend, out, fav)); lGUI.displayGame();}
    }

    //Handles when the back button is called
    private void back()
    {
        if(page==1) {sPanel.removeAll();name();sPanel.repaint();page--;}

        else if(page==2) {sPanel.removeAll();gend();sPanel.repaint();page--;}

        else if(page==3) {sPanel.removeAll();out();sPanel.repaint();page--;}

        else if(page==4) {sPanel.removeAll();fav();sPanel.repaint();page--;}
    }
    
    private void paint(JPanel x)
    {
        for (Component c : x.getComponents()) {x.setComponentZOrder(c, 0);}
        for(int i=0; i<40; i++)
        {
            for(int j=0; j<75; j++)
            {
                if((int)(Math.random()*18) == 0)
                {
                    if((int)(Math.random()*2)==0)
                    {
                        JLabel star = new JLabel();
                        star.setOpaque(true);
                        star.setBackground(Color.YELLOW);
                        star.setSize(new Dimension(20, 20));
                        star.setLocation(j*20, i*20);
                        x.setComponentZOrder(star, 1);
                        x.add(star);
                    }
                    else
                    {
                        JLabel star = new JLabel();
                        star.setOpaque(true);
                        star.setBackground(new Color(189,185,38));
                        star.setSize(new Dimension(20, 20));
                        star.setLocation(j*20, i*20);
                        x.setComponentZOrder(star, 1);
                        x.add(star);
                    }                    
                }
                else
                {
                    JLabel star = new JLabel();
                    star.setOpaque(true);
                    star.setBackground(Color.BLACK);
                    star.setSize(new Dimension(20, 20));
                    star.setLocation(j*20, i*20);
                    x.setComponentZOrder(star, 1);
                    x.add(star);
                }
            }
        }
    }

    //Handles the buttons
    public void actionPerformed(ActionEvent e)
    {
        //The source of the button click
        JButton j = (JButton)(e.getSource());

        if(j.equals(buttons[0])) {next();}

        else if(j.equals(buttons[1])) {back();}

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

    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
}
