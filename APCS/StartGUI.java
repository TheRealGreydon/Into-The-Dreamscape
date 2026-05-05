/*Name:	Mr. Klus
 *Date: 05/01/2019
 *Description: A graphical user interface for the drop game.
 */
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
    
    private String name;
    private int gend;
    private int out;
    private int fav;
    
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
        //gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 2;       
        gbc.gridwidth = 1;   
        gbc.gridy = 1;
        nameBox.setFont(new Font("SansSerif", Font.PLAIN, 48));
        sPanel.add(nameBox,gbc);

        JLabel title = new JLabel();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;       
        gbc.weighty = 1.0;   
        gbc.anchor = GridBagConstraints.PAGE_START; 
        //gbc.insets = new Insets(10,0,0,0); 
        gbc.gridx = 2;       
        gbc.gridwidth = 1;   
        gbc.gridy = -1;       
        title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("What is your name?");
        sPanel.add(title,gbc);

        buttons[0]=new JButton();
        gbc.gridx=10;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_END; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(Color.RED);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
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
        buttons[0].setBackground(Color.RED);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");

        buttons[1]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(Color.RED);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].addActionListener(this);

        buttons[2]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=1;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[2],gbc);
        buttons[2].setPreferredSize(new Dimension(200,100));
        buttons[2].setBackground(Color.RED);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setText("Male");
        buttons[2].addActionListener(this);

        buttons[3]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[3],gbc);
        buttons[3].setPreferredSize(new Dimension(100,100));
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("Female");
        buttons[3].addActionListener(this);

        buttons[4]=new JButton();
        gbc.gridx=3;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[4],gbc);
        buttons[4].setPreferredSize(new Dimension(200,100));
        buttons[4].setBackground(Color.RED);
        buttons[4].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[4].setFont(new Font(buttons[4].getFont().getName(), Font.BOLD, 15));
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
        buttons[0].setBackground(Color.RED);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");

        buttons[1]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(Color.RED);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].addActionListener(this);

        buttons[2]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=1;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[2],gbc);
        buttons[2].setPreferredSize(new Dimension(200,100));
        buttons[2].setBackground(Color.RED);
        buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[2].setFont(new Font(buttons[2].getFont().getName(), Font.BOLD, 15));
        buttons[2].setText("1");
        buttons[2].addActionListener(this);

        buttons[3]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=2;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[3],gbc);
        buttons[3].setPreferredSize(new Dimension(100,100));
        buttons[3].setBackground(Color.RED);
        buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[3].setFont(new Font(buttons[3].getFont().getName(), Font.BOLD, 15));
        buttons[3].setText("2");
        buttons[3].addActionListener(this);

        buttons[4]=new JButton();
        gbc.gridx=3;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.CENTER; 
        sPanel.add(buttons[4],gbc);
        buttons[4].setPreferredSize(new Dimension(200,100));
        buttons[4].setBackground(Color.RED);
        buttons[4].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
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
        gbc.gridx = 2;       
        gbc.gridwidth = 1;   
        gbc.gridy = -1;       
        title.setBounds(100,20,600,80);
        title.setOpaque(true);
        title.setBackground(Color.WHITE);
        title.setFont(new Font(title.getFont().getName(), Font.BOLD, 40));
        title.setText("Whats your favorite color?");
        sPanel.add(title,gbc);

        gbc.gridx=10;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_END; 
        sPanel.add(buttons[0],gbc);
        buttons[0].setPreferredSize(new Dimension(100,100));
        buttons[0].setBackground(Color.RED);
        buttons[0].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[0].setFont(new Font(buttons[0].getFont().getName(), Font.BOLD, 15));
        buttons[0].setText("NEXT");

        buttons[1]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[1],gbc);
        buttons[1].setPreferredSize(new Dimension(100,100));
        buttons[1].setBackground(Color.RED);
        buttons[1].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        buttons[1].setFont(new Font(buttons[1].getFont().getName(), Font.BOLD, 15));
        buttons[1].setText("BACK");
        buttons[1].addActionListener(this);

        buttons[5]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[5],gbc);
        buttons[5].setPreferredSize(new Dimension(100,100));
        buttons[5].setBackground(Color.RED);
        buttons[5].setText("");
        buttons[5].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        buttons[5].addActionListener(this);

        buttons[6]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[6],gbc);
        buttons[6].setPreferredSize(new Dimension(100,100));
        buttons[6].setBackground(Color.BLUE);
        buttons[6].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        buttons[6].addActionListener(this);

        buttons[7]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[7],gbc);
        buttons[7].setPreferredSize(new Dimension(100,100));
        buttons[7].setBackground(Color.GREEN);
        buttons[7].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        buttons[7].addActionListener(this);

        buttons[8]=new JButton();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx=0;
        gbc.gridy = 1;       
        gbc.anchor = GridBagConstraints.LINE_START; 
        sPanel.add(buttons[8],gbc);
        buttons[8].setPreferredSize(new Dimension(100,100));
        buttons[8].setBackground(Color.WHITE);
        buttons[8].setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
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
            sPanel.removeAll();
            gend();
            sPanel.repaint();
        }
        else if(page==1)
        {
            sPanel.removeAll();
            out();
            sPanel.repaint();
        }
        else if(page==2)
        {
            sPanel.removeAll();
            fav();
            sPanel.repaint();
        }
        else if(page==3)
        {
            sPanel.removeAll();
            fin();
            sPanel.repaint();
        }
    }

    public void back()
    {
        if(page==1)
        {
            sPanel.removeAll();
            name();
            sPanel.repaint();
        }
        else if(page==2)
        {
            sPanel.removeAll();
            gend();
            sPanel.repaint();
        }
        else if(page==3)
        {
            sPanel.removeAll();
            out();
            sPanel.repaint();
        }
    }
    
    public void actionPerformed(ActionEvent e)
    {
        //the source of the button click
        JButton j = (JButton)(e.getSource());

        if(j.equals(buttons[0]))
        {
            next();
            page++;
        }
        else if(j.equals(buttons[1]))
        {
            back();
            System.out.println(page);
            page--;
        }
        else if(j.equals(buttons[2]))
        {
            if(page==1)
            {
                gend=1;
            }
            else
            {
                out=1;
            }
            buttons[2].setBackground(Color.BLUE);
            buttons[2].setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            buttons[3].setBackground(Color.RED);
            buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            buttons[4].setBackground(Color.RED);
            buttons[4].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        }
        else if(j.equals(buttons[3]))
        {
            if(page==1)
            {
                gend=2;
            }
            else
            {
                out=2;
            }
            buttons[2].setBackground(Color.RED);
            buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            buttons[3].setBackground(Color.BLUE);
            buttons[3].setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            buttons[4].setBackground(Color.RED);
            buttons[4].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        }
        else if(j.equals(buttons[4]))
        {
            if(page==1)
            {
                gend=3;
            }
            else
            {
                out=3;
            }
            buttons[2].setBackground(Color.RED);
            buttons[2].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            buttons[3].setBackground(Color.RED);
            buttons[3].setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            buttons[4].setBackground(Color.BLUE);
            buttons[4].setBorder(BorderFactory.createLineBorder(Color.RED, 5));
        }
    }
}
