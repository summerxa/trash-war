import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.net.*;

/**
 * Once a "host" user chooses to create a server, this thread runs in the
 * background and accepts the client who requests to join the current game.
 * 
 * @author  Anne Xia
 * @version 05/11/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class ServerThread extends Thread {
    private boolean isRunning, isError;
    private List<Player> players;
    
    private ServerSocket ss;
    private Socket s;
    private ObjectInputStream iStream;
    private ObjectOutputStream oStream;

    /**
     * Constructs a ServerThread.
     * @param host the host player.
     */
    public ServerThread(Player host) {
        players = new ArrayList<Player>();
        players.add(host);
        isError = false;
    }

    /**
     * Starts the thread.
     */
    public void start() {
        isRunning = true;
        s = null;
        try {
            ss = new ServerSocket(Server.PORT);
            ss.setSoTimeout(2000);
        } catch (Exception e) {
            ss = null;
            isError = true;
            System.out.println("Error in ServerThread:");
            e.printStackTrace();
        }
    }

    /**
     * Waits for a client to connect to the game server,
     * which then stops the thread.
     */
    public void run() {
        try {
            while (isRunning && s == null) {
                try {
                    s = ss.accept();
                    try {
                        oStream = new ObjectOutputStream(s.getOutputStream());
                        oStream.flush();
                        iStream = new ObjectInputStream(s.getInputStream());
                        players.add(new Player(iStream.readUTF()));
                    } catch (Exception e) {
                        isError = true;
                        System.out.println("Error in ServerThread:");
                        e.printStackTrace();
                    }
                    stopThread();
                } catch (SocketTimeoutException te) {
                    continue;
                } catch (Exception e) {
                    isError = true;
                    System.out.println("Error in ServerThread:");
                    e.printStackTrace();
                    stopThread();
                }
            }
        } catch (Exception e) {
            isError = true;
            System.out.println("Error in ServerThread:");
            e.printStackTrace();
            stopThread();
        }
    }

    /**
     * Stops the thread and sends list of all players to client's computer.
     * Users will no longer be able to connect to the server.
     */
    public void stopThread() {
        isRunning = false;
        try {
            if (!isError) {
                oStream.writeObject(players);
                oStream.flush();
            }
            oStream.close();
            iStream.close();
            if (ss != null) {
                ss.close();
            }
        } catch (Exception e) {
            isError = true;
            System.out.println("Error in ServerThread:");
            e.printStackTrace();
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
     * Returns true if this thread encountered an error, otherwise false.
     * @return true if this thread encountered an error, otherwise false.
    */
    public boolean crashed() {
        return isError;
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
