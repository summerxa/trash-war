import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * A client that connects to the game. Its main purpose is to sync
 * the local game state with the version on the server.
 * 
 * @author  Anne Xia
 * @version 05/12/2022
 * 
 * @author Sources - Meenakshi, Vaishnavi
 */
public class Client extends PlayerComputer {
    private Socket s;
    private GameThread gThread;
    
    private DataInputStream iStream;
    private DataOutputStream oStream;

    private boolean isPlaying;
    private Player self;
    private Score scores;
    
    /**
     * Constructs a client and connects to a server.
     * @param address the IP address of the server.
     * @param playerName the name of this player.
     */
    public Client(String address, String playerName) {
        self = new Player(playerName);
        // TODO initialize scores
        connectToServer(address);
    }

    /**
     * Connects to a given server.
     * @param address the IP address of the server
     */
    private void connectToServer(String address) {
        try {
            s = new Socket(address, PlayerComputer.PORT);
            System.out.print("#### connected, (socket == null) evaluates to " + (s == null));
            System.out.println(" and is socket closed? " + s.isClosed());

            try {
                oStream = new DataOutputStream(s.getOutputStream());
                oStream.writeUTF(self.getName()); // sends player name to server
                iStream = new DataInputStream(s.getInputStream());
            } catch (Exception e) {
                System.out.println("Error in Client:");
                e.printStackTrace();
                return;
            }

            isPlaying = false;
            gThread = new GameThread(this, false, s);
            gThread.start();
        } catch (Exception ex) {
            System.out.println("Error connecting to server:");
            ex.printStackTrace();
        }
    }

    /**
     * Starts the game by creating a GameThread for the current user.
     */
    public void startGame() {
        isPlaying = true;
        System.out.println("#### Started game");
        // try {
        //     // get list of players from server
        //     String[] allPlayers = iStream.readUTF().split(StateUpdate.U_DELIM);
        //     players = new ArrayList<Player>();
        //     for (String s : allPlayers) {
        //         players.add(new Player(s));
        //     }
        // } catch (Exception e) {
        //     System.out.println("Error in Client:");
        //     e.printStackTrace();
        //     return;
        // }
    }

    /**
     * Stops the game by stopping the current user's thread.
     */
    public void stopGame() {
        System.out.println("#### Stopped game");
        isPlaying = false;
        gThread.stopThread();
    }
    
    /**
     * Sends a slap card update to the server.
     */
    public void slapCard() {
        if (isPlaying) {
            System.out.println("#### card slap");
            gThread.slapCard(self);
        }
    }

    /**
     * Updates the score of a given player.
     * @param player a player.
     * @param newScore the new score.
     */
    public void updatePoints(Player player, int newScore) {
        if (isPlaying) {
            player.setPoints(newScore);
            System.out.println("#### update points " + player.getName() + " " + newScore);
            // TODO refresh scoreboard
        }
    }

    /**
     * Simulates a new card being dealt by this player.
     * Randomly generates a new card from the deck.
     */
    public void dealCard() {
        if (isPlaying) {
            gThread.dealCard(self, null);
            // note: actual card will be generated on server, null is a filler "default" value
        }
    }

    /**
     * Displays the random card generated. Method should be called
     * only by GameThread, use the no-args version of this method for
     * other card dealing.
     */
    public void dealCard(Player player, Card card) {
        if (isPlaying) {
            // TODO notify local class to update center deck
            // TODO notify GUI window to draw the card (if not done already)
        }
    }

    /**
     * Sets the local players list to the given list.
     * @param players a list of players.
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * For debugging purposes only
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Client aTest = new Client("localhost", "CoolClient");

        System.out.println("#### Created client, enter to see players");
        scan.nextLine();

        System.out.println("#### Players: ");
        for (Player p : aTest.getPlayers()) {
            System.out.println(p.getName() + " - " + p.getPoints());
        }

        System.out.println("#### Enter to see CoolClient's point value");
        scan.nextLine();
        System.out.println(aTest.getMatch("CoolClient").getPoints());

        System.out.println("#### Enter to see sErVeR's point value");
        scan.nextLine();
        System.out.println(aTest.getMatch("sErVeR").getPoints());

        System.out.println("#### Enter to slap a card");
        scan.nextLine();
        aTest.slapCard();

        // TODO debug: add client, start game, send a couple dummy updates

        scan.close();
    }
}
