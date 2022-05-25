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
     * @param musicFile the musics file
     * @throws Exception
     */
    public void playMusic(String musicFile)throws Exception{
        try{
            File musicPath = new File(musicFile);
        if(musicPath.exists()){
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
      
        }
        else System.out.println("CANT FIND FILE");

    }
    catch(Exception e){
        e.printStackTrace();
    }
}

}
