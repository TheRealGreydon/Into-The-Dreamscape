package APCS.GUIs;

import APCS.Player;
import APCS.Assets.AssetClasses.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.*;

//Character creation screen and starts the game
public class StartGUI extends JFrame implements ActionListener
{
    private JTextField nameBox;
    private String name = "";
    private int out = -1, fav = -1, page = 0;
    private File save = new File("APCS/Save.txt");

    private JPanel sPanel = new JPanel();
    //KRONK IS IMPORTANT DONT MESS WITH KRONK
    private JFrame kronk;
    private JButton[]buttons = new JButton[9];
    private JLabel title;
    private Player character;
    
    public StartGUI(JFrame kronk) {this.kronk = kronk; sPanel = new BackgroundPanel(new ImageIcon("APCS/Assets/Img/Background Img/RewardBack/Rew.png").getImage(), 2);}

    //Main initalize
    public void start()
    {
        sPanel.setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        kronk.pack();
        name();
        kronk.add(sPanel);
        kronk.setSize(1500, 800);
        kronk.setVisible(true);
    }

    //Name page
    private void name()
    {
        nameBox = new JTextField(9);
        nameBox.setFont(new Font("SansSerif", Font.PLAIN, 48));
        nameBox.setBounds(600, 400, 300,100);
        nameBox.setBorder(BorderFactory.createLineBorder(Color.blue, 5));

        title = new JLabel();
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What is your name?");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-100);

        buttons[0]=new JButton();
        buttons[0].setPreferredSize(new Dimension(100,100));buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));buttons[0].setForeground(Color.black);
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));buttons[0].setText("NEXT");buttons[0].addActionListener(this);
        buttons[0].setBounds(900, 400, 100, 100);
        
        sPanel.add(buttons[0]);
        sPanel.add(title);
        sPanel.add(nameBox);

        paint(5);
    }

    //Outfit selection page
    private void out()
    {
        for(int i=0;i<5;i++)
        {
            buttons[i] = new JButton();
            buttons[i].setBackground(new Color(179, 9, 9));
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            buttons[i].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
            buttons[i].setFocusable(false);
            buttons[i].setForeground(Color.black);
            buttons[i].addActionListener(this);
        }

        title.setText("Pick an outfit");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);
        
        buttons[0].setText("NEXT");
        buttons[0].setBounds(1200, 450, 100, 100);
        
        buttons[1].setText("BACK");
        buttons[1].setBounds(200, 450, 100, 100);

        buttons[2].setText("1");
        buttons[2].setBounds(430,600,200,100);

        buttons[3].setText("2");
        buttons[3].setBounds(647,600,200,100);

        buttons[4].setText("3");
        buttons[4].setBounds(856,600,200,100);

        for(int i=2; i<5; i++) {buttons[i].setBackground(new Color(179, 9, 9));buttons[i].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));}

        JLabel outs[] = new JLabel[3];
        for(int i=0;i<3;i++) {outs[i] = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/Char" + (i+1) +".png").getImage()).getImage().getScaledInstance(488, 488, Image.SCALE_DEFAULT)));}

        outs[0].setBounds(425,194,225,413);
        outs[1].setBounds(637,194,225,413);
        outs[2].setBounds(850,194,225,413);

        sPanel.add(title);
        for(int i=0; i<3; i++) {sPanel.add(outs[i]);}
        for(int i=0; i<5; i++) {sPanel.add(buttons[i]);}

        paint(5);
    }

    //Favorite color page
    private void fav()
    {
        for(int i=5;i<9; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 20));
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            sPanel.add(buttons[i]);
        }

        buttons[6].setBackground(new Color(43,18,204));buttons[5].setBackground(new Color(179, 9, 9));buttons[7].setBackground(new Color(5,97,51));buttons[8].setBackground(Color.WHITE);
        buttons[6].setBounds(750,150,200,200);buttons[5].setBounds(550,150,200,200);buttons[7].setBounds(550,350,200,200);buttons[8].setBounds(750,350,200,200);

        title.setText("What's your favorite color?");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);
        
        sPanel.add(title);
        sPanel.add(buttons[0]);
        sPanel.add(buttons[1]);
        
        paint(5);
    }

    private void fin()
    {
        title.setText("Finalize your character");
        title.setSize(title.getPreferredSize());
        title.setSize(title.getWidth()+10, title.getHeight());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);

        String o, f;
        o = switch (out) {case 1 -> "1";case 2 -> "2";default -> "3";};

        f = switch (fav) {case 1 -> "Red";case 2 -> "Blue";case 3 -> "Green";default -> "White";};

        JTextPane charlist = new JTextPane();
        charlist.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        charlist.setText("Name: " + name + "\nOutfit number: " + o + "\nFavorite color: " + f);
        charlist.setBackground(Color.white);
        charlist.setFont(new Font(charlist.getFont().getName(), Font.BOLD, 45));
        charlist.setForeground(Color.BLACK);      
        charlist.setSize(charlist.getPreferredSize());
        charlist.setSize(charlist.getWidth()+10, charlist.getHeight());
        charlist.setLocation(750-charlist.getWidth()/2,300-charlist.getHeight()/2);
        charlist.setEditable(false);

        JLabel charImg = new JLabel(new ImageIcon(new ImageIcon(new ImageIcon("APCS/Assets/Img/Character Img/Char" + (out) +".png").getImage()).getImage().getScaledInstance(488, 488, Image.SCALE_DEFAULT)));
        charImg.setBounds(600,400,225,413);

        buttons[0].setText("START");buttons[1].setText("BACK");
        buttons[0].setBounds(1200, 300, 150, 150);buttons[1].setBounds(200, 300, 150, 150);

        sPanel.add(title);
        sPanel.add(charlist);
        sPanel.add(buttons[0]);
        sPanel.add(buttons[1]);
        sPanel.add(charImg);

        paint(5);
    }

    //Handles when the next button is called
    private void next()
    {
        switch (page) 
        {
            case 0 -> {name = nameBox.getText();if(name.length()>0) {sPanel.removeAll();out();sPanel.repaint();page++;}}
            case 1 -> {if(out!=-1) {sPanel.removeAll();fav();sPanel.repaint();page++;}}

            case 2 -> {if(fav!=-1) {sPanel.removeAll();fin();sPanel.repaint();page++;}}

            case 3 -> 
            {
                String temp = "";
                try (Scanner sc = new Scanner(save)) {sc.nextLine();temp = sc.nextLine();}
                catch (FileNotFoundException e){}

                character = new Player(name, out, fav);
                character.loadAch(temp);
                character.saveGame();
                kronk.remove(sPanel);
                new IntroGUI(character,kronk);
            }
        }
    }

    //Handles when the back button is called
    private void back()
    {
        sPanel.removeAll();
        switch (page) {case 1 -> {name = "";name();} case 2 -> {out = -1;out();} case 3 -> {fav = -1;fav();}}
        page--;sPanel.repaint();
    }
    
    //Paints a JPanel with a percentage of stars randomly
    private void paint(int percent)
    {
        for (Component c : sPanel.getComponents()) {sPanel.setComponentZOrder(c, 0);}
        for(int i=0; i<40; i++)
        {
            for(int j=0; j<75; j++)
            {
                if(j<18 || j>56)
                {
                    JLabel star = new JLabel();
                    star.setOpaque(true);
                    star.setSize(new Dimension(20, 20));
                    star.setLocation(j*20, i*20);
                    star.setBackground(Color.BLACK);
                    sPanel.add(star);
                    
                    if((int)(Math.random()*100) <= percent-1) 
                    {
                        if((int)(Math.random()*2)==0) {star.setBackground(Color.YELLOW);}
                    
                        else {star.setBackground(new Color(189,185,38));}
                    }
                }
            }
        }
    }

    @Override
    //Handles the buttons    
    public void actionPerformed(ActionEvent e)
    {
        //The source of the button click
        JButton j = (JButton)(e.getSource());

        if(j.equals(buttons[0])) {next();}

        else if(j.equals(buttons[1])) {back();}

        else if(j.equals(buttons[2]))
        {
            out = 1;
            buttons[2].setBackground(new Color(43,18,204));
            buttons[2].setBorder(BorderFactory.createLineBorder(new Color(179, 9, 9), 5));
            buttons[3].setBackground(new Color(179, 9, 9));
            buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            buttons[4].setBackground(new Color(179, 9, 9));
            buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        }
        else if(j.equals(buttons[3]))
        {
            out = 2;
            buttons[2].setBackground(new Color(179, 9, 9));
            buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
            buttons[3].setBackground(new Color(43,18,204));
            buttons[3].setBorder(BorderFactory.createLineBorder(new Color(179, 9, 9), 5));
            buttons[4].setBackground(new Color(179, 9, 9));
            buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        }
        else if(j.equals(buttons[4]))
        {
            out = 3;
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
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.gray,20));
        }
        else if(j.equals(buttons[6]))
        {
            fav=2;
            buttons[5].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.white,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.gray,20));
        }
        else if(j.equals(buttons[7]))
        {
            fav=3;
            buttons[5].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.white,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.gray,20));
        }
        else if(j.equals(buttons[8]))
        {
            fav=4;
            buttons[5].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[6].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[7].setBorder(BorderFactory.createLineBorder(Color.gray,20));
            buttons[8].setBorder(BorderFactory.createLineBorder(Color.white,20));
        }
    }
}
