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
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * GUI for the home screen
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */
public class Home
    extends Draw
{
    /**
     * playersButton to join game
     */
    private static JButton playerButton;
    /**
     * hostsButton to join game
     */
    private static JButton hostsButton;
    /**
     * frame that holds all of the GUI components
     */
    private JFrame         frame;
    /**
     * label that directs user to enter hosts ip address
     */
    private JLabel         hostsIpAddressLabel       = new JLabel("                           Hosts IP Address:");
    /**
     * separator that separates labels
     */
    private JLabel         labelSeparator    = new JLabel("");
    /**
     * button to officially start the game
     */
    private JButton        startButton         = new JButton("Start");
    /**
     * textField that takes the hosts IP Adress
     */
    private JTextField     IPAddresstextfield = new JTextField();
    /**
     * Player computer object that temporarily stores the server/client obejct
     * while waiting to start game
     */
    private PlayerComputer pc;

    // public static void main(String[] args)
    //     throws Exception
    // {
    //     new Home();
    // }


    /**
     * Constructor for Home Class
     * calls the design method
     * @throws Exception
     */
    public Home()
        throws Exception
    {
        design();

    }


    /**
     * Design for the Home Screen
     * 
     * @throws IOException
     */
    public void design()
        throws Exception
    {
        hostsIpAddressLabel.setVisible(false);
        frame = new JFrame();
        frame.setSize(2000, 1000);
        JLabel trashWarLabel = new JLabel(" Trash War ");
        trashWarLabel.setFont(new Font("AvantGarde", Font.BOLD, 100));
        trashWarLabel.setForeground(Color.BLUE);
        JPanel panel2 = new JPanel();
        panel2.setBackground(Color.WHITE);
        Image myPicture = ImageIO.read(
            new File(
                getClass().getResource("TrashWarImagesAndSounds" + File.separator + "re.png.jpg")
                    .toURI()));
        myPicture = getScaledImage(myPicture, 250, 250);
        JLabel recycleImage = new JLabel(new ImageIcon(myPicture));
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        trashWarLabel.setBounds(100, 150, 0, 0);
        JLabel spaceSeparator1 = new JLabel("                       ");
        JLabel spaceSeparator2 = new JLabel("                       ");
        JLabel spaceSeparator3 = new JLabel("                                  ");
        JLabel spaceSeparator4 = new JLabel(
            "                                                                                                                                                                                "
                + "                                                                                                                                                                                                                   ");

        Clicklistener click = new Clicklistener();

        playerButton = new JButton("   Play Game     ");
        playerButton.addActionListener(click);
        hostsButton = new JButton("Host a Game");
        hostsButton.addActionListener(click);
        startButton.addActionListener(click);
        IPAddresstextfield = new JTextField("");
        playerButton.setPreferredSize(new Dimension(300, 50));
        hostsButton.setPreferredSize(new Dimension(300, 50));

        JPanel panel = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.add(trashWarLabel);
        panel.add(recycleImage);
        panel2.add(spaceSeparator2);
        panel2.add(picLabel);
        panel2.add(panel);
        panel2.add(spaceSeparator1);
        panel2.add(spaceSeparator4);
        panel2.add(playerButton);
        panel2.add(spaceSeparator3);
        panel2.add(hostsButton);
        panel3.setLayout(new GridLayout());
        panel3.add(hostsIpAddressLabel);
        hostsIpAddressLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel3.add(IPAddresstextfield);
        panel4.add(startButton);
        startButton.setVisible(false);
        panel3.add(hostsIpAddressLabel);
        panel3.add(labelSeparator);
        panel3.setBackground(Color.WHITE);
        panel2.add(panel3);
        panel2.add(panel4);
        panel4.setBackground(Color.WHITE);
        panel3.setBorder(new EmptyBorder(50, 50, 50, 50));

        frame.add(panel2);
        frame.setVisible(true);
        IPAddresstextfield.setVisible(false);
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
            if (e.getSource() == playerButton)
            {
                try
                {
                    startButton.setVisible(true);
                    hostsIpAddressLabel.setVisible(true);
                    IPAddresstextfield.setVisible(true);

                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }

            if (e.getSource() == hostsButton)
            {
                try
                {
                   
                    startButton.setVisible(true);
                    hostsIpAddressLabel.setVisible(false);
                    IPAddresstextfield.setVisible(false);

                    pc = new Server();

                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }

            if (e.getSource() == startButton)
            {
                try
                {
                    if (IPAddresstextfield.isVisible())
                    {
                        String value = IPAddresstextfield.getText();
                        frame.setVisible(false);
                        pc = new Client(value);
                    }
                    else
                    {
                        frame.setVisible(false);
                        pc.startGame();
                    }

                }
                catch (Exception e1)
                {
                    e1.printStackTrace();

                }
            }
        }
    }
}
