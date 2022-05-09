import javax.swing.*;

/**
 * GUI for thr game screen
 * 
 * @author Vaishnavi Kunapuli
 * @version 04/29/2022
 * @author Sources - Meenakshi, Anne
 */

public class Game {

    public Game() {
        design();
    }


   /**
    * Design for the Game Screen
    */
    public void design(){
        JFrame frame = new JFrame();
       
        frame.setSize(2000,1000);
        JPanel panel = new JPanel();
      
       
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
    
}
