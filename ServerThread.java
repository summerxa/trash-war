import java.util.*;
import java.net.*;

/**
 * Once a "host" user chooses to create a server, this thread runs in the
 * background and accepts the client who requests to join the current game.
 * 
 * @author  Anne Xia
 * @version 04/29/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class ServerThread extends Thread {
    private int port;
    
    /**
    * Creates a ServerThread.
    * 
    * @param port the port to bind to when running.
    */
    public ServerThread(int port) {
        this.port = port;
    }
    
    /**
     * Waits for a client to connect to the game server,
     * which then stops the thread.
     */
    public void run() {
        ServerSocket ss = new ServerSocket(port);
        // get the host player
        try {
            Socket s = ss.accept();
            // get the client player
        } catch (Exception e) {
            System.out.println("Error in ServerThread: " + e);
        }
    }

    /**
     * Stops the thread before it exits on its own. Users will
     * no longer be able to connect to the server.
     * 
     * @return a list containing all players.
     */
    public ArrayList<Player> stopThread() {
        // get the players
        return null; // return list
    }
}
