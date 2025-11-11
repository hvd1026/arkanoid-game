package objects.ui.button;

import util.AssetManager;
import util.Constant;
import util.MouseHandle;
import util.SoundManager;

import java.awt.*;

public class MusicButton extends Button {
    public MusicButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void update(double deltaTime) {
        if (MouseHandle.getInstance().isClickOn(this)) {
            if (SoundManager.getInstance().isMusicMuted()) {
                SoundManager.getInstance().unmuteMusic();
            } else {
                SoundManager.getInstance().muteMusic();
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        if (SoundManager.getInstance().isMusicMuted()) {
            AssetManager.getInstance().draw(g, Constant.MUSIC_MUTED_IMG, x, y, width, height);
        } else {
            AssetManager.getInstance().draw(g, Constant.MUSIC_IMG, x, y, width, height);
        }
    }
}
