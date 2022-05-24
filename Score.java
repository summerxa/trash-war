/**
 * Identifies the global scores and current score of the player
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */
public class Score {
    /**
     * Ordered Array of ten players with highest scores from greatest to least
     */
    private int[] globalScores;
    /**
     * Score of player who just finished playing the game
     */
    private int currentScore;

    /**
     * The Constructor that sets the globalScores 
     */
    public Score(){
        globalScores = new int[10];
        currentScore = 0; // have to change this

    }

    /**
     * Sets the new global scores
     */
    public void newGlobalScores(){

        int i = 0;
        int curr = currentScore;

        while ((i < globalScores.length) && (currentScore <= globalScores[i]))
        {
            i++;
        }
        
        while (i < globalScores.length)
        {
            int temp = globalScores[i];
            globalScores[i] = curr; 
            curr = temp; 
            i++;

        }
    }

    /**
     * sets the current score
     * @param score
     */
    public void setCurrent(int score)
    {
        currentScore = score;
    }

    /**
     * 
     * @returns the list of global scores
     */
    public int[] getGlobalScores()
    {
        return globalScores;
    }

    public static void main(String args[])
    {
        Score sc = new Score();
        sc.setCurrent(13);
        sc.newGlobalScores();

        int[] result = sc.getGlobalScores();
        for (int num : result)
        {
            System.out.println(num);
        }
    }


    
}
