import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Identifies the global scores and current score of the player
 * 
 * @author All
 * @version 04/29/2022
 */

public class Score
{
    /**
     * Ordered Array of ten players with highest scores from greatest to least
     */
    private static int[] globalScores;
    /**
     * Score of player who just finished playing the game
     */
    private static int   currentScore;

    /**
     * The Constructor that sets the globalScores
     * @throws Exception
     */
    public Score(int score) throws Exception
    {
        globalScores = new int[10];
        globalScores = assignScores(globalScores);
        currentScore = score;

    }

    /**
     * Reads data from file and assigns them into the array 
     * @param arr
     * @return
     * @throws Exception
     */
    public int[] assignScores(int[] arr)
        throws Exception
    {
        BufferedReader f = new BufferedReader(new FileReader("Global Leaderboard"));
        String line;
        int i = 0;
        while ((line = f.readLine()) != null)
        {
            arr[i++] = Integer.parseInt(line);
        }
        return arr;

    }

    public int[] returnScores(){
        return globalScores;
    }


    /**
     * Sets the new global scores
     */
    public int[] newGlobalScores(int[] arr, int points)
    {

        int i = 0;
        int curr = points;

        while ((i < arr.length) && (currentScore >= arr[i]))
        {
            i++;
        }

        while (i < arr.length)
        {
            int temp = arr[i];
            arr[i] = curr;
            curr = temp;
            i++;

        }
         if(i==arr.length){
             arr[arr.length-1] = curr;
         }

        return arr;
    }


    /**
     * sets the current score
     * 
     * @param score
     */
    public void setCurrent(int score)
    {
        currentScore = score;
    }


    /**
     * sends the list of global scores
     * @returns the list of global scores
     */
    public int[] getGlobalScores()
    {
        return globalScores;
    }


    // /**
    //  * Main method
    //  * @param args
    //  * @throws Exception
    //  */
    // public static void main(String args[]) throws Exception
    // {
    //     Score sc = new Score(5);
    //     sc.setCurrent(13);
    //     sc.newGlobalScores(globalScores, currentScore);

    //     int[] result = sc.getGlobalScores();
    //     for (int num : result)
    //     {
    //         System.out.println(num);
    //     }
    // }

}
