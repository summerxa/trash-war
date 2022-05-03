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
    private ServerSocket ss;
    private boolean isRunning;

    /**
     * Constructs a ServerThread.
     * 
     * @param list reference to an ArrayList of players,
     *             will contain all connected players once thread is stopped,
     *             or null if an error occurred.
     */
    public ServerThread() {
        isRunning = true;
        try {
            ss = new ServerSocket(Server.PORT);
            ss.setSoTimeout(2000);
            // get the host player
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
                    Socket s = ss.accept();
                    // get the client player
                    // stopThread(true);
                } catch (SocketTimeoutException te) {
                    continue;
                } catch (Exception e) {
                    System.out.println("Error in ServerThread: " + e);
                    stopThread(false);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in ServerThread: " + e);
            stopThread(false);
        }
    }

    /**
     * Stops the thread before it exits on its own. Users will
     * no longer be able to connect to the server.
     * 
     * @param getList boolean, if true, returns an ArrayList of players.
     * @return an ArrayList containing all players, or null if
     *         getList is false.
     */
    public ArrayList<Player> stopThread(boolean getList) {
        isRunning = false;
        if (ss != null) {
            try {
                ss.close();
            } catch (Exception e) {
                System.out.println("Error in ServerThread: " + e);
                return null;
            }
        }
        if (getList) {
            // get the players
            return null; // return a list
        }
        return null; // no need for a list, return nothing
    }

    /**
     * Returns true if this thread has stopped, otherwise false.
     * @return true if this thread has stopped, otherwise false.
     */
    public boolean isStopped() {
        return !isRunning;
    }
}
