import java.net.*;

/**
 * A client that connects to the game. Its main purpose is to sync
 * the local game state with the version stored on the server.
 * 
 * @author  Anne Xia
 * @version 05/03/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Client {
    private Score scores;
    private Socket s;
    private Player self;
    private GameThread messenger;
    
    /**
     * Constructs a client and does nothing.
     */
    public Client() {

    }

    /**
    * Connects to a given server.
    * @param address the IP address of the server
    */
    public void connectToServer(String address) {
        try {
            s = new Socket(address, Server.PORT);
        } catch (Exception ex) {
            System.out.println("Error connecting to server:" + ex);
        }
    }

    /**
     * Starts the game by creating a GameThread for the current user.
     */
    public void startGame() {
        
    }

    /**
     * Stops the game by stopping the current user's thread.
     */
    public void stopGame() {
        
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
    private void updatePoints(Player player, int newScore) {
        player.addPoints(newScore);
        scores.newGlobalScores();
    }
}
