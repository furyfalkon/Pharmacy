/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObject.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 *
 * @author andre
 */
public class UnendlichesAbspielen {
      private Clip clip;
    public void play(String fileName) {
        try { URL url = getClass().getResource("/Musiken/" + fileName);
        
        if (url == null) {
            System.out.println("Datei nicht gefunden:" + fileName);
            return; 
        }
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        }
    public void stop() {
        if (clip != null) {
            clip.stop();
            clip.close();
        }
    }
}
