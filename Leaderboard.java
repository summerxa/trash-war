import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

/**
 * GUI for Leaderboard
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */

public class Leaderboard
    extends Draw
{

    /**
     * holds points of the players
     */
    private int     points;

    /**
     * frame that holds all gui components
     */
    private JFrame  frame;

    public static void main(String[] args)
        throws Exception
    {
        new Leaderboard(8);
    }


    /**
     * Leaderboard Constructor initializes points and calls design method to set
     * up the GUI
     * 
     * @param score
     * @throws Exception
     */
    public Leaderboard(int score)
        throws Exception
    {
        this.points = score;
        design();

    }


    /**
     * Design for the Leaderboard Screen
     */
    public void design()
        throws Exception
    {
        Score score = new Score(points);
        int[] arr = score.returnScores();
        arr = score.assignScores(arr);
        arr = score.newGlobalScores(arr, points);
        frame = new JFrame();
        frame.setSize(400, 500);
        frame.setLocation(450, 100);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, 1));
        JLabel leaderboardLabel = new JLabel("Leaderboard");
        leaderboardLabel.setFont(new Font("AvantGarde", Font.BOLD, 50));
        leaderboardLabel.setForeground(Color.BLUE);
        panel.add(leaderboardLabel);
        JLabel allScores;
        for (int i = 0; i < arr.length; i++)
        {
            System.out.println("Arr i: " + arr[i]);
            allScores = new JLabel(Integer.toString(arr[i]));
            allScores.setBorder(new EmptyBorder(10, 10, 10, 10));

            panel.add(allScores);
        }
        frame.add(panel);
        frame.setVisible(true);

    }

}
