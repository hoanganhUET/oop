package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL []soundURL = new URL[3];
    public Sound() {
        soundURL[0] = this.getClass().getResource("/sound/pacman_chomp.wav");
        soundURL[1] = this.getClass().getResource("/sound/hitmonster.wav");
        soundURL[2] = this.getClass().getResource("/sound/pacman_beginning.wav");
    }
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception e) {
        }
    }
    public void play() {
        clip.start();
    }
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }
    public void stop() {
        clip.stop();
    }
}
