import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * GUI for the home screen
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */
public class Home extends Draw{

    public static JButton playerButton;
    public static JButton hostsButton;
    public JFrame frame;
    public JLabel labell;
    public JTextField text;
    public JLabel lab = new JLabel(  "                           Hosts IP Address:");
    public JLabel space9 = new JLabel("");
    public JButton b = new JButton("Start");
    private JTextField textfield = new JTextField();

    public static void main(String[] args) throws Exception {
        new Home();
    }

    public Home() throws Exception{
        design();

    }

  

   /**
    * Design for the Home Screen
 * @throws IOException
    */
    public void design() throws Exception{
        lab.setVisible(false);
        frame = new JFrame();
        frame.setSize(2000, 1000);
        JLabel label = new JLabel(" Trash War ");
        label.setFont(new Font("AvantGarde", Font.BOLD, 100));
        label.setForeground(Color.BLUE);
        JLabel space2 = new JLabel("   sss    ");
        space2.setFont(new Font("AvantGarde", Font.BOLD, 100));
        space2.setForeground(Color.BLUE);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
       Image myPicture = ImageIO.read(new File(getClass().getResource("TrashWarImagesAndSounds" + File.separator + "re.png.jpg").toURI()));

       // Image myPicture = ImageIO.read(new File("TrashWarImagesAndSounds\\orangepeel.jpg"));
        myPicture = getScaledImage(myPicture, 250, 250);
        JLabel l = new JLabel(new ImageIcon(myPicture));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        label.setBounds(100, 150, 0, 0);
        JLabel space3 = new JLabel("                       ");
        JLabel space4 = new JLabel("                       ");
        JLabel space5 = new JLabel("                                  ");
        JLabel space6 = new JLabel(
                "                                                                                                                                                                                "
                        + "                                                                                                                                                                                                                   ");
        
        Clicklistener click = new Clicklistener();

        playerButton = new JButton("   Play Game     ");
        playerButton.addActionListener(click);
        hostsButton = new JButton("Host a Game");
        hostsButton.addActionListener(click);
        b.addActionListener(click);
        labell = new JLabel("                            Give player your IP Address");
        text = new JTextField("");
        playerButton.setPreferredSize(new Dimension(300, 50));
        hostsButton.setPreferredSize(new Dimension(300, 50));

        JPanel panel = new JPanel();
        JPanel newp = new JPanel();
        JPanel pa = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.add(label);
        panel.add(l);
        panel2.add(space4);
        panel2.add(picLabel);
        panel2.add(panel);
        panel2.add(space3);
        panel2.add(space6);
        panel2.add(playerButton);
        panel2.add(space5);
        panel2.add(hostsButton);
        newp.setLayout(new GridLayout());
        newp.add(lab);
        lab.setBorder(new EmptyBorder(5,5,5,5));
        newp.add(text);
      pa.add(b);
      b.setVisible(false);
        newp.add(labell);
        newp.add(space9);
       newp.setBackground(Color.WHITE);
        panel2.add(newp);
        panel2.add(pa);
        pa.setBackground(Color.WHITE);
       newp.setBorder(new EmptyBorder(50, 50, 50, 50));
        labell.setVisible(false);
        labell.setFont(new Font("AvantGarde", Font.BOLD, 15));
        labell.setForeground(Color.RED);
        
        frame.add(panel2);
        frame.setVisible(true);
        text.setVisible(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

    private static Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
   
    class Clicklistener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == playerButton) {
                try {
                   
                    labell.setVisible(false);
                    b.setVisible(true);
                   lab.setVisible(true);
                   text.setVisible(true);

                   

                
                // b.setVisible(false);
                // lab.setVisible(false);
                // text.setVisible(false);
                // labell.setVisible(true);
                    
                    
                    

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (e.getSource() == hostsButton) {
                try {
                    // b.setVisible(false);
                    // lab.setVisible(false);
                    // text.setVisible(false);
                    // labell.setVisible(true);
                    new Game(new Server());
                    
                 
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if(e.getSource() == b){
                try {
                    String value = textfield.getText();
                    new Game(new Client(value));
                 
                } catch (Exception e1) {
                    e1.printStackTrace();
                    
                }
            }
        }
    }
}
