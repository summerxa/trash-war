import java.util.*;
import java.net.*;

/**
 * Once a "host" user chooses to create a server, this thread runs in the
 * background and accepts users who request to join the current game.
 * 
 * @author  Anne Xia
 * @version 04/27/2022
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
     * Begins running the thread, which allows users to connect to
     * the game server until the thread is terminated.
     */
    public void run() {
        ServerSocket ss = new ServerSocket(port);
        // get the host player
        try {
            Socket s = ss.accept();
            // get the client player
            // stop the thread
        } catch (Exception e) {
            System.out.println("Error in ServerThread: " + e);
        }
    }

    /**
     * Stops the thread, or does nothing if the thread has already been
     * stopped. New users will no longer be able to connect
     * to the server.
     * 
     * @return a list containing all players.
     */
//     public List<Player> stopThread() {
//         return null;
//     }
}
