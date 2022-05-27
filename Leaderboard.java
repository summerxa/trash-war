import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * GUI for Leaderboard
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */

public class Leaderboard extends Draw{

    private int points;

    public static void main(String[ ]args) throws Exception {
        new Leaderboard(8);
    }

    public Leaderboard(int score) throws Exception {
        this.points = score;
        design();

    }

   /**
    * Design for the Leaderboard Screen
    */
    public void design()throws Exception {
        Score score = new Score(points);
        
        int[] arr= score.returnScores();
        arr = score.assignScores(arr);
        arr = score.newGlobalScores(arr, points);

        JFrame frame = new JFrame();
        frame.setSize(400,480);
        frame.setLocation(450, 100);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,1));
        JLabel lab = new JLabel("Leaderboard");
        lab.setFont(new Font("AvantGarde", Font.BOLD, 50));
        lab.setForeground(Color.BLUE);
        panel.add(lab);
        JLabel label;
        for(int i = 0; i < arr.length; i++ ){
            System.out.println("Arr i: " + arr[i]);
            label = new JLabel(Integer.toString(arr[i]));
            label.setBorder(new EmptyBorder(10,10,10,10));
            
            panel.add(label);
        }
        frame.add(panel);
        frame.setVisible(true);

        


    }
    
}
