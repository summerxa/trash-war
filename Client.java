import java.net.*;

/**
 * A client that connects to the game. Its main purpose is to sync
 * the local game state with the version stored on the server.
 * 
 * @author  Anne Xia
 * @version 05/08/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Client extends PlayerComputer {
    private Score scores;
    private Socket s;
    private Player self;
    private GameThread messenger;
    
    /**
     * Constructs a client and connects to a server..
     * @param address the IP address of the server
     */
    public Client(String address) {
        // TODO initialize scores
        // TODO create / get self Player
        connectToServer(address);
    }

    /**
    * Connects to a given server.
    * @param address the IP address of the server
    */
    private void connectToServer(String address) {
        try {
            s = new Socket(address, Server.PORT);
        } catch (Exception ex) {
            System.out.println("Error connecting to server:" + ex);
        }
    }

    // TODO start messenger elsewhere and fix these 2 methods accordingly

    /**
     * Starts the game by creating a GameThread for the current user.
     */
    public void startGame() {
        messenger = new GameThread(this, false, s);
    }

    /**
     * Stops the game by stopping the current user's thread.
     */
    public void stopGame() {
        messenger.stopThread();
    }
    
    /**
    * Sends a slap card update to the server.
    */
    public void slapCard() {
        messenger.slapCard(self);
    }

    /**
     * Updates the score of a given player.
     * @param player a player.
     * @param newScore the new score.
     */
    public void updatePoints(Player player, int newScore) {
        player.addPoints(newScore);
        scores.newGlobalScores();
    }

    /**
     * Simulates a new card being dealt by this player.
     * Randomly generates a new card from the deck.
     */
    public void dealCard() {
        // TODO
    }

    /**
     * Displays the random card generated. Method should be called
     * only by GameThread, use the no-args version of this method for
     * other card dealing.
     */
    public void dealCard(Player player, Card card) {
        // TODO
    }
}
