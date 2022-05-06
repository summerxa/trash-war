import java.util.*;

/**
 * The server of the game. Its main purpose is to send updates
 * on the game state to the client player.
 * 
 * @author  Anne Xia
 * @version 04/30/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
// TODO extends playercomputer, fix stuff
public class Server {

    /**
     * The port to use.
     */
    public static final int PORT = 12345;
    
    private ServerThread sThread;
    private ArrayList<Player> players;
    private Player host;
    private GameThread client; // we will only have one player for now

    /**
     * Constructs a server that begins accepting players.
     */
    public Server() {
        // whether this list contains the host or not is tbd
        players = new ArrayList<Player>();
        // TODO initialize the host
        sThread = new ServerThread();
        sThread.start();
    }

    
    /**
     * Starts the game by getting all connected users (including the host)
     * and creating a GameThread for each user.
     */
    public void startGame() {
        players = sThread.stopThread(true);
        // the last condition is because we expect this to be a 2-player game
        if (players == null || players.isEmpty() || players.size() != 2) {
            System.out.println("Something went wrong while accessing the players");
            return;
        }
        // TODO create threads
    }

    /**
     * Stops the game by stopping each user's thread.
     */
    public void stopGame() {
        
    }

    /**
     * Handles a player slapping a card.
     * @param player the player who slapped the card.
     */
    public void onCardSlapped(Player player) {

    }

    /**
     * Sends a message to the client that a player's
     * score has changed.
     * @param player the player whose score changed.
     * @param newScore the new score of the player.
     */
    private void updatePoints(Player player, int newScore) {

    }

    /**
     * Sends an update on the game state (change in points,
     * new card dealt, etc.) to all users.
     * @param type the integer code of the type of update.
     */
    public void sendUpdate(int type) {

    }

    /**
     * Processes an update to the game state sent from another device.
     * @param type the integer code of the type of update.
     * @param sender the player that sent the original update
     */
    public void processUpdate(int type) {

    }

    /**
     * For debugging purposes only
     * @param args
     */
    public static void main(String[] args) {
        Server aTest = new Server();
    }
}
