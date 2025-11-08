package util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;
import java.util.concurrent.*;

public class SoundManager {

    private static SoundManager instance = null;
    private boolean musicMuted = false;
    private boolean audioMuted = false;

    // Clip
    private Clip backgroundMusic;
    private final ConcurrentHashMap<String, Clip> audio = new ConcurrentHashMap<>();
    // Thread pool for audio playback
    private static ExecutorService audioPool;
    // thread lock
    private final Object bgLock = new Object();

    private SoundManager() {
        audioPool = Executors.newFixedThreadPool(6); // Thread pool for audio playback
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    public void loadAll() {
        loadBackgroundMusic("src/main/resources/audio/background_music.wav");
        loadAudio("normal_brick", "src/main/resources/audio/normal_brick.wav");
        loadAudio("strong_brick", "src/main/resources/audio/strong_brick.wav");
        loadAudio("paddle", "src/main/resources/audio/paddle.wav");
        loadAudio("powerup", "src/main/resources/audio/powerup.wav");
        loadAudio("win", "src/main/resources/audio/win.wav");
        loadAudio("lost", "src/main/resources/audio/lost.wav");
    }

    public void shutdown() {
        System.out.println("Stopping audio thread pool...");
        audioPool.shutdown();
        synchronized (bgLock) {
            if (backgroundMusic != null && backgroundMusic.isRunning()) {
                backgroundMusic.close();
            }
        }
    }

    // Background music
    private void loadBackgroundMusic(String filePath) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath))) {
            backgroundMusic = AudioSystem.getClip();
            backgroundMusic.open(audioStream);
        } catch (Exception e) {
            System.err.println("Can't load background music: " + filePath);
            e.printStackTrace();
        }
    }

    public void playBackgroundMusic() {
        if (musicMuted || backgroundMusic == null) return;
        audioPool.submit(() -> {
            synchronized (bgLock) {
                if (!backgroundMusic.isRunning()) {
                    backgroundMusic.setFramePosition(0);
                    backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        });
    }

    public void stopBackgroundMusic() {
        audioPool.submit(() -> {
            synchronized (bgLock) {
                if (backgroundMusic != null && backgroundMusic.isRunning()) {
                    backgroundMusic.stop();
                }
            }
        });
    }

    public void setMusicVolume(float volume) {
        // volume: 0.0 (mute) to 1.0 (max)
        if (backgroundMusic == null) return;
        FloatControl gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (20.0 * Math.log10(volume <= 0.0 ? 0.0001 : volume)); // amplitude to dB
        gainControl.setValue(dB);
    }

    // Audio
    private void loadAudio(String id, String filePath) {
        try (AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(filePath))) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            audio.put(id, clip);
        } catch (Exception e) {
            System.err.println("Can't load audio: " + filePath);
            e.printStackTrace();
        }
    }

    public void playAudio(String id) {
        if (audioMuted) return;
        Clip clip = audio.get(id);
        if (clip == null) {
            System.err.println("Audio ID not found: " + id);
            return;
        }
        audioPool.submit(() -> {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        });
    }

    public void stopAllAudio() {
        for (Clip c : audio.values()) {
            synchronized (c) {
                if (c.isRunning()) c.stop();
            }
        }
    }

}
