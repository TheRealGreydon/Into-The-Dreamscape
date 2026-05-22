package APCS.GUIs;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;

import APCS.Player;
import javax.swing.*;

//Character creation screen and starts the game
public class StartGUI extends JFrame implements ActionListener
{
    JTextField nameBox;
    
    private String name = "";private int gend = -1;private int out = 1;private int fav = -1;   
    private int page = 0;

    private LevelSelGUI lGUI;

    private JPanel sPanel = new JPanel();
    private JButton[]buttons = new JButton[9];
    private JLabel title;
    private Player character;
    
    public StartGUI() {;sPanel.setLayout(null);}
    
    public void start() {displayGame();initialize();}

    private void displayGame() {java.awt.EventQueue.invokeLater(new Runnable() {public void run() {setVisible(true);}});}

    //Main initalize
    private void initialize()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        name();
        this.add(sPanel);
        this.setSize(1500, 800);
        this.setVisible(true);
        //int gend = 1;int out = 1;int fav = 1;page = 4;next();
    }

    //Name page
    private void name()
    {
        nameBox = new JTextField(9);
        nameBox.setFont(new Font("SansSerif", Font.PLAIN, 48));
        nameBox.setBounds(600, 400, 300,100);

        title = new JLabel();
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

        paint(5);
    }

    //Gender selection page
    private void gend()
    {
        title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What gender are you?");
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setSize(title.getPreferredSize());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);
        

        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");
        buttons[0].setBounds(1200, 450, 100, 100);
        buttons[0].setFocusable(false);

        buttons[1]=new JButton();
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].setForeground(Color.black);
        buttons[1].addActionListener(this);
        buttons[1].setFocusable(false);
        buttons[1].setBounds(200, 450, 100, 100);

        buttons[2]=new JButton();
        buttons[2].setBackground(new Color(179, 9, 9));
        buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setForeground(Color.black);
        buttons[2].setText("Male");
        buttons[2].addActionListener(this);
        buttons[2].setBounds(430,600,200,100);
        buttons[2].setFocusable(false);

        buttons[3]=new JButton();
        buttons[3].setBackground(new Color(179, 9, 9));
        buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setForeground(Color.black);
        buttons[3].setText("Female");
        buttons[3].addActionListener(this);
        buttons[3].setBounds(647,600,200,100);
        buttons[3].setFocusable(false);

        buttons[4]=new JButton();
        buttons[4].setBackground(new Color(179, 9, 9));
        buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 15));
        buttons[4].setForeground(Color.black);buttons[4].setText("Non-Binary");
        buttons[4].addActionListener(this);
        buttons[4].setBounds(856,600,200,100);
        buttons[4].setFocusable(false);

        JLabel out1lab = null;
        try {out1lab = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Default Img/male.png")).getScaledInstance(488, 488, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}out1lab.setOpaque(false);out1lab.setBounds(425,194,225,413);
        
        JLabel out2lab = null;
        try {out2lab = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Default Img/fem.png")).getScaledInstance(488, 488, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}out2lab.setOpaque(false);out2lab.setBounds(637,194,225,413);
        
        JLabel out3lab = null;
        try {out3lab = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Default Img/nb.png")).getScaledInstance(488, 488, Image.SCALE_SMOOTH)));} 
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

        paint(5);
    }

    //Outfit selection page
    private void out()
    {
        title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Pick an outfit");
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setSize(title.getPreferredSize());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);
        

        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");
        buttons[0].setBounds(1200, 450, 100, 100);
        buttons[0].setFocusable(false);

        buttons[1]=new JButton();
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].setForeground(Color.black);
        buttons[1].addActionListener(this);
        buttons[1].setBounds(200, 450, 100, 100);
        buttons[1].setFocusable(false);

        buttons[2]=new JButton();
        buttons[2].setBackground(new Color(179, 9, 9));
        buttons[2].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setForeground(Color.black);
        buttons[2].setText("1");
        buttons[2].addActionListener(this);
        buttons[2].setBounds(430,600,200,100);
        buttons[2].setFocusable(false);

        buttons[3]=new JButton();
        buttons[3].setBackground(new Color(179, 9, 9));
        buttons[3].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setForeground(Color.black);
        buttons[3].setText("2");
        buttons[3].addActionListener(this);
        buttons[3].setBounds(647,600,200,100);
        buttons[3].setFocusable(false);

        buttons[4]=new JButton();
        buttons[4].setBackground(new Color(179, 9, 9));
        buttons[4].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 15));
        buttons[4].setForeground(Color.black);buttons[4].setText("3");
        buttons[4].addActionListener(this);
        buttons[4].setBounds(856,600,200,100);
        buttons[4].setFocusable(false);

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

        paint(5);
    }

    //Favorite color page
    private void fav()
    {
        title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Whats your favorite color?");
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setSize(title.getPreferredSize());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);

        
        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].setFocusable(false);
        
        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");
        buttons[0].setFocusable(false);

        for(int i=5;i<9; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 20));
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            sPanel.add(buttons[i]);
        }

        buttons[5].setBackground(new Color(179, 9, 9));
        buttons[5].setBounds(550,150,200,200);

        buttons[6].setBackground(new Color(43,18,204));
        buttons[6].setBounds(750,150,200,200);
    
        buttons[7].setBackground(new Color(5,97,51));
        buttons[7].setBounds(550,350,200,200);
        
        buttons[8].setBackground(Color.WHITE);
        buttons[8].setBounds(750,350,200,200);

        sPanel.add(title);
        sPanel.add(buttons[0]);
        sPanel.add(buttons[1]);

        paint(5);
    }

    private void fin()
    {
        title = new JLabel();
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Finalize your character");
        title.setBorder(BorderFactory.createLineBorder(Color.blue, 5));
        title.setSize(title.getPreferredSize());
        title.setLocation(750 - title.getWidth()/2, 400 - title.getHeight()/2-300);

        String g; String o; String f;
        if(gend==1){g="Male";}else if(gend==2){g="Female";}else{g="Non-Binary";}
        if(out==1){o="1";}else if(out==2){o="2";}else{o="3";}
        if(fav==1){f="red";}else if(fav==2){f="blue";}else if(fav==3){f="green";}else{f="white";}
        JTextPane charlist = new JTextPane();
        charlist.setBackground(Color.white);
        charlist.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        charlist.setForeground(Color.BLACK);
        charlist.setText("Name: " + name + "\nGender: " + g + "\nOutfit number: " + o + "\nFavorite color: " + f);
        charlist.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        charlist.setSize(charlist.getPreferredSize());
        charlist.setLocation(450,194);

        if(gend==1){g="M";}else if(gend==2){g="F";}else{g="N";} if(out==1){o="1";}else if (out==2){o="2";}else{o="3";}

        JLabel charImg = null;
        try {charImg = new JLabel(new ImageIcon(ImageIO.read(new File("APCS/Assets/Img/Character Img/Char3.png")).getScaledInstance(488, 488, Image.SCALE_SMOOTH)));} 
        catch (IOException e) {e.printStackTrace();}charImg.setOpaque(false);charImg.setBounds(850,194,225,413);


        buttons[0].setBackground(new Color(179, 9, 9));
        buttons[0].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 30));
        buttons[0].setText("START");
        buttons[0].setFocusable(false);
        buttons[0].setBounds(1200, 300, 150, 150);

        buttons[1].setBackground(new Color(179, 9, 9));
        buttons[1].setBorder(BorderFactory.createLineBorder(new Color(43,18,204), 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 30));
        buttons[1].setText("BACK");
        buttons[1].setFocusable(false);
        buttons[1].setBounds(200, 300, 150, 150);

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
        if(page==0) {name=nameBox.getText();if(name.length()>0){sPanel.removeAll();gend();sPanel.repaint();page++;}}

        else if(page==1) {sPanel.removeAll();out();sPanel.repaint();page++;}

        else if(page==2) {if(out!=-1){sPanel.removeAll();fav();sPanel.repaint();page++;}}

        else if(page==3) {if(fav!=-1){sPanel.removeAll();fin();sPanel.repaint();page++;}}

        else if(page==4) 
        {
            character = new Player(name, gend, out, fav);character.saveGame();
            close();lGUI = new LevelSelGUI(character);lGUI.displayGame();
        }
    }

    //Handles when the back button is called
    private void back()
    {
        if(page==1) {
            for (Component c : sPanel.getComponents()) {{if (c instanceof JLabel == false){sPanel.remove(c);}}};
            name();sPanel.repaint();page--;}

        else if(page==2) {sPanel.removeAll();gend();sPanel.repaint();page--;}

        else if(page==3) {sPanel.removeAll();out();sPanel.repaint();page--;}

        else if(page==4) {sPanel.removeAll();fav();sPanel.repaint();page--;}
    }
    
    //Paints a JPanel with a percentage of stars randomly
    private void paint(int percent)
    {
        for (Component c : sPanel.getComponents()) {sPanel.setComponentZOrder(c, 0);}
        for(int i=0; i<40; i++)
        {
            for(int j=0; j<75; j++)
            {
                if((int)(Math.random()*100) == percent-1)
                {
                    if((int)(Math.random()*2)==0)
                    {
                        JLabel star = new JLabel();
                        star.setOpaque(true);
                        star.setBackground(Color.YELLOW);
                        star.setSize(new Dimension(20, 20));
                        star.setLocation(j*20, i*20);
                        sPanel.add(star);
                    }
                    else
                    {
                        JLabel star = new JLabel();
                        star.setOpaque(true);
                        star.setBackground(new Color(189,185,38));
                        star.setSize(new Dimension(20, 20));
                        star.setLocation(j*20, i*20);
                        sPanel.add(star);
                    }                    
                }
                else
                {
                    JLabel star = new JLabel();
                    star.setOpaque(true);
                    star.setBackground(Color.BLACK);
                    star.setSize(new Dimension(20, 20));
                    star.setLocation(j*20, i*20);
                    sPanel.add(star);
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

    private void close() {Window[] windows = Window.getWindows(); for (Window window : windows) {if (window != null) {window.dispose();}}}
}
