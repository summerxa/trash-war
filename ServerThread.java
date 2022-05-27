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
 * @version 05/23/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class ServerThread extends Thread {
    private volatile boolean isRunning; // thread-safe
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

        isRunning = true;
        s = null;
        try {
            ss = new ServerSocket(Server.PORT);
            // ss.setSoTimeout(2000); // refreshes every 2 seconds to see if thread was stopped
        } catch (Exception e) {
            ss = null;
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
                    stopThread(); // once player has joined, we can stop waiting
                // } catch (SocketTimeoutException te) {
                //     continue; // 2 seconds have passed, check to see if isRunning is true
                } catch (Exception e) {
                    System.out.println("Error in ServerThread:");
                    e.printStackTrace();
                    stopThread();
                }
            }
        } catch (Exception e) {
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
                players.add(new Player(StateUpdate.decode64(iStream.readUTF()))); // get client's player name
                if (players != null) {
                    // convert list of all players to a single string
                    StringBuilder s = new StringBuilder();
                    String pref = "";
                    for (Player p : players) {
                        s.append(pref);
                        pref = StateUpdate.U_DELIM;
                        s.append(StateUpdate.encode64(p.getName()));
                    }
                    oStream.writeUTF(s.toString()); // send full list of players to client
                }
            } catch (Exception e) {
                System.out.println("Error in ServerThread:");
                e.printStackTrace();
            }
        } catch (Exception e) {
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
     * Returns the client socket.
     * @return the client socket.
     */
    public Socket getSocket() {
        return s;
    }
    
    /**
     * Returns the list of players.
     * @return the list of players.
     */
    public List<Player> getPlayerList() {
        return players;
    }
}
