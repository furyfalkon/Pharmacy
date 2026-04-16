/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gameObject.music;

import helper.Pause;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author andre
 */
public class EinmaligesAbspielen {

    public static void play(String fileName) {

        Thread th = new Thread(() -> {
        String fullFilename = "resources/Geraeusche/" + fileName;
        Clip clip;
        File soundFile;
        AudioInputStream audioStream = null;
        try {
            soundFile = new File(fullFilename);                             //Datei in, welcher der Sound ist, wird geladen und gespeichert
            try {
                audioStream = AudioSystem.getAudioInputStream(soundFile);
                //Die SoundDatei wird in einen audio Stream konvertiert
                try {

                    clip = AudioSystem.getClip();                           //Es wird ein Audio Clip erstellt und abgespielt
                    clip.open(audioStream);
                    clip.start();
                    Pause.pauseMS(1000);
                    clip.stop();
                    clip.close();

                } catch (LineUnavailableException e) {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
                    th.start();
    }

}
