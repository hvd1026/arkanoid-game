package util;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

//SINGLETON WITH SOUND
public class SoundManager {
    private static volatile SoundManager instance;     //Chỉ có 1 instance trong đa luồng
    private AdvancedPlayer bgPlayer; //Dùng để dừng nhạc background
    private final Map<Integer, byte[]> soundData = new ConcurrentHashMap<>();
    private Thread backgroundThread;
    private volatile boolean stopBackground = false;
    private boolean soundMute = false;
    private boolean musicMute = false;

    private SoundManager(){
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            //Dù nhiều luồng gọi thì vẫn chỉ có 1 đối tượng
            synchronized (SoundManager.class) {
                if (instance == null) {
                    instance = new SoundManager();
                }
            }
        }
        return instance;
    }

    //Load Sound Theo ID Trong Constant
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

    private String getSoundPath(int idSound) {
        switch (idSound) {
            case Constant.BACKGROUND_SOUND:
                return "/audio/background_music.mp3";
            case Constant.LOST_SOUND:
                return "/audio/lost.mp3";
            case Constant.NORMAL_BRICK_SOUND:
                return "/audio/normal_brick.mp3";
            case Constant.STRONG_BRICK_SOUND:
                return "/audio/strong_brick.mp3";
            case Constant.PADDLE_SOUND:
                return "/audio/paddle.mp3";
            case Constant.POWERUP_SOUND:
                return "/audio/powerup.mp3";
            case Constant.WIN_SOUND:
                return "/audio/win.mp3";
            default:
                return null;
        }
    }

    private void loadSound(int idSound) {
        String path = getSoundPath(idSound);
        if (path == null) return;
        try {
            InputStream is = getClass().getResourceAsStream(path);
            if (is == null) {
                System.err.println(path + " not found");
                return;
            }
            //Doc cac file vao byte[]
            byte[] buffer = is.readAllBytes();
            soundData.put(idSound, buffer);
            is.close();
            System.out.println("Loaded sound" + path);
        } catch (Exception e) {
            System.err.println("Error loading " + path);
            e.printStackTrace();
        }
    }

    public void playSound(int idSound) {
        if (soundMute) return;

        byte[] data = soundData.get(idSound);
        if (data == null) {
            System.err.println("Sound " + idSound + " not found");
            return;
        }

        new  Thread(() -> {
            try {
                InputStream is = new BufferedInputStream(new ByteArrayInputStream(data));
                Player player = new Player(is);
                player.play();
            } catch (Exception e) {
                System.err.println("Error playing sound " + idSound);
                e.printStackTrace();
            }
        }).start();
    }

    //Dùng AdvancePlayer để quản lý dừng nhạc
    public void playBackgroundMusic(int idSound, boolean loop) {
        stopBackgroundMusic();

        byte[] data = soundData.get(idSound);
        if (data == null) {
            System.err.println("Sound " + idSound + " not found");
            return;
        }

        stopBackground = false;
        backgroundThread = new Thread(() -> {
            try {
                do {
                    if (stopBackground || musicMute || Thread.interrupted()) break;
                    try (InputStream is = new BufferedInputStream(new ByteArrayInputStream(data))) {
                        bgPlayer = new AdvancedPlayer(is);
                        bgPlayer.play();
                    }
                } while (loop && !stopBackground && !musicMute && !Thread.interrupted()); // Nếu loop=true thì chạy lại
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BackgroundMusicThread");
        backgroundThread.start();
    }

    public void stopBackgroundMusic() {
        stopBackground = true;
        try {
            if (bgPlayer != null) {
                bgPlayer.close();
                bgPlayer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Ngắt luồng phát nhạc
        if (backgroundThread != null && backgroundThread.isAlive()) {
            backgroundThread.interrupt();
            backgroundThread = null; //Giải phóng
        }
    }

    public void muteSound() {
        soundMute = true;
        System.out.println("SOUND MUTED!!!");
    }

    public void unmountSound() {
        soundMute = false;
        System.out.println("SOUND UNMOUNTED!!!");
    }

    public boolean isSoundMuted() {
        return soundMute;
    }

    public boolean isMusicMuted() {
        return musicMute;
    }
}
