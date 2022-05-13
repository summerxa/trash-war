import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Sends and receives updates to and from the central game server,
 * updates local game state accordingly.
 * 
 * @author  Anne Xia
 * @version 05/13/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class GameThread extends Thread {
    /**
     * Delay between update sends, in milliseconds.
     */
    public static final int DELAY = 500;

    private boolean isServer, isRunning, clientFirst;
    private PlayerComputer self;
    private Queue<StateUpdate> updates;

    private Socket s;
    private DataInputStream iStream;
    private DataOutputStream oStream;

    /**
     * Constructs a GameThread.
     * @param pc either a Server or a Client FIXTHIS.
     * @param isAServer true if this thread is for a host, otherwise false.
     * @param socket a client socket.
     */
    public GameThread(PlayerComputer pc, boolean isAServer, Socket socket) {
        isServer = isAServer;
        self = pc;
        s = socket;

        updates = new LinkedList<StateUpdate>();
        isRunning = true;
        clientFirst = true;
    }

    /**
     * Begins running the thread. This thread will synchronize the game
     * state between the server and client until the game ends and the
     * thread is terminated.
     */
    public void run() {
        try {
            oStream = new DataOutputStream(s.getOutputStream());
            iStream = new DataInputStream(s.getInputStream());
            if (!isServer) {
                // get list of players from server
                String[] allPlayers = iStream.readUTF().split(StateUpdate.U_DELIM);
                ArrayList<Player> players = new ArrayList<Player>();
                for (String s : allPlayers) {
                    players.add(new Player(StateUpdate.decode64(s)));
                }
                self.setPlayers(players);
            }
        } catch (Exception e) {
            System.out.println("Error in GameThread:");
            e.printStackTrace();
            stopThread();
        }
        while (isRunning) {
            if (isServer) { // server sends first
                sendUpdates();
                readUpdates();
                try {
                    Thread.sleep(DELAY);
                } catch (Exception e) {
                    System.out.println("Error in GameThread:");
                    e.printStackTrace();
                }
            } else {
                readUpdates();
                try {
                    Thread.sleep(DELAY);
                } catch (Exception e) {
                    System.out.println("Error in GameThread:");
                    e.printStackTrace();
                }
                sendUpdates();
            }
        }
        sendUpdates();
        
        try {
            iStream.close();
            oStream.close();
            s.close();
        } catch (Exception e) {
            System.out.println("Error in GameThread:");
            e.printStackTrace();
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
        addUpdate(new StateUpdate(player));
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
        addUpdate(new StateUpdate(player, newScore));
    }

    /**
     * For either server or client, deals a card and notifies the other player.
     * @param player the player who dealt a card.
     * @param card the card.
     */
    public void dealCard(Player player, Card card) {
        addUpdate(new StateUpdate(player, card));
    }
    
    /**
     * Starts the game.
     */
    public void startGame() {
        if (!isServer) {
            return;
        }
        addUpdate(new StateUpdate(StateUpdate.BGIN_GAME));
    }
    
    /**
     * Stops the game.
     */
    public void stopGame() {
        if (!isServer) {
            return;
        }
        addUpdate(new StateUpdate(StateUpdate.STOP_GAME));
        stopThread();
    }

    /**
     * Adds an update to the queue with synchronization.
     * @param su update.
     */
    private void addUpdate(StateUpdate su) {
        synchronized (updates) {
            updates.add(su);
        }
    }

    /**
     * Sends updates on the game state.
     */
    private void sendUpdates() {
        try {
            StringBuilder s = new StringBuilder();
            String pref = "";
            // obtain lock before accessing queue
            synchronized(updates) {
                while (!updates.isEmpty()) {
                    StateUpdate su = updates.remove();
                    s.append(pref);
                    pref = StateUpdate.U_DELIM;
                    s.append(su.toString());
                }
            }
            oStream.writeUTF(s.toString());
        } catch (SocketException e) {
            return; // game has ended
        } catch (Exception e) {
            System.out.println("Error in GameThread:");
            e.printStackTrace();
            stopThread();
        }
    }

    /**
     * Processes updates on the game state.
     */
    private void readUpdates() {
        try {
            boolean stopTheGame = false;
            String readIn = iStream.readUTF();
            if (readIn.isEmpty()) {
                return; // the other side doesn't have any updates
            }
            String[] allStrings = readIn.split(StateUpdate.U_DELIM);
            for (String s : allStrings) {
                if (stopTheGame) {
                    break; // a stop game state has been reached, stop immediately
                }
                String[] upd = s.split(StateUpdate.F_DELIM);
                int curType = Integer.parseInt(upd[0]);
                String name = null;
                if (curType != StateUpdate.BGIN_GAME && curType != StateUpdate.STOP_GAME) {
                    name = StateUpdate.decode64(upd[1]);
                }
                switch (curType) {
                    case StateUpdate.CARD_SLAP:
                        self.slapCard(self.getMatch(name));
                        break;
                    case StateUpdate.NEW_SCORE:
                        self.updatePoints(self.getMatch(name), Integer.parseInt(upd[2]));
                        break;
                    case StateUpdate.DEAL_CARD:
                        Card card = null;
                        if (!upd[2].equals(StateUpdate.NULLCARD)) {
                            card = new Card(upd[2]);
                        }
                        // self.dealCard(self.getMatch(name), card);
                        // TODO card constructor with string
                        break;
                    case StateUpdate.BGIN_GAME:
                        self.startGame();
                        break;
                    case StateUpdate.STOP_GAME:
                        stopTheGame = true;
                        self.stopGame();
                        break;
                    default:
                        System.out.println("GameThread: Incorrect type for a state update");
                }
            }
        } catch (EOFException e) {
            return; // no updates to read
        } catch (SocketException e) {
            return; // game has ended
        } catch (Exception e) {
            System.out.println("Error in GameThread:");
            e.printStackTrace();
            stopThread();
        }
    }

    /**
     * Stops the thread, or does nothing if the thread has already been
     * stopped. Sends out any remaining updates.
     */
    public void stopThread() {
        isRunning = false;
    }
}
