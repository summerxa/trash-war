import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * Class to help play music
 */
public class MusicPlayer
{


    /**
     * Plays the music
     * @param musicFile the music file
     * @throws Exception
     */
    public void playMusic(String musicFile)throws Exception{
        try{
            File musicPath = new File(musicFile);
        if(musicPath.exists()){
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(musicPath);
            Clip c = AudioSystem.getClip();
            c.open(audioIn);
            c.start();
      
        }
        else System.out.println("FILE UNAVAILABLE");

    }
    catch(Exception e){
        e.printStackTrace();
    }
}

}
