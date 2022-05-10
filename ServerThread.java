import java.util.*;
import java.net.*;

/**
 * Once a "host" user chooses to create a server, this thread runs in the
 * background and accepts the client who requests to join the current game.
 * 
 * @author  Anne Xia
 * @version 05/10/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class ServerThread extends Thread {
    private ServerSocket ss;
    private Socket s;
    private boolean isRunning;
    
    private List<Player> players;

    /**
     * Constructs a ServerThread.
     */
    public ServerThread() {
        isRunning = true;
        players = new ArrayList<Player>();
        try {
            ss = new ServerSocket(Server.PORT);
            ss.setSoTimeout(2000);
            // TODO get the host player
        } catch (Exception e) {
            ss = null;
            System.out.println("Error in ServerThread: " + e);
        }
    }

    /**
     * Waits for a client to connect to the game server,
     * which then stops the thread.
     */
    public void run() {
        try {
            while (isRunning) {
                try {
                    s = ss.accept();
                    // TODO get the client player
                } catch (SocketTimeoutException te) {
                    continue;
                } catch (Exception e) {
                    System.out.println("Error in ServerThread: " + e);
                    stopThread();
                }
            }
        } catch (Exception e) {
            System.out.println("Error in ServerThread: " + e);
            stopThread();
        }
    }

    /**
     * Stops the thread. Users will no longer be able to connect
     * to the server.
     */
    public void stopThread() {
        isRunning = false;
        try {
            ss.close();
        } catch (Exception e) {
            System.out.println("Error in ServerThread: " + e);
        }
    }

    /**
     * Returns true if this thread has stopped, otherwise false.
     * @return true if this thread has stopped, otherwise false.
     */
    public boolean isStopped() {
        return !isRunning;
    }

    /**
     * Gets the client socket.
     * @return the client socket.
     */
    public Socket getSocket() {
        return s;
    }
    
    /**
     * Gets the list of players.
     * @return the list of players.
     */
    public List<Player> getPlayerList() {
        return players;
    }
}
