import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class MusicPlayer
{

    // public static void main(String[] args)
    //     throws Exception
    // {
    //     MusicPlayer m = new MusicPlayer();
    //     String filePath = "bensound-moose.wav";
    //     m.playMusic(filePath);
    // }


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
