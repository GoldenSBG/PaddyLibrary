package PaddyLibrary.audio;

import javax.sound.sampled.AudioInputStream;

import java.io.*;

import javax.sound.sampled.*;

public final class Audio {

    private String audioPath;
    private Clip clip;
    private AudioInputStream ais;

    public Audio(String audioPath) {
        this.audioPath = audioPath;
        init();
    }

    public Audio(String audioPath, boolean loop) {
        this.audioPath = audioPath;

        init();

        if (loop)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    private void init() {
        try {
            ais = AudioSystem.getAudioInputStream(new File(audioPath).getAbsoluteFile());
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e1) {
            e1.printStackTrace();
        }
    }

    public void play() {
        try {
            clip.open(ais);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
    }

    public void stop() {
        clip.stop();
        clip.close();
    }

    public void pause() {
        if (clip.isOpen())
            clip.stop();
    }

}