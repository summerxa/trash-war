/**
 * Sends and receives updates to and from the central game server,
 * updates local game state accordingly.
 * 
 * @author  Anne Xia
 * @version 04/27/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class GameThread extends Thread {
    private boolean isServer;
    private Server server;
    private Client client;

    /**
     * Constructs a GameThread for a host player.
     * @param server the host's server.
     */
    public GameThread(Server server) {
        isServer = true;
        this.server = server;
        client = null;
    }

    /**
     * Constructs a GameThread for a client player.
     * @param client the client.
     */
    public GameThread(Client client) {
        isServer = false;
        this.client = client;
        server = null;
    }

    /**
     * Begins running the thread. This thread will synchronize the game
     * state between the server and clients until the game ends and the
     * thread is terminated.
     */
    public void run() {

    }

    /**
     * For clients only, sends a message to the server that this player
     * has slapped a card.
     * @param player the player who slapped a card.
     */
    public void slapCard(Player player) {
        if (isServer) {
            return;
        }
    }

    /**
     * For servers only, sends a message to the client that a player's
     * score has changed.
     * @param player the player whose score changed.
     * @param newScore the new score of the player.
     */
    public void changeScore(Player player, int newScore) {
        if (!isServer) {
            return;
        }
    }

    /**
     * Sends an update on the game state.
     * @param update the update to send.
     */
    private void sendUpdate(StateUpdate update) {

    }

    /**
     * Processes an update on the game state.
     * @param update the update to handle.
     */
    private void getUpdate(StateUpdate update) {

    }

    /**
     * Stops the thread, or does nothing if the thread has already been
     * stopped. Sends out any remaining updates to all users.
     */
    public void stopThread() {

    }
}
