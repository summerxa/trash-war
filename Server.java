import java.util.*;

/**
 * The server of the game. Its main purpose is to send updates
 * on the game state to the client player.
 * 
 * @author  Anne Xia
 * @version 04/27/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Server {
    public static final int PORT = 12345;
    
    private ServerThread sThread;
    private ArrayList<Player> players;

    /**
     * Constructs a server that begins accepting players.
     */
    public Server() {
        // whether this list contains the host or not is tbd
        players = new ArrayList<Player>();
        sThread = new ServerThread();
        sThread.start();
    }

    
    /**
     * Starts the game by getting all connected users (including the host)
     * and creating a GameThread for each user.
     */
    public void startGame() {
        players = sThread.stopThread(true);
    }

    /**
     * Stops the game by stopping each user's thread.
     */
    public void stopGame() {
        
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
