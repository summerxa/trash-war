import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Once a "host" user chooses to create a server, this thread runs in the
 * background and accepts the client who requests to join the current game.
 * 
 * @author  Anne Xia
 * @version 05/12/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class ServerThread extends Thread {
    private boolean isRunning, isError;
    private List<Player> players;
    
    private ServerSocket ss;
    private Socket s;
    private DataInputStream iStream;
    private DataOutputStream oStream;

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
            while (isRunning) {
                try {
                    s = ss.accept();
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
            try {
                oStream = new DataOutputStream(s.getOutputStream());
                oStream.flush();
                iStream = new DataInputStream(s.getInputStream());
                players.add(new Player(iStream.readUTF())); // get client's player name
                System.out.println("#### player name: " + players.get(players.size()-1).getName());
                if (!isError) {
                    // send list of all players
                    StringBuilder s = new StringBuilder();
                    String pref = "";
                    for (Player p : players) {
                        s.append(pref);
                        pref = StateUpdate.U_DELIM;
                        s.append(StateUpdate.encode64(p.getName()));
                    }
                    // Thread.sleep(1000);
                    System.out.println("#### player list: " + s.toString());
                    oStream.writeUTF(s.toString());
                }
            } catch (Exception e) {
                isError = true;
                System.out.println("Error in ServerThread:");
                e.printStackTrace();
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
