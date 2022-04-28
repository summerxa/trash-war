/**
 * Sends and receives updates to and from the central game server,
 * updates local game state accordingly.
 * 
 * @author  Anne Xia
 * @version 04/27/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class GameThread extends Thread {
    /**
     * Begins running the thread. This thread will synchronize the game
     * state between the server and clients until the game ends and the
     * thread is terminated.
     */
    public void run() {

    }

    /**
     * Sends an update (change in points, new card dealt, etc.) on the
     * game state to the user handled by this specific thread.
     * @param type the integer code of the type of update.
     */
    public void sendUpdate(int type) {

    }

    /**
     * Stops the thread, or does nothing if the thread has already been
     * stopped. Sends out any remaining updates to all users.
     */
    public void stopThread() {

    }
}
