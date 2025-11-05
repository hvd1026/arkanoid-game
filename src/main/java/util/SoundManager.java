package util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

//SINGLETON WITH SOUND
public class SoundManager {
    private static volatile SoundManager instance;     //Chỉ có 1 instance trong đa luồng

    private final Map<Integer, MediaPlayer> soundPlayers = new ConcurrentHashMap<>();
    private MediaPlayer backgroundPlayer;
    private boolean soundMute = false;
    private boolean musicMute = false;

    private SoundManager() {
        new javafx.embed.swing.JFXPanel();
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            synchronized (SoundManager.class) {
                if (instance == null) {
                    instance = new SoundManager();
                }
            }
        }
        return instance;
    }


    public void loadAllSound() {
        //Thread riêng để Load Sound mà k ảnh hưởng đến game
        new Thread(() -> {
            loadSound(Constant.BACKGROUND_SOUND);
            loadSound(Constant.LOST_SOUND);
            loadSound(Constant.NORMAL_BRICK_SOUND);
            loadSound(Constant.STRONG_BRICK_SOUND);
            loadSound(Constant.PADDLE_SOUND);
            loadSound(Constant.POWERUP_SOUND);
            loadSound(Constant.WIN_SOUND);
        }).start();
    }

    private void loadSound(int idSound) {
        String path = null;
        switch (idSound) {
            case Constant.BACKGROUND_SOUND:
                path = "/audio/background_music.mp3";
                break;
            case Constant.LOST_SOUND:
                path = "/audio/lost.mp3";
                break;
            case Constant.NORMAL_BRICK_SOUND:
                path = "/audio/normal_brick.mp3";
                break;
            case Constant.STRONG_BRICK_SOUND:
                path = "/audio/strong_brick.mp3";
                break;
            case Constant.PADDLE_SOUND:
                path = "/audio/paddle.mp3";
                break;
            case Constant.POWERUP_SOUND:
                path = "/audio/powerup.mp3";
                break;
            case Constant.WIN_SOUND:
                path = "/audio/win.mp3";
                break;


        }
        try {
            URL soundURL = getClass().getResource(path);
            if (soundURL == null) {
                System.err.println("Sound not found :" + path);
                return;
            }
            Media media = new Media(soundURL.toExternalForm());
            MediaPlayer player = new MediaPlayer(media);
            soundPlayers.put(idSound, player);
            System.out.println("Completed " + soundPlayers.get(idSound));
        } catch (Exception e) {
            System.err.println("Error loading " + soundPlayers.get(idSound));
            e.printStackTrace();
        }
    }

    public void playSound(int idSound) {
        if (soundMute) return;
        MediaPlayer player = soundPlayers.get(idSound);
        if (player != null) {
            new Thread(() -> {
                synchronized (player) {
                    player.stop();
                    player.seek(Duration.ZERO);
                    player.setVolume(1.0);
                    player.play();
                }
            }).start();
        }
        else {
            System.err.println("Sound not found:" + idSound);

        }
    }

    public void muteSound() {
        soundMute = true;
        soundPlayers.values().forEach(p -> p.setMute(true));
        System.out.println("SOUND MUTED!!!");
    }

    public void unmountSound() {
        soundMute = false;
        soundPlayers.values().forEach(p -> p.setMute(false));
        System.out.println("SOUND UNMOUNTED!!!");
    }

    public void playBackgroundMusic(int idSound, boolean loop) {
        stopBackgroundMusic();
        MediaPlayer player = soundPlayers.get(idSound);
        if (player == null) {
            System.err.println("Background music playing " + idSound);
            return;
        }
        backgroundPlayer = player;
        backgroundPlayer.setMute(musicMute);
        backgroundPlayer.setVolume(0.5);
        if (loop) {
            backgroundPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        else {
            backgroundPlayer.setCycleCount(1);
        }
        backgroundPlayer.play();
        System.out.println("Background music playing ");
    }

    public void stopBackgroundMusic() {
        if (backgroundPlayer != null) {
            backgroundPlayer.stop();
        }
    }

    public void muteMusic() {
        musicMute = true;
        if (backgroundPlayer != null) {
            backgroundPlayer.setMute(true);
            System.out.println("Music muted!!!");
        }
    }

    public void unmuteMusic() {
        musicMute = false;
        if (backgroundPlayer != null) {
            backgroundPlayer.setMute(false);
            backgroundPlayer.play();
        }
        System.out.println("Music Unmuted!!!");
    }
}
