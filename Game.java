import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * GUI for thr game screen
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */

public class Game
{

    public JButton playerDeck1;
    public JButton playerDeck2;
    public JButton centerDeck;
    public JPanel panel;
    public static void main(String[] args)
        throws Exception
    {

        new Game();
    }


    public Game()
        throws Exception
    {
        MusicPlayer m = new MusicPlayer();
        String filePath = "TrashWarImagesAndSounds\\bensound-moose.wav";
        m.playMusic(filePath);
        design();
    }


    /**
     * Design for the Game Screen
     * 
     * @throws IOException
     */
    public void design()
        throws IOException
    {

        JFrame frame = new JFrame();
        frame.setSize(2000, 1000);

        JLabel space = new JLabel(
            "                                                                                                            ");
        Image img = ImageIO.read(new File("TrashWarImagesAndSounds\\playerDeck1.jpg"));
        img = getScaledImage(img, 1000, 500);
        panel = new JPanel();

        panel.setSize(1000, 500);
        panel.setLayout(new BoxLayout(panel, 1));

        centerDeck = new JButton();
        Clicklistener click = new Clicklistener();
        centerDeck.addActionListener(click);
        centerDeck.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerDeck.setEnabled(false);
        Color color = new Color(207, 185, 151);
        centerDeck.setBackground(color);
        Image myPicture = ImageIO.read(new File("TrashWarImagesAndSounds\\playerDeck1.jpg"));
        myPicture = getScaledImage(myPicture, 250, 300);
        playerDeck1 = new JButton(new ImageIcon(myPicture));
        playerDeck1.addActionListener(click);
        playerDeck1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        myPicture = ImageIO.read(new File("TrashWarImagesAndSounds\\playerDeck2.jpg"));
        myPicture = getScaledImage(myPicture, 250, 300);
        playerDeck2 = new JButton(new ImageIcon(myPicture));
        playerDeck2.addActionListener(click);
        playerDeck2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel panel2 = new JPanel();
        panel.setBorder(new EmptyBorder(10, 100, 0, 100));
        panel2.setBorder(new EmptyBorder(50, 100, 0, 100));
        panel2.add(playerDeck1);
        centerDeck.setContentAreaFilled(false);
       
        panel.setBackground(color);
        panel2.setBackground(color);
        panel2.add(space);
        panel2.add(playerDeck2);
        centerDeck.setBorder(new EmptyBorder(120, 90, 150, 90));
        centerDeck.setPreferredSize(new Dimension(250, 300));
        playerDeck1.setPreferredSize(new Dimension(250, 300));
        playerDeck2.setPreferredSize(new Dimension(250, 300));
        centerDeck.setAlignmentX(panel.CENTER_ALIGNMENT);
        panel.add(centerDeck);
        panel.add(panel2);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    private static Image getScaledImage(Image srcImg, int w, int h)
    {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public String[] init(String[] arr){
        arr[0] = "Trash" + " "+ "TrashWarImagesAndSounds\\cardboard.jpg";
        arr[1] = "Trash" + " " + "TrashWarImagesAndSounds\\cermicpot.jpg";
        arr[2] = "Trash" + " " + "TrashWarImagesAndSounds\\diaper.jpg";
        arr[3] = "Trash" + " " + "TrashWarImagesAndSounds\\trash.jpg";

        arr[4] = "Recycle" + " " + "TrashWarImagesAndSounds\\aluminum.jpg";
        arr[5] = "Recycle" + " " + "TrashWarImagesAndSounds\\bottle.jpg";
        arr[6] = "Recycle" + " " + "TrashWarImagesAndSounds\\glass.jpg";
        arr[7] = "Recycle" + " " + "TrashWarImagesAndSounds\\recycle.jpg";

        arr[8] = "Compost" + " " + "TrashWarImagesAndSounds\\branches.jpg";
        arr[9] = "Compost" + " " + "TrashWarImagesAndSounds\\orangepeel.jpg";
        arr[10] = "Compost" + " " + "TrashWarImagesAndSounds\\bananapeel.jpg";
        arr[11] = "Compost" + " " + "TrashWarImagesAndSounds\\compost.jpg";

        return arr;
        

    }

    class Clicklistener
        implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == playerDeck1)
            {
                try
                {
                    MusicPlayer m = new MusicPlayer();
                    String filePath = "TrashWarImagesAndSounds\\mixkit-retro-arcade-casino-notification-211.wav";
                    m.playMusic(filePath);
                    String[] arr = new String[12];
                    arr = init(arr);
                   // System.out.println(arr[0]);
                    int i = (int)(Math.random()*12);

                    System.out.println(i +  " " + arr[i]);
                    String[] buffer = arr[i].split(" ");
                    String path = buffer[1];
                    Image myPicture = ImageIO.read(new File(path));
                    myPicture = getScaledImage(myPicture, 250, 300);
                    centerDeck.setEnabled(true);
                    centerDeck.setIcon(new ImageIcon(myPicture));

                    // Card[] arr = new Card[12];
                    // System.out.println("ksjdfh");
                    // arr = card.init(arr);
                    // int i = card.index();
                    // String p = arr[i].path;
                    // Image myPicture = ImageIO.read(new File(p));
                    // myPicture = getScaledImage(myPicture, 250, 300);
                    // centerDeck.setEnabled(true);
                    // centerDeck.setIcon(new ImageIcon(myPicture));
                    



                    //if the button is clicked, I need to place a new button with a random image in the center deck pile
                    //random images will be:
                    //trash word/label
                        //cardboard
                        //ceramic pot
                        //diaper
                    //recycle word/label
                        // aluminum can
                        // plastic bottle
                        // glass jar
                    //compost word/label
                        // branches
                        // orange peels
                        // banana peels

                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (e.getSource() == playerDeck2)
            {
                try
                {
                    MusicPlayer m = new MusicPlayer();
                    String filePath = "TrashWarImagesAndSounds\\mixkit-retro-arcade-casino-notification-211.wav";
                    m.playMusic(filePath);
                    String[] arr = new String[12];
                    arr = init(arr);
                   // System.out.println(arr[0]);
                    int i = (int)(Math.random()*12);

                    System.out.println(i +  " " + arr[i]);
                    String[] buffer = arr[i].split(" ");
                    String path = buffer[1];
                    Image myPicture = ImageIO.read(new File(path));
                    myPicture = getScaledImage(myPicture, 250, 300);
                    centerDeck.setEnabled(true);
                    centerDeck.setIcon(new ImageIcon(myPicture));

                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

}
