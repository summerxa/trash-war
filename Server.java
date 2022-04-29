/**
 * The server of the game. Its main purpose is to send updates
 * on the game state to every player.
 * 
 * @author  Anne Xia
 * @version 04/27/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Server {
    /**
     * Constructs a server containing the players.
     * @param host the host of the server.
     * @param players a list of all players, including the host.
     */
    public Server() {

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
     */
    public void processUpdate(int type) {

    }

    /**
     * Starts the game by creating a GameThread for each user.
     */
    public void startGame() {
        
    }

    /**
     * Stops the game by stopping each user's thread.
     */
    public void stopGame() {
        
    }
}
