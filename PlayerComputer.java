import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Abstract parent class for the Server and Client. Provides
 * methods to perform actions and update the game state.
 * 
 * @author  Anne Xia
 * @version 05/15/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
*/
public abstract class PlayerComputer {
    /**
     * The port to use.
     */
    public static final int PORT = 12345;

    protected List<Player> players;
    protected Game gameWindow;

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
    // TODO + empty deck, - burn and do nothing
    // TODO insterad of score value do change in score
    public abstract void updatePoints(Player player, int newScore);
    
    /**
     * Simulates a new card being dealt.
     * Randomly generates a new card from the current player's deck.
     */
    public abstract void dealCard();
    
    /**
     * Overloaded version of dealCard to display a card on GUI window.
     * @param player the player who dealt the card.
     * @param card the card being dealt.
     * @throws IOException
     */
    // TODO replace dealcard in gamethread
    public void drawCard(Player player, Card card) throws IOException {
        // do nothing
    }

    /**
     * Gets the player with a given name from the current
     * object's list of players, or null if player not found.
     * @param name player name to search for, or null if not found.
     */
    public Player getMatch(String name) {
        if (players == null) {
            return null;
        }
        for (Player p : players) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns a list of all players.
     * @return a list of all players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * For client class only, sets the local players list
     * to the given list.
     * @param players a list of players.
     */
    public void setPlayers(List<Player> players) {
        // do nothing
    }

    public void setGUI(Game theGame) {
        gameWindow = theGame;
    }
}
