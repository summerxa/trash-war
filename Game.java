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
    /**
     * Clients card deck
     */
    private JButton        playerDeck1;
    /**
     * Player's card deck
     */
    private JButton        playerDeck2;
    /**
     * center card deck
     */
    private JButton        centerDeck;
    /**
     * Jpanel on Jframe which holds main gui components
     */
    private JPanel         panel;
    /**
     * CenterDeck object
     */
    private CenterDeck     cd   = new CenterDeck();
    /**
     * slap button that the player clicks if they think a pattern was detected
     */
    private JButton        slap = new JButton("Slap!");
    /**
     * PlayerComputer object
     */
    private PlayerComputer pc;

    // public static void main(String[] args)
    // throws Exception
    // {

    // new Game();
    // }

    /**
     * Game constructor
     * 
     * @param playercomp
     * @throws Exception
     */
    public Game(PlayerComputer playercomp)
        throws Exception
    {
        pc = playercomp;
        MusicPlayer m = new MusicPlayer();
        String filePath = "TrashWarImagesAndSounds/bensound-moose.wav";
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

        JLabel space = new JLabel("                                                   ");

        JLabel space2 = new JLabel("                                                   ");

        Image img =
            ImageIO.read(new File("TrashWarImagesAndSounds" + File.separator + "playerDeck1.jpg"));
        img = getScaledImage(img, 1000, 500);
        panel = new JPanel();

        panel.setSize(1000, 500);
        panel.setLayout(new BoxLayout(panel, 1));

        centerDeck = new JButton();
        Clicklistener click = new Clicklistener();
        slap.addActionListener(click);
        centerDeck.addActionListener(click);
        centerDeck.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        Color color = new Color(207, 185, 151);
        centerDeck.setBackground(color);
        Image myPicture =
            ImageIO.read(new File("TrashWarImagesAndSounds" + File.separator + "playerDeck1.jpg"));
        myPicture = getScaledImage(myPicture, 250, 300);
        playerDeck1 = new JButton(new ImageIcon(myPicture));
        playerDeck1.addActionListener(click);
        playerDeck1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        myPicture =
            ImageIO.read(new File("TrashWarImagesAndSounds" + File.separator + "playerDeck2.jpg"));
        myPicture = getScaledImage(myPicture, 250, 300);
        playerDeck2 = new JButton(new ImageIcon(myPicture));
        playerDeck2.addActionListener(click);
        playerDeck2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel panel2 = new JPanel();
        panel.setBorder(new EmptyBorder(10, 100, 0, 100));
        panel2.add(playerDeck1);
        panel.setBackground(color);
        panel2.setBackground(color);
        panel2.add(space);
        slap.setAlignmentX(panel2.CENTER_ALIGNMENT);
        panel2.add(slap);
        panel2.add(space2);
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


     /**
     * Method to help re-scale an image
     * 
     * @param srcImg
     * @param w
     * @param h
     * @return
     */
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


    /**
     * creates a gameOver tab for the loser
     * 
     * @param score
     *            the score
     * @throws Exception
     */
    public void newYouLost(int score)
        throws Exception
    {
        new GameOver(false, score);
    }


    /**
     * creates a gameOver tab for the winner
     * 
     * @param score
     *            the score
     * @throws Exception
     */
    public void newYouWon(int score)
        throws Exception
    {
        new GameOver(true, score);
    }


    /**
     * shows congrats message
     * 
     * @throws Exception
     */
    public void showCongratsWithPause(boolean isTrue)
        throws Exception
    {

        Result c = new Result(isTrue);
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run()
            {
                c.dispose();
            }
        }, 700);

    }


    /**
     * @return the centerdeck
     */
    public CenterDeck getCenterDeck()
    {
        return cd;
    }


    /**
     * set the centerdeck to visible based on state
     * 
     * @param state
     *            a boolean
     */
    public void changeVisibilityOfCenterDeck(boolean state)
    {
        if (!state)
        {
            centerDeck.setIcon(null);
        }

    }


    /**
     * Draw method that draws the card passed in the parameter
     * 
     * @param card
     * @throws IOException
     */
    public void draw(Card card)
        throws IOException
    {

        String filePath = card.getFilePath();
        Image myPicture = ImageIO.read(new File(filePath));
        myPicture = getScaledImage(myPicture, 250, 300);
        centerDeck.setEnabled(true);
        centerDeck.setIcon(new ImageIcon(myPicture));
        changeVisibilityOfCenterDeck(true);
        cd.addCard(card);

    }

     /**
     * Class to help detect button clicks
     */
    class Clicklistener
        implements ActionListener
    {
        /**
         * method from actionlistener interface used to check if an
         * ActionEvent's source is from the corresponding button
         */
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == playerDeck1)
            {
                try
                {
                    MusicPlayer m = new MusicPlayer();
                    String filePath = "TrashWarImagesAndSounds" + File.separator
                        + "mixkit-retro-arcade-casino-notification-211.wav";
                    m.playMusic(filePath);
                    if (pc instanceof Client)
                        pc.dealCard();

                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (e.getSource() == slap)
            {
                try
                {
                    if (!cd.isEmpty())
                    {
                        pc.slapCard();
                    }

                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                // caall slapcardmethod
            }

            if (e.getSource() == playerDeck2)
            {
                try
                {
                    MusicPlayer m = new MusicPlayer();
                    String filePath = "TrashWarImagesAndSounds" + File.separator
                        + "mixkit-retro-arcade-casino-notification-211.wav";
                    m.playMusic(filePath);
                    if (pc instanceof Server)
                        pc.dealCard();
                    // m.playMusic(filePath);
                    // String[] arr = new String[12];
                    // arr = init(arr);
                    // // System.out.println(arr[0]);
                    // int i = (int)(Math.random() * 12);

                    // System.out.println(i + " " + arr[i]);
                    // String[] buffer = arr[i].split(" ");
                    // String type = buffer[0];
                    // String path = buffer[1];
                    // Image myPicture = ImageIO.read(new File(path));
                    // myPicture = getScaledImage(myPicture, 250, 300);
                    // centerDeck.setEnabled(true);
                    // centerDeck.setIcon(new ImageIcon(myPicture));

                    // cd.addCard(type);

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
