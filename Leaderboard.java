import javax.swing.*;

/**
 * GUI for Leaderboard
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */

public class Leaderboard extends Draw{

    private int points;

    public static void main(String[ ]args){
        new Leaderboard(8);
    }

    public Leaderboard(int score) {
        this.points = score;

    }

   /**
    * Design for the Leaderboard Screen
    */
    public void design(){
        Score score = new Score(points);
        int[] arr= score.returnScores();

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label;
        for(int i = 0; i < arr.length; i++ ){
            label = new JLabel(Integer.toString(arr[i]));
            panel.add(label);
        }
        frame.add(panel);
        frame.setVisible(true);

        


    }
    
}
