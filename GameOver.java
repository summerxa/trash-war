import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
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
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 * You Lost Screen GUI
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */

public class GameOver
    extends Draw
{

    private int     score;
    private boolean isWin;
    public String   filePath;
    public JButton  button;

    public static void main(String[] args)
        throws Exception
    {
        new GameOver(true, 8);
    }


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
     * Design for the YouLost Screen
     * 
     * @throws Exception
     */
    public void design()
        throws Exception
    {
        JFrame frame = new JFrame();
        frame.setSize(400, 300);
        frame.setLocation(450, 100);
        Image myPicture = ImageIO.read(new File(filePath));
        myPicture = getScaledImage(myPicture, 400, 200);
        JLabel l = new JLabel(new ImageIcon(myPicture));
        String s = Integer.toString(score);
        JLabel points = new JLabel("Total Score: "   + s);
        points.setBorder(new EmptyBorder(10, 10, 5, 10));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));

        button = new JButton("Next");
        button.setBorder(new EmptyBorder(10, 10,5, 10));
        Clicklistener click = new Clicklistener();
        button.addActionListener(click);

        panel.add(l);
        panel.add(points);
        panel.add(button);
        frame.add(panel);
        frame.setVisible(true);

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
            if (e.getSource() == button)
            {
                try
                {

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
