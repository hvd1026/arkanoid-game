package objects.ui.button;

import util.AssetManager;
import util.Constant;
import util.MouseHandle;
import util.SoundManager;

import java.awt.*;

public class AudioButton extends Button {
    public AudioButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {
        if (MouseHandle.getInstance().isClickOn(this)) {
            if (SoundManager.getInstance().isAudioMuted()) {
                SoundManager.getInstance().unmuteAudio();
            } else {
                SoundManager.getInstance().muteAudio();
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (SoundManager.getInstance().isAudioMuted()) {
            AssetManager.getInstance().draw(g, Constant.AUDIO_MUTED_IMG, x, y, width, height);
        } else {
            AssetManager.getInstance().draw(g, Constant.AUDIO_IMG, x, y, width, height);
        }
    }
}
