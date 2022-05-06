import java.net.Socket;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Sends and receives updates to and from the central game server,
 * updates local game state accordingly.
 * 
 * @author  Anne Xia
 * @version 05/06/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class GameThread extends Thread {
    private boolean isServer, isRunning;
    private PlayerComputer self;
    private Queue<StateUpdate> updates;

    private Socket s;
    // TODO input, output streams

    /**
     * Constructs a GameThread for a
     * @param pc either a Server or a Client object.
     * @param isAServer true if this thread is for a host, otherwise false.
     * @param socket a client socket.
     */
    public GameThread(PlayerComputer pc, boolean isAServer, Socket socket) {
        isServer = isAServer;
        self = pc;
        s = socket;

        updates = new LinkedList<StateUpdate>();
        isRunning = true;
        System.out.println("done");
    }

    /**
     * Begins running the thread. This thread will synchronize the game
     * state between the server and client until the game ends and the
     * thread is terminated.
     */
    public void run() {
        // TODO get input / output from s
        while (isRunning) {
            // TODO do stuff
        }
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
     * stopped. Sends out any remaining updates.
     */
    public void stopThread() {
        isRunning = false;
        // TODO send updates
    }
}
