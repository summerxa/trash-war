import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * Game Over Screen GUI which either shows that you won or lost and also shows
 * your total points
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */

public class GameOver
    extends Draw
{
    /**
     * keeps track of the players' scores
     */
    private int     score;
    /**
     * checks whether the player won or lost
     */
    private boolean isWin;
    /**
     * stores the image file path of either the youLose image or youWin image
     */
    private String  filePath;
    /**
     * Take the user to the next screen
     */
    private JButton nextButton;

    /**
     * Jframe that holds all the GUI components
     */
    private JFrame frame;

    // public static void main(String[] args)
    //     throws Exception
    // {
    //     new GameOver(true, 8);
    // }


    /**
     * GameOver constructor
     * @param isWin
     * @param score
     * @throws Exception
     */
    public GameOver(boolean isWin, int score)
        throws Exception
    {
        this.score = score;
        this.isWin = isWin;
        if (isWin)
        {
            filePath = "TrashWarImagesAndSounds" + File.separator + "youwin.jpg";
        }
        else
        {
            filePath = "TrashWarImagesAndSounds" + File.separator + "youlost.jpg";
        }
        design();

    }


    /**
     * Design for the GameOver Screen
     * 
     * @throws Exception
     */
    public void design()
        throws Exception
    {
        frame = new JFrame();
        frame.setSize(400, 300);
        frame.setLocation(450, 100);
        Image myPicture = ImageIO.read(new File(filePath));
        myPicture = getScaledImage(myPicture, 400, 200);
        JLabel gameOverStateImage = new JLabel(new ImageIcon(myPicture));
        String scoreString = Integer.toString(score);
        JLabel points = new JLabel("Total Score: " + scoreString);
        points.setBorder(new EmptyBorder(10, 10, 5, 10));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));

        nextButton = new JButton("Next");
        nextButton.setBorder(new EmptyBorder(10, 10, 5, 10));
        Clicklistener click = new Clicklistener();
        nextButton.addActionListener(click);

        panel.add(gameOverStateImage);
        panel.add(points);
        panel.add(nextButton);
        frame.add(panel);
        frame.setVisible(true);

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
        BufferedImage newImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImg.createGraphics();

        g.setRenderingHint(
            RenderingHints.KEY_INTERPOLATION,
            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(srcImg, 0, 0, w, h, null);
        g.dispose();

        return newImg;
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
            if (e.getSource() == nextButton)
            {
                try
                {
                    frame.setVisible(false);
                    new Leaderboard(score);

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
