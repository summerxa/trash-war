import java.io.*;
import java.net.Socket;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Sends and receives updates to and from the central game server,
 * updates local game state accordingly.
 * 
 * @author  Anne Xia
 * @version 05/10/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class GameThread extends Thread {
    private boolean isServer, isRunning;
    private PlayerComputer self;
    private Queue<StateUpdate> updates;

    private Socket s;
    private ObjectInputStream iStream;
    private ObjectOutputStream oStream;

    /**
     * Constructs a GameThread.
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
    }

    /**
     * Begins running the thread. This thread will synchronize the game
     * state between the server and client until the game ends and the
     * thread is terminated.
     */
    public void run() {
        try {
            oStream = new ObjectOutputStream(s.getOutputStream());
            oStream.flush();
            iStream = new ObjectInputStream(s.getInputStream());
        } catch (Exception e) {
            System.out.println("Error in GameThread: " + e);
            stopThread();
        }
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
        updates.add(new StateUpdate(player));
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
        updates.add(new StateUpdate(player, newScore));
    }

    /**
     * For either server or client, deals a card and notifies the other player.
     * @param player the player who dealt a card.
     * @param card the card.
     */
    public void dealCard(Player player, Card card) {
        updates.add(new StateUpdate(player, card));
    }
    
    /**
     * Starts the game.
     */
    public void startGame() {
        updates.add(new StateUpdate(StateUpdate.BGIN_GAME));
    }
    
    /**
     * Stops the game.
     */
    public void stopGame() {
        updates.add(new StateUpdate(StateUpdate.STOP_GAME));
    }

    /**
     * Sends updates on the game state.
     */
    private void sendUpdates() {
        try {
            // lock?
            oStream.writeObject(updates);
        } catch (Exception e) {
            System.out.println("Error in GameThread: " + e);
            stopThread();
        }
    }

    /**
     * Processes updates on the game state.
     */
    private void getUpdates() {
        try {
            // lock?
            boolean stopTheGame = false;
            Queue<StateUpdate> get = (Queue<StateUpdate>) iStream.readObject();
            while (!get.isEmpty() && !stopTheGame) {
                StateUpdate upd = get.remove();
                int curType = upd.getType();
                switch (curType) {
                    case StateUpdate.CARD_SLAP:
                        self.slapCard(getMatch(upd.getPlayer()));
                        break;
                    case StateUpdate.NEW_SCORE:
                        self.updatePoints(getMatch(upd.getPlayer()), upd.getScore());
                        break;
                    case StateUpdate.DEAL_CARD:
                        self.dealCard(getMatch(upd.getPlayer()), upd.getCard());
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
        } catch (Exception e) {
            System.out.println("Error in GameThread: " + e);
            stopThread();
        }
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
