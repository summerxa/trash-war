/**
 * Abstract parent class for the Server and Client. Provides
 * methods to perform actions and update the game state.
 * 
 * @author  Anne Xia
 * @version 05/06/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
*/

public abstract class PlayerComputer {
    /**
     * The port to use.
     */
    public static final int PORT = 12345;
    
    /**
     * Servers only: begins accepting Client players.
    */
    public void startServer() {
        // do nothing
    }
    
    /**
     * Clients only: connects to a Server.
     * @param address IP address of host server.
    */
    public void connectToServer(String address) {
        // do nothing
    }
  
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
     * Overloaded version of slapCard for Server class.
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
     * Randomly generates a new card from the current player's deck.
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
