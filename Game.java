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

    public static void main(String[] args)
        throws Exception
    {

        new Game();
    }


    public Game()
        throws Exception
    {
        MusicPlayer m = new MusicPlayer();
        String filePath = "bensound-moose.wav";
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
        Image img = ImageIO.read(new File("playerDeck1.jpg"));
        img = getScaledImage(img, 1000, 500);
        JPanel panel = new JPanel();

        panel.setSize(1000, 500);
        panel.setLayout(new BoxLayout(panel, 1));

        JButton centerDeck = new JButton("        Empty      ");
        Clicklistener click = new Clicklistener();
        centerDeck.addActionListener(click);
        centerDeck.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        centerDeck.setEnabled(false);
        centerDeck.setBackground(Color.BLACK);
        Image myPicture = ImageIO.read(new File("playerDeck1.jpg"));
        myPicture = getScaledImage(myPicture, 250, 300);
        playerDeck1 = new JButton(new ImageIcon(myPicture));
        playerDeck1.addActionListener(click);
        playerDeck1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        myPicture = ImageIO.read(new File("playerDeck2.jpg"));
        myPicture = getScaledImage(myPicture, 250, 300);
        playerDeck2 = new JButton(new ImageIcon(myPicture));
        playerDeck2.addActionListener(click);
        playerDeck2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel panel2 = new JPanel();
        panel.setBorder(new EmptyBorder(10, 100, 0, 100));
        panel2.setBorder(new EmptyBorder(50, 100, 0, 100));
        panel2.add(playerDeck1);

        Color color = new Color(207, 185, 151);
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

    class Clicklistener
        implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == playerDeck1)
            {
                try
                {
                    System.out.println("button one clicked");

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
                    System.out.println("button two clicekd");

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
