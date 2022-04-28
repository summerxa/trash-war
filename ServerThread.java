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
    /**
     * Begins running the thread, which allows users to connect to
     * the game server until the thread is terminated.
     */
    public void run() {

    }

    /**
     * Stops the thread, or does nothing if the thread has already been
     * stopped. New users will no longer be able to connect
     * to the server.
     * 
     * @return a server containing all players.
     */
    public Server stopThread() {
        return null;
    }
}
