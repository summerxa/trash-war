/**
 * The server of the game. Its main purpose is to send updates
 * on the game state to the client player.
 * 
 * @author  Anne Xia
 * @version 04/27/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Client {
    /**
     * Constructs a client that connects to the given server.
     * @param address the IP address of the server
     */
    public Client(String address) {

    }

    /**
     * Sends an update on the game state (change in points,
     * new card dealt, etc.) to the server.
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
     * Starts the game by creating a GameThread for the current user.
     */
    public void startGame() {
        
    }

    /**
     * Stops the game by stopping the current user's thread.
     */
    public void stopGame() {
        
    }
}
