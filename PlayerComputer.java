/**
 * Abstract parent class for the Server and Client. Provides
 * methods to perform actions and update the game state.
 * 
 * @author  Anne Xia
 * @version 05/06/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
*/
public abstract class PlayerComputer {
    /**
     * The port to use.
     */
    public static final int PORT = 12345;
  
    /**
     * Starts the game.
    */
    public abstract void startGame();
  
    /**
     * Stops the game.
    */
    public abstract void stopGame();
  
    /**
     * Simulates a player slapping a card.
    */
    public abstract void slapCard();
  
    /**
     * Overloaded version of slapCard, optional method used exclusively
     * by the Server class to process card slaps from client player.
     * @param player the player who slapped the card.
    */
    public void slapCard(Player player) {
        // do nothing
    }
  
    /**
     * Updates the score of a given player.
     * @param player the player whose score to update.
     * @param newScore the new score of the player.
    */
    public abstract void updatePoints(Player player, int newScore);
  
    /**
     * Simulates a new card being dealt.
    */
    public abstract void dealCard();
  
    /**
     * Overloaded version of dealCard for Server class.
     * @param player the player who dealt the card.
     * @param card the card being dealt.
    */
    public void dealCard(Player player, Card card) {
        // do nothing
    }
}
