/**
 * Identifies the global scores and current score of the player
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */
public class Score {
    /**
     * Ordered Array of ten players with highest scores
     */
    private int[] globalScores;
    /**
     * Score of player who just finished playing the game
     */
    private int currentScore;

    public Score(){
        globalScores = new int[10];
        currentScore = 0; // have to change this

    }

    public void newGlobalScores(){
        if(currentScore > globalScores[0]){
            // find its correct location in globalScores array.
            // shift all scores down
            // place the new score in its right spot
        }
    }


    
}
